/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kpg.db.sql.rb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author kopychenko
 */
public class BasicDBForID extends BasicDB {

    public BasicDBForID(DataSource ds, String table, List<Field> fieldsList) {
        super(ds, table, fieldsList);
        fieldsList.add(new Field("id", Types.INTEGER));
    }


    /**
     * Получить все записи
     * @return
     * @throws Exception
     */
    public Result getRows() throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + getTable());

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Получить запись
     * @return
     * @throws Exception
     */
    public Result getRow(int id) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + getTable() + " WHERE id=" + id);

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(ex.getMessage());
        }
    }


    /**
     * Получить запись по условию
     * @param condition
     * @return
     * @throws Exception
     */
    public Result getRows(String condition) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            System.out.println("SELECT * FROM " + getTable() + " WHERE " + condition);
            rs = stmt.executeQuery("SELECT * FROM " + getTable() + " WHERE " + condition);

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(ex.getMessage());
        }
    }




    /**
     * Вставить запись
     * @param params
     * @throws Exception
     */
    public void insert(Map<String,Object> params) throws Exception{
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String namesStr = "";
            String paramsStr = "";
            for(Iterator<String> it = (Iterator<String>) params.keySet().iterator(); it.hasNext();){
                String key = it.next();
                namesStr += key + ",";
                paramsStr += "?,";
            }

            namesStr = namesStr.substring(0, namesStr.length() - 1);
            paramsStr = paramsStr.substring(0, paramsStr.length() - 1);

            String query = "INSERT INTO " + getTable() + "(" + namesStr + ") VALUES (" + paramsStr + ");";
            System.out.println(query);

            pstmt = conn.prepareStatement(query);

            int i = 0;
            for(Iterator<String> it = (Iterator<String>) params.keySet().iterator(); it.hasNext();){
                String key = it.next();

                pstmt.setObject(++i, params.get(key), getFields().get(key).getSqlType());
            }

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Вставить запись
     * @param params
     * @throws Exception
     */
    public int insertWithReturningId(Map<String,Object> params) throws Exception{

        Connection conn = null;
        PreparedStatement pstmt = null;
        int newId = 0;

        try {
            conn = getConnection();

            String namesStr = "";
            String paramsStr = "";
            for(Iterator<String> it = (Iterator<String>) params.keySet().iterator(); it.hasNext();){
                String key = it.next();
                namesStr += key + ",";
                paramsStr += "?,";
            }

            namesStr = namesStr.substring(0, namesStr.length() - 1);
            paramsStr = paramsStr.substring(0, paramsStr.length() - 1);

            String query = "INSERT INTO " + getTable() + "(" + namesStr + ") VALUES (" + paramsStr + ") returning id;";
            System.out.println(query);

            pstmt = conn.prepareStatement(query);

            int i = 0;
            for(Iterator<String> it = (Iterator<String>) params.keySet().iterator(); it.hasNext();){
                String key = it.next();

                pstmt.setObject(++i, params.get(key), getFields().get(key).getSqlType());
            }

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                newId = rs.getInt(1);
            }

            closeAll(conn, null, pstmt, null, null);
            return newId;
        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(ex.getMessage());
        }
    }



    /**
     * Обновить строку
     * @param id
     * @param params
     * @throws Exception
     */
    public void update(int id, Map<String,Object> params) throws Exception{

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String paramsStr = "";
            for(Iterator<String> it = (Iterator<String>) params.keySet().iterator(); it.hasNext();){
                String key = it.next();
                paramsStr += key+"=?,";
            }

            paramsStr = paramsStr.substring(0, paramsStr.length() - 1);

            String query = "UPDATE " + getTable() + " SET " + paramsStr + " WHERE id=?;";
            System.out.println(query);

            pstmt = conn.prepareStatement(query);

            int i = 0;
            for(Iterator<String> it = (Iterator<String>) params.keySet().iterator(); it.hasNext();){
                String key = it.next();

                pstmt.setObject(++i, params.get(key), getFields().get(key).getSqlType());
            }

            pstmt.setInt(++i, id);

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Удалить строку
     * @param id
     * @throws Exception
     */
    public void delete(int id) throws Exception{

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String query = "DELETE FROM " + getTable() + " WHERE id=?;";
            System.out.println(query);

            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, id);

            int r = pstmt.executeUpdate();

            closeAll(conn, null, pstmt, null, null);
        } catch (Exception ex) {
            Logger.getLogger(BasicDBForID.class.getName()).log(Level.SEVERE, null, ex);
            closeAll(conn, null, pstmt, null, null);
            throw new Exception(ex.getMessage());
        }
    }

}
