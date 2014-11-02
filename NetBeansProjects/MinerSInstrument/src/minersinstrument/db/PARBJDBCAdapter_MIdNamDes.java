/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minersinstrument.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Vector;
import minersinstrument.ui.ADelDialog;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.ARBDialogPanel_MIdNamDes;
import minersinstrument.ui.AUniversalAddDialog;
import minersinstrument.ui.AUniversalEditDialog;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public class PARBJDBCAdapter_MIdNamDes extends PAJDBCAdapterPostgreSQL {

    private String tName;
    private String sequence;

    public PARBJDBCAdapter_MIdNamDes(PGPoolingDataSource source, String tName, String sequence) {
        super(source);

        this.tName = tName;
        this.sequence = sequence;

        updateModel();
    }

    @Override
    protected PAJDBCResult vExecuteQuery(Connection conn) throws Exception {

        PreparedStatement selectQueryStat =
                conn.prepareStatement("SELECT id,name,description FROM " + tName);

        return new PAJDBCResult(selectQueryStat.executeQuery(), conn, null);
    }

    @Override
    protected Vector vAddRow(Connection conn) throws Exception {
        ARBDialogPanel_MIdNamDes p = new ARBDialogPanel_MIdNamDes();
        AUniversalAddDialog d = new AUniversalAddDialog(p, null, true);

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            String insertQuery = "INSERT INTO " + tName + " (\"name\",description) values (?,?);";

            PreparedStatement insertQueryStat = conn.prepareStatement(insertQuery);
            insertQueryStat.setString(1, p.get_Name());
            insertQueryStat.setString(2, p.getDescription());
            insertQueryStat.executeUpdate();

            CallableStatement proc = conn.prepareCall("{? = call currval(?)}");
            proc.registerOutParameter(1, Types.BIGINT);
            proc.setString(2, sequence);
            proc.execute();

            long id = proc.getLong(1);
            proc.close();

            Vector newRow = new Vector();
            newRow.addElement(id);
            newRow.addElement(p.get_Name());
            newRow.addElement(p.getDescription());

            return newRow;
        } else {
            return null;
        }
    }

    @Override
    protected Vector vEditRow(Connection conn, Vector curRow) throws Exception {
        ARBDialogPanel_MIdNamDes p = new ARBDialogPanel_MIdNamDes();
        AUniversalEditDialog d = new AUniversalEditDialog(p, null, true);

        p.set_Name(curRow.elementAt(1).toString());
        p.setDescription(curRow.elementAt(2).toString());

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            String insertQuery = "UPDATE " + tName + " SET \"name\"=?, description=? WHERE id=?;";

            PreparedStatement insertQueryStat = conn.prepareStatement(insertQuery);
            insertQueryStat.setString(1, p.get_Name());
            insertQueryStat.setString(2, p.getDescription());
            insertQueryStat.setInt(3, id);
            insertQueryStat.executeUpdate();

            Vector newRow = new Vector();

            newRow.addElement(id);
            newRow.addElement(p.get_Name());
            newRow.addElement(p.getDescription());

            return newRow;
        } else {
            return null;
        }
    }

    @Override
    protected Boolean vDelRow(Connection conn, Vector curRow) throws Exception {
        ADelDialog d = new ADelDialog(null, true);
        d.addPar("Наименование", curRow.elementAt(1).toString());
        d.addPar("Краткое описание", curRow.elementAt(2).toString());

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            String insertQuery = "DELETE FROM " + tName + " WHERE id=?;";

            PreparedStatement insertQueryStat = conn.prepareStatement(insertQuery);
            insertQueryStat.setInt(1, id);
            insertQueryStat.executeUpdate();

            return true;
        } else {
            return false;
        }
    }
}
