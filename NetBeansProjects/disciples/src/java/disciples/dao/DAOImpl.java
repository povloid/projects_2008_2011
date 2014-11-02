/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package disciples.dao;

import disciples.model.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import disciples.model.Dep;
import disciples.model.Disciple;
import disciples.model.Dsptc;
import disciples.model.Measure;
import disciples.model.PhCh;
import disciples.model.Role;
import disciples.model.SClass;
import disciples.model.Sdep;
import disciples.model.Sp;
import disciples.web.session.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kpg.db.sql.ADBBase;

/**
 *
 * @author dev_sport
 */
public final class DAOImpl extends ADBBase {

    /**
     * Получить соединение
     * @return
     * @throws NamingException
     * @throws SQLException
     */
    protected static Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();

        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/disciples");


        return ds.getConnection();
    }

    /**
     * Получить пользователя
     * @return
     * @throws Exception
     */
    public static Result getUser(String userName) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM disciples.web_users WHERE user_name='" + userName + "'");

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить список пользователей
     * @return
     * @throws Exception
     */
    public static Result getUsers() throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM disciples.web_users u, disciples.deps d WHERE u.dep_id=d.id ORDER BY u.id;");

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить список департаментов
     * @return
     */
    public static List<Dep> getDeps() {

        List<Dep> deps = new ArrayList<Dep>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM disciples.deps;");

            while (rs.next()) {

                Dep dep = new Dep();
                dep.setId(rs.getInt("id"));
                dep.setKeyName(rs.getString("key_name"));
                dep.setDescription(rs.getString("description"));

                deps.add(dep);

            }

            closeAll(conn, null, null, stmt, rs);

        } catch (Exception e) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, null, stmt, rs);
        }

        return deps;
    }

    /**
     * Загрузить
     * @param id
     * @param user
     */
    public static void loadUser(int id, User user) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM disciples.web_users WHERE id=" + id + "");
            rs.next();

            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("USER_NAME"));
            user.setDescription(rs.getString("DESCRIPTION"));
            user.setDepId(rs.getInt("DEP_ID"));
            user.setAccessLevel(rs.getInt("ACCESS_LEVEL"));
            user.setBlocked(rs.getBoolean("blocked"));

            closeAll(conn, null, null, stmt, rs);

            initUserRoles(user);

        } catch (Exception e) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, null, stmt, rs);
        }

    }

    /**
     *
     * @param user
     */
    public static void initUserRoles(User user) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("SELECT 1 as in_user, r.id, r.role_name "
                    + " FROM disciples.web_users_roles ur, disciples.web_users u, disciples.web_roles r "
                    + " WHERE ur.user_id = ? AND ur.user_id = u.id AND ur.role_id = r.id "
                    + " UNION "
                    + " SELECT 0 as in_user, r.id, r.role_name "
                    + " FROM disciples.web_roles r WHERE r.id "
                    + " NOT IN (SELECT r.id "
                    + " FROM disciples.web_users_roles ur, disciples.web_users u, disciples.web_roles r "
                    + " WHERE ur.user_id = ? AND ur.user_id = u.id AND ur.role_id = r.id)");

            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, user.getId());

            rs = pstmt.executeQuery();

            while (rs.next()) {

                int inUser = rs.getInt("in_user");

                if (inUser > 0) {

                    user.getUserRoles().getHaveRoles().add(new Role(
                            rs.getInt("id"),
                            rs.getString("role_name"),
                            ""));

                } else {

                    user.getUserRoles().getOtherRoles().add(new Role(
                            rs.getInt("id"),
                            rs.getString("role_name"),
                            ""));
                }
            }

            closeAll(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, pstmt, null, rs);
        }
    }

    /**
     * Получить список спортивных отделений
     * @return
     * @throws Exception
     */
    public static Result getSdeps() throws Exception {
        return executeSQLQuery(getConnection(), "SELECT * FROM disciples.sdeps ORDER BY id;");
    }

    /**
     * Добавить спортивное отделение
     * @param dep
     * @throws Exception
     */
    public static void addSdep(Sdep sdep) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO disciples.sdeps(key_name,description) VALUES ( ?, ?);",
                sdep,
                "keyName=>1:VARCHAR,description=>2:VARCHAR");

    }

    /**
     * Загрузить спортивное отделение
     * @param id
     * @return
     * @throws Exception
     */
    public static Sdep loadSdep(int id) throws Exception {
        Sdep sdep = new Sdep(id);
        executeSQLInGetObject(getConnection(),
                "SELECT * FROM disciples.sdeps WHERE id=?",
                sdep,
                "id=>1:INTEGER",
                sdep,
                "keyName<=key_name,description<=description");
        return sdep;
    }

    /**
     * Редактировать cпортивное отделение
     * @param dep
     * @throws Exception
     */
    public static void editSdep(Sdep sdep) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE disciples.sdeps SET key_name=?, description=? WHERE id=?",
                sdep,
                "keyName=>1:VARCHAR,description=>2:VARCHAR,id=>3:INTEGER");

    }

    /**
     * Удалить спортивное отделение
     * @param dep
     * @throws Exception
     */
    public static void delSdep(Sdep sdep) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.sdeps WHERE id=?", sdep,
                "id=>1:INTEGER");
    }

    /**
     * Получить список учеников
     * @return
     * @throws Exception
     */
    public static Result getDisciples() throws Exception {
        return executeSQLQuery(getConnection(), "SELECT d.*, s.key_name AS sclass, sd.key_name as sdep "
                + "FROM disciples.disciples d, disciples.sclasses s, disciples.sdeps sd "
                + "WHERE d.sclass_id=s.id AND d.sdep_id = sd.id "
                + "ORDER BY d.id;");
    }

    //--------------------------------------------------------------------------
    //  BEGIN SClass;
    /**
     * Получить список спортивных классов
     * @return
     * @throws Exception
     */
    public static Result getSclasses() throws Exception {
        return executeSQLQuery(getConnection(), "SELECT * FROM disciples.sclasses ORDER BY id;");
    }

    /**
     * Добавить спортивный класс
     * @param dep
     * @throws Exception
     */
    public static void addSclass(SClass sclass) throws Exception {
//        executeSQL(getConnection(),
//                "INSERT INTO disciples.sclasses (key_name , description) VALUES ( ?, ?);",
//                new Par[]{
//                    new Par(Types.VARCHAR, sclass.getKeyName()),
//                    new Par(Types.VARCHAR, sclass.getKeyName())});
//
        executeSQLInObject(getConnection(),
                "INSERT INTO disciples.sclasses (key_name , description) VALUES ( ?, ?);",
                sclass,
                "keyName=>1:VARCHAR,description=>2:VARCHAR");

    }

    /**
     * Загрузить спортивный класс
     * @param id
     * @return
     * @throws Exception
     */
    public static SClass loadSclass(int id) throws Exception {
        SClass sclass = new SClass(id);
//        DAOImpl.OutPars outPars = executeSQLGetPars(getConnection(),
//                "SELECT * FROM disciples.sclasses WHERE id=?",
//                new Par[]{
//                    new Par(Types.INTEGER, id)});
//        sclass = new SClass(
//                outPars.getInt("id"),
//                outPars.getString("key_name"),
//                outPars.getString("description"));

        executeSQLInGetObject(getConnection(),
                "SELECT * FROM disciples.sclasses WHERE id=?",
                sclass,
                "id=>1:INTEGER",
                sclass,
                "keyName<=key_name,description<=description");


//         DAOImpl.executeSQLGetObject(getConnection(),
//                true,
//                "SELECT * FROM disciples.sclasses WHERE id=1",
//                sclass,
//                "keyName<=key_name,description<=description");



        return sclass;
    }

    /**
     * Редактировать спортивный класс
     * @param dep
     * @throws Exception
     */
    public static void editSclass(SClass sclass) throws Exception {
//          executeSQL(getConnection(),
//                "UPDATE disciples.sclasses SET key_name=?, description=? "
//                    + " WHERE id=?",
//                new Par[]{
//                    new Par(Types.VARCHAR, sclass.getKeyName()),
//                    new Par(Types.VARCHAR, sclass.getKeyName()),
//                    new Par(Types.INTEGER, sclass.getId())});


        executeSQLInObject(getConnection(),
                "UPDATE disciples.sclasses SET key_name=?, description=? WHERE id=?",
                sclass,
                "keyName=>1:VARCHAR,description=>2:VARCHAR,id=>3:INTEGER");


    }

    /**
     * Удалить спортивное отделение
     * @param dep
     * @throws Exception
     */
    public static void delSclass(SClass sclass) throws Exception {

//        executeSQL(getConnection(),
//                "DELETE FROM disciples.sclasses WHERE id=?",
//                new Par[]{
//                    new Par(Types.INTEGER, sclass.getId())});

        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.sclasses WHERE id=?",
                sclass,
                "id=>1:INTEGER");

    }
    // END SClass;

    //  BEGIN measures ---------------------------------------------------------;
    /**
     * Получить список мер
     * @return
     * @throws Exception
     */
    public static Result getMeasures() throws Exception {
        return executeSQLQuery(getConnection(), "SELECT * FROM disciples.measures ORDER BY id;");
    }

    /**
     * Добавить меру
     * @param measure
     * @throws Exception
     */
    public static void addMeasure(Measure measure) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO disciples.measures (key_name , description) VALUES ( ?, ?);",
                measure,
                "keyName=>1:VARCHAR,description=>2:VARCHAR");

    }

    /**
     * Загрузить меру
     * @param id
     * @return
     * @throws Exception
     */
    public static Measure loadMeasure(int id) throws Exception {
        Measure measure = new Measure(id);
        executeSQLInGetObject(getConnection(),
                "SELECT * FROM disciples.measures WHERE id=?",
                measure,
                "id=>1:INTEGER",
                measure,
                "keyName<=key_name,description<=description");
        return measure;
    }

    /**
     * Редактировать меру
     * @param dep
     * @throws Exception
     */
    public static void editMeasure(Measure measure) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE disciples.measures SET key_name=?, description=? WHERE id=?",
                measure,
                "keyName=>1:VARCHAR,description=>2:VARCHAR,id=>3:INTEGER");


    }

    /** Удалить меру
     * @param dep
     * @throws Exception
     */
    public static void delMeasure(Measure measure) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.measures WHERE id=?",
                measure,
                "id=>1:INTEGER");

    }
    // END measures;

    //--------------------------------------------------------------------------
    /**
     * Получить список спортивных отделений
     * @return
     * @throws Exception
     */
    public static List getRBSdeps() throws Exception {
        return getEntrysIDValue(getConnection(),
                "SELECT * FROM disciples.sdeps;", "id", "key_name");
    }

    /**
     * Получить список спортивных классов
     * @return
     * @throws Exception
     */
    public static List getRBSclasses() throws Exception {
        return getEntrysIDValue(getConnection(),
                "SELECT * FROM disciples.sclasses;", "id", "key_name");
    }

    /**
     * Добавть ученика
     * @param disciple
     * @throws Exception
     */
    public static void addDisciple(Disciple disciple) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO disciples( "
                + "sdep_id, fname, iname, oname, birthday, bdate, edate, sclass_id, parents, hobbi, performance, description, foto, fotoctype)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                disciple,
                "sdepId=>1:INTEGER,"
                + "fname=>2:VARCHAR,"
                + "iname=>3:VARCHAR,"
                + "oname=>4:VARCHAR,"
                + "birthday=>5:DATE,"
                + "bdate=>6:DATE,"
                + "edate=>7:DATE,"
                + "sclassId=>8:INTEGER,"
                + "parents=>9:VARCHAR,"
                + "hobbi=>10:VARCHAR,"
                + "performance=>11:VARCHAR,"
                + "description=>12:VARCHAR,"
                + "foto=>13:VARCHAR,"
                + "fotoContentType=>14:VARCHAR");
    }

    /**
     * Загрузить ученика
     * @param id
     * @return
     * @throws Exception
     */
    public static Disciple loadDisciple(int id) throws Exception {

        Disciple disciple = new Disciple(id);

        executeSQLInGetObject(getConnection(),
                "SELECT *, float4(avscore) as avscore2 FROM disciples.disciples WHERE id=?",
                disciple,
                "id=>1:INTEGER",
                disciple,
                "sdepId<=sdep_id,"
                + "fname<=fname,"
                + "iname<=iname,"
                + "oname<=oname,"
                + "birthday<=birthday,"
                + "bdate<=bdate,"
                + "edate<=edate,"
                + "sclassId<=sclass_id,"
                + "parents<=parents,"
                + "hobbi<=hobbi,"
                + "performance<=performance,"
                + "description<=description,"
                + "avscore<=avscore2,"
                + "foto<=foto,"
                + "fotoContentType<=fotoctype");
        return disciple;

    }

    /**
     * Редактировать ученика
     * @param disciple
     * @throws Exception
     */
    public static void editDisciple(Disciple disciple) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE disciples "
                + " SET sdep_id=?, fname=?, iname=?, oname=?, birthday=?, bdate=?,"
                + " edate=?, sclass_id=? , parents=?, hobbi=?, performance=?, description=?, foto=?, fotoctype=? "
                + " WHERE id=?;",
                disciple,
                "sdepId=>1:INTEGER,"
                + "fname=>2:VARCHAR,"
                + "iname=>3:VARCHAR,"
                + "oname=>4:VARCHAR,"
                + "birthday=>5:DATE,"
                + "bdate=>6:DATE,"
                + "edate=>7:DATE,"
                + "sclassId=>8:INTEGER,"
                + "parents=>9:VARCHAR,"
                + "hobbi=>10:VARCHAR,"
                + "performance=>11:VARCHAR,"
                + "description=>12:VARCHAR,"
                + "foto=>13:VARCHAR,"
                + "fotoContentType=>14:VARCHAR,"
                + "id=>15:INTEGER");
    }

    /**
     * Удалить ученика
     * @param dep
     * @throws Exception
     */
    public static void delDisciple(Disciple disciple) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.disciples WHERE id=?",
                disciple,
                "id=>1:INTEGER");

    }

    // Физические характеристики
    /**
     * Получить список физических характеристик
     * @return
     * @throws Exception
     */
    public static Result getPhChs() throws Exception {
        return executeSQLQuery(getConnection(),
                "SELECT "
                + " d.id as disciple_id, "
                + " int4(EXTRACT(YEAR FROM age(pc.cdate , d.birthday))) as disciple_age, "
                + " d.sdep_id,d.sclass_id,d.fname,d.iname,d.oname,d.birthday, "
                + " pc.* "
                + " FROM  "
                + " disciples.disciples d "
                + "LEFT OUTER JOIN "
                + " (SELECT * FROM disciples.ph_ch "
                + " WHERE id IN (SELECT last_id from disciples.v_last_ph_ch)) "
                + " pc ON (d.id = pc.disciple_id) "
                + " ORDER BY d.fname");
    }

    /**
     * Получить список физических характеристик (Историю)
     * @return
     * @throws Exception
     */
    public static Result getPhChsHist(int discipleId) throws Exception {
        return executeSQLQuery(getConnection(),
                "SELECT id, disciple_id, cdate, height, weight, description FROM ph_ch "
                + " WHERE disciple_id=? ORDER BY id;",
                new Par[]{new Par(Types.INTEGER, discipleId)});
    }

    // Физические характеристики -----------------------------------------------
    /**
     * Добавить физическую характеристику
     * @param phCh
     * @throws Exception
     */
    public static void addPhCh(PhCh phCh) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO ph_ch( "
                + "disciple_id, height, weight, description)"
                + "VALUES (?, ?, ?, ?);",
                phCh,
                "discipleId=>1:INTEGER,"
                + "height=>2:INTEGER,"
                + "weight=>3:INTEGER,"
                + "description=>4:VARCHAR");
    }

    /**
     * Загрузить физическую характеристику
     * @param id
     * @return
     * @throws Exception
     */
    public static PhCh loadPhCh(int id) throws Exception {
        PhCh phCh = new PhCh(id);
        executeSQLInGetObject(getConnection(),
                "SELECT * FROM disciples.ph_ch WHERE id=?",
                phCh,
                "id=>1:INTEGER",
                phCh,
                "discipleId<=disciple_id,"
                + "cdate<=cdate,"
                + "height<=height,"
                + "weight<=weight,"
                + "description<=description");
        return phCh;
    }

    /**
     * Редактировать физическую характеристику
     * @param disciple
     * @throws Exception
     */
    public static void editPhCh(PhCh phCh) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE ph_ch "
                + " SET height=?, weight=?, description=? "
                + " WHERE id=?;",
                phCh,
                "height=>1:INTEGER,"
                + "weight=>2:INTEGER,"
                + "description=>3:VARCHAR,"
                + "id=>4:INTEGER");
    }

    /**
     * Удалить физическую характеристику
     * @param dep
     * @throws Exception
     */
    public static void delPhCh(PhCh phCh) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.ph_ch WHERE id=?",
                phCh,
                "id=>1:INTEGER");

    }

    // Спортивные подготовки ---------------------------------------------------
    /**
     * Получить список спортивных подготовок
     * @return
     * @throws Exception
     */
