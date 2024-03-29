/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.operations.products.bases;

import tradeterminal.operations.products.selling.ProductSellingRow;
import tradeterminal.operations.products.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.postgresql.ds.PGPoolingDataSource;
import tradeterminal.Setup;

/**
 *
 * @author PKopychenko
 */
public abstract class ProductOperationsBaseModel extends AbstractTableModel {

    protected PGPoolingDataSource source = Setup.getSource();
    protected Vector<BaseProductRow> rows = new Vector<BaseProductRow>();
    protected int customerId = -1;
    protected double toCredit = 0;

    public double getToCredit() {
        return toCredit;
    }

    public void setToCredit(double toCredit) {
        this.toCredit = toCredit;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ProductOperationsBaseModel() {
    }

    // Создание нового ордера
    public void createNewOrder() {
        rows.clear();
        fireTableDataChanged();

        customerId = -1;
        toCredit = 0;
    }

    //Добавление продукта в ордер
    public int addNewProductRow(BaseProductRow productRownew) {

        int recId = -1;

        boolean b = false;
        for (BaseProductRow row : rows) { // 

            if (row.getScod().equals(productRownew.getScod())) {
                b = true;
                recId = rows.indexOf(row);
                break;
            }
        }

        if (!b) { // Если добавляется новая запись

            productRownew.setRowIndex(rows.size() + 1);
            rows.addElement(productRownew);
            recId = rows.indexOf(productRownew);
            fireTableRowsInserted(recId, recId);
        } else { // Иначе просто количество увеличить на одну единицу   

            BaseProductRow row = ((BaseProductRow) rows.get(recId));
            row.setQuantity(row.getQuantity() + 1);
            fireTableRowsUpdated(recId, recId);
        }

        return recId;
    }

    // Вернуть итоговую сумму за все продукты
    public double getSummForAllProducts() {
        double summ = 0;
        for (BaseProductRow row : rows) {
            summ += row.getOutputPrice();
        }

        return summ;
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return ProductSellingRow.SIZE;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Vector row = (Vector) rows.elementAt(rowIndex);
            return row.elementAt(columnIndex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public BaseProductRow getProductRowAt(int rowIndex) {
        try {
            return (BaseProductRow) rows.elementAt(rowIndex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    public static final int PRODUCT_QUANTITY_IS_ZIRO = -2;
    public static final int THE_PRODUCT_HAVE_NOT = -1;

    public int findAndInsertProductForScod(String scod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Увеличить на единицу приращения
    public void incQuantity(int recId) {
        BaseProductRow row = ((BaseProductRow) rows.get(recId));
        int product_id = row.getId();
        double maxQuantity = 0;

        Connection conn = null;

        try {
            conn = Setup.getSource().getConnection();
            CallableStatement proc = conn.prepareCall("{ ? = call select_products_quantity_for_id(?) }");
            proc.registerOutParameter(1, Types.NUMERIC);
            proc.setInt(2, product_id);

            proc.execute();

            maxQuantity = ((Number) proc.getObject(1)).doubleValue();

            proc.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductOperationsBaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductOperationsBaseModel.class.getName()).log(Level.ALL, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(ProductOperationsBaseModel.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        }


        double newCurrQuantity = row.getQuantity() + row.getStep();
        if (newCurrQuantity <= maxQuantity) {
            row.setQuantity(newCurrQuantity);
            fireTableRowsUpdated(recId, recId);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "ТОвара в наличии больше не имеется.",
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);
        }


    }

    public int getProductId(int recId) {
        BaseProductRow row = ((BaseProductRow) rows.get(recId));
        return row.getId();
    }

    public double getQuantity(int recId) {
        BaseProductRow row = ((BaseProductRow) rows.get(recId));
        return row.getQuantity();
    }

    public void setQuantity(int recId, double q) {
        BaseProductRow row = ((BaseProductRow) rows.get(recId));
        row.setQuantity(q);
        fireTableRowsUpdated(recId, recId);
    }

    public void removeProduct(int recId) {
        rows.remove(recId); // TODO не удаляется последняя строка

        if (recId == 0) {
            fireTableRowsDeleted(recId + 1, recId + 1);
        } else if (rows.size() >= 1) {
            fireTableRowsDeleted(recId - 1, recId - 1);
        } else {
            fireTableRowsDeleted(recId, recId);
        }
    }

    public double getListPrice(int recId) {
        BaseProductRow row = rows.get(recId);
        return row.getListPrice();
    }

    public double getPercentDiscount(int recId) {
        BaseProductRow row = rows.get(recId);
        return row.getPercentDiscount();
    }

    public double getSpecPrice(int recId) {
        BaseProductRow row = rows.get(recId);
        return row.getSpecPrice();
    }

    public BaseProductRow getRow(int recId) {
        return rows.get(recId);
    }

    public class OperationResult {

        private int result;

        public OperationResult(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    protected abstract OperationResult executeOpertion();
}


