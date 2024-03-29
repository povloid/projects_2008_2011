/*
 * ProductsOperationBasePanel.java
 *
 * Created on 4 Март 2008 г., 15:08
 */
package tradeterminal.operations.products.bases;

import tradeterminal.references_books.customers.FindCustomerDPAnel;
import javax.swing.Icon;
import tradeterminal.operations.products.*;
import org.jdesktop.application.Action;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.AUniversalDialog;
import tradeterminal.IPage;
import tradeterminal.products.ProductsMainPanel;

/**
 *
 * @author  PKopychenko
 */
public class ProductsOperationBaseMainPanel extends javax.swing.JPanel
        implements ContainerListener, KeyListener, IPage {

    protected ProductOperationsBaseModel model;
    private String enterString = "";
    MaskFormatter scodFormatter;
    protected List<JMenuItem> menuItemList = new ArrayList<JMenuItem>();
    protected ImageIcon measuresIcon1;
    protected ImageIcon measuresIcon2;

    /** Creates new form ProductsOperationBasePanel */
    public ProductsOperationBaseMainPanel(final ProductOperationsBaseModel model) {
        this.model = model;


        measuresIcon1 = new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/kolvo.png"));
        measuresIcon2 = new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/kolvo2.png"));

        initComponents();


        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(32, 32));
        jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(32, 32));

        jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(32, 32));
        jScrollPane2.getHorizontalScrollBar().setPreferredSize(new Dimension(32, 32));



        // Заполняем список меню
        menuItemList.add(jMenuItem1);
        menuItemList.add(jMenuItem2);
        menuItemList.add(jMenuItem3);