//    public static Result getSpTrangs() throws Exception {
//        return executeSQLQuery(getConnection(), "SELECT spt.*, sd.key_name as sdep_key_name FROM disciples.sp_trangs spt, disciples.sdeps sd "
//                + " WHERE spt.sdep_id = sd.id ORDER BY spt.id; ");
//    }
//
//    /**
//     * Добавть Спортивную подготовку
//     * @param disciple
//     * @throws Exception
//     */
//    public static void addSpTrang(SpTrang dsptc) throws Exception {
//        executeSQLInObject(getConnection(),
//                "INSERT INTO disciples.sp_trangs( "
//                + " sdep_id, key_name, description, specialized) "
//                + " VALUES (?, ?, ?, ?);",
//                dsptc,
//                "sdepId=>1:INTEGER,"
//                + "keyName=>2:VARCHAR,"
//                + "description=>3:VARCHAR,"
//                + "specialized=>4:BOOLEAN");
//    }
//
//    /**
//     * Загрузить Спортивную подготовку
//     * @param id
//     * @return
//     * @throws Exception
//     */
//    public static SpTrang loadSpTrang(int id) throws Exception {
//        SpTrang dsptc = new SpTrang(id);
//        executeSQLInGetObject(getConnection(),
//                "SELECT * FROM disciples.sp_trangs WHERE id=?",
//                dsptc,
//                "id=>1:INTEGER",
//                dsptc,
//                "sdepId<=sdep_id,"
//                + "keyName<=key_name,"
//                + "specialized<=specialized,"
//                + "description<=description");
//        return dsptc;
//    }
//
//    /**
//     * Редактировать спортивную подготовку
//     * @param disciple
//     * @throws Exception
//     */
//    public static void editSpTrang(SpTrang dsptc) throws Exception {
//        executeSQLInObject(getConnection(),
//                "UPDATE disciples.sp_trangs "
//                + " SET sdep_id=?, key_name=?, description=?, specialized=? "
//                + " WHERE id=?;",
//                dsptc,
//                "sdepId=>1:INTEGER,"
//                + "keyName=>2:VARCHAR,"
//                + "description=>3:VARCHAR,"
//                + "specialized=>4:BOOLEAN,"
//                + "id=>5:INTEGER");
//    }
//
//    /**
//     * Удалить спортивную подготовку
//     * @param dep
//     * @throws Exception
//     */
//    public static void delSpTrang(SpTrang dsptc) throws Exception {
//        executeSQLInObject(getConnection(),
//                "DELETE FROM disciples.sp_trangs WHERE id=?",
//                dsptc,
//                "id=>1:INTEGER");
//
//    }
    // Категория ---------------------------------------------------------------
