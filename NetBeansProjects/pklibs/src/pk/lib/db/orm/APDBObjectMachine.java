/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import pk.lib.db.orm.annotacions.DBField;
import pk.lib.db.orm.annotacions.DBFieldPK;
import pk.lib.db.orm.annotacions.DBFieldPK.PKType;
import pk.lib.db.orm.annotacions.DBTable;
import org.apache.commons.beanutils.PropertyUtils;
import pk.lib.db.orm.annotacions.DBSQuery;
import pk.lib.db.orm.annotacions.SQuery;

/**
 *
 * @author traveler
 */
public abstract class APDBObjectMachine implements IOM{

    protected final static String $TABLE = "$table";
    protected final static String $CONDITION = "$condition";
    protected final static String $FIELDS = "$fields";
    protected final static String $VALUES = "$values";
    protected final static String $KEYS = "$keys";

    /**
     * Получить имя таблици
     * @param o
     * @return
     * @throws Exception
     */
    protected static String getDBTableName(Object o) throws Exception {
        return getDBTableName(o.getClass());
    }

    /**
     * Получить имя таблици
     * @param cs
     * @return
     * @throws Exception
     */
    protected static String getDBTableName(Class cs) throws Exception {
        DBTable dbTable = ((DBTable) cs.getAnnotation(DBTable.class));
        if (dbTable != null) {
            return dbTable.sqlName();
        } else {
            throw new Exception("Аннотация DBTable не указана");
        }
    }

    /**
     * Описание поля
     */
    protected static class SetField {

        private int position;
        private boolean pk;
        private PKType pkType;
        private String objFieldName;
        private String sqlFieldName;
        private Object sqlFieldValue;
        private int sqlFieldType;
        private boolean selected = false;
        private boolean inserted = false;
        private boolean updated = false;

        public SetField(PKType pkType, String objFieldName, String sqlFieldName, Object sqlFieldValue, int sqlFieldType, boolean inserted, boolean updated) {
            this.pk = true;
            this.pkType = pkType;
            this.objFieldName = objFieldName;
            this.sqlFieldName = sqlFieldName;
            this.sqlFieldValue = sqlFieldValue;
            this.sqlFieldType = sqlFieldType;
            this.inserted = inserted;
            this.updated = updated;
        }

        public SetField(String objFieldName, String sqlFieldName, Object sqlFieldValue, int sqlFieldType, boolean inserted, boolean updated) {
            this.pk = false;
            this.objFieldName = objFieldName;
            this.sqlFieldName = sqlFieldName;
            this.sqlFieldValue = sqlFieldValue;
            this.sqlFieldType = sqlFieldType;
            this.inserted = inserted;
            this.updated = updated;
        }

        public String getObjFieldName() {
            return objFieldName;
        }

        public boolean isPk() {
            return pk;
        }

        public PKType getPkType() {
            return pkType;
        }

        public String getSqlFieldName() {
            return sqlFieldName;
        }

        public int getSqlFieldType() {
            return sqlFieldType;
        }

        public Object getSqlFieldValue() {
            return sqlFieldValue;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public boolean isInserted() {
            return inserted;
        }

        public void setInserted(boolean inserted) {
            this.inserted = inserted;
        }

        public boolean isUpdated() {
            return updated;
        }

        public void setUpdated(boolean updated) {
            this.updated = updated;
        }
    }

    /**
     * Получить поля для заполнения
     * @param o
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    protected static List<SetField> getSetFields(Object o)
            throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException,
            NoSuchMethodException {
        Class cs = o.getClass();
        List<SetField> fieldsSets = new ArrayList<SetField>();

        for (Field field : cs.getDeclaredFields()) {
            if (field.getAnnotations().length > 0) {
                DBField dbField = field.getAnnotation(DBField.class);
                DBFieldPK dbFieldPK = field.getAnnotation(DBFieldPK.class);

                if (dbField != null && dbFieldPK == null) {
                    fieldsSets.add(new SetField(field.getName(), dbField.sqlName(),
                            PropertyUtils.getSimpleProperty(o, field.getName()),
                            dbField.sqlType(), dbField.inserted(), dbField.updated()));
                } else if (dbField != null && dbFieldPK != null) {
                    fieldsSets.add(new SetField(dbFieldPK.getPKType(), field.getName(), dbField.sqlName(),
                            PropertyUtils.getSimpleProperty(o, field.getName()),
                            dbField.sqlType(), dbField.inserted(), dbField.updated()));
                }
            }
        }
        return fieldsSets;
    }

    /**
     * Удалить последний символ
     * @param s
     * @return
     */
    protected static String deleteLastChar(String s) {
        return deleteLastChar(s, 1);
    }

