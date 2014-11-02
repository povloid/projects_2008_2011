/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.operations.products.returnproduct;

import tradeterminal.operations.products.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import minersinstrument.ui.AErrorDialog;
import org.postgresql.ds.PGPoolingDataSource;
import tradeterminal.Setup;
import tradeterminal.conf.User;

/**
 *
 * @author PKopychenko
 */
public class ReturnProductModel extends AbstractTableModel {

    private int customerId = -1;
    private String customerInfo = "";
    private double currentBalance = 0;
    private double toBalance = 0;
    protected PGPoolingDataSource source = Setup.getSource();
    protected Vector<ReturnProductRow> rows = new Vector<ReturnProductRow>();
    public static int NOTHING = -1;
    private int orderId = NOTHING;

    public String getCustomerInfo() {
        return customerInfo;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ReturnProductModel() {
    }

    public double getToBalance() {
        return toBalance;
    }

    public void setToBalance(double toBalance) {
        this.toBalance = toBalance;
    }
    // Создание нового ордера

    public void clearModel() {
        rows.clear();
        this.orderId = NOTHING;
        fireTableDataChanged();

        customerId = -1;
        customerInfo = "";
        currentBalance = 0;
        toBalance = 0;
    }

    // Вернуть итоговую сумму за все продукты
    public double getCurrentReturnedSummForAll() {
        double summ = 0;
        for (ReturnProductRow row : rows) {
            summ += row.getCurrentReturnedSumm();
        }

        return summ;
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return ReturnProductRow.SIZE;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Vector row = (Vector) rows.elementAt(rowIndex);
            return row.elementAt(columnIndex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public ReturnProductRow getProductRowAt(int rowIndex) {
        try {
            return rows.elementAt(rowIndex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public int findAndShowSellingOrder(int orderId) {
        clearModel();

        int recId = NOTHING;

        Connection conn = null;

        try {
            conn = Setup.getSource().getConnection();
            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall("{ ? = call find_and_show_selling_order(?) }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setInt(2, orderId);

            proc.execute();

            ResultSet resultSet = (ResultSet) proc.getObject(1);

            while (resultSet.next()) {
                //System.out.println(resultSet.getString(3));

                ReturnProductRow r = new ReturnProductRow(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getDouble(9),
                        resultSet.getDouble(10),
                        resultSet.getDouble(11));

                r.setRowIndex(rows.size() + 1);
                rows.addElement(r);
                recId = rows.indexOf(r);
                fireTableRowsInserted(recId, recId);

            }

            resultSet.close();
            proc.close();

            this.orderId = orderId;

            // Теперь по потребителю
            customerId = -1;
            currentBalance = 0;
            toBalance = 0;

            System.out.println(orderId);

            proc = conn.prepareCall("{? = call rb_customers_select_for_order_id(?)}");
            proc.registerOutParameter(1, Types.OTHER);
            proc.setInt(2, orderId);

            proc.execute();

            resultSet = (ResultSet) proc.getObject(1);

            while (resultSet.next()) {
                customerId = resultSet.getInt("id");
                currentBalance = resultSet.getDouble("balance");

                customerInfo = "";
                customerInfo += "Тип документа: " + resultSet.getString("doc_type_name") + "\n";
                customerInfo += "№ " + resultSet.getString("doc") + "\n";
                customerInfo += "Краткое наименование: " + resultSet.getString("short_name") + "\n";
                customerInfo += "Ф.И.О.: " + resultSet.getString("fio") + "\n";

                customerInfo += "Адрес: " + resultSet.getString("address") + "\n";
                customerInfo += "Телефны: " + resultSet.getString("phone_number") + ", " + resultSet.getString("phone_number_2") + "\n";
                customerInfo += "Электронный адрес: " + resultSet.getString("email") + "\n";
                customerInfo += "Краткое описание: " + resultSet.getString("description") + "\n";

            }



            System.out.println(customerId + "   " + currentBalance);
            System.out.println(customerInfo);

            resultSet.close();
            proc.close();

        } catch (SQLException ex) {
            Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            der.setVisible(true);
            der.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }
        } catch (Exception ex) {
            Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog der = new AErrorDialog("Ошибка...", ex.getMessage());
            der.setVisible(true);
            der.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    der.setVisible(true);
                    der.dispose();
                }
            }

            return recId;
        }
    }

    public int getProductId(int recId) {
        ReturnProductRow row = rows.get(recId);
        return row.getId();
    }

    public double getCurrentReturnedQuantityMAX(int recId) {
        return rows.get(recId).getCurrentReturnedQuantityMAX();
    }

    public double getCurrentReturnedQuantity(int recId) {
        return rows.get(recId).getCurrentReturnedQuantity();
    }

    public void setCurrentReturnedQuantity(int recId, double q) {
        rows.get(recId).setCurrentReturnedQuantity(q);
        fireTableRowsUpdated(recId, recId);
    }

    public double getCurrentReturnedStep(int recId) {
        return rows.get(recId).getStep();
    }

    public ReturnProductRow getRow(int recId) {
        return rows.get(recId);
    }

    public class ReturnProductModelOperationResult {

        private int result;

        public ReturnProductModelOperationResult(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    protected ReturnProductModelOperationResult executeOpertion() {

        if (orderId == NOTHING) {
            return new ReturnProductModelOperationResult(-1);
        }
        int RESULT = -1;

        Connection conn = null;

        try {
            conn = source.getConnection();
            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall("{ ? = call create_order(?,?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.setString(2, "prt");
            proc.setString(3, null);
            proc.setInt(4, orderId);
            proc.setInt(5, User.id);

            if (customerId != -1) {
                proc.setInt(6, customerId);
            } else {
                proc.setNull(6, Types.NULL);
            }

            // Признак В КРЕДИТ
            if (toBalance > 0) {
                proc.setBoolean(7, true);
            } else {
                proc.setBoolean(7, false);
            }
            proc.execute();

            int subOrderId = proc.getInt(1);
            proc.close();

            proc = conn.prepareCall("{ ? = call create_item(?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.setInt(2, subOrderId);


            // Теперь заполняем содержимое ордера
            for (ReturnProductRow row : rows) {

                if (row.getCurrentReturnedQuantity() > 0) {
                    proc.setInt(3, row.getId());
                    proc.setObject(4, row.getActualPrice(), Types.NUMERIC);
                    proc.setObject(5, row.getCurrentReturnedQuantity(), Types.NUMERIC);
                    proc.setString(6, null);

                    proc.execute();



                    System.out.println("Добавлен товар по коду " + row.getId() + ", добавлена запись ордера под номером " + proc.getInt(1) + ".");
                }
            }

            // Теперь на баланс
            if (toBalance > 0) {
                proc.setNull(3, Types.NULL);
                proc.setObject(4, -new Double(toBalance), Types.NUMERIC);
                proc.setInt(5, 1);
                proc.setString(6, null);

                proc.execute();
                proc.close();

                // Теперь записываем в баланс
                proc = conn.prepareCall("{call customer_add_credit(?,?,?,?)}");
                proc.setInt(1, subOrderId);
                proc.setInt(2, customerId);
                proc.setObject(3, new Double(toBalance), Types.NUMERIC);
                proc.setString(4, "Вернул товар");

                proc.execute();

            }

            proc.close();

            // Если все в порядке то подтверждаем транзакцию и успешно выходим
            conn.commit();
            RESULT = 1;
            //return new ReturnProductModelOperationResult(1);
        } catch (SQLException ex) {
            Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            der.setVisible(true);
            der.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }
        } catch (Exception ex) {
            Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog der = new AErrorDialog("Ошибка...", ex.getMessage());
            der.setVisible(true);
            der.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(ReturnProductModel.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    der.setVisible(true);
                    der.dispose();
                }
            }
            return new ReturnProductModelOperationResult(RESULT);
        }
    }

    public int getCustomerId() {
        return customerId;
    }
}


