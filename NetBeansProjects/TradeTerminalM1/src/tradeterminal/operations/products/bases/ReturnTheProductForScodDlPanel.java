/*
 * ReturnTheProductControlPanel.java
 *
 * Created on 4 Март 2008 г., 15:05
 */
package tradeterminal.operations.products.bases;

import minersinstrument.ui.ADialog;
import minersinstrument.ui.AUniversalDialog;
import minersinstrument.ui.IADialogPanel;
import org.jdesktop.application.Action;
import tradeterminal.products.ProductsMainPanel;

/**
 *
 * @author  PKopychenko
 */
public class ReturnTheProductForScodDlPanel extends javax.swing.JPanel
        implements IADialogPanel {

    /** Creates new form ReturnTheProductControlPanel */
    public ReturnTheProductForScodDlPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scodTextField = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getActionMap(ReturnTheProductForScodDlPanel.class, this);
        jButton1.setAction(actionMap.get("findProduct")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ReturnTheProductForScodDlPanel.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        scodTextField.setText(resourceMap.getString("scodTextField.text")); // NOI18N
        scodTextField.setName("scodTextField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(scodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public Object getScod() {
        return scodTextField.getText();
    }

    public boolean checkPanel() {
        return true;
    }

    public void openPanel() {
        scodTextField.requestFocus();
    }

    /**
     * Поиск товара по справочнику
     */
    @Action
    public void findProduct() {

        ProductsMainPanel p = new ProductsMainPanel();
        p.setVisibleEditBattons(false);
        AUniversalDialog d = new AUniversalDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/poisk.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            scodTextField.setText(p.getSelectedScode());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField scodTextField;
    // End of variables declaration//GEN-END:variables
}