    /**
     * Удалить последние 4 символа
     * @param s
     * @return
     */
    protected static String deleteLast4Char(String s) {
        return deleteLastChar(s, 4);
    }

    /**
     * Удалить последнии символы
     * @param s
     * @return
     */
    protected static String deleteLastChar(String s, int i) {
        return s.substring(0, s.length() - i);
    }

    /**
     * Класс описывающий параметр условия выборки запроса
     */
    public static class CondPar {

        private int i;
        private Object value;

        public CondPar(int i, Object value) {
            this.i = i;
            this.value = value;
        }

        public int getI() {
            return i;
        }

        public Object getValue() {
            return value;
        }
    }

    /**
     * Мапирование селекта
     */
    protected static class SQFieldMap {

        private String sqlName;
        private int sqlType;
        private String objName;

        public SQFieldMap(String sqlName, int sqlType, String objName) {
            this.sqlName = sqlName;
            this.sqlType = sqlType;
            this.objName = objName;
        }

        public String getObjName() {
            return objName;
        }

        public String getSqlName() {
            return sqlName;
        }

        public int getSqlType() {
            return sqlType;
        }
    }

    /**
     * Получить список мапирования для выборки
     * @param cs
     * @return
     */
    protected static List<SQFieldMap> getSQFieldMaps(Class cs) {

        List<SQFieldMap> list = new ArrayList<SQFieldMap>();

        for (Field field : cs.getDeclaredFields()) {
            if (field.getAnnotations().length > 0) {
                DBField dbField = field.getAnnotation(DBField.class);
                if (dbField != null) {
                    list.add(new SQFieldMap(dbField.sqlName(), dbField.sqlType(), field.getName()));
                }
            }
        }
        return list;
    }

    /**
     * Результат поток
     */
    public static class ObjectsSet {

        private Connection conn;
        private PreparedStatement ps;
        private ResultSet rs;
        private List<SQFieldMap> sQFieldMap;
        private Class cls;

        public ObjectsSet(Connection conn, PreparedStatement ps, ResultSet rs, Class cls) {
            this.conn = conn;
            this.ps = ps;
            this.rs = rs;
            this.cls = cls;
            this.sQFieldMap = getSQFieldMaps(cls);
        }

        /**
         * Закрыть все кроме соединения
         * @throws SQLException
         */
        public void closeConnFalse() throws SQLException {
            close(false);
        }

        /**
         * Закрыть все и автоматом соединение
         * @throws SQLException
         */
        public void close() throws SQLException {
            close(true);
        }

        /**
         * Закрыть
         * @param closeConnection
         * @throws SQLException
         */
        public void close(boolean closeConnection) throws SQLException {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (conn != null && closeConnection) {
                conn.close();
            }

        }

        /**
         * Следующий
         * @return
         * @throws SQLException
         */
        public boolean next() throws SQLException {
            return rs.next();
        }

        /**
         * Получить объект из потока
         * @param <T>
         * @return
         * @throws SQLException
         */
        public <T> T load() throws SQLException, InstantiationException,
                IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Object o = cls.newInstance();
            for (SQFieldMap map : sQFieldMap) {
                PropertyUtils.setSimpleProperty(o, map.getObjName(), rs.getObject(map.getSqlName()));
            }
            return (T) o;
        }
    }

    /**
     * Найти запрос по имени
     * @param cs
     * @param qName
     * @return
     * @throws Exception
     */
    protected static SQuery getSQuery(Class cs, String qName) throws Exception {

        DBSQuery dbSQuery = ((DBSQuery) cs.getAnnotation(DBSQuery.class));
        if (dbSQuery != null) {
            SQuery[] squerys = dbSQuery.querys();

            if (squerys == null || squerys.length == 0) {
                throw new Exception("Список SQuery[] пустой");
            }

            SQuery ssq = null;
            for (SQuery sq : squerys) {
                if (sq.qname().equals(qName)) {
                    ssq = sq;
                    break;
                }
            }

            if (ssq == null) {
                throw new Exception("Запрос под именем " + qName + " не найден");
            }

            return ssq;

        } else {
            throw new Exception("Аннотация DBSQuery не указана");
        }
    }

