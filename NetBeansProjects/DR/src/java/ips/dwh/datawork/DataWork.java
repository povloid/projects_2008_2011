package ips.dwh.datawork;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DataWork {

    public static final String DATE_PATTERN = "dd.MM.yyyy";

    public static final class Result {

        private Connection conn;
        private ResultSet rs;
        private CallableStatement call;

        /**
         * Конструктор
         * @param conn
         * @param rs
         * @param call
         */
        public Result(Connection conn, ResultSet rs, CallableStatement call) {
            super();
            this.conn = conn;
            this.rs = rs;
            this.call = call;
        }

        public Connection getConn() {
            return conn;
        }

        public ResultSet getRs() {
            return rs;
        }

        public CallableStatement getCall() {
            return call;
        }

        /**
         * Закрыть все
         */
        public void closeAll() {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Получить соединение
     * @return
     * @throws NamingException
     * @throws SQLException
     */
    private static final Connection getConnection() throws NamingException, SQLException {

        Context initContext = new InitialContext();

        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/ips_main");
        return ds.getConnection();
    }

    /**
     * Закрыть ресурсы
     * @param conn
     * @param stat
     */
    private static final void closeAll(Connection conn, CallableStatement stat) {

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Закрыть ресурсы
     * @param conn
     * @param stat
     * @param rs
     */
    private static final void closeAll(Connection conn, CallableStatement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        closeAll(conn, stat);
    }

    /**
     * Получить
     * @param userName
     * @return
     * @throws Exception
     */
    public static final int getUserAccessLevel(String userName) throws Exception {

        Result result = getUser(userName);

        ResultSet rs = result.getRs();
        int level = -1;

        if (rs.next()) {
            level = rs.getInt("ACCESS_LEVEL");
        }

        result.closeAll();

        if (level == -1) {
            throw new Exception("Нет данных по пользователю " + userName);
        }

        return level;

    }

    /**
     * Получить пользователя
     * @return
     * @throws Exception
     */
    public static final Result getUser(String userName) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_web_user(?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setString(2, userName);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }

    /**
     * Получить список групп
     * @return
     * @throws Exception
     */
    public static final Result getSections(String parentId) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_sections(?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setObject(2, parentId, Types.NUMERIC);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;

    }

    /**
     * Получить секцию
     * @param words
     * @return
     * @throws Exception
     */
    public static final Result getSection(String idS) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_section(?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setObject(2, idS, Types.NUMERIC);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }

    /**
     * Получть путь к секции в дереве
     * @param words
     * @return
     * @throws Exception
     */
    public static final String getSectionPath(String idS) throws Exception {

        String path = "";
        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ ? = call ips.get_section_path(?) }");
            proc.registerOutParameter(1, Types.VARCHAR);
            proc.setObject(2,
                    (idS == null || idS.length() == 0 || idS.equals("root")) ? null : Long.parseLong(idS),
                    Types.NUMERIC);

            proc.execute();
            path = proc.getString(1);

            proc.close();

            closeAll(conn, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }

        return path;
    }

    /**
     * Получть путь к секции в дереве
     * @param words
     * @return
     * @throws Exception
     */
    public static final String getSectionPath2(String idS) throws Exception {

        String path = "";
        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ ? = call ips.get_section_path_2(?) }");
            proc.registerOutParameter(1, Types.VARCHAR);
            proc.setObject(2,
                    (idS == null || idS.length() == 0 || idS.equals("root")) ? null : Long.parseLong(idS),
                    Types.NUMERIC);

            proc.execute();
            path = proc.getString(1);

            proc.close();

            closeAll(conn, proc);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }

        return path;
    }

    public final static class ConnCont {

        private Connection conn;

        public Connection getConn() {
            return conn;
        }

        public void setConn(Connection conn) {
            this.conn = conn;
        }

        /**
         * Подтвердить и закрыть
         * @throws SQLException
         */
        public void commitAndClose() throws SQLException {
            if (conn != null) {
                conn.commit();
                conn.close();
            }
        }

        /**
         * Отменить и закрыть
         * @throws SQLException
         */
        public void rollbackAndClose() throws SQLException {
            if (conn != null) {
                conn.rollback();
                conn.close();
            }
        }
    }

    /**
     * Добавить группу
     * @param parentIdS
     * @param keyName
     * @param description
     * @return
     * @throws Exception
     */
    public static final int addGroup(String parentIdS, String keyName, String description) throws Exception {
        ConnCont cc = new ConnCont();
        int id = addGroup(cc, parentIdS, keyName, description);
        cc.commitAndClose();
        return id;
    }

    /**
     * Добавить группу
     * @param cc
     * @param parentIdS
     * @param keyName
     * @param description
     * @return
     * @throws Exception
     */
    public static final int addGroup(ConnCont cc, String parentIdS, String keyName, String description) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        int id = 0;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            cc.setConn(conn);

            proc = conn.prepareCall("{ ? = call ips.add_section(?,?,?) }");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.setObject(2,
                    (parentIdS == null || parentIdS.length() == 0 || parentIdS.equals("root")) ? null : Long.parseLong(parentIdS),
                    Types.NUMERIC);
            proc.setString(3, keyName);
            proc.setString(4, description);

            proc.execute();
            id = proc.getInt(1);

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }

        return id;
    }

    /**
     * Редактировать группу
     * @param parentIdS
     * @param keyName
     * @param description
     * @throws Exception
     */
    public static final void editGroup(String idS, String parentIdS, String keyName, String description) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ call ips.edit_section(?,?,?,?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.NUMERIC);
            proc.setObject(2,
                    (parentIdS == null || parentIdS.length() == 0 || parentIdS.equals("root")) ? null : Long.parseLong(parentIdS),
                    Types.NUMERIC);
            proc.setString(3, keyName);
            proc.setString(4, description);

            proc.execute();

            proc.close();

            closeAll(conn, proc);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Удалить группу
     * @param cc
     * @param words
     * @throws Exception
     */
    public static final void delGroup(ConnCont cc, String idS) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            cc.setConn(conn);

            proc = conn.prepareCall("{ call ips.del_section(?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.NUMERIC);

            proc.execute();

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить список документов
     * @param webUserName
     * @param parentId
     * @return
     * @throws Exception
     */
    public static final Result getBoxes(String webUserName, String parentId) throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_boxes(?,?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setString(2, webUserName);
            proc.setObject(3, parentId, Types.NUMERIC);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;
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
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }

    /**
     * ДОбавить коробку
     * @param cc
     * @param parentIdS
     * @param keyName
     * @param description
     * @param keyWords
     * @param keyFios
     * @param fileName
     * @param accessLevel
     * @param kDate
     * @return
     * @throws Exception
     */
    public static final int addBoxBegin(ConnCont cc,
            String parentIdS,
            String keyName,
            String description,
            String keyWords,
            String keyFios,
            String fileName,
            String accessLevel,
            String kDate)
            throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        int id = 0;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            cc.setConn(conn);

            proc = conn.prepareCall("{ ? = call ips.add_box_begin(?,?,?,?,?,?,?,?) }");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.setObject(2,
                    (parentIdS == null || parentIdS.length() == 0 || parentIdS.equals("root")) ? null : Integer.parseInt(parentIdS),
                    Types.INTEGER);
            proc.setString(3, keyName);
            proc.setString(4, description);
            proc.setString(5, keyWords);
            proc.setString(6, keyFios);
            proc.setString(7, fileName);
            proc.setInt(8, Integer.parseInt(accessLevel));

            if (kDate != null && kDate.length() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
                proc.setDate(9, new java.sql.Date(sdf.parse(kDate).getTime()));
            } else {
                proc.setDate(9, null);
            }

            proc.execute();
            id = proc.getInt(1);

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }

        return id;
    }

    /**
     * Обновить вставленную коробку
     * @param cc
     * @param IdS
     * @param repFilePath
     * @throws Exception
     */
    public static final void addBoxEnd(ConnCont cc,
            String IdS,
            String repFilePath)
            throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = cc.getConn();

            proc = conn.prepareCall("{ call ips.add_box_end(?,?) }");

            proc.setInt(1, Integer.parseInt(IdS));
            proc.setString(2, repFilePath);

            proc.execute();
            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }
    }



    /**
     * Установить путь к файлу
     * @param IdS
     * @param repFilePath
     * @throws Exception
     */
    public static final void addBoxEnd(
            String IdS,
            String repFilePath)
            throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ call ips.add_box_end(?,?) }");

            proc.setInt(1, Integer.parseInt(IdS));
            proc.setString(2, repFilePath);

            proc.execute();
            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, proc);
        }
    }



    /**
     * Редактировать коробку
     * @param cc
     * @param words
     * @param keyName
     * @param description
     * @param keyWords
     * @param keyFios
     * @throws Exception
     */
    public static final void editBox(ConnCont cc, String idS,
            String keyName,
            String description,
            String keyWords,
            String keyFios,
            String accessLevel,
            String kDate) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            cc.setConn(conn);

            proc = conn.prepareCall("{ call ips.edit_box(?,?,?,?,?,?,?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.INTEGER);
            proc.setString(2, keyName);
            proc.setString(3, description);
            proc.setString(4, keyWords);
            proc.setString(5, keyFios);
            proc.setInt(6, Integer.parseInt(accessLevel));

            if (kDate != null && kDate.length() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
                proc.setDate(7, new java.sql.Date(sdf.parse(kDate).getTime()));
            } else {
                proc.setDate(7, null);
            }

            proc.execute();

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Редактировать имя файла в коробке
     * @param cc
     * @param words
     * @param fileName
     * @throws Exception
     */
    public static final void editBoxFileName(ConnCont cc,
            String idS,
            String fileName) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            cc.setConn(conn);

            proc = conn.prepareCall("{ call ips.edit_box_file_name(?,?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.INTEGER);
            proc.setString(2, fileName);

            proc.execute();

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Удалить коробку
     * @param cc
     * @param words
     * @throws Exception
     */
    public static final void delBox(ConnCont cc,
            String idS) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            cc.setConn(conn);

            proc = conn.prepareCall("{ call ips.del_box(?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.INTEGER);

            proc.execute();

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить из справочника урони доступа
     * @return
     * @throws Exception
     */
    public static final Result getRbAccessLevels() throws Exception {

        Result result = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            proc = conn.prepareCall("{ ? = call ips.get_rb_access_levels() }");
            proc.registerOutParameter(1, Types.OTHER);

            proc.execute();

            rs = (ResultSet) proc.getObject(1);
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }

    /**
     * Получить путь к файлу в репозитории
     * @param words
     * @param userName
     * @return
     * @throws Exception
     */
    public static final String getBoxRepFilePath(
            String idS, String userName) throws Exception {

        String path = "";
        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ ? = call ips.get_box_rep_file_path(?,?) }");
            proc.registerOutParameter(1, Types.VARCHAR);
            proc.setInt(2, Integer.parseInt(idS));
            proc.setString(3, userName);

            proc.execute();
            path = proc.getString(1);

            proc.close();

            closeAll(conn, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        }

        return path;
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
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
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
            result = new Result(conn, rs, proc);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc, rs);
            throw new Exception(e.getMessage());
        }

        return result;
    }


    /**
     * Переместить секцию в секцию
     * @param idS
     * @param parentIdS
     * @throws Exception
     */
    public static final void movSectionToSection(
            String idS, String parentIdS) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ call ips.mov_section_to_section(?,?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.INTEGER);
            proc.setObject(2,
                    (parentIdS == null || parentIdS.length() == 0 || parentIdS.equals("root")) ? null : Long.parseLong(parentIdS),
                    Types.INTEGER);

            proc.execute();

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, proc);
        }
    }


    /**
     * Переместить документ в секцию
     * @param idS
     * @param parentIdS
     * @throws Exception
     */
    public static final void movBoxToSection(
            String idS, String parentIdS) throws Exception {

        Connection conn = null;
        CallableStatement proc = null;

        try {
            conn = getConnection();

            proc = conn.prepareCall("{ call ips.mov_box_to_section(?,?) }");
            proc.setObject(1, Integer.parseInt(idS), Types.INTEGER);
            proc.setObject(2,
                    (parentIdS == null || parentIdS.length() == 0 || parentIdS.equals("root")) ? null : Long.parseLong(parentIdS),
                    Types.INTEGER);

            proc.execute();

            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, proc);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, proc);
        }
    }


}
