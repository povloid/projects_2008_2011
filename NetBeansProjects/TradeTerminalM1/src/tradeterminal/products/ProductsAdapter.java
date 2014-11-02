/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.products;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import minersinstrument.db.PAJDBCAdapterPostgreSQL;
import minersinstrument.ui.ADelDialog;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.AUniversalAddDialog;
import minersinstrument.ui.AUniversalEditDialog;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public class ProductsAdapter extends PAJDBCAdapterPostgreSQL {

    private int productsGroupsId = -1;

    public ProductsAdapter(PGPoolingDataSource source) {
        super(source);
        updateModel();
    }

    public void update(int productsGroupsId) {
        this.productsGroupsId = productsGroupsId;
        updateModel();
    }

    @Override
    protected PAJDBCResult vExecuteQuery(Connection conn) throws SQLException {
        conn.setAutoCommit(false);

        CallableStatement proc = conn.prepareCall("{ ? = call products_select_by_products_groups_id(?) }");
        proc.registerOutParameter(1, Types.OTHER);
        proc.setInt(2, productsGroupsId);

        proc.execute();

        return new PAJDBCResult((ResultSet) proc.getObject(1), conn, proc);
    }

    @Override
    protected Vector vAddRow(Connection conn) throws SQLException {
        ProductsAddEditDialogPanel p = new ProductsAddEditDialogPanel();
        AUniversalAddDialog d = new AUniversalAddDialog(p, null, true);

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/tovarplus.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            CallableStatement proc = conn.prepareCall("{? = call products_insert(?,?,?,?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.setInt(2, productsGroupsId);

            proc.setString(3, p.get_Name());
            proc.setString(4, p.getDescription());
            proc.setObject(5, p.getScod(), Types.VARCHAR);
            proc.setInt(6, p.getMeasures_id());
            proc.setObject(7, new BigDecimal((Double) p.getList_price()), Types.NUMERIC);
            proc.setObject(8, new BigDecimal((Double) p.getSpec_price()), Types.NUMERIC);
            proc.setObject(9, new BigDecimal(((Double) p.getPercent_discount()) * 0.01f), Types.NUMERIC);

            proc.execute();

            int id = proc.getInt(1);
            proc.close();

            Vector newRow = new Vector();
            newRow.addElement(id);
            newRow.addElement(p.get_Name());
            newRow.addElement(p.getDescription());
            newRow.addElement(p.getScod());
            newRow.addElement(0);
            newRow.addElement(p.getMeasures_id());
            newRow.addElement(p.getMeasures_Name());
            newRow.addElement(new BigDecimal((Double) p.getList_price()));
            newRow.addElement(new BigDecimal((Double) p.getSpec_price()));
            newRow.addElement(new BigDecimal((Double) p.getPercent_discount()));
            newRow.addElement(p.getEdSpec_price());
            newRow.addElement(p.getTotalSpec_price());

            return newRow;
        } else {
            return null;
        }
    }

    @Override
    protected Vector vEditRow(Connection conn, Vector curRow) throws SQLException {

        ProductsAddEditDialogPanel p = new ProductsAddEditDialogPanel();
        AUniversalEditDialog d = new AUniversalEditDialog(p, null, true);

        p.set_Name(curRow.elementAt(1).toString());
        p.setDescription(curRow.elementAt(2).toString());
        p.setScod(curRow.elementAt(3).toString());
        p.setQuantity(curRow.elementAt(4));
        p.setMeasures_id((Integer) curRow.elementAt(5));


        p.setList_price(((BigDecimal) curRow.elementAt(7)).doubleValue());
        p.setSpec_price(((BigDecimal) curRow.elementAt(8)).doubleValue());
        p.setPercent_discount(((BigDecimal) curRow.elementAt(9)).doubleValue());

        p.commitAllFormatedTextFields();


        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/tovarred.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call products_update(?,?,?,?,?,?,?,?,?)}");

            proc.setInt(1, id);
            proc.setInt(2, productsGroupsId);

            proc.setString(3, p.get_Name());
            proc.setString(4, p.getDescription());
            proc.setObject(5, p.getScod(), Types.VARCHAR);
            proc.setInt(6, p.getMeasures_id());
            proc.setObject(7, new BigDecimal((Double) p.getList_price()), Types.NUMERIC);
            proc.setObject(8, new BigDecimal((Double) p.getSpec_price()), Types.NUMERIC);
            proc.setObject(9, new BigDecimal(((Double) p.getPercent_discount()) * 0.01f), Types.NUMERIC);

            proc.execute();
            proc.close();

            Vector newRow = new Vector();

            newRow.addElement(id);
            newRow.addElement(p.get_Name());
            newRow.addElement(p.getDescription());
            newRow.addElement(p.getScod());
            newRow.addElement(curRow.elementAt(4));
            newRow.addElement(p.getMeasures_id());
            newRow.addElement(p.getMeasures_Name());
            newRow.addElement(new BigDecimal((Double) p.getList_price()));
            newRow.addElement(new BigDecimal((Double) p.getSpec_price()));
            newRow.addElement(new BigDecimal((Double) p.getPercent_discount()));
            newRow.addElement(p.getEdSpec_price());
            newRow.addElement(p.getTotalSpec_price());

            return newRow;
        } else {
            return null;
        }
    }

    @Override
    protected Boolean vDelRow(Connection conn, Vector curRow) throws SQLException {
        ADelDialog d = new ADelDialog(null, true);

        d.addPar("Наименование", curRow.elementAt(1).toString());
        d.addPar("Описание", curRow.elementAt(2).toString());
        d.addPar("Штрихкод", curRow.elementAt(3).toString());
        d.addPar("Количкство", curRow.elementAt(4));
        d.addPar("Мера", (Integer) curRow.elementAt(5));
        d.addPar("Цена по прайс листу", curRow.elementAt(7));
        d.addPar("Специальная цена", curRow.elementAt(8));
        d.addPar("Процентная скидка", curRow.elementAt(9).toString());

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/deltovar.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call products_delete(?)}");

            proc.setInt(1, id);
            proc.execute();
            proc.close();

            return true;
        } else {
            return false;
        }
    }

    public int geModelIdForProductId(int id) {
        System.out.println("__" + id);

        for (Object rowo : rows) {
            Vector row = (Vector) rowo;

            // System.out.print("*" + row.elementAt(0).getClass().toString());

            if (id == ((Integer) row.elementAt(0)).intValue()) {
                System.out.println("select product " + id);

                return rows.indexOf(rowo);
            }
        }

        return -1;
    }
}