    /**
     * Заполнить объект
     * @param o
     * @param rs
     * @param sQFieldMap
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SQLException
     */
    protected void fillObj(Object o, ResultSet rs, List<SQFieldMap> sQFieldMap)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
        for (SQFieldMap map : sQFieldMap) {
            PropertyUtils.setSimpleProperty(o, map.getObjName(), rs.getObject(map.getSqlName()));
        }
    }

    /**
     * Получить шаблон вставки
     * @return
     */
    abstract protected String getInsetTemplite();

    /**
     * Создать запись
     * @param ds
     * @param o
     * @throws Exception 
     */
    @Override
    public void insert(DataSource ds, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        Connection conn = null;
        try {
            conn = ds.getConnection();
            insert(conn, o);
            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!rezult) {
                throw new Exception("Ошибка работы insert:\n" + errorText);
            }
        }
    }

    /**
     * Создать запись
     * @param conn
     * @param o
     * @throws Exception
     */
    public void insert(Connection conn, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            String t = getDBTableName(o);
            List<SetField> setFields = getSetFields(o);
            String f = "";
            String v = "";
            String k = "";

            int posicion = 0;
            for (SetField sf : setFields) {
                if (sf.isPk()) {
                    // Обработка ключевого поля
                    if (sf.getPkType() == PKType.APP_SET) {
                        sf.setSelected(true);
                        sf.setPosition(++posicion);
                        f += sf.getSqlFieldName() + ",";
                        v += "?,";
                    }

                    k += sf.getSqlFieldName() + ",";

                } else if (sf.isInserted()) {
                    // Обработка простого поля
                    f += sf.getSqlFieldName() + ",";
                    v += "?,";
                    sf.setSelected(true);
                    sf.setPosition(++posicion);
                }
            }
            String insert = getInsetTemplite().replace($TABLE, t).replace($FIELDS, deleteLastChar(f)).replace($VALUES, deleteLastChar(v)).replace($KEYS, deleteLastChar(k));
            System.out.println(insert);
            ps = conn.prepareStatement(insert);

            for (SetField sf : setFields) {
                if (sf.isSelected()) {
                    ps.setObject(sf.getPosition(), sf.getSqlFieldValue(), sf.getSqlFieldType());
                }
            }
            // Выполняем запрос
            rs = ps.executeQuery();
            // Теперь работаем по ключевым полям
            if (rs.next()) {
                for (SetField sf : setFields) {
                    if (sf.isPk() && sf.getPkType() == PKType.AUTOINDENTING) {
                        PropertyUtils.setSimpleProperty(o, sf.getObjFieldName(), rs.getObject(sf.getSqlFieldName()));
                    }
                }
            }

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!rezult) {
                throw new Exception("Ошибка работы insert:\n" + errorText);
            }
        }
    }
    /***************************************************************************
     * *************************************************************************
     * *************************************************************************
     * *************************************************************************
     */
    /**
     * Запрос обновления записи
     */
    private final static String UPDATE_TEMPLITE = "UPDATE " + $TABLE + " SET " + $FIELDS + " WHERE " + $CONDITION + ";";

    /**
     * Получить шаблон обновления
     * @return
     */
    protected String getUpdateTemplite() {
        return UPDATE_TEMPLITE;
    }

    /**
     * Обновить
     * @param ds
     * @param o
     * @throws Exception 
     */
    @Override
    public void update(DataSource ds, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        Connection conn = null;
        try {
            conn = ds.getConnection();
            update(conn, o);
            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!rezult) {
                throw new Exception("Ошибка работы update:\n" + errorText);
            }
        }
    }

    /**
     * Обновить
     * @param conn
     * @param o
     * @throws Exception
     */
    public void update(Connection conn, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            String t = getDBTableName(o);
            List<SetField> setFields = getSetFields(o);
            String f = "";
            String c = "";
            int posicion = 0;
            for (SetField sf : setFields) {
                if (!sf.isPk() && sf.isUpdated()) {
                    // Обработка простого поля
                    f += sf.getSqlFieldName() + "=?,";
                    sf.setSelected(true);
                    sf.setPosition(++posicion);
                }
            }

            for (SetField sf : setFields) {
                if (sf.isPk()) {
                    c += sf.getSqlFieldName() + "=? AND ";
                    sf.setSelected(true);
                    sf.setPosition(++posicion);
                }
            }

            String update = getUpdateTemplite().replace($TABLE, t).replace($FIELDS, deleteLastChar(f)).replace($CONDITION, deleteLast4Char(c));
            System.out.println(update);

            ps = conn.prepareStatement(update);


            // Обрабатываем поля
            for (SetField sf : setFields) {
                if (sf.isSelected()) {
                    ps.setObject(sf.getPosition(), sf.getSqlFieldValue(), sf.getSqlFieldType());
                }
            }

            // Выполняем запрос
            ps.execute();


            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!rezult) {
                throw new Exception("Ошибка работы update:\n" + errorText);
            }
        }
    }
    /**
     * Запрос обновления записи
     */
    /**
     * Запрос обновления записи
     */
    private final static String DELETE_TEMPLITE = "DELETE FROM " + $TABLE + " WHERE " + $CONDITION + ";";

    /**
     * Получить шаблон удаления
     * @return
     */
    protected String getDeleteTemplite() {
        return DELETE_TEMPLITE;
    }

    /**
     * Удалить запись
     * @param ds
     * @param o
     * @throws Exception 
     */
    @Override
    public void delete(DataSource ds, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        Connection conn = null;
        try {
            conn = ds.getConnection();
            delete(conn, o);
            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!rezult) {
                throw new Exception("Ошибка работы delete:\n" + errorText);
            }
        }
    }

    /**
     * Удалить запись
     * @param conn
     * @param o
     * @throws Exception
     */
    public void delete(Connection conn, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);

            String t = getDBTableName(o);
            List<SetField> setFields = getSetFields(o);
            String c = "";
            int posicion = 0;
            for (SetField sf : setFields) {
                if (sf.isPk()) {
                    c += sf.getSqlFieldName() + "=? AND ";
                    sf.setSelected(true);
                    sf.setPosition(++posicion);
                }
            }

            String delete = getDeleteTemplite().replace($TABLE, t).replace($CONDITION, deleteLast4Char(c));
            System.out.println(delete);

            ps = conn.prepareStatement(delete);

            // Обрабатываем ключи
            for (SetField sf : setFields) {
                if (sf.isPk()) {
                    ps.setObject(sf.getPosition(), sf.getSqlFieldValue(), sf.getSqlFieldType());
                }
            }

            // Выполняем запрос
            ps.execute();

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!rezult) {
                throw new Exception("Ошибка работы delete:\n" + errorText);
            }
        }
    }
    /**
     * Запрос обновления записи
     */
    private final static String LOAD_TEMPLITE = "SELECT * FROM " + $TABLE + " WHERE " + $CONDITION + ";";

    /**
     * Получить шаблон удаления
     * @return
     */
    protected String getLoadTemplite() {
        return LOAD_TEMPLITE;
    }

    /**
     * Загрузить объект только указав ключевые поля
     * @param ds
     * @param o
     * @throws Exception 
     */
    @Override
    public void load(DataSource ds, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        Connection conn = null;
        try {
            conn = ds.getConnection();
            load(conn, o);
            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!rezult) {
                throw new Exception("Ошибка работы load:\n" + errorText);
            }
        }
    }

    /**
     * Загрузить объект, указав только ключевые поля
     * @param conn
     * @param o
     * @throws Exception
     */
    public void load(Connection conn, Object o) throws Exception {
        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            String t = getDBTableName(o);
            List<SetField> setFields = getSetFields(o);

            String c = "";
            int posicion = 0;
            for (SetField sf : setFields) {
                if (sf.isPk()) {
                    c += sf.getSqlFieldName() + "=? AND ";
                    sf.setSelected(true);
                    sf.setPosition(++posicion);
                }
            }

            String load = getLoadTemplite().replace($TABLE, t).replace($CONDITION, deleteLast4Char(c));
            System.out.println(load);

            ps = conn.prepareStatement(load);

            // Обрабатываем ключи
            for (SetField sf : setFields) {
                if (sf.isPk()) {
                    ps.setObject(sf.getPosition(), sf.getSqlFieldValue(), sf.getSqlFieldType());
                }
            }

            rs = ps.executeQuery();


            List<SQFieldMap> sQFieldMap = getSQFieldMaps(o.getClass());

            if (rs.next()) {
                fillObj(o, rs, sQFieldMap);
            }

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!rezult) {
                throw new Exception("Ошибка работы load:\n" + errorText);
            }
        }
    }

    /***************************************************************************
     * *************************************************************************
     * *************************************************************************
     * *************************************************************************
     */
    /**
     * Выбрать список объектов
     * @param <T>
     * @param ds
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception 
     */
    @Override
    public <T> List<T> select(DataSource ds, Class a, String selectName, CondPar[] cpars) throws Exception {
        boolean rezult = false;
        String errorText = "";
        Connection conn = null;
        List<T> list = null;
        try {
            conn = ds.getConnection();

            list = select(conn, a, selectName, cpars);

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }


            if (!rezult) {
                throw new Exception("Ошибка работы select:\n" + errorText);
            }
        }

        return list;
    }

    /**
     * Выбрать список объектов
     * @param <T>
     * @param conn
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception
     */
    public <T> List<T> select(Connection conn, Class a, String selectName, CondPar[] cpars) throws Exception {

        List<T> list = new ArrayList<T>();

        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);


            String table = getDBTableName(a);
            System.out.println(table);

            SQuery sq = getSQuery(a, selectName);
            System.out.println("QUERY:" + sq.query());
            String select = sq.query().replace($TABLE, table);
            System.out.println(">>QUERY:" + select);

            ps = conn.prepareStatement(select);


            int[] qtypes = sq.qtypes();

            // Отлавливаем ошибку
            if (qtypes.length > 0 && (cpars == null || qtypes.length != cpars.length)) {
                throw new Exception("Число параметров выборки не совпадает");
            }


            if (qtypes.length > 0 && cpars != null && cpars.length > 0) {
                for (CondPar cp : cpars) {
                    int i = cp.getI();
                    ps.setObject(i, cp.getValue(), qtypes[i - 1]);
                }
            }

            // Выполняем запрос
            rs = ps.executeQuery();

            List<SQFieldMap> sQFieldMap = getSQFieldMaps(a);

            while (rs.next()) {
                Object o = a.newInstance();
                fillObj(o, rs, sQFieldMap);
                list.add((T) o);
            }

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!rezult) {
                throw new Exception("Ошибка работы select:\n" + errorText);
            }
        }

        return list;
    }

    
    @Override
    public ObjectsSet selectObjectsSet(DataSource ds, Class a, String selectName, CondPar[] cpars) throws Exception {
        boolean rezult = false;
        String errorText = "";
        Connection conn = null;
        ObjectsSet os = null;
        try {
            conn = ds.getConnection();
            os = selectObjectsSet(conn, a, selectName, cpars);
            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            if (!rezult) {
                throw new Exception("Ошибка работы selectObjectsSet:\n" + errorText);
            }
        }

        return os;
    }
    
    /**
     * Выбрать в поток в поток
     * @param conn
     * @param o
     * @param selectName
     * @param pars
     * @return
     * @throws Exception
     */
    public ObjectsSet selectObjectsSet(Connection conn, Class a, String selectName, CondPar[] cpars) throws Exception {
        ObjectsSet os = null;
        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            String table = getDBTableName(a);
            System.out.println(table);

            SQuery sq = getSQuery(a, selectName);
            System.out.println("QUERY:" + sq.query());
            String select = sq.query().replace($TABLE, table);
            System.out.println(">>QUERY:" + select);

            ps = conn.prepareStatement(select);


            int[] qtypes = sq.qtypes();

            // Отлавливаем ошибку
            if (qtypes.length > 0 && (cpars == null || qtypes.length != cpars.length)) {
                throw new Exception("Число параметров выборки не совпадает");
            }


            if (qtypes.length > 0 && cpars != null && cpars.length > 0) {
                for (CondPar cp : cpars) {
                    int i = cp.getI();
                    ps.setObject(i, cp.getValue(), qtypes[i - 1]);
                }
            }

            // Выполняем запрос
            rs = ps.executeQuery();

            os = new ObjectsSet(conn, ps, rs, a);

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            if (!rezult) {
                throw new Exception("Ошибка работы selectObjectsSet:\n" + errorText);
            }
        }

        return os;
    }

    /**
     * Получить колл строк запроса
     * @param conn
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception 
     */
    public int selectCount(Connection conn, Class a, String selectName, CondPar[] cpars) throws Exception {
        int count = 0;
        boolean rezult = false;
        String errorText = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);


            String table = getDBTableName(a);
            System.out.println(table);

            SQuery sq = getSQuery(a, selectName);
            System.out.println("QUERY:" + sq.query());
            String select = sq.query().replace($TABLE, table);
            System.out.println(">>QUERY:" + select);

            ps = conn.prepareStatement("SELECT count(*) FROM (" + select + ") a;");


            int[] qtypes = sq.qtypes();

            // Отлавливаем ошибку
            if (qtypes.length > 0 && (cpars == null || qtypes.length != cpars.length)) {
                throw new Exception("Число параметров выборки не совпадает");
            }


            if (qtypes.length > 0 && cpars != null && cpars.length > 0) {
                for (CondPar cp : cpars) {
                    int i = cp.getI();
                    ps.setObject(i, cp.getValue(), qtypes[i - 1]);
                }
            }

            // Выполняем запрос
            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rezult = true;
        } catch (Exception ex) {
            Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            errorText += ex.getMessage() + "\n" + ex.getStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(APDBObjectMachine.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!rezult) {
                throw new Exception("Ошибка работы select:\n" + errorText);
            }
        }

        return count;
    }
}
