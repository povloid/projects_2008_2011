/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.references_books.measures;

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
 * @author PKopychenko
 */
public class RBMeasuresAdapter extends PAJDBCAdapterPostgreSQL {

    public RBMeasuresAdapter(PGPoolingDataSource source) {
        super(source);

        updateModel();
    }

    @Override
    protected PAJDBCResult vExecuteQuery(Connection conn) throws Exception {
//        PreparedStatement selectQueryStat =
//                conn.prepareStatement("SELECT id,name,description FROM rb_measures");
//        return selectQueryStat.executeQuery();

        conn.setAutoCommit(false);
        CallableStatement proc = conn.prepareCall("{? = call rb_measures_select()}");
        proc.registerOutParameter(1, Types.OTHER);
        proc.execute();

        return new PAJDBCResult((ResultSet) proc.getObject(1), conn, proc);
    }

    @Override
    protected Vector vAddRow(Connection conn) throws Exception {
        RBMeasuresDPanel p = new RBMeasuresDPanel();
        AUniversalAddDialog d = new AUniversalAddDialog(p, null, true);

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/meraplus.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            CallableStatement proc = conn.prepareCall("{? = call rb_measures_insert(?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);

            proc.setString(2, p.get_Name());
            proc.setString(3, p.getDescription());
            proc.setBoolean(4, p.getMtype());
            proc.execute();

            int id = proc.getInt(1);

            proc.close();

            Vector newRow = new Vector();
            newRow.addElement(id);
            newRow.addElement(p.get_Name());
            newRow.addElement(p.getDescription());
            newRow.addElement(p.getMtype());

            return newRow;
        } else {
            return null;
        }
    }

    @Override
    protected Vector vEditRow(Connection conn, Vector curRow) throws Exception {
        RBMeasuresDPanel p = new RBMeasuresDPanel();
        AUniversalEditDialog d = new AUniversalEditDialog(p, null, true);

        p.set_Name(curRow.elementAt(1).toString());
        p.setDescription(curRow.elementAt(2).toString());
        p.setMtype((Boolean) curRow.elementAt(3));

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/merared.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call rb_measures_update(?,?,?,?)}");

            proc.setInt(1, id);
            proc.setString(2, p.get_Name());
            proc.setString(3, p.getDescription());
            proc.setBoolean(4, p.getMtype());
            proc.execute();
            proc.close();

            Vector newRow = new Vector();

            newRow.addElement(id);
            newRow.addElement(p.get_Name());
            newRow.addElement(p.getDescription());
            newRow.addElement(p.getMtype());

            return newRow;
        } else {
            return null;
        }
    }

    @Override
    protected Boolean vDelRow(Connection conn, Vector curRow) throws Exception {
        ADelDialog d = new ADelDialog(null, true);
        d.addPar("Наименование", curRow.elementAt(1).toString());
        d.addPar("Описание", curRow.elementAt(2).toString());
        d.addPar("Делимое", curRow.elementAt(3));

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/meradel.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call rb_measures_delete(?)}");

            proc.setInt(1, id);
            proc.execute();
            proc.close();

            return true;
        } else {
            return false;
        }
    }
}
