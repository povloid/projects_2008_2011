/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Вся власть Аллаху!!!
 */
package minersinstrument.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import minersinstrument.ui.AErrorDialog;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public abstract class PAJDBCAdapterPostgreSQL extends AbstractTableModel implements IPJDBCAdapter {

    private PGPoolingDataSource source;
    //protected ResultSet resultSet;
    // здесь будут содержаться названия таблиц БД
    protected String[] columnNames = {};
    // здесь будут  храниться все данные таблицы БД
    protected Vector rows = new Vector();
    protected ResultSetMetaData metaData;
    protected String tableName = "";

    public PAJDBCAdapterPostgreSQL(PGPoolingDataSource source) {
        this.source = source;
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int aRow, int aColumn) {
        try {

            Vector row = (Vector) rows.elementAt(aRow);
            return row.elementAt(aColumn);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String dbRepresentation(int column, Object value) {
        int type;

        if (value == null) {
            return "null";
        }

        try {
            type = metaData.getColumnType(column + 1);
        } catch (SQLException e) {
            return value.toString();
        }

        switch (type) {
            case Types.INTEGER:
            case Types.DOUBLE:
            case Types.FLOAT:
                return value.toString();
            case Types.BIT:
                return ((Boolean) value).booleanValue() ? "1" : "0";
            case Types.DATE:
                return value.toString(); // This will need some conversion.
            default:
                return "\"" + value.toString() + "\"";
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        try {
            return metaData.isWritable(column + 1);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        int type;
        try {
            type = metaData.getColumnType(column + 1);
        } catch (SQLException e) {
            return super.getColumnClass(column);
        }

        switch (type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return String.class;

            case Types.BIT:
                return Boolean.class;

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                return Integer.class;

            case Types.BIGINT:
                return Long.class;

            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL:
                return Double.class;

            case Types.DATE:
                return java.sql.Date.class;

            default:
                return Object.class;
        }
    }

    // -------------------------------------------------------------------------   
    // Выполнение запроса
    protected abstract PAJDBCResult vExecuteQuery(Connection conn) throws Exception;

    // Построение колонок
    protected void createColumns(ResultSet resultSet) throws Exception {

        metaData = resultSet.getMetaData();

        // получим количество полей исследуемой таблицы БД
        int numberOfColumns = metaData.getColumnCount();
        columnNames = new String[numberOfColumns];

        // получим названия этих полей и присвоим их нашему
        // полю columnNames
        for (int column = 0; column < numberOfColumns; column++) {
            columnNames[column] = metaData.getColumnLabel(column + 1);
        }
    }

    /**
     * Результирующий класс
     */
    public static class PAJDBCResult {

        private ResultSet rs;
        private Connection conn;
        private CallableStatement proc;

        public PAJDBCResult(ResultSet rs, Connection conn, CallableStatement proc) {
            this.rs = rs;
            this.conn = conn;
            this.proc = proc;
        }

        public Connection getConn() {
            return conn;
        }

        public CallableStatement getProc() {
            return proc;
        }

        public ResultSet getRs() {
            return rs;
        }

        public void close() {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (proc != null) {
                    proc.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Обновить модель
    public void updateModel() {

        PAJDBCResult result = null;

        Connection conn = null;

        try {
            conn = source.getConnection();

            // Получаем результат запроса
            result = vExecuteQuery(conn);

            ResultSet resultSet = result.getRs();

            if (resultSet == null) {
                return;
            }

            // Создаем колонки
            if (columnNames.length == 0) {
                createColumns(resultSet);
            }

            // Начнем получать данные из БД
            if (rows == null) {
                rows = new Vector();
            } else {
                rows.clear();
            }

            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= getColumnCount(); i++) {
                    newRow.addElement(resultSet.getObject(i));
                }
                // прибавим новую  запись
                rows.addElement(newRow);
            }

            // оповестим listeners о том, что таблица изменена
            fireTableDataChanged();
        } catch (SQLException ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } catch (Exception ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (result != null) {
                result.close();
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();

                }
            }
        }
    }

    // От IPJDBCAdapter IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
    public int addRow() {
        int recId = -1;
        Connection conn = null;

        try {
            conn = source.getConnection();
            conn.setAutoCommit(false);

            Vector newRow = vAddRow(conn);
            if (newRow != null) {

                conn.commit();

                // прибавим новую  запись
                rows.addElement(newRow);
                recId = rows.indexOf(newRow);

                fireTableRowsInserted(recId + 1, recId);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }

        } catch (Exception ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();

                }
            }

            return recId;
        }
    }

    protected abstract Vector vAddRow(Connection conn) throws Exception;

    public void editRow(int row) {
        Connection conn = null;

        try {
            conn = source.getConnection();
            conn.setAutoCommit(false);

            Vector curRow = (Vector) rows.elementAt(row);

            Vector updatedRow = vEditRow(conn, curRow);

            if (updatedRow != null) {
                conn.commit();

                curRow.clear();
                curRow.addAll(updatedRow);

                fireTableRowsUpdated(row + 1, row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }

        } catch (Exception ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();
                }
            }

            return;
        }
    }

    protected abstract Vector vEditRow(Connection conn, Vector curRow) throws Exception;

    public void delRow(int row) {
        Connection conn = null;

        try {
            conn = source.getConnection();
            conn.setAutoCommit(false);

            Vector curRow = (Vector) rows.elementAt(row);

            if (vDelRow(conn, curRow)) {
                conn.commit();

                // Удаляем из представления
                rows.remove(curRow);

                System.out.println("row=" + row + "  rows.size()=" + rows.size());

                if (row == 0) {
                    fireTableRowsDeleted(row + 1, row + 1);
                } else if (rows.size() >= 1) {
                    fireTableRowsDeleted(row - 1, row - 1);
                } else {
                    fireTableRowsDeleted(row, row);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }

        } catch (Exception ex) {
            Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PAJDBCAdapterPostgreSQL.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();
                }
            }

            return;
        }
    }

    protected abstract Boolean vDelRow(Connection conn, Vector curRow) throws Exception;

    public void commitData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rollbackData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
