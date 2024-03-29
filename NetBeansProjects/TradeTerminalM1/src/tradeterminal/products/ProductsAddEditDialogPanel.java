/*
 * ProductsAddEditDialogPanel.java
 *
 * Created on 1 Март 2008 г., 3:59
 */
package tradeterminal.products;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import minersinstrument.db.PAJDBCComboBoxModel;
import minersinstrument.ui.ADialogFocusLisner;
import minersinstrument.ui.IADialogPanel;
import org.jdesktop.application.Action;
import org.postgresql.ds.PGPoolingDataSource;
import tradeterminal.Setup;

/**
 *
 * @author  kopychenko
 */
public class ProductsAddEditDialogPanel extends javax.swing.JPanel implements IADialogPanel {

    class Rb_measuresComboBoxModel extends PAJDBCComboBoxModel {

        public Rb_measuresComboBoxModel(PGPoolingDataSource source) {
            super(source);
        }

        protected PAJDBCCResult vExecuteQuery(Connection conn) throws SQLException {
            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall("{ ? = call rb_measures_select() }");
            proc.registerOutParameter(1, Types.OTHER);

            proc.execute();

            return new PAJDBCCResult((ResultSet) proc.getObject(1), conn, proc);
        }
    }
    private Rb_measuresComboBoxModel rb_measuresComboBoxModel = new Rb_measuresComboBoxModel(Setup.getSource());
    private NumberFormat nf = NumberFormat.getInstance();

