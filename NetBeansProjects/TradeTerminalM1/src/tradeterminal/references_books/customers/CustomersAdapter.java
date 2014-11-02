/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.references_books.customers;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * @author pacman
 */
public class CustomersAdapter extends PAJDBCAdapterPostgreSQL implements ICostumerFilterPanelAdapter {

    public CustomersAdapter(PGPoolingDataSource source) {
        super(source);

        updateModel();
    }

    enum SelectType {

        ALL, FOR_SHORT_NAME, FOR_FIO, FOR_DOC
    }
    private SelectType selectType = SelectType.ALL;
    private String selectMask = "";

    public void selectAll() {
        selectType = SelectType.ALL;
        selectMask = "";
        updateModel();
    }

    public void selectForShortName(String mask) {
        selectType = SelectType.FOR_SHORT_NAME;
        selectMask = mask;
        updateModel();
    }

    public void selectForFio(String mask) {
        selectType = SelectType.FOR_FIO;
        selectMask = mask;
        updateModel();
    }

    public void selectForDoc(String mask) {
        selectType = SelectType.FOR_DOC;
        selectMask = mask;
        updateModel();
    }

    @Override
    protected PAJDBCResult vExecuteQuery(Connection conn) throws Exception {
        conn.setAutoCommit(false);
        CallableStatement proc = null;

        switch (selectType) {
            case ALL:
                proc = conn.prepareCall("{? = call rb_customers_select()}");
                break;
            case FOR_SHORT_NAME:
                proc = conn.prepareCall("{? = call rb_customers_select_for_short_name(?)}");
                proc.setString(2, selectMask);
                break;
            case FOR_FIO:
                proc = conn.prepareCall("{? = call rb_customers_select_for_fio(?)}");
                proc.setString(2, selectMask);
                break;
            case FOR_DOC:
                proc = conn.prepareCall("{? = call rb_customers_select_for_doc(?)}");
                proc.setString(2, selectMask);
                break;
        }

        proc.registerOutParameter(1, Types.OTHER);
        proc.execute();

        return new PAJDBCResult((ResultSet) proc.getObject(1), conn, proc);
    }

    private Vector createRow(int id, CostomersDPanel p) {
        Vector newRow = new Vector();
        newRow.addElement(id);
        newRow.addElement(p.getUrURadioButton());

        newRow.addElement(p.getDoc());
        newRow.addElement(p.getDocType());
        newRow.addElement(p.getDocTypeName());


        newRow.addElement(p.getShortName());

        newRow.addElement(p.getFio());
        newRow.addElement(new BigDecimal(p.getSex()));

        newRow.addElement(p.getAdress());
        newRow.addElement(p.getPhoneNumber());
        newRow.addElement(p.getPhoneNumber2());
        newRow.addElement(p.getEmail());
        newRow.addElement(p.getDescription());


        return newRow;
    }

    @Override
    protected Vector vAddRow(Connection conn) throws Exception {
        CostomersDPanel p = new CostomersDPanel();
        AUniversalAddDialog d = new AUniversalAddDialog(p, null, true);

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/newklient.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            CallableStatement proc = conn.prepareCall("{? = call rb_customers_insert(?,?,?,?,?,?,?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);


            proc.setString(2, p.getFio());
            proc.setString(3, p.getAdress());
            proc.setString(4, p.getPhoneNumber());
            proc.setString(5, p.getPhoneNumber2());
            proc.setString(6, p.getEmail());
            proc.setString(7, p.getDescription());

            proc.setBoolean(8, p.getUrURadioButton());
            proc.setString(9, p.getShortName());
            proc.setInt(10, p.getDocType());
            proc.setString(11, p.getDoc());
            proc.setInt(12, p.getSex());

            proc.execute();

            int id = proc.getInt(1);

            proc.close();

            return createRow(id, p);
        } else {
            return null;
        }
    }

    @Override
    protected Vector vEditRow(Connection conn, Vector curRow) throws Exception {
        CostomersDPanel p = new CostomersDPanel();
        AUniversalEditDialog d = new AUniversalEditDialog(p, null, true);

        p.setUrURadioButton((Boolean) curRow.elementAt(1));

        p.setDoc((String) curRow.elementAt(2));
        p.setDocType((Integer) curRow.elementAt(3));

        p.setShortName((String) curRow.elementAt(5));

        p.setFio((String) curRow.elementAt(6));
        p.setSex(((BigDecimal) curRow.elementAt(7)).intValue());

        p.setAdress((String) curRow.elementAt(8));
        p.setPhoneNumber((String) curRow.elementAt(9));
        p.setPhoneNumber2((String) curRow.elementAt(10));
        p.setEmail((String) curRow.elementAt(11));
        p.setDescription((String) curRow.elementAt(12));


        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/redklient.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call rb_customers_update(?,?,?,?,?,?,?,?,?,?,?,?)}");

            proc.setInt(1, id);
            proc.setString(2, p.getFio());
            proc.setString(3, p.getAdress());
            proc.setString(4, p.getPhoneNumber());
            proc.setString(5, p.getPhoneNumber2());
            proc.setString(6, p.getEmail());
            proc.setString(7, p.getDescription());

            proc.setBoolean(8, p.getUrURadioButton());
            proc.setString(9, p.getShortName());
            proc.setInt(10, p.getDocType());
            proc.setString(11, p.getDoc());
            proc.setInt(12, p.getSex());

            proc.execute();

            proc.close();

            return createRow(id, p);
        } else {
            return null;
        }
    }

    @Override
    protected Boolean vDelRow(Connection conn, Vector curRow) throws Exception {
        ADelDialog d = new ADelDialog(null, true);
        d.addPar("Наименование", curRow.elementAt(1).toString());

        d.addPar("Юр.лице.", (Boolean) curRow.elementAt(1));

        d.addPar("Документ", (String) curRow.elementAt(2));
        d.addPar("Тип документа", (String) curRow.elementAt(4));

        d.addPar("Краткое наименование", (String) curRow.elementAt(5));

        d.addPar("Ф.И.О.", (String) curRow.elementAt(6));

        int b = ((BigDecimal) curRow.elementAt(7)).intValue();

        if (b == 0) {
            d.addPar("Пол", "Мужской");
        } else {
            d.addPar("Пол", "Женский");
        }

        d.addPar("Адрес", (String) curRow.elementAt(8));
        d.addPar("Телефон 1", (String) curRow.elementAt(9));
        d.addPar("Телефон 2", (String) curRow.elementAt(10));
        d.addPar("email", (String) curRow.elementAt(11));
        d.addPar("Описание", (String) curRow.elementAt(12));

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/delklient.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call rb_customers_delete(?)}");

            proc.setInt(1, id);
            proc.execute();
            proc.close();

            return true;
        } else {
            return false;
        }
    }
}
