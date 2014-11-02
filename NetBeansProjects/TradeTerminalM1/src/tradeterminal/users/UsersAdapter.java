/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.users;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import minersinstrument.cryptographic.Functions;
import minersinstrument.db.PAJDBCAdapterPostgreSQL;
import minersinstrument.ui.ADelDialog;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.AUniversalAddDialog;
import minersinstrument.ui.AUniversalDialog;
import minersinstrument.ui.AUniversalEditDialog;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public class UsersAdapter extends PAJDBCAdapterPostgreSQL {

    public UsersAdapter(PGPoolingDataSource source) {
        super(source);

        updateModel();
    }

    @Override
    protected PAJDBCResult vExecuteQuery(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        CallableStatement proc = conn.prepareCall("{ ? = call users_select() }");
        proc.registerOutParameter(1, Types.OTHER);
        proc.execute();

        return new PAJDBCResult((ResultSet) proc.getObject(1), conn, proc);

        //PreparedStatement selectQueryStat = conn.prepareStatement("SELECT id,name,passwd,isadmin,description FROM users");
        //return selectQueryStat.executeQuery();
    }

    @Override
    protected Vector vAddRow(Connection conn) throws SQLException {
        AddUserDPanel p = new AddUserDPanel();
        AUniversalAddDialog d = new AUniversalAddDialog(p, null, true);


        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            String password = Functions.computeDigest(p.getJPassword(), "MD5");

            CallableStatement proc = conn.prepareCall("{? = call users_insert(?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.setString(2, p.getUName());
            proc.setString(3, password);
            proc.setBoolean(4, p.getIsAdmin());
            proc.setString(5, p.getDescription());
            proc.execute();
            int id = proc.getInt(1);
            proc.close();

            Vector newRow = new Vector();
            newRow.addElement(id);
            newRow.addElement(p.getUName());
            newRow.addElement("");
            newRow.addElement(p.getIsAdmin());
            newRow.addElement(p.getDescription());

            return newRow;
        } else {
            return null;
        }


    }
    private Boolean setPassword = false;

    public void setSetPassword(Boolean setPassword) {
        this.setPassword = setPassword;
    }

    @Override
    protected Vector vEditRow(Connection conn, Vector curRow) throws SQLException {

        if (setPassword) {
            ChangeUserPasswordDPanel p = new ChangeUserPasswordDPanel();
            AUniversalDialog d = new AUniversalDialog(p, null, true);
            d.setTitle("Установка пароля пользователя");
            d.setTitleText("Установка пароля");
            d.setTitleDescriptionText("Вам необходимо ввести пароль и подтведить его.");
            d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/parol.png")));

            d.pack();

            d.setVisible(true);
            d.dispose();

            if (d.getReturnStatus() == ADialog.RET_OK) {
                int id = Integer.parseInt(curRow.elementAt(0).toString());

                CallableStatement proc = conn.prepareCall("{call users_update_password(?,?)}");

                proc.setInt(1, id);
                proc.setString(2, Functions.computeDigest(p.getJPassword(), "MD5")); //////<----<<

                proc.execute();
                proc.close();

                return new Vector(curRow);
            } else {
                return null;
            }
        }

        EditUserDPanel p = new EditUserDPanel();
        AUniversalEditDialog d = new AUniversalEditDialog(p, null, true);

        p.setUName(curRow.elementAt(1).toString());
        p.setIsAdmin(Boolean.valueOf(curRow.elementAt(3).toString()));
        p.setDescription(curRow.elementAt(4).toString());

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call users_update(?,?,?,?)}");

            proc.setInt(1, id);
            proc.setString(2, p.getUName());
            proc.setBoolean(3, p.getIsAdmin());
            proc.setString(4, p.getDescription());
            proc.execute();
            proc.close();

            Vector newRow = new Vector();

            newRow.addElement(id);
            newRow.addElement(p.getUName());
            newRow.addElement(""); //////<----<<
            newRow.addElement(p.getIsAdmin());
            newRow.addElement(p.getDescription());

            return newRow;

        } else {
            return null;
        }
    }

    @Override
    protected Boolean vDelRow(
            Connection conn, Vector curRow)
            throws SQLException {
        ADelDialog d = new ADelDialog(null, true);
        d.addPar("Наименование", curRow.elementAt(1).toString());
        d.addPar("Администратор", curRow.elementAt(3));
        d.addPar("Краткое описание", curRow.elementAt(4).toString());

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            int id = Integer.parseInt(curRow.elementAt(0).toString());

            CallableStatement proc = conn.prepareCall("{call users_delete(?)}");

            proc.setInt(1, id);
            proc.execute();
            proc.close();

            return true;
        } else {
            return false;
        }
    }
}