    /** Creates new form ProductsAddEditDialogPanel */
    public ProductsAddEditDialogPanel() {
        initComponents();

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(32, 32));
        jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(32, 32));


        quantityFormattedTextField.setValue(0);

        ADialogFocusLisner adf = new ADialogFocusLisner();

        list_priceSpinner.setModel(new SpinnerNumberModel(
                0,
                0,
                1000000000,
                0.01));

        ((JSpinner.DefaultEditor) list_priceSpinner.getEditor()).getTextField().addFocusListener(adf);
        ((JSpinner.DefaultEditor) list_priceSpinner.getEditor()).getTextField().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcTotal();
            }
        });


        spec_priceSpinner.setModel(new SpinnerNumberModel(
                0,
                0,
                1000000000,
                0.01));

        ((JSpinner.DefaultEditor) spec_priceSpinner.getEditor()).getTextField().addFocusListener(adf);
        ((JSpinner.DefaultEditor) spec_priceSpinner.getEditor()).getTextField().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcTotal();
            }
        });

        percent_discountSpinner.setModel(new SpinnerNumberModel(
                0,
                0,
                100,
                0.01));

        ((JSpinner.DefaultEditor) percent_discountSpinner.getEditor()).getTextField().addFocusListener(adf);
        ((JSpinner.DefaultEditor) percent_discountSpinner.getEditor()).getTextField().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcTotal();
            }
        });

        edSpecPriceFormattedTextField.setValue(0);
        totalSpecFormattedTextField1.setValue(0);

        measures_idComboBox.setModel(rb_measuresComboBoxModel);


        setNoSpecPriceVarioants();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        nameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        quantityFormattedTextField = new javax.swing.JFormattedTextField();
        measures_idComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spec_priceRadioButton = new javax.swing.JRadioButton();
        percent_discountRadioButton = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        edListPriceFormattedTextField = new javax.swing.JFormattedTextField();
        totalListPriceSpecFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        edSpecPriceFormattedTextField = new javax.swing.JFormattedTextField();
        totalSpecFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        noSpecPriceRadioButton = new javax.swing.JRadioButton();
        scodTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        list_priceSpinner = new javax.swing.JSpinner();
        percent_discountSpinner = new javax.swing.JSpinner();
        spec_priceSpinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ProductsAddEditDialogPanel.class);
        nameTextField.setText(resourceMap.getString("nameTextField.text")); // NOI18N
        nameTextField.setName("nameTextField"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        jScrollPane1.setViewportView(descriptionTextArea);

        quantityFormattedTextField.setEditable(false);
        quantityFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        quantityFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantityFormattedTextField.setText(resourceMap.getString("quantityFormattedTextField.text")); // NOI18N
        quantityFormattedTextField.setFocusable(false);
        quantityFormattedTextField.setFont(resourceMap.getFont("quantityFormattedTextField.font")); // NOI18N
        quantityFormattedTextField.setName("quantityFormattedTextField"); // NOI18N
        quantityFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityFormattedTextFieldKeyReleased(evt);
            }
        });

        measures_idComboBox.setName("measures_idComboBox"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getActionMap(ProductsAddEditDialogPanel.class, this);
        spec_priceRadioButton.setAction(actionMap.get("setSpec_priceVariant")); // NOI18N
        buttonGroup1.add(spec_priceRadioButton);
        spec_priceRadioButton.setName("spec_priceRadioButton"); // NOI18N

        percent_discountRadioButton.setAction(actionMap.get("setPercent_discountVariant")); // NOI18N
        buttonGroup1.add(percent_discountRadioButton);
        percent_discountRadioButton.setText(resourceMap.getString("percent_discountRadioButton.text")); // NOI18N
        percent_discountRadioButton.setName("percent_discountRadioButton"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        edListPriceFormattedTextField.setEditable(false);
        edListPriceFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        edListPriceFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        edListPriceFormattedTextField.setText(resourceMap.getString("edListPriceFormattedTextField.text")); // NOI18N
        edListPriceFormattedTextField.setFocusable(false);
        edListPriceFormattedTextField.setFont(resourceMap.getFont("edListPriceFormattedTextField.font")); // NOI18N
        edListPriceFormattedTextField.setName("edListPriceFormattedTextField"); // NOI18N

        totalListPriceSpecFormattedTextField.setEditable(false);
        totalListPriceSpecFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        totalListPriceSpecFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalListPriceSpecFormattedTextField.setText(resourceMap.getString("totalListPriceSpecFormattedTextField.text")); // NOI18N
        totalListPriceSpecFormattedTextField.setFocusable(false);
        totalListPriceSpecFormattedTextField.setFont(resourceMap.getFont("totalListPriceSpecFormattedTextField.font")); // NOI18N
        totalListPriceSpecFormattedTextField.setName("totalListPriceSpecFormattedTextField"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        edSpecPriceFormattedTextField.setEditable(false);
        edSpecPriceFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        edSpecPriceFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        edSpecPriceFormattedTextField.setText(resourceMap.getString("edSpecPriceFormattedTextField.text")); // NOI18N
        edSpecPriceFormattedTextField.setFocusable(false);
        edSpecPriceFormattedTextField.setFont(resourceMap.getFont("edSpecPriceFormattedTextField.font")); // NOI18N
        edSpecPriceFormattedTextField.setName("edSpecPriceFormattedTextField"); // NOI18N

        totalSpecFormattedTextField1.setEditable(false);
        totalSpecFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        totalSpecFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalSpecFormattedTextField1.setText(resourceMap.getString("totalSpecFormattedTextField1.text")); // NOI18N
        totalSpecFormattedTextField1.setFocusable(false);
        totalSpecFormattedTextField1.setFont(resourceMap.getFont("totalSpecFormattedTextField1.font")); // NOI18N
        totalSpecFormattedTextField1.setName("totalSpecFormattedTextField1"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(edListPriceFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE).addComponent(totalListPriceSpecFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE).addComponent(jLabel11)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(edSpecPriceFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE).addComponent(totalSpecFormattedTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE).addComponent(jLabel8)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel11).addComponent(jLabel8)).addGap(5, 5, 5).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel9).addComponent(edSpecPriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(edListPriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel10).addComponent(totalListPriceSpecFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(totalSpecFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(16, Short.MAX_VALUE)));

        noSpecPriceRadioButton.setAction(actionMap.get("setNoSpecPriceVarioants")); // NOI18N
        buttonGroup1.add(noSpecPriceRadioButton);
        noSpecPriceRadioButton.setSelected(true);
        noSpecPriceRadioButton.setName("noSpecPriceRadioButton"); // NOI18N

        scodTextField.setText(resourceMap.getString("scodTextField.text")); // NOI18N
        scodTextField.setName("scodTextField"); // NOI18N
        scodTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scodTextFieldActionPerformed(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        list_priceSpinner.setName("list_priceSpinner"); // NOI18N
        list_priceSpinner.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                list_priceSpinnerStateChanged(evt);
            }
        });

        percent_discountSpinner.setName("percent_discountSpinner"); // NOI18N
        percent_discountSpinner.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                percent_discountSpinnerStateChanged(evt);
            }
        });

        spec_priceSpinner.setName("spec_priceSpinner"); // NOI18N
        spec_priceSpinner.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spec_priceSpinnerStateChanged(evt);
            }
        });

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel2)).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel3)).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel5)).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel6)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(percent_discountRadioButton).addComponent(noSpecPriceRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel4).addGap(76, 76, 76))).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(spec_priceRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(scodTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(quantityFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(percent_discountSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE).addComponent(spec_priceSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE).addComponent(list_priceSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel13).addComponent(jLabel12).addComponent(jLabel7)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(measures_idComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)).addGap(10, 10, 10)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(scodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(quantityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel1).addComponent(measures_idComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(jLabel3)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(list_priceSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel12)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(noSpecPriceRadioButton).addGap(24, 24, 24)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(spec_priceSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(spec_priceRadioButton).addComponent(jLabel13))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(percent_discountRadioButton).addComponent(percent_discountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel7))).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void quantityFormattedTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityFormattedTextFieldKeyReleased
        try {
            quantityFormattedTextField.commitEdit();
            calcTotal();
        } catch (ParseException ex) {
        }
    }//GEN-LAST:event_quantityFormattedTextFieldKeyReleased

    private void scodTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scodTextFieldActionPerformed
    }//GEN-LAST:event_scodTextFieldActionPerformed

    private void list_priceSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_list_priceSpinnerStateChanged
        calcTotal();
    }//GEN-LAST:event_list_priceSpinnerStateChanged

    private void spec_priceSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spec_priceSpinnerStateChanged
        calcTotal();
    }//GEN-LAST:event_spec_priceSpinnerStateChanged

    private void percent_discountSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_percent_discountSpinnerStateChanged
        calcTotal();
    }//GEN-LAST:event_percent_discountSpinnerStateChanged

    public void commitAllFormatedTextFields() {
        try {

            quantityFormattedTextField.commitEdit();
            list_priceSpinner.commitEdit();
            spec_priceSpinner.commitEdit();
            percent_discountSpinner.commitEdit();

            double spec_price = ((Double) spec_priceSpinner.getValue());
            double percent_discount = ((Double) percent_discountSpinner.getValue()).doubleValue();


            if (spec_price > 0) {
                setSpec_priceVariant();
                spec_priceRadioButton.setSelected(true);
            } else if (percent_discount > 0) {
                setPercent_discountVariant();
                percent_discountRadioButton.setSelected(true);
            }

            calcTotal();
        } catch (ParseException ex) {
        }
    }

    private void calcTotal() {

        double quantity = ((Number) quantityFormattedTextField.getValue()).doubleValue();

        double list_price = 0d;
        double spec_price = 0d;
        double percent_discount = 0d;

        try {
            list_price = nf.parse(((JSpinner.DefaultEditor) list_priceSpinner.getEditor()).getTextField().getText()).doubleValue();
            spec_price = nf.parse(((JSpinner.DefaultEditor) spec_priceSpinner.getEditor()).getTextField().getText()).doubleValue();
            percent_discount = nf.parse(((JSpinner.DefaultEditor) percent_discountSpinner.getEditor()).getTextField().getText()).doubleValue();

        } catch (ParseException ex) {
            //Logger.getLogger(ProductsAddEditDialogPanel.class.getName()).log(Level.SEVERE, null, ex);
        }


        double edSpecPrice = 0;

        if (spec_priceRadioButton.isSelected()) {
            edSpecPrice = spec_price;
        } else if (percent_discountRadioButton.isSelected()) {
            edSpecPrice = list_price - list_price * percent_discount * 0.01d;
        } else {
            edSpecPrice = list_price;
        }
        double totalSpecPrice = edSpecPrice * quantity;
        double totalListPrice = list_price * quantity;

        edSpecPriceFormattedTextField.setValue(edSpecPrice);
        edListPriceFormattedTextField.setValue(list_price);

        totalSpecFormattedTextField1.setValue(totalSpecPrice);
        totalListPriceSpecFormattedTextField.setValue(totalListPrice);

    }

    public String getDescription() {
        return descriptionTextArea.getText();
    }

    public void setDescription(String s) {
        this.descriptionTextArea.setText(s);
    }

    public Object getList_price() {
        return list_priceSpinner.getValue();
    }

    public void setList_price(Object o) {
        list_priceSpinner.setValue(o);
    }

    public int getMeasures_id() {
        return rb_measuresComboBoxModel.getSelectedRowId();
    }

    public String getMeasures_Name() {
        return rb_measuresComboBoxModel.getSelectedRowName();
    }

    public String getMeasures_Description() {
        return rb_measuresComboBoxModel.getSelectedRowDescription();
    }

    public void setMeasures_id(int i) {
        rb_measuresComboBoxModel.setSelectedRowId(i);
    }

    public String get_Name() {
        return nameTextField.getText();
    }

    public void set_Name(String s) {
        this.nameTextField.setText(s);
    }

    public Object getPercent_discount() {
        return percent_discountSpinner.getValue();
    }

    public void setPercent_discount(Object o) {
        percent_discountSpinner.setValue(o);
    }

    public Object getQuantity() {
        return quantityFormattedTextField.getValue();
    }

    public void setQuantity(Object o) {
        if (o != null) {
            this.quantityFormattedTextField.setValue(o);
        } else {
            this.quantityFormattedTextField.setValue(0);
        }
    }

    public String getScod() {
        return scodTextField.getText();
    }

    public void setScod(String s) {
        this.scodTextField.setText(s);
    }

    public Object getSpec_price() {
        return spec_priceSpinner.getValue();
    }

    public void setSpec_price(Object o) {
        spec_priceSpinner.setValue(o);
    }

    public Object getTotalSpec_price() {
        return totalSpecFormattedTextField1.getValue();
    }

    public Object getEdSpec_price() {
        return edSpecPriceFormattedTextField.getValue();
    }

    @Action
    public void setSpec_priceVariant() {
        spec_priceSpinner.setEnabled(true);
        spec_priceSpinner.setFocusable(true);
        percent_discountSpinner.setEnabled(false);
        percent_discountSpinner.setFocusable(false);
        percent_discountSpinner.setValue(0d);
        calcTotal();
    }

    @Action
    public void setPercent_discountVariant() {
        spec_priceSpinner.setEnabled(false);
        spec_priceSpinner.setFocusable(false);

        spec_priceSpinner.setValue(0d);
        percent_discountSpinner.setEnabled(true);
        percent_discountSpinner.setFocusable(true);
        calcTotal();
    }

    @Action
    public void setNoSpecPriceVarioants() {
        spec_priceSpinner.setEnabled(false);
        spec_priceSpinner.setValue(0d);
        spec_priceSpinner.setFocusable(false);
        percent_discountSpinner.setEnabled(false);
        percent_discountSpinner.setValue(0d);
        percent_discountSpinner.setFocusable(false);
        calcTotal();
    }

    @Action
    public void findProductForSCod() {
    }

    public boolean checkPanel() {
        if (nameTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Введите наименование товара.",
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);

            nameTextField.requestFocus();
            return false;
        }


        // Проверка ввода штрихкода
        if (scodTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Введите номер штрихкода.",
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);

            scodTextField.requestFocus();
            return false;
        }

        // Если курсор находится в поле ввода штрихкода
        if (scodTextField.isFocusOwner()) {
            ((JSpinner.DefaultEditor) list_priceSpinner.getEditor()).getTextField().requestFocus();
            return false;
        }

        return true;
    }

    public void openPanel() {
        nameTextField.requestFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JFormattedTextField edListPriceFormattedTextField;
    private javax.swing.JFormattedTextField edSpecPriceFormattedTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner list_priceSpinner;
    private javax.swing.JComboBox measures_idComboBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JRadioButton noSpecPriceRadioButton;
    private javax.swing.JRadioButton percent_discountRadioButton;
    private javax.swing.JSpinner percent_discountSpinner;
    private javax.swing.JFormattedTextField quantityFormattedTextField;
    private javax.swing.JTextField scodTextField;
    private javax.swing.JRadioButton spec_priceRadioButton;
    private javax.swing.JSpinner spec_priceSpinner;
    private javax.swing.JFormattedTextField totalListPriceSpecFormattedTextField;
    private javax.swing.JFormattedTextField totalSpecFormattedTextField1;
    // End of variables declaration//GEN-END:variables
}