//    /**
//     * Получить список спортивных подготовок
//     * @return
//     * @throws Exception
//     */
//    public static List getRBSpTrangs() throws Exception {
//        return getEntrysIDValue(getConnection(),
//                "SELECT * FROM disciples.sp_trangs;", "id", "key_name");
//    }
    /**
     * Получить список мер измерения
     * @return
     * @throws Exception
     */
    public static List getRBMeasures() throws Exception {
        return getEntrysIDValue(getConnection(),
                "SELECT * FROM disciples.measures;", "id", "key_name");
    }

    /**
     * Получить список спортивных категорий
     * @return
     * @throws Exception
     */
    public static Result getСategories() throws Exception {
        return executeSQLQuery(getConnection(),
                "SELECT * FROM ( "
                + " SELECT 1 as ORD, c.*, 'общая подготовка' sdep_key_name, m.key_name as measure_key_name "
                + " FROM disciples.categories c, measures m "
                + " WHERE c.sdep_id is null AND c.measure_id=m.id "
                + " UNION "
                + " SELECT 2 as ORD, c.*, sd.key_name sdep_key_name, m.key_name as measure_key_name "
                + " FROM disciples.categories c, disciples.sdeps sd, measures m "
                + " WHERE c.sdep_id=sd.id AND c.measure_id=m.id "
                + " ) a ORDER BY ORD, sdep_id, key_name");
    }

    /**
     * Добавть Спортивную категорию
     * @param disciple
     * @throws Exception
     */
    public static void addСategory(Category category) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO disciples.categories( "
                + " sdep_id, key_name, description, measure_id, specialized) "
                + " VALUES (?, ?, ?, ?, ?);",
                category,
                "sdepId=>1:INTEGER,"
                + "keyName=>2:VARCHAR,"
                + "description=>3:VARCHAR,"
                + "measureId=>4:INTEGER,"
                + "specialized=>5:BOOLEAN");
    }

    /**
     * Загрузить Спортивную категорию
     * @param id
     * @return
     * @throws Exception
     */
    public static Category loadСategory(int id) throws Exception {
        Category spTrang = new Category(id);
        executeSQLInGetObject(getConnection(),
                "SELECT * FROM disciples.categories WHERE id=?",
                spTrang,
                "id=>1:INTEGER",
                spTrang,
                "sdepId<=sdep_id,"
                + "keyName<=key_name,"
                + "description<=description,"
                + "specialized<=specialized,"
                + "measureId<=measure_id");
        return spTrang;
    }

    /**
     * Редактировать спортивную категорию
     * @param disciple
     * @throws Exception
     */
    public static void editСategory(Category category) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE disciples.categories "
                + " SET sdep_id=?, key_name=?, description=?, measure_id=?, specialized=? "
                + " WHERE id=?;",
                category,
                "sdepId=>1:INTEGER,"
                + "keyName=>2:VARCHAR,"
                + "description=>3:VARCHAR,"
                + "measureId=>4:INTEGER,"
                + "specialized=>5:BOOLEAN,"
                + "id=>6:INTEGER");
    }

    /**
     * Удалить спортивную категорию
     * @param dep
     * @throws Exception
     */
    public static void delСategory(Category category) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.categories WHERE id=?",
                category,
                "id=>1:INTEGER");

    }
    // Спортивные категории ----------------------------------------------------
    // .........................................................................

    /**
     * Получить физподготовку
     * @return
     * @throws Exception
     */
    public static Result getDsptcs() throws Exception {
        return executeSQLQuery(getConnection(),
                "select a.*, sd.key_name as sdep_key_name, sd.description as sdep_description "
                + " FROM ( "
                + " SELECT 1 as sp, d.*, "
                + " int4(EXTRACT(YEAR FROM age(now() , d.birthday))) as disciple_age, "
                + " p.height, p.weight, "
                + " get_dsptc(d.id, d.sdep_id, FALSE) "
                + " FROM disciples.disciples d, (SELECT * FROM ph_ch p, v_last_ph_ch vl WHERE p.id=vl.last_id) p "
                + " WHERE p.disciple_id = d.id "
                + " UNION "
                + " SELECT 2 as sp, d.*,  "
                + " int4(EXTRACT(YEAR FROM age(now() , d.birthday))) as disciple_age, "
                + " p.height, p.weight, "
                + " get_dsptc(d.id, d.sdep_id, TRUE) "
                + " FROM disciples.disciples d, (SELECT * FROM ph_ch p, v_last_ph_ch vl WHERE p.id=vl.last_id) p "
                + " WHERE p.disciple_id = d.id "
                + " ) a, disciples.sdeps sd "
                + " WHERE a.sdep_id=sd.id "
                + " ORDER BY sp, sdep_id");
    }

    /**
     * Получить физподготовку историю
     * @return
     * @throws Exception
     */
    public static Result getDsptcsHistory(int discipleId, int sdepId) throws Exception {
        return executeSQLQuery(getConnection(),
                "SELECT dc.*, c.key_name as category_key_name , m.key_name as measure_key_name "
                + " FROM disciples.dsptc dc , disciples.categories c, disciples.measures m "
                + " WHERE dc.disciple_id=? AND (?>0 AND dc.sdep_id=? OR ?=0 AND dc.sdep_id IS NULL) "
                + " AND dc.category_id=c.id AND c.measure_id = m.id "
                + " ORDER BY dc.cdate, dc.id", new Par[]{
                    new Par(Types.INTEGER, discipleId),
                    new Par(Types.INTEGER, sdepId),
                    new Par(Types.INTEGER, sdepId),
                    new Par(Types.INTEGER, sdepId)});
    }

    /**
     * Получить список спортивных категорий
     * @return
     * @throws Exception
     */
    public static List getRBCategories(int sdepId) throws Exception {
        return getEntrysIDValue(getConnection(),
                "SELECT c.id, (c.key_name || ' (' || m.key_name || ')') as key_name "
                + " FROM disciples.categories c, disciples.measures m "
                + " WHERE  "
                + " ((" + sdepId + ">0 AND c.sdep_id=" + sdepId + " AND c.specialized=true) "
                + " OR "
                + " (" + sdepId + "=0 AND c.sdep_id IS NULL AND c.specialized=false))"
                + " AND c.measure_id = m.id", "id", "key_name");
    }

    /**
     * Добавть значение физподготовки
     * @param disciple
     * @throws Exception
     */
    public static void addDsptc(Dsptc dsptc) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO dsptc("
                + "disciple_id,category_id,val,cdate,sdep_id) "
                + " VALUES (?,?,?,?,"
                + (dsptc.getSdepId() > 0 ? "?" : "null")
                + ");",
                dsptc,
                "discipleId=>1:INTEGER,"
                + "categoryId=>2:INTEGER,"
                + "val=>3:FLOAT,"
                + "cdate=>4:DATE"
                + (dsptc.getSdepId() > 0 ? ",sdepId=>5:INTEGER" : ""));
    }