//        menuItemList.add(jMenuItem4);
        menuItemList.add(jMenuItem5);
        menuItemList.add(jMenuItem6);

        addKeyAndContainerListenerRecursively(this);

        operationTable.setModel(model);
        operationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        operationTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        operationTable.setEditable(false);

        operationTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            tabloFormattedTextField.setValue(model.getSummForAllProducts());

                            renderBalancesPanel();

                            // Получаем индекc в таблице
                            int tableSelectedRow = operationTable.getSelectedRow();

                            if (tableSelectedRow > -1) {
                                // Получаем реальный индекс в модели
                                int modelSelectedRow = operationTable.convertRowIndexToModel(tableSelectedRow);

                                if (model.getProductRowAt(modelSelectedRow).ismType()) {
                                    jButton3.setIcon(measuresIcon2);
                                } else {
                                    jButton3.setIcon(measuresIcon1);
                                }

                                tableRowValueChanged(modelSelectedRow);

                            } else {
                                jButton3.setIcon(measuresIcon1);
                            }


                        }
                    }
                });
    }


    protected void tableRowValueChanged(int modelSelectedRow){

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jLabel1 = new javax.swing.JLabel();
        tabloFormattedTextField = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        sellingButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        createNewOperationButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        findProductButton = new javax.swing.JButton();
        enterProductForScodButton = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        operationTable = new org.jdesktop.swingx.JXTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerInfoTextArea = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        currentBalanceLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        toCassLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        toCreditLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        newBalanceLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getActionMap(ProductsOperationBaseMainPanel.class, this);
        jMenuItem1.setAction(actionMap.get("createNewOperation")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N

        jMenuItem2.setAction(actionMap.get("enterProductForScod")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N

        jMenuItem3.setAction(actionMap.get("setProductQuantity")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N

        jMenuItem5.setAction(actionMap.get("removeProduct")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N

        jMenuItem6.setAction(actionMap.get("enter")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 550));
        setLayout(new java.awt.BorderLayout(5, 5));

        jXPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jXPanel1.setInheritsPopupMenu(true);
        jXPanel1.setName("jXPanel1"); // NOI18N
        jXPanel1.setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ProductsOperationBaseMainPanel.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jXPanel1.add(jLabel1, java.awt.BorderLayout.LINE_START);

        tabloFormattedTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        tabloFormattedTextField.setEditable(false);
        tabloFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        tabloFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tabloFormattedTextField.setFont(resourceMap.getFont("tabloFormattedTextField.font")); // NOI18N
        tabloFormattedTextField.setInheritsPopupMenu(true);
        tabloFormattedTextField.setName("tabloFormattedTextField"); // NOI18N
        jXPanel1.add(tabloFormattedTextField, java.awt.BorderLayout.CENTER);

        jPanel4.setInheritsPopupMenu(true);
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        sellingButton.setAction(actionMap.get("enter")); // NOI18N
        sellingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sellingButton.setInheritsPopupMenu(true);
        sellingButton.setName("sellingButton"); // NOI18N
        sellingButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        sellingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(sellingButton, java.awt.BorderLayout.CENTER);

        jXPanel1.add(jPanel4, java.awt.BorderLayout.LINE_END);

        add(jXPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel1.setInheritsPopupMenu(true);
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel3.setInheritsPopupMenu(true);
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(120, 114));
        jPanel3.setLayout(new java.awt.GridLayout(12, 0));

        createNewOperationButton.setAction(actionMap.get("createNewOperation")); // NOI18N
        createNewOperationButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        createNewOperationButton.setInheritsPopupMenu(true);
        createNewOperationButton.setName("createNewOperationButton"); // NOI18N
        jPanel3.add(createNewOperationButton);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel3.add(jPanel6);

        findProductButton.setAction(actionMap.get("findProduct")); // NOI18N
        findProductButton.setText(resourceMap.getString("findProductButton.text")); // NOI18N
        findProductButton.setName("findProductButton"); // NOI18N
        jPanel3.add(findProductButton);

        enterProductForScodButton.setAction(actionMap.get("enterProductForScod")); // NOI18N
        enterProductForScodButton.setText(resourceMap.getString("enterProductForScodButton.text")); // NOI18N
        enterProductForScodButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        enterProductForScodButton.setInheritsPopupMenu(true);
        enterProductForScodButton.setName("enterProductForScodButton"); // NOI18N
        jPanel3.add(enterProductForScodButton);

        jPanel14.setName("jPanel14"); // NOI18N
        jPanel3.add(jPanel14);

        jButton3.setAction(actionMap.get("setProductQuantity")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setInheritsPopupMenu(true);
        jButton3.setName("jButton3"); // NOI18N
        jPanel3.add(jButton3);

        jButton5.setAction(actionMap.get("removeProduct")); // NOI18N
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setInheritsPopupMenu(true);
        jButton5.setName("jButton5"); // NOI18N
        jPanel3.add(jButton5);

        jPanel15.setName("jPanel15"); // NOI18N
        jPanel3.add(jPanel15);

        jButton1.setAction(actionMap.get("findCostumer")); // NOI18N
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setName("jButton1"); // NOI18N
        jPanel3.add(jButton1);

        jButton2.setAction(actionMap.get("setOperattionAsCredit")); // NOI18N
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setName("jButton2"); // NOI18N
        jPanel3.add(jButton2);

        jButton4.setAction(actionMap.get("clearCustomerReference")); // NOI18N
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setName("jButton4"); // NOI18N
        jPanel3.add(jButton4);

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel5.setInheritsPopupMenu(true);
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setInheritsPopupMenu(true);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        operationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        operationTable.setColumnControlVisible(true);
        operationTable.setInheritsPopupMenu(true);
        operationTable.setName("operationTable"); // NOI18N
        operationTable.setShowGrid(true);
        jScrollPane1.setViewportView(operationTable);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel8.border.title"))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        customerInfoTextArea.setColumns(30);
        customerInfoTextArea.setEditable(false);
        customerInfoTextArea.setLineWrap(true);
        customerInfoTextArea.setRows(5);
        customerInfoTextArea.setName("customerInfoTextArea"); // NOI18N
        jScrollPane2.setViewportView(customerInfoTextArea);

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel9.border.title"))); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N
        jPanel9.setLayout(new java.awt.GridLayout(4, 0));

        jPanel10.setName("jPanel10"); // NOI18N
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel10.add(jLabel2);

        currentBalanceLabel.setFont(resourceMap.getFont("toCreditLabel.font")); // NOI18N
        currentBalanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        currentBalanceLabel.setText(resourceMap.getString("currentBalanceLabel.text")); // NOI18N
        currentBalanceLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        currentBalanceLabel.setName("currentBalanceLabel"); // NOI18N
        currentBalanceLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel10.add(currentBalanceLabel);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel10.add(jLabel3);

        jPanel9.add(jPanel10);

        jPanel11.setName("jPanel11"); // NOI18N
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel11.add(jLabel4);

        toCassLabel.setFont(resourceMap.getFont("toCreditLabel.font")); // NOI18N
        toCassLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        toCassLabel.setText(resourceMap.getString("toCassLabel.text")); // NOI18N
        toCassLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        toCassLabel.setName("toCassLabel"); // NOI18N
        toCassLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel11.add(toCassLabel);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel11.add(jLabel5);

        jPanel9.add(jPanel11);

        jPanel12.setName("jPanel12"); // NOI18N
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel12.add(jLabel6);

        toCreditLabel.setFont(resourceMap.getFont("toCreditLabel.font")); // NOI18N
        toCreditLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        toCreditLabel.setText(resourceMap.getString("toCreditLabel.text")); // NOI18N
        toCreditLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        toCreditLabel.setName("toCreditLabel"); // NOI18N
        toCreditLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel12.add(toCreditLabel);

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel12.add(jLabel7);

        jPanel9.add(jPanel12);

        jPanel13.setName("jPanel13"); // NOI18N
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel13.add(jLabel8);

        newBalanceLabel.setFont(resourceMap.getFont("toCreditLabel.font")); // NOI18N
        newBalanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newBalanceLabel.setText(resourceMap.getString("newBalanceLabel.text")); // NOI18N
        newBalanceLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        newBalanceLabel.setName("newBalanceLabel"); // NOI18N
        newBalanceLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel13.add(newBalanceLabel);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel13.add(jLabel9);

        jPanel9.add(jPanel13);

        jPanel8.add(jPanel9, java.awt.BorderLayout.LINE_END);

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void createNewOperation() {
        model.createNewOrder();
        currentBalance = 0;
        // Отрисовка интерфейса
        customerInfoTextArea.setText("клиент не выбран");
        renderBalancesPanel();
    }

    @Action
    public void enterProductForScod() {
        ReturnTheProductForScodDlPanel p = new ReturnTheProductForScodDlPanel();
        AUniversalDialog d = new AUniversalDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/poiskpokodu.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            findProductForScod(p.getScod().toString());
        }
    }

    private void findProductForScod(String scod) {
        int id = model.findAndInsertProductForScod(scod);
        if (id == ProductOperationsBaseModel.PRODUCT_QUANTITY_IS_ZIRO) {
            JOptionPane.showMessageDialog(
                    this,
                    "Данного товара в наличии не имеется.",
                    "Информация...",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (id != ProductOperationsBaseModel.THE_PRODUCT_HAVE_NOT) {
            int tableIndex = operationTable.convertRowIndexToView(id);
            operationTable.setRowSelectionInterval(tableIndex, tableIndex);
            operationTable.scrollRowToVisible(operationTable.getSelectedRow());
            operationTable.repaint();
            setProductQuantity();
        }
    }

    @Action
    public void enter() {

        if (!(JOptionPane.showConfirmDialog(this,
                "Вы желаете провести операцию?",
                "Внимание...",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)) {
            return;
        }



        int result = model.executeOpertion().getResult();

        if (result == 1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Операция продажи проведена успешно.",
                    "Информация...",
                    JOptionPane.INFORMATION_MESSAGE);

            model.createNewOrder();

        } else if (result == -2) {
            JOptionPane.showMessageDialog(
                    this,
                    "Вы ничего не выбрали. Операция не проведена.",
                    "Внимание...",
                    JOptionPane.WARNING_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "При проведении операции возникла ошибка. Операция не проведена.",
                    "Ошибка...",
                    JOptionPane.ERROR_MESSAGE);
        }

        createNewOperation();
    }

    @Action
    public void setCast() {
    }

    @Action
    public void setProductQuantity() {
        // Получаем индекc в таблице
        int tableSelectedRow = operationTable.getSelectedRow();

        if (tableSelectedRow != -1) { // Если элемент всеже выбран
            // Получаем реальный индекс в модели

            int modelSelectedRow = operationTable.convertRowIndexToModel(tableSelectedRow);

            ProductQuantityDPanel p = new ProductQuantityDPanel(model.getProductId(modelSelectedRow), model.getQuantity(modelSelectedRow));
            AUniversalDialog d = new AUniversalDialog(p, null, true);

            if (model.getProductRowAt(modelSelectedRow).ismType()) {
                d.setTitleIcon(measuresIcon2);
            } else {
                d.setTitleIcon(measuresIcon1);
            }

            d.setVisible(true);
            d.dispose();

            if (d.getReturnStatus() == ADialog.RET_OK) {
                model.setQuantity(modelSelectedRow, p.getQuantity());

                operationTable.updateUI();
                operationTable.setRowSelectionInterval(tableSelectedRow, tableSelectedRow);
                operationTable.requestFocus();
            }
        }
    }

    @Action
    public void repiat() {
        // Получаем индекc в таблице
        int tableSelectedRow = operationTable.getSelectedRow();

        if (tableSelectedRow != -1) { // Если элемент всеже выбран
            // Получаем реальный индекс в модели

            int modelSelectedRow = operationTable.convertRowIndexToModel(tableSelectedRow);

            model.incQuantity(modelSelectedRow);

            operationTable.updateUI();
            operationTable.setRowSelectionInterval(tableSelectedRow, tableSelectedRow);
        }
    }

    @Action
    public void removeProduct() {
        // Получаем индекc в таблице
        int tableSelectedRow = operationTable.getSelectedRow();
        // Получаем реальный индекс в модели
        int modelSelectedRow = operationTable.convertRowIndexToModel(tableSelectedRow);

        if (modelSelectedRow != -1) {
            model.removeProduct(modelSelectedRow);
            operationTable.scrollRowToVisible(operationTable.getSelectedRow());
        }
    }

    // -------------------------------------------------------------------------
    // The following function is recursive and is intended for internal use
    // only. It is private to prevent anyone calling it from other classes
    // The function takes a Component as an argument and adds this Dialog as a
    // KeyListener to it.
    // Besides it checks if the component is actually a Container and if it is,
    // there are 2 additional things to be done to this Container :
    // 1 - add this Dialog as a ContainerListener to the Container
    // 2 - call this function recursively for every child of the Container.
    private void addKeyAndContainerListenerRecursively(Component c) {
        // To be on the safe side, try to remove KeyListener first just in case
        // it has been added before.
        // If not, it won't do any harm
        c.removeKeyListener(this);
        // Add KeyListener to the Component passed as an argument
        c.addKeyListener(this);

        if (c instanceof Container) {

            // Component c is a Container. The following cast is safe.
            Container cont = (Container) c;

            // To be on the safe side, try to remove ContainerListener first
            // just in case it has been added before.
            // If not, it won't do any harm
            cont.removeContainerListener(this);

            // Add ContainerListener to the Container.
            cont.addContainerListener(this);

            // Get the Container's array of children Components.
            Component[] children = cont.getComponents();

            // For every child repeat the above operation.
            for (int i = 0; i < children.length; i++) {
                addKeyAndContainerListenerRecursively(children[i]);
            }
        }
    }

    // The following function is the same as the function above with the
    // exception that it does exactly the opposite - removes this Dialog
    // from the listener lists of Components.
    private void removeKeyAndContainerListenerRecursively(Component c) {
        c.removeKeyListener(this);

        if (c instanceof Container) {

            Container cont = (Container) c;
            cont.removeContainerListener(this);
            Component[] children = cont.getComponents();

            for (int i = 0; i < children.length; i++) {
                removeKeyAndContainerListenerRecursively(children[i]);
            }
        }
    }

    // ContainerListener interface
    /** ******************************************************* */    // This function is called whenever a Component or a Container is added to
    // another Container belonging to this Dialog
    public void componentAdded(ContainerEvent e) {
        addKeyAndContainerListenerRecursively(e.getChild());
    }

    // This function is called whenever a Component or a Container is removed
    // from another Container belonging to this Dialog
    public void componentRemoved(ContainerEvent e) {
        removeKeyAndContainerListenerRecursively(e.getChild());
    }

    /** ******************************************************* */
    // KeyListener interface
    /** ******************************************************* */
    // This function is called whenever a Component belonging to this Dialog (or
    // the Dialog itself) gets the KEY_PRESSED event
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        char c = e.getKeyChar();

        // Вылавливаем цифровой штрихкод
        // Закоментировано, потому что штрих кода могут содержать буквы
        if (c == '0' ||
                c == '1' ||
                c == '2' ||
                c == '3' ||
                c == '4' ||
                c == '5' ||
                c == '6' ||
                c == '7' ||
                c == '8' ||
                c == '9' ||
                c == 'A' ||
                c == 'B' ||
                c == 'C' ||
                c == 'D' ||
                c == 'E' ||
                c == 'F' ||
                c == 'G' ||
                c == 'H' ||
                c == 'I' ||
                c == 'J' ||
                c == 'K' ||
                c == 'L' ||
                c == 'M' ||
                c == 'N' ||
                c == 'O' ||
                c == 'P' ||
                c == 'Q' ||
                c == 'R' ||
                c == 'S' ||
                c == 'T' ||
                c == 'U' ||
                c == 'V' ||
                c == 'W' ||
                c == 'X' ||
                c == 'Y' ||
                c == 'Z') {

            enterString += e.getKeyChar();
        }
//        } else if (code != KeyEvent.VK_ENTER) {
//            enterString = "";
//        }

        //if (code != KeyEvent.VK_ENTER)
        //    enterString += e.getKeyChar();

        if (code == KeyEvent.VK_ESCAPE) {
            // Key pressed is the ESCAPE key. Hide this Dialog.
        } else if (code == KeyEvent.VK_ENTER) {
            System.out.println("Получен код: " + enterString);

            operationTable.requestFocus();
            jPanel5.requestFocus();

            findProductForScod(enterString);

            enterString = "";
        }

    }

    // We need the following 2 functions to complete imlementation of
    // KeyListener
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void updateContent() {
        createNewOperation();
    }
    // Эти методы с нужными иконками уже перекрыты в потомках

    public Icon getCaptionIcon() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCaptionText() {
        throw new UnsupportedOperationException("Not supported yet.");
    }//------------------------------------------------------------------------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createNewOperationButton;
    private javax.swing.JLabel currentBalanceLabel;
    private javax.swing.JTextArea customerInfoTextArea;
    private javax.swing.JButton enterProductForScodButton;
    private javax.swing.JButton findProductButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    protected javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private javax.swing.JLabel newBalanceLabel;
    protected org.jdesktop.swingx.JXTable operationTable;
    private javax.swing.JButton sellingButton;
    private javax.swing.JFormattedTextField tabloFormattedTextField;
    private javax.swing.JLabel toCassLabel;
    private javax.swing.JLabel toCreditLabel;
    // End of variables declaration//GEN-END:variables

    public List<JMenuItem> getMenuItemList() {
        return menuItemList;
    }
    private DecimalFormat df = new DecimalFormat();
    private double currentBalance = 0;

    private void renderBalancesPanel() {
        currentBalanceLabel.setText(df.format(currentBalance));

        if (model.getSummForAllProducts() < model.getToCredit()) {
            model.setToCredit(model.getSummForAllProducts());
        }

        toCassLabel.setText(df.format(model.getSummForAllProducts() - model.getToCredit()));

        toCreditLabel.setText(df.format(model.getToCredit()));

        newBalanceLabel.setText(df.format(currentBalance - model.getToCredit()));
    }

    @Action
    public void findCostumer() {
        FindCustomerDPAnel p = new FindCustomerDPAnel();
        AUniversalDialog d = new AUniversalDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/poisk.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            model.setCustomerId(p.getSelectedId());
            customerInfoTextArea.setText(p.getCustomerInfo());
            customerInfoTextArea.select(0, 1);
            currentBalance = p.getBalance();
        }
        renderBalancesPanel();
    }

    @Action
    public void setOperattionAsCredit() {

        // Проверяем, введен ли клиент?
        if (model.getCustomerId() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Для проведения операции в долг необходимо ввести клиента.",
                    "Внимание...",
                    JOptionPane.WARNING_MESSAGE);
            findCostumer();
        }

        if (model.getCustomerId() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Клиент не введен, операция не помечена как операция в долг.",
                    "Внимание...",
                    JOptionPane.WARNING_MESSAGE);

            model.setToCredit(0);

        } else {

            // Сдесь можно задать в кредит
            SetSummToCreditDPanel p = new SetSummToCreditDPanel(model.getSummForAllProducts(), model.getToCredit());
            AUniversalDialog d = new AUniversalDialog(p, null, true);
            d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/vdolg.png")));

            d.setVisible(true);
            d.dispose();

            if (d.getReturnStatus() == ADialog.RET_OK) {
                model.setToCredit(p.getSumm());
            }
        }

        renderBalancesPanel();
    }

    @Action
    public void clearCustomerReference() {
        customerInfoTextArea.setText("");
        model.setCustomerId(-1);
        model.setToCredit(0);
        currentBalance = 0;

        renderBalancesPanel();
    }

    /**
     * Поиск товара по справочнику
     */
    @Action
    public void findProduct() {

        ProductsMainPanel p = new ProductsMainPanel();
        p.setVisibleEditBattons(false);
        AUniversalDialog d = new AUniversalDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/poiskpokodu.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
            findProductForScod(p.getSelectedScode());
        }
    }
}
