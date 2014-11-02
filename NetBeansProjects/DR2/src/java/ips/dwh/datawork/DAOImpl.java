/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.datawork;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kpg.db.sql.ADBBase;

/**
 * Модуль работы с данными
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
     * Получить путь к файлу в репозитории
     * @param id
     * @return
     * @throws Exception
     */
    public static final int getUserAccessLevel(String user) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT access_level " +
                    "FROM ips.web_users " +
                    "WHERE  user_name = ?;");

            pstmt.setString(1, user);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                int userAccessLevel = rs.getInt("access_level");

                closeAll(conn, null, pstmt, null, rs);
                return userAccessLevel;

            } else {
                closeAll(conn, null, pstmt, null, rs);
                throw new Exception("Нет сведений по " + user);
            }


        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить дочерние секции
     * @return
     * @throws Exception
     */
    public static final Result getChildSections(Integer id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM ips.dwh_sections " +
                    "WHERE parent_id IS null AND ? IS null " +
                    "OR parent_id = ? AND ? IS NOT null");

            pstmt.setObject(1, id, Types.INTEGER);
            pstmt.setObject(2, id, Types.INTEGER);
            pstmt.setObject(3, id, Types.INTEGER);

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить дочерние коробки
     * @return
     * @throws Exception
     */
    public static final Result getChildBoxes(Integer sectionId, String userName) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT b.*, v.key_name as access_key_name, v.access_level as access_level " +
                    "FROM ips.dwh_boxes b, ips.rb_access_levels v " +
                    "WHERE ((b.section_id IS NULL AND ? IS NULL) " +
                    "OR (b.section_id=? AND ? IS NOT NULL)) " +
                    "AND b.access_level_id = v.id " +
                    "AND v.access_level <= (SELECT access_level FROM ips.web_users WHERE  user_name = ?) " +
                    "ORDER BY b.key_name;");

            pstmt.setObject(1, sectionId, Types.INTEGER);
            pstmt.setObject(2, sectionId, Types.INTEGER);
            pstmt.setObject(3, sectionId, Types.INTEGER);

            pstmt.setString(4, userName);

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получть путь к секции в дереве
     * @param words
     * @return
     * @throws Exception
     */
    public static final String getSectionPath2(Integer id) throws Exception {

        String path = "";
        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ ? = call ips.get_section_path_2(?) }");
            proc.registerOutParameter(1, Types.VARCHAR);
            proc.setObject(2,
                    id,
                    Types.NUMERIC);

            proc.execute();
            path = proc.getString(1);

            proc.close();

            closeAll(conn, proc, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, null, null, null);
            throw new Exception(e.getMessage());
        }

        return path;
    }

    /**
     * Получить секцию
     * @return
     * @throws Exception
     */
    public static final Result getSection(Integer id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM ips.dwh_sections " +
                    "WHERE id=?");

            pstmt.setObject(1, id, Types.INTEGER);

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Добавить секцию
     * @param parentId
     * @param keyName
     * @param description
     * @throws Exception
     */
    public static final void addSection(Integer parentId,
            String keyName,
            String description) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("INSERT INTO ips.dwh_sections(" +
                    "parent_id, key_name, description)" +
                    "VALUES (?, ?, ?);");

            pstmt.setObject(1, parentId, Types.INTEGER);
            pstmt.setObject(2, keyName, Types.VARCHAR);
            pstmt.setObject(3, description, Types.VARCHAR);

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Редактировать секцию
     * @param parentId
     * @param keyName
     * @param description
     * @throws Exception
     */
    public static final void editSection(
            int id,
            //Integer parentId,
            String keyName,
            String description) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("UPDATE ips.dwh_sections" +
                    " SET key_name=?, description=?" +
                    " WHERE id=?;");

            pstmt.setObject(1, keyName, Types.VARCHAR);
            pstmt.setObject(2, description, Types.VARCHAR);
            pstmt.setInt(3, id);

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Удалить секцию
     * @param parentId
     * @param keyName
     * @param description
     * @throws Exception
     */
    public static final void delSection(int id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("DELETE FROM ips.dwh_sections WHERE id=?;");
            pstmt.setInt(1, id);

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить справочник рангов
     * @return
     * @throws Exception
     */
    public static final Result getRbAccessLevels() throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM ips.rb_access_levels;");

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Добавить коробку
     * @return
     * @throws Exception
     */
    public static final int addBoxBegin(
            Integer sectionId,
            String keyName,
            String description,
            String keyWords,
            String keyFios,
            String fileName,
            int accessLevelId,
            Date kDate) throws Exception {

        int newId = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO ips.dwh_boxes(" +
                    "section_id, key_name, description, key_words, key_fios, " +
                    "file_name, access_level_id, kdate) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                    " RETURNING id;");

            pstmt.setObject(1, sectionId, Types.INTEGER);
            pstmt.setString(2, keyName);
            pstmt.setString(3, description);
            pstmt.setString(4, keyWords);
            pstmt.setString(5, keyFios);
            pstmt.setString(6, fileName);

            pstmt.setInt(7, accessLevelId);
            pstmt.setDate(8, new java.sql.Date(kDate.getTime()));

            rs = pstmt.executeQuery();

            if (rs.next()) {
                newId = rs.getInt("id");
                closeAll(conn, null, pstmt, null, rs);
            } else {
                throw new Exception("Внесение данных прошло неудачно");
            }

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }

        return newId;
    }

    /**
     * Завершить запись в репозиторий
     * @return
     * @throws Exception
     */
    public static final void addBoxEnd(int id,
            String repFilePath) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("UPDATE ips.dwh_boxes " +
                    "SET rep_file_path=?, udate=now() " +
                    "WHERE id=?;");

            pstmt.setString(1, repFilePath);
            pstmt.setInt(2, id);

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить коробку
     * @param id
     * @return
     * @throws Exception
     */
    public static final Result getBox(int id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT b.*, v.key_name as access_key_name, v.access_level as access_level " +
                    "FROM ips.dwh_boxes b, ips.rb_access_levels v " +
                    "WHERE b.id=? " +
                    "AND b.access_level_id = v.id " +
                    "ORDER BY b.key_name;");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Редактировать коробку
     * @return
     * @throws Exception
     */
    public static final void editBox(
            int id,
            String keyName,
            String description,
            String keyWords,
            String keyFios,
            String fileName,
            int accessLevelId,
            Date kDate) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("UPDATE ips.dwh_boxes SET " +
                    "key_name=?, " +
                    "description=?, " +
                    "key_words=?, " +
                    "key_fios=?, " +
                    "file_name=?, " +
                    "udate=now(), " +
                    "access_level_id=?, " +
                    "kdate=? " +
                    "WHERE id=?;");

            pstmt.setString(1, keyName);
            pstmt.setString(2, description);
            pstmt.setString(3, keyWords);
            pstmt.setString(4, keyFios);
            pstmt.setString(5, fileName);
            pstmt.setInt(6, accessLevelId);
            pstmt.setDate(7, new java.sql.Date(kDate.getTime()));
            pstmt.setInt(8, id);

            pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Удалить коробку
     * @return
     * @throws Exception
     */
    public static final void delBox(int id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("DELETE FROM ips.dwh_boxes WHERE id=?;");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Переместить коробку
     * @return
     * @throws Exception
     */
    public static final void movBoxToSection(int id, Integer sectionId) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("UPDATE ips.dwh_boxes SET section_id=? WHERE id=?;");

            pstmt.setObject(1, sectionId, Types.INTEGER);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Переместить секцию
     * @return
     * @throws Exception
     */
    public static final void movSectionToSection(int id, Integer sectionId) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("UPDATE ips.dwh_sections SET parent_id=? WHERE id=?;");

            pstmt.setObject(1, sectionId, Types.INTEGER);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    public static final class BoxFile {

        private String fileName;
        private String RepFilePath;

        public BoxFile(String fileName, String RepFilePath) {
            this.fileName = fileName;
            this.RepFilePath = RepFilePath;
        }

        public String getRepFilePath() {
            return RepFilePath;
        }

        public String getFileName() {
            return fileName;
        }
    }

    /**
     * Получить путь к файлу в репозитории
     * @param id
     * @return
     * @throws Exception
     */
    public static final BoxFile getBoxFile(int id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT rep_file_path,file_name FROM ips.dwh_boxes WHERE id=?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                BoxFile boxFile = new BoxFile(
                        rs.getString("file_name"),
                        rs.getString("rep_file_path"));

                closeAll(conn, null, pstmt, null, rs);
                return boxFile;

            } else {
                closeAll(conn, null, pstmt, null, rs);
                throw new Exception("Нет сведений по такому ID");
            }


        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить коробку
     * @param webUserName
     * @param idS
     * @return
     * @throws Exception
     */
    public static final Result getBox(
            String webUserName,
            String idS) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_box(?,?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setString(2, webUserName);
            proc.setObject(3, Integer.parseInt(idS), Types.INTEGER);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, proc, null, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, null, null, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }


     /**
     * Простой общий поиск по ключевым словам
     * @param words
     * @return
     * @throws Exception
     */
    public static final Result getBoxesForWords(
            String webUserNames,
            String words) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_boxes_for_words(?,?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setString(2, webUserNames);
            proc.setString(3, words);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, proc, null, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, null, null, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }

    /**
     * Поиск по датам
     * @param webUserName
     * @param ktype
     * @param ctype
     * @param utype
     * @param bkdate
     * @param ekdate
     * @param cbdate
     * @param cedate
     * @param ubdate
     * @param uedate
     * @return
     * @throws Exception
     */
    public static final Result getBoxesForDates(
            String webUserName,
            boolean ktype,
            boolean ctype,
            boolean utype,
            Date bkdate,
            Date ekdate,
            Date cbdate,
            Date cedate,
            Date ubdate,
            Date uedate) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_boxes_for_dates(?,?,?,?,?,?,?,?,?,?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setString(2, webUserName);
            proc.setBoolean(3, ktype);
            proc.setBoolean(4, ctype);
            proc.setBoolean(5, utype);

            java.sql.Date nullDate =  new java.sql.Date(new Date().getTime());

            if (ktype) {
                proc.setDate(6, new java.sql.Date(bkdate.getTime()));
                proc.setDate(7, new java.sql.Date(ekdate.getTime() /*+ 60 * 60 * 24 * 1000*/ ));
            } else {
                proc.setDate(6, nullDate);
                proc.setDate(7, nullDate);
            }

            if (ctype) {
                proc.setDate(8, new java.sql.Date(cbdate.getTime()));
                proc.setDate(9, new java.sql.Date(cedate.getTime() + 60 * 60 * 24 * 1000));
            } else {
                proc.setDate(8, nullDate);
                proc.setDate(9, nullDate);
            }

            if (utype) {
                proc.setDate(10, new java.sql.Date(ubdate.getTime()));
                proc.setDate(11, new java.sql.Date(uedate.getTime() + 60 * 60 * 24 * 1000));
            } else {
                proc.setDate(10, nullDate);
                proc.setDate(11, nullDate);
            }

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, proc, null, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, null, null, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }


}
