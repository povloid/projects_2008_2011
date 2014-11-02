/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.dao;

import java.sql.Connection;
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

}