//SELECT *, float4(val) as d,
//CASE WHEN sdep_id IS NULL THEN 0
//ELSE sdep_id
//END, COALESCE(sdep_id,0)
//FROM dsptc
    /**
     * Загрузить значение физподготовки
     * @param id
     * @return
     * @throws Exception
     */
    public static Dsptc loadDsptc(int id) throws Exception {
        Dsptc dsptc = new Dsptc(id);
        executeSQLInGetObject(getConnection(),
                "SELECT *, float4(val) as mval, COALESCE(sdep_id,0) as msdep_id FROM dsptc WHERE id=?;",
                dsptc,
                "id=>1:INTEGER",
                dsptc,
                "sdepId<=msdep_id,"
                + "categoryId<=category_id,"
                + "discipleId<=disciple_id,"
                + "val<=mval,"
                + "cdate<=cdate");
        return dsptc;
    }

    /**
     * Редактировать физподготовку
     * @param disciple
     * @throws Exception
     */
    public static void editDsptc(Dsptc dsptc) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE disciples.dsptc "
                + " SET category_id=?, val=?, cdate=? "
                + " WHERE id=?;",
                dsptc,
                "categoryId=>1:INTEGER,"
                + "val=>2:FLOAT,"
                + "cdate=>3:DATE,"
                + "id=>4:INTEGER");
    }

    /**
     * Удалить физподготовку
     * @param dep
     * @throws Exception
     */
    public static void delDsptc(Dsptc dsptc) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.dsptc WHERE id=?",
                dsptc,
                "id=>1:INTEGER");

    }

    // Спортивные достижения ----------------------------------------------------
    // .........................................................................
    /**
     * Получить физподготовку
     * @return
     * @throws Exception
     */
    public static Result getDisciplesAndSpsCounts() throws Exception {
        return executeSQLQuery(getConnection(),
                "SELECT d.*, cc.count , d.id AS disciple_id"
                + " FROM disciples.disciples d "
                + " LEFT OUTER JOIN "
                + " (SELECT disciple_id, count(*) AS count FROM disciples.sp GROUP BY disciple_id) "
                + " cc ON (d.id = cc.disciple_id) ORDER BY d.fname, d.iname, d.oname");
    }

    /**
     * Добавть спортивное достижение
     * @param disciple
     * @throws Exception
     */
    public static void addSp(Sp sp) throws Exception {
        executeSQLInObject(getConnection(),
                "INSERT INTO sp (disciple_id, cdate, description) VALUES (?,?,?);",
                sp,
                "discipleId=>1:INTEGER,"
                + "cdate=>2:DATE,"
                + "description=>3:VARCHAR");
    }

    /**
     * Получить историю спортивных достижений
     * @param discipleId
     * @return
     * @throws Exception
     */
    public static Result getDisciplesSpsHistory(int discipleId) throws Exception {
        return executeSQLQuery(getConnection(),
                "SELECT * FROM sp WHERE disciple_id=" + discipleId + " ORDER BY cdate;");
    }

    /**
     * Загрузить спортивное достижение
     * @param id
     * @return
     * @throws Exception
     */
    public static Sp loadSp(int id) throws Exception {
        Sp sp = new Sp(id);
        executeSQLInGetObject(getConnection(),
                "SELECT * FROM disciples.sp WHERE id=?;",
                sp,
                "id=>1:INTEGER",
                sp,
                "discipleId<=disciple_id,"
                + "cdate<=cdate,"
                + "description<=description");
        return sp;
    }

    /**
     * Редактировать спортивное достижение
     * @param disciple
     * @throws Exception
     */
    public static void editSp(Sp sp) throws Exception {
        executeSQLInObject(getConnection(),
                "UPDATE disciples.sp "
                + " SET cdate=?, description=? "
                + " WHERE id=?;",
                sp,
                "cdate=>1:DATE,"
                + "description=>2:VARCHAR,"
                + "id=>3:INTEGER");
    }

    /**
     * Удалить спортивное достижение
     * @param dep
     * @throws Exception
     */
    public static void delSp(Sp sp) throws Exception {
        executeSQLInObject(getConnection(),
                "DELETE FROM disciples.sp WHERE id=?",
                sp,
                "id=>1:INTEGER");

    }

    // Средний бал ----------------------------------------------------
    // .........................................................................

    /**
     * Установить средний бал
     * @param disciple
     * @param avscore
     * @throws Exception
     */
    public static void setAvscore(Disciple disciple) throws Exception {
        executeSQL(getConnection(),
                "UPDATE disciples SET avscore=? WHERE id=?;",
                new Par[]{new Par(Types.NUMERIC, disciple.getAvscore()),
                    new Par(Types.INTEGER, disciple.getId())});
    }
}
