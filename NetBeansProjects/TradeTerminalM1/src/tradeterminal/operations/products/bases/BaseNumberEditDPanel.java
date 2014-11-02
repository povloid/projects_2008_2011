/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BaseNumberEditDPanel.java
 *
 * Created on 07.08.2009, 22:14:39
 */
package tradeterminal.operations.products.bases;

/**
 *
 * @author pk
 */
public class BaseNumberEditDPanel extends javax.swing.JPanel {

    protected double min;
    protected double max;

    /** Creates new form BaseNumberEditDPanel */
    public BaseNumberEditDPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prefixLabel = new javax.swing.JLabel();
        minButton = new javax.swing.JButton();
        numberSpinner = new javax.swing.JSpinner();
        maxButton = new javax.swing.JButton();
        suffixLabel = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(550, 70));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(BaseNumberEditDPanel.class);
        prefixLabel.setText(resourceMap.getString("prefixLabel.text")); // NOI18N
        prefixLabel.setName("prefixLabel"); // NOI18N

        minButton.setText(resourceMap.getString("minButton.text")); // NOI18N
        minButton.setName("minButton"); // NOI18N
        minButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minButtonActionPerformed(evt);
            }
        });

        numberSpinner.setFont(resourceMap.getFont("numberSpinner.font")); // NOI18N
        numberSpinner.setName("numberSpinner"); // NOI18N
        numberSpinner.setPreferredSize(new java.awt.Dimension(300, 56));

        maxButton.setText(resourceMap.getString("maxButton.text")); // NOI18N
        maxButton.setName("maxButton"); // NOI18N
        maxButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxButtonActionPerformed(evt);
            }
        });

        suffixLabel.setText(resourceMap.getString("suffixLabel.text")); // NOI18N
        suffixLabel.setName("suffixLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 550, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(0, 13, Short.MAX_VALUE).addComponent(prefixLabel).addGap(5, 5, 5).addComponent(minButton).addGap(5, 5, 5).addComponent(numberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(5, 5, 5).addComponent(maxButton).addGap(5, 5, 5).addComponent(suffixLabel).addGap(0, 14, Short.MAX_VALUE))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 70, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(0, 7, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(prefixLabel)).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(minButton)).addComponent(numberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(maxButton)).addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(suffixLabel))).addGap(0, 7, Short.MAX_VALUE))));
    }// </editor-fold>//GEN-END:initComponents

    private void minButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minButtonActionPerformed

        numberSpinner.setValue(0d);

    }//GEN-LAST:event_minButtonActionPerformed

    private void maxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxButtonActionPerformed

        numberSpinner.setValue(max);

    }//GEN-LAST:event_maxButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton maxButton;
    protected javax.swing.JButton minButton;
    protected javax.swing.JSpinner numberSpinner;
    protected javax.swing.JLabel prefixLabel;
    protected javax.swing.JLabel suffixLabel;
    // End of variables declaration//GEN-END:variables
}