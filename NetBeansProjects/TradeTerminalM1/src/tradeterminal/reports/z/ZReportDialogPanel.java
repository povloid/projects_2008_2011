/*
 * ZReportDialogPanel.java
 *
 * Created on 16 Июль 2008 г., 21:42
 */
package tradeterminal.reports.z;

import java.math.BigDecimal;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import minersinstrument.ui.ATimeStampCellRender;
import minersinstrument.ui.IADialogPanel;
import tradeterminal.Setup;

/**
 *
 * @author  povlo
 */
public class ZReportDialogPanel extends javax.swing.JPanel implements IADialogPanel {

    private ZReportDialogAdapter zda = new ZReportDialogAdapter(Setup.getSource());
    private int zId = -1;
    private Date orderDate;
    private double summ;
    private String description;

    /** Creates new form ZReportDialogPanel */
    public ZReportDialogPanel() {
        initComponents();

        table.setAdapter(zda);

        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(0));

        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(0).setHeaderValue("№");

        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setHeaderValue("Дата");
        table.getColumnModel().getColumn(1).setCellRenderer(new ATimeStampCellRender());

        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setHeaderValue("сумма");

        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setHeaderValue("Описание");

        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {

                            // Получаем индекc в таблице
                            int tableSelectedRow = table.getSelectedRow();

                            if (tableSelectedRow > -1) {
                                // Получаем реальный индекс в модели
                                int modelSelectedRow = table.convertRowIndexToModel(tableSelectedRow);
                                zId = (Integer) table.getModel().getValueAt(modelSelectedRow, 1);
                                System.out.println(zId);

                                orderDate = (Date) table.getModel().getValueAt(modelSelectedRow, 2);

                                summ = ((BigDecimal) table.getModel().getValueAt(modelSelectedRow, 3)).doubleValue();

                                description = (String) table.getModel().getValueAt(modelSelectedRow, 4);

                            }
                        }
                    }
                });

    }

    public int getZId() {
        return zId;
    }

    public String getDescription() {
        return description;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getSumm() {
        return summ;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new minersinstrument.ui.ADBJXTable();

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        table.setName("table"); // NOI18N
        jScrollPane1.setViewportView(table);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ZReportDialogPanel.class);
        table.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("table.columnModel.title0")); // NOI18N
        table.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("table.columnModel.title1")); // NOI18N
        table.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("table.columnModel.title2")); // NOI18N
        table.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("table.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE).addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private minersinstrument.ui.ADBJXTable table;
    // End of variables declaration//GEN-END:variables

    public boolean checkPanel() {

        if (zId == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Z-отчет не задан.",
                    "Внимание...",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }


        return true;
    }

    public void openPanel() {
    }
}
