/*
 * ChangeUserPasswordDPanel.java
 *
 * Created on 24 Январь 2008 г., 13:41
 */
package tradeterminal.users;

import java.util.Arrays;
import javax.swing.JOptionPane;
import minersinstrument.ui.IADialogPanel;

/**
 *
 * @author  PKopychenko
 */
public class ChangeUserPasswordDPanel extends javax.swing.JPanel implements IADialogPanel {

    /** Creates new form changeUserPasswordDPanel */
    public ChangeUserPasswordDPanel() {
        initComponents();
    }

    public boolean checkPanel() {
        if (!Arrays.equals(jPasswordField1.getPassword(),
                (jPasswordField2.getPassword()))) {
            JOptionPane.showMessageDialog(
                    this,
                    "Пороли не совпадают, введите пароль корректно.",
                    "Внимание...",
                    JOptionPane.ERROR_MESSAGE);

            jPasswordField1.setText("");
            jPasswordField2.setText("");

            jPasswordField1.requestFocus();

            return false;
        }


        return true;
    }

    public void openPanel() {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ChangeUserPasswordDPanel.class);
        setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("Form.border.lineColor"))); // NOI18N
        setName("Form"); // NOI18N

        jPasswordField1.setText(resourceMap.getString("jPasswordField1.text")); // NOI18N
        jPasswordField1.setName("jPasswordField1"); // NOI18N

        jPasswordField2.setText(resourceMap.getString("jPasswordField2.text")); // NOI18N
        jPasswordField2.setName("jPasswordField2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2)).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE).addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2)).addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    public char[] getJPassword() {
        return jPasswordField1.getPassword();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables
}
