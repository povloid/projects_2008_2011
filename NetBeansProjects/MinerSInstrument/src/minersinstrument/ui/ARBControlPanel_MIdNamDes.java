/*
 * ARBControlPanel_MIdNamDes.java
 *
 * Created on 2 Март 2008 г., 23:47
 */
package minersinstrument.ui;

import javax.swing.Icon;
import minersinstrument.db.PARBJDBCAdapter_MIdNamDes;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author  kopychenko
 */
public class ARBControlPanel_MIdNamDes extends javax.swing.JPanel implements IADialogPanel {

    private PARBJDBCAdapter_MIdNamDes adapter;
    private String captionText;

    /** Creates new form ARBControlPanel_MIdNamDes */
    public ARBControlPanel_MIdNamDes(String captionText, PGPoolingDataSource source, String tName, String sequence) {
        this.captionText = captionText;

        initComponents();

        adapter = new PARBJDBCAdapter_MIdNamDes(source, tName, sequence);

        aDBJXTable.setAdapter(adapter);

        aDBJXTable.getColumnModel().removeColumn(aDBJXTable.getColumnModel().getColumn(0));

        aDBJXTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        aDBJXTable.getColumnModel().getColumn(0).setHeaderValue("Наименование");
        aDBJXTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        aDBJXTable.getColumnModel().getColumn(1).setHeaderValue("Описание");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        aDBJXTable = new minersinstrument.ui.ADBJXTable();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout(5, 5));

        aDBJXTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}));
        jScrollPane1.setViewportView(aDBJXTable);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minersinstrument/ui/icons/add_24.png"))); // NOI18N
        addButton.setText("Доб.");
        addButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minersinstrument/ui/icons/edit_24.png"))); // NOI18N
        editButton.setText("Ред.");
        editButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jPanel1.add(editButton);

        delButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minersinstrument/ui/icons/remov_24.png"))); // NOI18N
        delButton.setText("Уд.");
        delButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });
        jPanel1.add(delButton);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        aDBJXTable.addRow();
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        aDBJXTable.editRow();
    }//GEN-LAST:event_editButtonActionPerformed

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        aDBJXTable.delRow();
    }//GEN-LAST:event_delButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minersinstrument.ui.ADBJXTable aDBJXTable;
    private javax.swing.JButton addButton;
    private javax.swing.JButton delButton;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void updateContent() {
        adapter.updateModel();
    }

    public boolean checkPanel() {
        return true;
    }

    public void openPanel() {
    }

    public String getCaptionText() {
        return captionText;
    }

    public Icon getIcon() {
        return new javax.swing.ImageIcon(getClass().getResource("/minersinstrument/ui/icons/addbk_32.png"));
    }
}
