/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package change_password_app.dao;


import change_password_app.session.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }



    /**
     * Сформировать пароль
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String encryptPassword(String password)
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
     * Сменить пароль
     * @param u
     */
    public static void changePassword(User u, String newPassword) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("UPDATE disciples.web_users "
                    + " SET user_password=? "
                    + " WHERE id=?;");

            pstmt.setString(1, encryptPassword(newPassword));
            pstmt.setInt(2, u.getId());
           
            pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, null);
        }

    }

    

}
