/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.dao;


import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import ips.prbase.model.attributes.interfaces.IDateAttribute;
import ips.prbase.model.attributes.interfaces.IImageAttribute;
import ips.prbase.model.attributes.interfaces.IIntAttribute;
import ips.prbase.model.attributes.interfaces.IOidAttribute;
import ips.prbase.model.attributes.interfaces.IRefferenceAttribute;
import ips.prbase.model.attributes.interfaces.ITextAttribute;
import ips.prbase.model.attributes.interfaces.impl.RefferenceAttribute;
import ips.prbase.model.objects.interfaces.impl.ObjectDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import kpg.db.sql.ADBBase;
import org.apache.tomcat.dbcp.dbcp.DelegatingConnection;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

/**
 *
 * @author kopychenko
 */
public final class DAOImpl extends ADBBase {

    /**
     * Получить соединение
     * @return
     * @throws NamingException
     * @throws SQLException
     */
    protected static final Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();

        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/ips_o");


        return ds.getConnection();
    }

    /**
     * Получить пользователя
     * @return
     * @throws Exception
     */
    public static final Result getUser(String userName) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.web_users WHERE user_name='" + userName + "'");

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Создать новый объект
     * @param conn
     * @param od
     * @throws Exception
     */
    public static void createNewObjectDescriptor(Connection conn, ObjectDescriptor od) throws Exception {

//        Connection conn = null;
        PreparedStatement pstmt1 = null;

        ResultSet rs = null;

        try {
//            conn = getConnection();
//            conn.setAutoCommit(true);


            // Создаем объект

            pstmt1 = conn.prepareStatement("INSERT INTO ips.obj_descriptors "
                    + "(key_name) VALUES (?) returning id,key_name,cdate;");

            pstmt1.setObject(1, od.getKeyName(), Types.VARCHAR);

            rs = pstmt1.executeQuery();

            if (rs.next()) {
                od.setId(rs.getInt(1));
            } else {
                throw new Exception("Объект небыл создан");
            }

            closeAll(null, null, pstmt1, null, rs);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(null, null, pstmt1, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Сохранить состояние объекта
     * @param od
     * @param userId
     * @throws Exception
     */
    public static void saveObjectDescriptor(ObjectDescriptor od, int userId) throws Exception {

        Connection conn = null;

        PreparedStatement pstmtCurrent = null;
        PreparedStatement pstmtInsertAttr = null;
        PreparedStatement pstmtUpdateAttr = null;
        PreparedStatement pstmtUpdateObj = null;
        ResultSet rs = null;


        try {
            conn = getConnection();
            conn.setAutoCommit(false);


            boolean insertFlag = false;
            insertFlag = od.getId() == 0;

            if (insertFlag) { // Если объект еще не создан
                createNewObjectDescriptor(conn, od);
                System.out.println("Создан новый объект id=" + od.getId());
            } else { // Если объект уже создан
            }


            pstmtInsertAttr = conn.prepareStatement("INSERT INTO ips.obj"
                    + "(vtext, vnum, vtime, void, user_id, obj_descriptor_id, vrb_id, vrb_root_id, key_name, vimage_id) "
                    + "VALUES (?, ?, ?, ?, " + userId + ", " + od.getId() + ", ?, ?, ?, ?);");


            pstmtUpdateAttr = conn.prepareStatement("UPDATE ips.obj SET "
                    + "vtext=?, vnum=?, vtime=?, void=?, user_id=" + userId + ", obj_descriptor_id=" + od.getId() + ", vrb_id=?, vrb_root_id=?, key_name=? , vimage_id=?"
                    + "WHERE id=?;");



            for (IBasicAttribute iba : od.getAttributes().values()) {

                // Заносим null во все параметры
                for (int i = 1; i < pstmtInsertAttr.getParameterMetaData().getParameterCount() + 1; i++) {
                    pstmtInsertAttr.setObject(i, null);
                }
                for (int i = 1; i < pstmtUpdateAttr.getParameterMetaData().getParameterCount() + 1; i++) {
                    pstmtUpdateAttr.setObject(i, null);
                }
                //Заканчиваем обнуление

                int id = iba.getId();

                if (id == 0) { // если такой есть то обновляем
                    System.out.println("---[Вставляем новую запись]---");
                    pstmtCurrent = pstmtInsertAttr;
                } else { // Иначе вставляем новый
                    System.out.println("---[Обновляем старую запись]---");
                    pstmtCurrent = pstmtUpdateAttr;
                    pstmtCurrent.setInt(9, id); // id
                }

                pstmtCurrent.setString(7, iba.getKeyName()); // key_name

                if (iba instanceof ITextAttribute) { // Если атрибут текстовый
                    ITextAttribute ita = (ITextAttribute) iba;
                    pstmtCurrent.setString(1, ita.getText());
                } else if (iba instanceof IIntAttribute) { // Если атрибут целочисленный
                    IIntAttribute iia = (IIntAttribute) iba;
                    pstmtCurrent.setInt(2, iia.getInt());
                } else if (iba instanceof IDateAttribute) { //Если атрибут дата
                    IDateAttribute ida = (IDateAttribute) iba;
                    pstmtCurrent.setDate(3,
                            (ida.getDate() != null ? new java.sql.Date(ida.getDate().getTime()) : null));
                } else if (iba instanceof IRefferenceAttribute) { // Если справочный атрибут
                    IRefferenceAttribute ira = (IRefferenceAttribute) iba;
                    pstmtCurrent.setInt(5, ira.getKey());
                    pstmtCurrent.setInt(6, ira.getRootId());
                } else if (iba instanceof IImageAttribute) { // Если фаловый атрибут
                    IImageAttribute ioa = (IImageAttribute) iba;

                    System.out.println("---------------------->> SAVE IMAGE ");
                    System.out.println("---------------------->> " + ioa.getFile());
                    System.out.println("---------------------->> " + ioa.getFileFileName());
                    System.out.println("---------------------->> " + ioa.getFileName());
                    System.out.println("---------------------->> " + ioa.getFileContentType());

                    if (ioa.getFile() != null) {

                        int ioaId = saveImage(conn, ioa);
                        System.out.println("SAVE IMAGE id=" + ioaId);

                        pstmtCurrent.setInt(8, ioaId);
                    } else {
                        if (ioa.getImageId() != 0) {
                            pstmtCurrent.setInt(8, ioa.getImageId());
                        }
                    }
                } else if (iba instanceof IOidAttribute && !(iba instanceof IImageAttribute)) { // Если фаловый атрибут
                    IOidAttribute ioa = (IOidAttribute) iba;

                    System.out.println("---------------------->> " + ioa.getFile());
                    System.out.println("---------------------->> " + ioa.getFileFileName());
                    System.out.println("---------------------->> " + ioa.getFileName());
                    System.out.println("---------------------->> " + ioa.getFileContentType());

                    if (ioa.getFileFileName() != null) {

                        int ioaId = saveOidAttributeFile(conn, ioa);
                        System.out.println("SAVE FILE id=" + ioaId);
                        pstmtCurrent.setInt(4, ioaId);
                    } else {
                        if (ioa.getOid() != 0) {
                            pstmtCurrent.setInt(4, ioa.getOid());
                        }
                    }
                }

                pstmtCurrent.executeUpdate();

            }

            conn.commit();
            closeAll(null, null, pstmtInsertAttr, null, null);
            closeAll(null, null, pstmtUpdateAttr, null, null);
            closeAll(null, null, pstmtUpdateObj, null, null);

            closeAll(conn, null, null, null, rs);

        } catch (Exception e) {
            e.printStackTrace();

            closeAll(null, null, pstmtInsertAttr, null, null);
            closeAll(null, null, pstmtUpdateAttr, null, null);
            closeAll(null, null, pstmtUpdateObj, null, null);

            closeAll(conn, null, null, null, rs);

            throw new Exception(e.getMessage());
        }

    }

    /**
     * Получит справочный атрибут
     * @param rbName
     * @return
     * @throws Exception
     */
    public static RefferenceAttribute getRefferenceAttribute(String rbName) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;


        try {
            conn = getConnection();
            conn.setAutoCommit(true);

            RefferenceAttribute ra = new RefferenceAttribute(rbName);

            pstmt = conn.prepareStatement("with recursive tree (vtext, root_id, id, parent_id, level, pathstr, parent_rb_root_id, parent_rb_name, parent_rb_id)"
                    + " as (select vtext, root_id, id, parent_id ,0, cast('' as text) , parent_rb_root_id, parent_rb_name, parent_rb_id "
                    + " from ips.rb_obj "
                    + " where parent_id is null AND rb_name=? "
                    + " union all "
                    + " select v.vtext, v.root_id, v.id, v.parent_id,tree.level + 1, tree.pathstr || '/' ||v.id, v.parent_rb_root_id, v.parent_rb_name, v.parent_rb_id "
                    + " from ips.rb_obj v "
                    + " inner join tree on tree.id = v.parent_id) "
                    + " select root_id, id, parent_id,level, vtext, pathstr, parent_rb_root_id, parent_rb_name, parent_rb_id from tree  where level > 0  order by pathstr;");

            pstmt.setString(1, rbName);

            rs = pstmt.executeQuery();

            boolean f = true;
            while (rs.next()) {

                if (f) {

                    ra.setRootId(rs.getInt("root_id"));
                    ra.setParentId(rs.getInt("parent_rb_id"));
                    ra.setParentRootName(rs.getString("parent_rb_name"));
                    ra.setParentRootId(rs.getInt("parent_rb_root_id"));

                    f = false;
                }

                int level = rs.getInt("level");
                String prefix = "";
                for (int i = 0; i < level - 1; i++) {
                    prefix = prefix + "";
                }


                ra.addEntry(rs.getInt("id"), prefix + ' ' + rs.getString("vtext"));

            }

            closeAll(conn, null, pstmt, null, rs);

            return ra;
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Загрузить файловый атрибути получит его id
     * @param conn
     * @param file
     * @return
     * @throws Exception
     */
    private static int saveOidAttributeFile(Connection conn, IOidAttribute file) throws Exception {

        PreparedStatement pstmt = null;
        FileInputStream fis = null;
        ResultSet rs = null;

        int id = 0;

//        // Устанавливаем соединение
//        String url = "jdbc:postgresql:ips_o";
//        Properties props = new Properties();
//        props.setProperty("user", "ips_architect");
//        props.setProperty("password", "paradox");
//        Connection conn = DriverManager.getConnection(url, props);

        try {
            // All LargeObject API calls must be within a transaction block
//            conn.setAutoCommit(false);

            PGConnection pgConn = (PGConnection) ((DelegatingConnection) conn).getInnermostDelegate();

            // Get the Large Object Manager to perform operations with
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();

            // Create a new large object
            long oid = lobj.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);

            // Open the large object for writing
            LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);

            // Now open the file
            fis = new FileInputStream(file.getFile());

            // Copy the data from the file to the large object
            byte buf[] = new byte[2048];
            int s, tl = 0;
            while ((s = fis.read(buf, 0, 2048)) > 0) {
                obj.write(buf, 0, s);
                tl += s;
            }

            // Close the large object
            obj.close();

            // Now insert the row into imageslo
            pstmt = conn.prepareStatement("INSERT INTO ips.oid_odj ( viod, file_name, content_type)"
                    + " VALUES (?,?,?) returning id");


            pstmt.setLong(1, oid);
            pstmt.setString(2, file.getFileFileName());
            pstmt.setString(3, file.getFileContentType());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

            pstmt.close();
            fis.close();

            // Finally, commit the transaction.
            //conn.commit();

            closeAll(null, null, pstmt, null, rs);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            fis.close();
            closeAll(null, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Загрузить объект
     * @param od
     * @throws Exception
     */
    public static void loadObjectDescriptor(ObjectDescriptor od) throws Exception {
        loadObjectDescriptor(od, true);
    }

    /**
     * Загрузить объект
     * @param od
     * @param loadValues
     * @throws Exception
     */
    public static void loadObjectDescriptor(ObjectDescriptor od, boolean loadValues) throws Exception {

//        ((ITextAttribute) od.getAttributes().get("fio")).setText("Базис");

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        // TODO Зделать проверу типа подключаемого объекта

        try {
            conn = getConnection();
            conn.setAutoCommit(true);

            pstmt = conn.prepareStatement("SELECT * FROM ips.obj WHERE obj_descriptor_id=?;");
            pstmt.setInt(1, od.getId());

            rs = pstmt.executeQuery();

            while (rs.next()) {

                String keyName = rs.getString("key_name");

                IBasicAttribute iba = od.getAttributes().get(keyName);
                iba.setId(rs.getInt("id"));

                if (loadValues) {
                    if (iba instanceof ITextAttribute) { // Текстовый атрибут
                        ITextAttribute ita = (ITextAttribute) iba;
                        ita.setText(rs.getString("vtext"));
                    } else if (iba instanceof IIntAttribute) { // Целочисленный атрибут
                        IIntAttribute iia = (IIntAttribute) iba;
                        iia.setInt(rs.getInt("vnum"));
                    } else if (iba instanceof IRefferenceAttribute) { // Справочный атрибут
                        IRefferenceAttribute ira = (IRefferenceAttribute) iba;
                        ira.setKey(rs.getInt("vrb_id"));
                    } else if (iba instanceof IDateAttribute) { // Дата атрибут
                        IDateAttribute ida = (IDateAttribute) iba;
                        ida.setDate(rs.getDate("vtime"));
                    }

                }
                if (iba instanceof IOidAttribute) { // Если фаил
                    IOidAttribute ioa = (IOidAttribute) iba;

                    ioa.setOid(rs.getInt("void"));
                    // TODO пока неяно как работать с фалами

                }

                if (iba instanceof IImageAttribute) { // Если фаил
                    IImageAttribute iia = (IImageAttribute) iba;

                    iia.setImageId(rs.getInt("vimage_id"));
                    // TODO пока неяно как работать с фалами

                }


            }

            closeAll(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить файловый объект
     * @param response
     * @param id
     * @throws Exception
     */
    public static void getOid(HttpServletResponse response, int id) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        LargeObject obj = null;
        ResultSet rs = null;

        ServletOutputStream stream = response.getOutputStream();

        try {
            conn = getConnection();
            // All LargeObject API calls must be within a transaction block
            conn.setAutoCommit(false);

            PGConnection pgConn = (PGConnection) ((DelegatingConnection) conn).getInnermostDelegate();

            // Get the Large Object Manager to perform operations with
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();

            ps = conn.prepareStatement("SELECT id, viod, file_name, content_type FROM ips.oid_odj WHERE id = (SELECT void FROM ips.obj WHERE id=?)");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                // Open the large object for reading
                long oid = rs.getLong("viod");
                response.setContentType(rs.getString("content_type"));

                obj = lobj.open(oid, LargeObjectManager.READ);

                byte buf[] = new byte[2048];
                int s, tl = 0;
                while ((s = obj.read(buf, 0, 2048)) > 0) {
                    stream.write(buf, 0, s);
                    tl += s;
                }

                // Close the object
                obj.close();
            }

            // Finally, commit the transaction.
            closeAll(conn, null, ps, null, rs);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());

            closeAll(conn, null, ps, null, rs);

            throw new Exception(e.getMessage());
        }

    }

    public static enum getListPersinsOrderType {

        FOR_CDATE,
        FOR_VTEXT,
        FOR_ID
    }

    /**
     * Список персон
     * @param s
     * @return
     * @throws Exception
     */
    public static final Result getListPersons(String s, getListPersinsOrderType type) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String orderBy = "";
        switch (type) {
            case FOR_ID:
                orderBy = "ORDER BY d.id";
                break;
            case FOR_CDATE:
                orderBy = "ORDER BY d.cdate";
                break;
            case FOR_VTEXT:
                orderBy = "ORDER BY obj.vtext";
                break;
        }


        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT d.id, d.cdate, obj.vtext FROM ips.obj_descriptors d "
                    + "LEFT OUTER JOIN ips.obj obj ON (d.id = obj.obj_descriptor_id AND obj.key_name='fio') "
                    + "WHERE d.key_name='PERSON' " + orderBy);

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить список по ключевым словам
     * @param s
     * @return
     * @throws Exception
     */
    public static final Result getListPersonsForKeyWords(String query) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;



        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT d.id, d.cdate, obj.vtext FROM ips.obj_descriptors d "
                    + "LEFT OUTER JOIN ips.obj obj ON (d.id = obj.obj_descriptor_id AND obj.key_name='fio') "
                    + "WHERE d.key_name='PERSON' AND d.id IN (SELECT DISTINCT obj_descriptor_id FROM ips.obj WHERE fts @@ to_tsquery(?))");

            pstmt.setString(1, query);

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Сохранить картинку
     * @param conn
     * @param iimage
     * @return
     * @throws Exception
     */
    private static int saveImage(Connection conn, IImageAttribute iimage) throws Exception {

        PreparedStatement pstmt = null;
        FileInputStream fis = null;
        ResultSet rs = null;

        int id = 0;

        try {
            conn.setAutoCommit(false);

            // Now open the file
            fis = new FileInputStream(iimage.getFile());

            pstmt = conn.prepareStatement("INSERT INTO ips.images ( vimage, file_name, content_type)"
                    + " VALUES (?,?,?) returning id");

            pstmt.setBinaryStream(1, fis, (int) iimage.getFile().length());
            pstmt.setString(2, iimage.getFileName());
            pstmt.setString(3, iimage.getFileContentType());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

            pstmt.close();
            fis.close();

            closeAll(null, null, pstmt, null, rs);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            fis.close();
            closeAll(null, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить файловый объект
     * @param response
     * @param id
     * @throws Exception
     */
    public static void getImage(HttpServletResponse response, int id) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ServletOutputStream stream = response.getOutputStream();

        try {
            conn = getConnection();
            // All LargeObject API calls must be within a transaction block
            conn.setAutoCommit(false);

            ps = conn.prepareStatement("SELECT id, vimage, file_name, content_type FROM ips.images" +
                    " WHERE id=?;");
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if(rs.next()) {
                response.setContentType(rs.getString("content_type"));

                byte[] imgBytes = rs.getBytes("vimage");
                // use the data in some way here
                stream.write(imgBytes, 0, imgBytes.length);
            }

            // Finally, commit the transaction.
            closeAll(conn, null, ps, null, rs);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());

            closeAll(conn, null, ps, null, rs);

            throw new Exception(e.getMessage());
        }

    }


    /**
     * Получить объект картинки
     * @param id
     * @return
     * @throws Exception
     */
    public static InputStream getImage(int id) throws Exception {

        InputStream is = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            // All LargeObject API calls must be within a transaction block
            conn.setAutoCommit(false);

            ps = conn.prepareStatement("SELECT id, vimage, file_name, content_type FROM ips.images" +
                    " WHERE id=?;");
            ps.setInt(1, id);

            rs = ps.executeQuery();


//            System.out.println("ВЫПОЛНЕН ЗАПРОС КАРТИНКИ");
            if(rs.next()) {

//                byte[] imgBytes = rs.getBytes("vimage");
                // use the data in some way here

//                System.out.println("ПОЛУЧЕНА КАРТИНКА");
                is = rs.getBinaryStream("vimage");
                
            }

            // Finally, commit the transaction.
            closeAll(conn, null, ps, null, rs);

            return is;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());


            closeAll(conn, null, ps, null, rs);

            if(is != null){
                is.close();
            }

            throw new Exception(e.getMessage());
        }

    }


}


