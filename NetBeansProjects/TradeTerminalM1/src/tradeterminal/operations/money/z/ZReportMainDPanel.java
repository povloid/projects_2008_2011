/*
 * ZReportMainDPanel.java
 *
 * Created on 2 Май 2008 г., 23:31
 */
package tradeterminal.operations.money.z;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import minersinstrument.ui.AErrorDialog;
import minersinstrument.ui.IADialogPanel;
import tradeterminal.Setup;
import tradeterminal.conf.User;

/**
 *
 * @author  pkopychenko
 */
public class ZReportMainDPanel extends javax.swing.JPanel implements IADialogPanel {

    private double balance;

    /** Creates new form ZReportMainDPanel */
    public ZReportMainDPanel() {
        initComponents();

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(32, 32));
        jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(32, 32));


        Connection conn = null;

        try {
            conn = Setup.getSource().getConnection();
            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall("{ ? = call get_cass_balance() }");
            proc.registerOutParameter(1, Types.NUMERIC);

            proc.execute();

            balance = proc.getBigDecimal(1).doubleValue();

            summTextField.setText("" + balance);
            proc.close();

        } catch (SQLException ex) {
            Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }
        } catch (Exception ex) {
            Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.ALL, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        summTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ZReportMainDPanel.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        summTextField.setEditable(false);
        summTextField.setFont(resourceMap.getFont("summTextField.font")); // NOI18N
        summTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        summTextField.setText(resourceMap.getString("summTextField.text")); // NOI18N
        summTextField.setName("summTextField"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        jScrollPane1.setViewportView(descriptionTextArea);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(summTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(summTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel3)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)).addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    public boolean checkPanel() {

        if (balance <= 0) // Нельзя проводить данную операцию при 0
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Вы не можете сейчас провести данную операцию. В кассе нет денег.",
                    "Ограничение...",
                    JOptionPane.WARNING_MESSAGE);

            return false;
        }



        Connection conn = null;

        try {
            conn = Setup.getSource().getConnection();
            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall("{ call make_z_report(?,?,?,?,?,?) }");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.TIMESTAMP);
            proc.registerOutParameter(3, Types.TIMESTAMP);
            proc.registerOutParameter(4, Types.NUMERIC);

            proc.setString(5, descriptionTextArea.getText());
            proc.setInt(6, User.id);

            proc.execute();

            JOptionPane.showMessageDialog(
                    this,
                    "Была проведена операция снятия всех наличных из кассы\n" + "в размере " + proc.getBigDecimal(4) + " уе.",
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);
            proc.close();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

            try {
                conn.rollback();
                // log error
            } catch (SQLException ex1) {
                Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.SEVERE, null, ex1);

                AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex1.getMessage());
                der2.setVisible(true);
                der2.dispose();

            }
        } catch (Exception ex) {
            Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.ALL, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(ZReportMainDPanel.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();
                }
            }

            return true;
        }
    }

    public void openPanel() {
        descriptionTextArea.requestFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField summTextField;
    // End of variables declaration//GEN-END:variables
}
