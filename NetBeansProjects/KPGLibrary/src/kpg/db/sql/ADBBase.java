/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpg.db.sql;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kpg.db.sql.entrys.finaly.EntryIdValueF;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author kopychenko
 */
public class ADBBase {

    /**
     * -------------------------------------------------------------------------
     * Уровень 0
     * -------------------------------------------------------------------------
     */
    /**
     * Закрыть-освободить ресурсы JDBC
     * (Полный вариант)
     * @param conn
     * @param setAutoCommit
     * @param call
     * @param pstmt
     * @param stmt
     * @param rs
     */
    protected static void closeAll(Connection conn, boolean setAutoCommit,
            CallableStatement call,
            PreparedStatement pstmt,
            Statement stmt,
            ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (call != null) {
            try {
                call.close();
            } catch (SQLException ex) {
                Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (conn != null) {
            try {

                if (setAutoCommit) {
                    conn.commit();
                    System.out.println("TRANSACTION COMMIT");
                } else {
                    //..conn.rollback(); НЕЛЬЗЯ ТАК ПОДУМАЛ Я
                    //..System.out.println("TRANSACTION ROLLBACK");
                }

                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Закрыть-освободить ресурсы JDBC - с автоподтверждением транзакции
     * (ленивый вариант)
     * @param conn
     * @param stat
     */
    protected static void closeAll(Connection conn, CallableStatement call,
            PreparedStatement pstmt, Statement stmt, ResultSet rs) {
        closeAll(conn, true, call, pstmt, stmt, rs);
    }

    /**
     * Класс описывающий поле
     */
    public static final class Par {

        private int SqlType;
        private Object value;

        public Par(int SqlType, Object value) {
            this.SqlType = SqlType;
            this.value = value;
        }

        public int getSqlType() {
            return SqlType;
        }

        public Object getValue() {
            return value;
        }
    }

    /**
     * Класс результата запроса
     */
    public static final class Result {

        private Connection conn;
        private CallableStatement call;
        private PreparedStatement pstmt;
        private Statement stmt;
        private ResultSet rs;

        public Result(Connection conn, CallableStatement call,
                PreparedStatement pstmt, Statement stmt, ResultSet rs) {
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
         * Закрыть-освободить ресурсы JDBC
         * с указанием действий для транзакций
         */
        public void closeAll(boolean setAutoCommit) {
            ADBBase.closeAll(conn, setAutoCommit, call, pstmt, stmt, rs);
        }

        /**
         * Закрыть-освободить ресурсы JDBC с автоподтверждением транзакции
         * (ленивый вариант)
         */
        public void closeAll() {
            ADBBase.closeAll(conn, call, pstmt, stmt, rs);
        }
    }

    /**
     * Закрыть-освободить ресурсы JDBC результата запроса
     * (Полный вариант)
     * @param result
     * @param setAutoCommit
     */
    public static void closeResult(Result result, boolean setAutoCommit) {
        if (result != null) {
            result.closeAll(setAutoCommit);
        }
    }

    /**
     * акрыть-освободить ресурсы JDBC результата запроса
     * с автоподтверждением транзакции
     * (Ленивый вариант)
     * @param result
     */
    public static void closeResult(Result result) {
        closeResult(result, true);
    }

    //--------------------------------------------------------------------------
    /**
     * L0
     * Выполнить SQL запрос (автокоммит ненужен, так как возвращается result)
     * @param query
     * @return
     * @throws Exception
     */
    public static Result executeSQLQuery(Connection conn, String query)
            throws Exception {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);

            return new Result(conn, null, null, stmt, rs);

        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, null, stmt, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * L0
     * Выполнить SQL запрос одной строкой
     * с указанием действий для подтверждения транзакций
     * (полный вариант)
     * @param conn
     * @param setAutoCommit
     * @param query
     * @throws Exception
     */
    public static void executeSQL(Connection conn,
            boolean setAutoCommit, String query)
            throws Exception {

        Statement stmt = null;

        try {
            conn.setAutoCommit(setAutoCommit);
            stmt = conn.createStatement();
            stmt.execute(query);

            closeAll(conn, setAutoCommit, null, null, stmt, null);
        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, setAutoCommit, null, null, stmt, null);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * L0
     * Выполнить SQL запрос одной строкой
     * с автоподтверждением транзакции
     * (Ленивый вариант)
     * @param conn
     * @param query
     * @throws Exception
     */
    public static void executeSQL(Connection conn, String query)
            throws Exception {
        executeSQL(conn, true, query);
    }

    /**
     * L0
     * Выполнить SQL запрос (автокоммит ненужен, так как возвращается result
     * который ничего не вернет если подтвердить и закрыть транзакцтию)
     * @param conn
     * @param query
     * @param fields
     * @return
     * @throws Exception
     */
    public static Result executeSQLQuery(Connection conn, String query,
            Par[] fields)
            throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(query);

            for (int i = 0; i < fields.length; i++) {
                pstmt.setObject(i + 1, fields[i].value, fields[i].SqlType);
            }

            rs = pstmt.executeQuery();

            return new Result(conn, null, pstmt, null, rs);

        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, null, pstmt, null, rs);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Выполнить SQL запрос с указанием списка параметров
     * с автоподтверждением транзакции
     * (ленивый вариант)
     * @param conn
     * @param query
     * @param fields
     * @throws Exception
     */
    public static void executeSQL(Connection conn, String query, Par[] fields)
            throws Exception {
        executeSQL(conn, true, query, fields);
    }

    /**
     * Выполнить SQL запрос с указанием списка параметров
     * и контролем транзакции
     * (основной вариант)
     * @param conn
     * @param query
     * @param setAutoCommit
     * @param fields
     * @throws Exception
     */
    public static void executeSQL(Connection conn, boolean setAutoCommit,
            String query, Par[] fields)
            throws Exception {

        PreparedStatement pstmt = null;

        try {
            conn.setAutoCommit(setAutoCommit);
            pstmt = conn.prepareStatement(query);

            for (int i = 0; i < fields.length; i++) {
                pstmt.setObject(i + 1, fields[i].value, fields[i].SqlType);
            }

            pstmt.execute();

            closeAll(conn, setAutoCommit, null, pstmt, null, null);
        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeAll(conn, setAutoCommit, null, pstmt, null, null);
            throw new Exception(e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Выполнить запрос и вернуть int
     * @param conn
     * @param query
     * @return
     * @throws Exception
     */
    public static int executeSQLRetInt(Connection conn, String query)
            throws Exception {
        return executeSQLRetInt(conn, true, query);
    }

    /**
     * Выполнить запрос и вернуть int
     * @param conn
     * @param setAutoCommit
     * @param query
     * @return
     * @throws Exception
     */
    public static int executeSQLRetInt(Connection conn, boolean setAutoCommit,
            String query) throws Exception {
        Result result = null;
        try {
            result = executeSQLQuery(conn, query);
            result.getRs().next();
            int i = result.getRs().getInt(1);
            result.closeAll(setAutoCommit);
            return i;
        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            result.closeAll();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Выполнить запрос и вернуть int
     * @param conn
     * @param query
     * @param fields
     * @return
     * @throws Exception
     */
    public static int executeSQLRetInt(Connection conn,
            String query, Par[] fields) throws Exception {
        return executeSQLRetInt(conn, true, query, fields);
    }

    /**
     * Выполнить запрос и вернуть int
     * @param conn
     * @param setAutoCommit
     * @param query
     * @param fields
     * @return
     * @throws Exception
     */
    public static int executeSQLRetInt(Connection conn, boolean setAutoCommit,
            String query, Par[] fields) throws Exception {
        Result result = null;
        try {
            result = executeSQLQuery(conn, query, fields);
            result.getRs().next();
            int i = result.getRs().getInt(1);
            result.closeAll(setAutoCommit);
            return i;
        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            result.closeAll();
            throw new Exception(e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Возвращаемый параметр
     */
    public static final class OutPar {

        private String fieldName;
        private Object value;

        public OutPar(String fieldName, Object value) {
            this.fieldName = fieldName;
            this.value = value;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    /**
     * Поиск параметра по имени
     * @param outPars
     * @param fieldName
     * @return
     */
    public static OutPar findOutPar(OutPar[] outPars, String fieldName) {

        OutPar par = null;

        for (OutPar p : outPars) {
            if (p.getFieldName().equals(fieldName)) {
                par = p;
                break;
            }
        }

        return par;
    }

    /**
     * Класс однострочного результата
     */
    public static final class OutPars {

        private OutPar[] pars;

        public OutPars(OutPar[] pars) {
            this.pars = pars;
        }

        /**
         * Получить объект значение по имени
         * @param fieldName
         * @return
         */
        public Object getObject(String fieldName) {
            return findOutPar(pars, fieldName).getValue();
        }

        /**
         * Получить строковое значение
         * @param fieldName
         * @return
         */
        public String getString(String fieldName) {
            return (String) getObject(fieldName);
        }

        /**
         * Полусить числовой тип
         * @param fieldName
         * @return
         */
        public Number getNumber(String fieldName) {
            return (Number) getObject(fieldName);
        }

        /**
         * Получить целочисленое значение
         * @param fieldName
         * @return
         */
        public int getInt(String fieldName) {
            return (Integer) getObject(fieldName);
        }

        /**
         * Получить вещественное значение
         * @param fieldName
         * @return
         */
        public float getFloat(String fieldName) {
            return (Float) getObject(fieldName);
        }

        /**
         * Получить вещетвенное значение двойной точности
         * @param fieldName
         * @return
         */
        public double getDouble(String fieldName) {
            return (Double) getObject(fieldName);
        }

        /**
         * Получить десятичное значение
         * @param fieldName
         * @return
         */
        public BigDecimal getBigDecimal(String fieldName) {
            return (BigDecimal) getObject(fieldName);
        }

        /**
         * Получит логическое значение
         * @param fieldName
         * @return
         */
        public boolean getBoolean(String fieldName) {
            return (Boolean) getObject(fieldName);
        }

        /**
         * Получить значение содержащее дату
         * @param fieldName
         * @return
         */
        public Date getDate(String fieldName) {
            return (Date) getObject(fieldName);
        }
    }

    /**
     * Выполнить запрос и получить строку параметров
     * @param conn
     * @param query
     * @return
     * @throws Exception
     */
    public static OutPars executeSQLGetPars(Connection conn,
            String query)
            throws Exception {
        return executeSQLGetPars(conn, true, query);
    }

    /**
     * Выполнить запрос и получить строку параметров
     * @param conn
     * @param setAutoCommit
     * @param query
     * @return
     * @throws Exception
     */
    public static OutPars executeSQLGetPars(Connection conn,
            boolean setAutoCommit, String query)
            throws Exception {

        OutPars pars = null;
        Result result = null;
        try {
            result = executeSQLQuery(conn, query);

            ResultSet rs = result.getRs();
            OutPar[] ppp = new OutPar[rs.getMetaData().getColumnCount()];

            if (rs.next()) {
                for (int i = 0; i < ppp.length; i++) {
                    ppp[i] = new OutPar(
                            rs.getMetaData().getColumnName(i + 1),
                            rs.getObject(i + 1));
                }

                pars = new OutPars(ppp);
            }

            closeResult(result, setAutoCommit);
        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeResult(result, false);
            throw new Exception(e.getMessage());
        } finally {
        }

        return pars;
    }

    /**
     * Выполнить запрос и получить строку параметров
     * @param conn
     * @param query
     * @param setAutoCommit
     * @param fields
     * @throws Exception
     */
    public static OutPars executeSQLGetPars(Connection conn,
            String query, Par[] fields)
            throws Exception {
        return executeSQLGetPars(conn, true, query, fields);
    }

    /**
     * Выполнить запрос и получить строку параметров
     * @param conn
     * @param query
     * @param setAutoCommit
     * @param fields
     * @throws Exception
     */
    public static OutPars executeSQLGetPars(Connection conn,
            boolean setAutoCommit,
            String query, Par[] fields)
            throws Exception {

        OutPars pars = null;
        Result result = null;
        try {
            result = executeSQLQuery(conn, query, fields);

            ResultSet rs = result.getRs();
            OutPar[] ppp = new OutPar[rs.getMetaData().getColumnCount()];

            if (rs.next()) {
                for (int i = 0; i < ppp.length; i++) {
                    ppp[i] = new OutPar(
                            rs.getMetaData().getColumnName(i + 1),
                            rs.getObject(i + 1));
                }

                pars = new OutPars(ppp);
            }

            closeResult(result, setAutoCommit);
        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeResult(result, false);
            throw new Exception(e.getMessage());
        } finally {
        }

        return pars;
    }

    // -------------------------------------------------------------------------
    /**
     * Мапированный параметр fname=>1:VARCHAR
     */
    private static final class InMapPar {

        public static final String VARCHAR = "VARCHAR";
        public static final String BOOLEAN = "BOOLEAN";
        public static final String INTEGER = "INTEGER";
        public static final String NUMERIC = "NUMERIC";
        public static final String FLOAT = "FLOAT";
        public static final String DOUBLE = "DOUBLE";
        public static final String DATE = "DATE";
        public static final String TIME = "TIME";
        public static final String TIMESTAMP = "TIMESTAMP";
        public static final String PAR_PARSER = "=>";
        public static final String TYPE_PARSER = ":";
        private String objField;
        private int sqlId;
        private String sqlType;

        public InMapPar(String sPar) {
            String[] p = sPar.split(PAR_PARSER);

            this.objField = p[0];

            String[] p2 = p[1].split(TYPE_PARSER);
            this.sqlId = Integer.parseInt(p2[0]);
            this.sqlType = p2[1];

        }

        public String getObjField() {
            return objField;
        }

        public int getSqlId() {
            return sqlId;
        }

        /**
         * Получить тип
         * @return
         */
        public int getSqlType() {

            if (sqlType.equals(VARCHAR)) {
                return Types.VARCHAR;
            } else if (sqlType.equals(BOOLEAN)) {
                return Types.BOOLEAN;
            } else if (sqlType.equals(INTEGER)) {
                return Types.INTEGER;
            } else if (sqlType.equals(NUMERIC)) {
                return Types.NUMERIC;
            } else if (sqlType.equals(FLOAT)) {
                return Types.FLOAT;
            } else if (sqlType.equals(DOUBLE)) {
                return Types.DOUBLE;
            } else if (sqlType.equals(DATE)) {
                return Types.DATE;
            } else if (sqlType.equals(TIME)) {
                return Types.TIME;
            } else if (sqlType.equals(TIMESTAMP)) {
                return Types.TIMESTAMP;
            }
            return Types.VARCHAR;
        }
    }

    /**
     * Выходной параметр
     */
    private static final class OutMapPar {

        public static final String PAR_PARSER = "<=";
        private String sqlFieldName;
        private String objFieldName;

        public OutMapPar(String sPar) {
            String[] p = sPar.split(PAR_PARSER);
            this.objFieldName = p[0];
            this.sqlFieldName = p[1];
        }

        public String getObjFieldName() {
            return objFieldName;
        }

        public String getSqlFieldName() {
            return sqlFieldName;
        }
    }
    public static final String PARS_PARSER = ",";

    /**
     * Разобрать на параметры
     * @param s
     * @return
     */
    private static InMapPar[] parseInMapParams(String s) {
        if (s != null) {
            String[] p0 = s.split(PARS_PARSER);
            InMapPar[] mapPar = new InMapPar[p0.length];
            int i = 0;
            for (String si : p0) {
                mapPar[i++] = new InMapPar(si);
            }
            return mapPar;
        } else {
            return null;
        }
    }

    /**
     * Разобрать на параметры
     * @param s
     * @return
     */
    private static OutMapPar[] parseOutMapParams(String s) {
        if (s != null) {
            String[] p0 = s.split(PARS_PARSER);
            OutMapPar[] mapPar = new OutMapPar[p0.length];
            int i = 0;
            for (String si : p0) {
                mapPar[i++] = new OutMapPar(si);
            }
            return mapPar;
        } else {
            return null;
        }
    }

    /**
     * Выполнить запрос с мапированием данных запроса
     * и результата на объект
     * @param conn
     * @param setAutoCommit
     * @param query
     * @param inObj
     * @param inDeclare
     * @param outObj
     * @param outDeclare
     * @throws Exception
     */
    public static void executeSQLInGetObject(Connection conn,
            boolean setAutoCommit, String query,
            Object inObj, String inDeclare,
            Object outObj, String outDeclare) throws Exception {

        InMapPar[] inPars = parseInMapParams(inDeclare);
        OutMapPar[] outPars = parseOutMapParams(outDeclare);

        OutPars outPars2 = null;

        // Входные параметры
        if (inPars != null && inPars.length > 0) { // Если входные параметры имеются
            Par[] pars = new Par[inPars.length];
            for (InMapPar mP : inPars) {
                pars[mP.getSqlId() - 1] =
                        new Par(mP.getSqlType(), PropertyUtils.getSimpleProperty(inObj, mP.getObjField()));
            }


            if (outPars != null) {
                // 1
                outPars2 = executeSQLGetPars(conn, setAutoCommit, query, pars);
            } else {
                // 2
                executeSQL(conn, setAutoCommit, query, pars);
            }

        } else {
            // 3
            outPars2 = executeSQLGetPars(conn, setAutoCommit, query);
        }

        // Выходные параметры
        if (outPars != null && outPars.length > 0) {
            for (OutMapPar p : outPars) {
                PropertyUtils.setSimpleProperty(outObj, p.getObjFieldName(), outPars2.getObject(p.getSqlFieldName()));
            }
        }
    }

    /**
     * Выполнить запрос с мапированием данных запроса
     * @param conn
     * @param query
     * @param inObj
     * @param inDeclare
     * @param outObj
     * @param outDeclare
     * @throws Exception
     */
    public static void executeSQLInGetObject(Connection conn,
            String query,
            Object inObj, String inDeclare,
            Object outObj, String outDeclare) throws Exception {
        executeSQLInGetObject(conn, true, query, inObj, inDeclare, outObj, outDeclare);
    }

    /**
     * Выполнить запрос с мапированием данных запроса
     * @param conn
     * @param query
     * @param inObj
     * @param inDeclare
     * @throws Exception
     */
    public static void executeSQLInObject(Connection conn,
            String query,
            Object inObj, String inDeclare) throws Exception {

        executeSQLInGetObject(conn, true, query,
                inObj, inDeclare, null, null);

    }

    /**
     * Выполнить запрос с мапированием данных запроса
     * @param conn
     * @param query
     * @param outObj
     * @param outDeclare
     * @throws Exception
     */
    public static void executeSQLGetObject(Connection conn,
            String query,
            Object outObj, String outDeclare) throws Exception {

        executeSQLInGetObject(conn, true, query,
                null, null, outObj, outDeclare);

    }

    /**
     * Выполнить запрос с мапированием данных запроса
     * @param conn
     * @param setAutoCommit
     * @param query
     * @param inObj
     * @param inDeclare
     * @throws Exception
     */
    public static void executeSQLInObject(Connection conn,
            boolean setAutoCommit, String query,
            Object inObj, String inDeclare) throws Exception {

        executeSQLInGetObject(conn, setAutoCommit, query,
                inObj, inDeclare, null, null);

    }

    /**
     * Выполнить запрос с мапированием данных запроса
     * @param conn
     * @param setAutoCommit
     * @param query
     * @param outObj
     * @param outDeclare
     * @throws Exception
     */
    public static void executeSQLGetObject(Connection conn,
            boolean setAutoCommit, String query,
            Object outObj, String outDeclare) throws Exception {

        executeSQLInGetObject(conn, setAutoCommit, query,
                null, null, outObj, outDeclare);
    }

    //--------------------------------------------------------------------------
    // Для справочников
    /**
     * Получить вхождения по запросу
     * @param conn
     * @param query
     * @param idField
     * @param valueField
     * @return
     * @throws Exception
     */
    public static List<EntryIdValueF> getEntrysIDValue(
            Connection conn,
            String query,
            String idField,
            String valueField) throws Exception {

        List<EntryIdValueF> list = new ArrayList<EntryIdValueF>();
        Result result = null;
        try {

            result = executeSQLQuery(conn, query);
            ResultSet rs = result.getRs();

            while (rs.next()) {
                list.add(new EntryIdValueF(
                        rs.getInt(idField), rs.getString(valueField)));
            }

            result.closeAll();

        } catch (Exception e) {
            Logger.getLogger(ADBBase.class.getName()).log(Level.SEVERE, null, e);
            closeResult(result, false);
            throw new Exception(e.getMessage());
        }
        return list;
    }
}
