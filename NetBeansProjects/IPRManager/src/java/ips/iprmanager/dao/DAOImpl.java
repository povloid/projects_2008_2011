/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.iprmanager.dao;

import ips.iprmanager.model.Dep;
import ips.iprmanager.model.Role;
import ips.iprmanager.session.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kpg.db.sql.ADBBase;

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
     * Получить список пользователей
     * @return
     * @throws Exception
     */
    public static final Result getUsers() throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.web_users u, ips.deps d WHERE u.dep_id=d.id ORDER BY u.id;");

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Получить список департаментов
     * @return
     */
    public static final List<Dep> getDeps() {

        List<Dep> deps = new ArrayList<Dep>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.deps;");

            while (rs.next()) {

                Dep dep = new Dep();
                dep.setId(rs.getInt("id"));
                dep.setKeyName(rs.getString("key_name"));
                dep.setDescription(rs.getString("description"));

                deps.add(dep);

            }

            closeAll(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
        }

        return deps;
    }

    /**
     * Загрузить
     * @param id
     * @param user
     */
    public static final void loadUser(int id, User user) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.web_users WHERE id=" + id + "");
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
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
        }

    }

    /**
     * 
     * @param user
     */
    public static final void initUserRoles(User user) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("SELECT 1 as in_user, r.id, r.role_name "
                    + " FROM ips.web_users_roles ur, ips.web_users u, ips.web_roles r "
                    + " WHERE ur.user_id = ? AND ur.user_id = u.id AND ur.role_id = r.id "
                    + " UNION "
                    + " SELECT 0 as in_user, r.id, r.role_name "
                    + " FROM ips.web_roles r WHERE r.id "
                    + " NOT IN (SELECT r.id "
                    + " FROM ips.web_users_roles ur, ips.web_users u, ips.web_roles r "
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
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
        }
    }

    /**
     * Сформировать пароль
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static final String encryptPassword(String password)
            throws NoSuchAlgorithmException {
        MessageDigest messageDigest =
                MessageDigest.getInstance("SHA-1");
        byte[] bs;
        messageDigest.reset();
        bs = messageDigest.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        //hex encode the digest
        for (int i = 0; i < bs.length; i++) {
            String hexVal = Integer.toHexString(0xFF & bs[i]);
            if (hexVal.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(hexVal);
        }
        return stringBuilder.toString();
    }

    /**
     * Добавить нового пользоывателя
     * @param u
     */
    public static final void addNewUser(User u) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtR = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("INSERT INTO ips.web_users( "
                    + " user_name, user_password, description, dep_id, access_level, blocked) "
                    + " VALUES (?, ?, ?, ?, ?, ?) returning id;");

            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, encryptPassword(u.getPassword()));
            pstmt.setString(3, u.getDescription());
            pstmt.setInt(4, u.getDepId());
            pstmt.setInt(5, u.getAccessLevel());
            pstmt.setBoolean(6, u.isBlocked());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                u.setId(rs.getInt("id"));
                System.out.println("NEW USER ID=" + u.getId());
            }

            List list = u.getUserRoles().getHaveRoles2();

            pstmtR = conn.prepareStatement("INSERT INTO ips.web_users_roles(user_id, role_id) VALUES (?, ?);");

            for (Object o : list) {
                pstmtR.clearParameters();

                pstmtR.setInt(1, u.getId());
                pstmtR.setInt(2, Integer.parseInt((String) o));

                pstmtR.executeUpdate();
            }

            conn.commit();

            closeAll(conn, null, pstmt, pstmtR, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, pstmtR, rs);
        }

    }

    /**
     * Редактировать пользоывателя
     * @param u
     */
    public static final void editUser(User u) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtR = null;
        PreparedStatement pstmtD = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            String password = "";
            if (u.getPassword().replace(" ", "").length() > 0) {
                password = "user_password='" + encryptPassword(u.getPassword()) + "',";
            }

            pstmt = conn.prepareStatement("UPDATE ips.web_users "
                    + " SET user_name=?, " + password + " description=?, dep_id=?, "
                    + " access_level=?, blocked=? "
                    + " WHERE id=?;");

            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getDescription());
            pstmt.setInt(3, u.getDepId());
            pstmt.setInt(4, u.getAccessLevel());
            pstmt.setBoolean(5, u.isBlocked());


            pstmt.setInt(6, u.getId());

            pstmt.executeUpdate();


            //--------------------------------------------
            pstmtD = conn.prepareStatement("DELETE FROM ips.web_users_roles WHERE user_id=?;");
            pstmtD.setInt(1, u.getId());
            pstmtD.executeUpdate();

            //--------------------------------------------

            List list = u.getUserRoles().getHaveRoles2();

            pstmtR = conn.prepareStatement("INSERT INTO ips.web_users_roles(user_id, role_id) VALUES (?, ?);");

            for (Object o : list) {
                pstmtR.clearParameters();

                pstmtR.setInt(1, u.getId());
                pstmtR.setInt(2, Integer.parseInt((String) o));

                pstmtR.executeUpdate();

            }

            conn.commit();

            closeAll(null, null, pstmtD, null, null);
            closeAll(conn, null, pstmt, pstmtR, rs);


        } catch (Exception e) {
            e.printStackTrace();
            closeAll(null, null, pstmtD, null, null);
            closeAll(conn, null, pstmt, pstmtR, rs);
        }

    }

    /**
     * Удалить пользователя
     * @param r
     */
    public static final void delUser(User u) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("DELETE FROM ips.web_users "
                    + " WHERE id=?");

            pstmt.setInt(1, u.getId());

            pstmt.executeUpdate();

            conn.commit();

            closeAll(conn, null, pstmt, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);

            throw new Exception(e.getMessage());
        }
    }


    /**
     * Получить список ролей
     * @return
     * @throws Exception
     */
    public static final Result getRoles() throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.web_roles ORDER BY id;");

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Добавить новую роль
     * @param r
     */
    public static final void addNewRole(Role r) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("INSERT INTO ips.web_roles( "
                    + " role_name , description) "
                    + " VALUES ( ?, ?);");

            pstmt.setString(1, r.getKeyName());
            pstmt.setString(2, r.getDescription());

            pstmt.execute();

            conn.commit();

            closeAll(conn, null, pstmt, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);

            throw new Exception(e.getMessage());
        }
    }

    /**
     * Добавить загрузить роль
     * @param id
     */
    public static final Role loadRole(int id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Role role = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("SELECT * FROM ips.web_roles WHERE id=?");
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                role = new Role(rs.getInt("id"),
                        rs.getString("role_name"),
                        rs.getString("description"));
            }

            closeAll(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);

            throw new Exception(e.getMessage());
        }

        return role;
    }

    /**
     * Добавить новую роль
     * @param r
     */
    public static final void editRole(Role r) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("UPDATE ips.web_roles "
                    + " SET  role_name=?, description=? "
                    + " WHERE id=?;");

            pstmt.setString(1, r.getKeyName());
            pstmt.setString(2, r.getDescription());

            pstmt.setInt(3, r.getId());

            pstmt.executeUpdate();

            conn.commit();

            closeAll(conn, null, pstmt, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);

            throw new Exception(e.getMessage());
        }
    }

    /**
     * Удалить роль
     * @param r
     */
    public static final void delRole(Role r) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("DELETE FROM ips.web_roles "
                    + " WHERE id=?");

            pstmt.setInt(1, r.getId());

            pstmt.executeUpdate();

            conn.commit();

            closeAll(conn, null, pstmt, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);

            throw new Exception(e.getMessage());
        }
    }

}
