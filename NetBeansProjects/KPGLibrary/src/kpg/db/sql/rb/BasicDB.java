/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kpg.db.sql.rb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author kopychenko
 */
public class BasicDB {

    /**
     * Класс описывающий поле
     */
    public static final class Field{

        String SqlName;
        int SqlType;

        public Field(String SqlName, int SqlType) {
            this.SqlName = SqlName;
            this.SqlType = SqlType;
        }

        public String getSqlName() {
            return SqlName;
        }

        public int getSqlType() {
            return SqlType;
        }

    }

    private DataSource ds;
    private String table;
    private Map<String,Field> fields = new HashMap<String,Field>();

    /**
     * Конструктор
     * @param conn
     * @param table
     * @param fieldsList
     */
    public BasicDB(DataSource ds, String table, List<Field> fieldsList) {
        this.ds = ds;
        this.table = table;

        for(Field field: fieldsList){
            this.fields.put(field.getSqlName(), field);
        }
    }

    public String getTable() {
        return table;
    }

    public Map<String, Field> getFields() {
        return fields;
    }



    /**
     * Получить соединение
     * @return
     * @throws NamingException
     * @throws SQLException
     */
    protected final Connection getConnection() throws SQLException{
        return ds.getConnection();
    }


    public final class Result {

        private Connection conn;
        private CallableStatement call;
        private PreparedStatement pstmt;
        private Statement stmt;
        private ResultSet rs;

        public Result(Connection conn, CallableStatement call, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
            this.conn = conn;
            this.call = call;
            this.pstmt = pstmt;
            this.stmt = stmt;
            this.rs = rs;
        }

        public CallableStatement getCall() {
            return call;
        }

        public Connection getConn() {
            return conn;
        }

        public PreparedStatement getPstmt() {
            return pstmt;
        }

        public ResultSet getRs() {
            return rs;
        }

        public Statement getStmt() {
            return stmt;
        }

        /**
         * Закрыть все
         */
        public void closeAll() {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (call != null) {
                try {
                    call.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    /**
     * Закрыть ресурсы
     * @param conn
     * @param stat
     */
    protected static void closeAll(Connection conn, CallableStatement call, PreparedStatement pstmt, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        if (call != null) {
            try {
                call.close();
            } catch (SQLException ex) {
                Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BasicDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

}
