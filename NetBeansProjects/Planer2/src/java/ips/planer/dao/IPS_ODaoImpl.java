/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.planer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kpg.db.sql.ADBBase;

/**
 *
 * @author kopychenko
 */
public final class IPS_ODaoImpl extends ADBBase {

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
        } finally {
        }
    }

    /**
     * Получить департамент
     * @return
     * @throws Exception
     */
    public static final Result getDep(int id) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.deps WHERE id=" + id);

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        } finally {
        }
    }

    /**
     * Получить список департаментов
     * @return
     * @throws Exception
     */
    public static final Result getDeps() throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ips.deps ORDER BY order_id");

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        } finally {
        }
    }

    /**
     * Получить список департаментов
     * @return
     * @throws Exception
     */
    public static final Result getDepsNotForDep(int depId, Date bDate, Date eDate) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(
                    "SELECT a.*,(select count(*) from ips.questions " +
                    "WHERE from_dep_id=a.id " +
                    "AND dep_id=? AND ctime BETWEEN ? AND ?) as incount " +
                    "FROM ips.deps a WHERE a.id!=? ");


            pstmt.setInt(1, depId);
            pstmt.setDate(2, new java.sql.Date(bDate.getTime()));
            pstmt.setDate(3, new java.sql.Date(eDate.getTime()));
            pstmt.setInt(4, depId);
            

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        } finally {
        }
    }

    /**
     * Получить количество входящих вопросов
     * @return
     * @throws Exception
     */
    public static final int getInputQuestionCount(int depId, Date bDate, Date eDate) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int count = 0;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            
            pstmt = conn.prepareStatement("SELECT count(*) FROM ips.questions WHERE dep_id=? AND ctime BETWEEN ? AND ?");
            
            pstmt.setInt(1, depId);
            pstmt.setDate(2, new java.sql.Date(bDate.getTime()));
            pstmt.setDate(3, new java.sql.Date(eDate.getTime()));

            rs = pstmt.executeQuery();

            rs.next();
            count = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, null, pstmt, null, rs);
        }

        return count;
    }

    /**
     * Получить список вопросов от департамента к департаменту
     * @param depId
     * @param fromDepId
     * @return
     * @throws Exception
     */
    public static final Result getQuestionsForDepFromDep(
            int depId, int fromDepId,
            Date bDate, Date eDate) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM ips.questions " +
                    "WHERE dep_id=? AND from_dep_id=? " +
                    " AND ctime BETWEEN ? AND ? " +
                    "ORDER BY id");

            pstmt.setInt(1, depId);
            pstmt.setInt(2, fromDepId);
            pstmt.setDate(3, new java.sql.Date(bDate.getTime()));
            pstmt.setDate(4, new java.sql.Date(eDate.getTime()));

            rs = pstmt.executeQuery();

            //rs = stmt.executeQuery(
            //        "SELECT * FROM ips.questions WHERE dep_id=" + depId + " AND from_dep_id=" + fromDepId + " ORDER BY id");

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, pstmt, rs);
            throw new Exception(e.getMessage());
        } finally {
        }
    }

    /**
     * Получить вопрос
     * @param id
     * @return
     * @throws Exception
     */
    public static final Result getQuestion(int id) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "SELECT * FROM ips.questions WHERE id=" + id);

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        } finally {
        }
    }


    /**
     * Добавить вопрос в базу
     * @param depId
     * @param question
     * @param description
     * @param webUserId
     * @param fromDepId
     * @throws Exception
     */
    public static final void addQuestion(
            int depId,
            String question,
            String description,
            int webUserId,
            int fromDepId) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("INSERT INTO ips.questions " +
                    "(dep_id, question, description, web_user_id, from_dep_id) " +
                    "VALUES (?, ?, ?, ?, ?);");
            pstmt.setInt(1, depId);
            pstmt.setString(2, question);
            pstmt.setString(3, description);
            pstmt.setInt(4, webUserId);
            pstmt.setInt(5, fromDepId);

            pstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, pstmt, null);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, null, pstmt, pstmt, null);
        }
    }

    /**
     * Редактировать вопрос в базе
     * @param id
     * @param depId
     * @param question
     * @param description
     * @param webUserId
     * @param fromDepId
     * @throws Exception
     */
    public static final void editQuestion(
            int id,
            String question,
            String description) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();

            pstmt = conn.prepareStatement("UPDATE ips.questions " +
                    "SET question=?, description=? " +
                    "WHERE id=?;");

            pstmt.setString(1, question);
            pstmt.setString(2, description);
            pstmt.setInt(3, id);

            pstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, pstmt, null);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, null, pstmt, pstmt, null);
        }
    }

    /**
     * Удалить вопрос
     * @param id
     * @throws Exception
     */
    public static final void delQuestion(
            int id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();

            pstmt = conn.prepareStatement("DELETE FROM ips.questions WHERE id=?;");
            pstmt.setInt(1, id);

            pstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            closeAll(conn, null, pstmt, pstmt, null);
            throw new Exception(e.getMessage());
        } finally {
            closeAll(conn, null, pstmt, pstmt, null);
        }
    }

}
