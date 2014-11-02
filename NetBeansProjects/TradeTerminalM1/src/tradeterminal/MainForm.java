/*
 * MainForm.java
 *
 * Created on 3 Март 2008 г., 0:50
 */
package tradeterminal;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.ARBControlPanel_MIdNamDes;
import minersinstrument.ui.AUniversalAddDialog;
import minersinstrument.ui.AUniversalCloseDialog;
import minersinstrument.ui.AUniversalDelDialog;
import org.jdesktop.application.Action;
import tradeterminal.conf.AppAccessSettings;
import tradeterminal.conf.GUISettings;
import tradeterminal.conf.User;
import tradeterminal.operations.money.AddMoneyToCassDPanel;
import tradeterminal.operations.money.MoneyOperationMainPanel;
import tradeterminal.operations.money.RemoveMoneyFromCass;
import tradeterminal.operations.money.customerbalahce.AddMoneyToCustomerBalance;
import tradeterminal.operations.money.customerbalahce.RemoveMoneyFromCustomerBalance;
import tradeterminal.operations.money.z.ZReportMainDPanel;
import tradeterminal.operations.products.incommingandoutcomming.IncomAndOutcomProductsDPanel;
import tradeterminal.operations.products.returnproduct.ReturnProductMainPanel;
import tradeterminal.operations.products.selling.ProductsSellingMainPanel;
import tradeterminal.products.ProductsMainPanel;
import tradeterminal.references_books.customers.CostomersMainPanel;
import tradeterminal.references_books.doc_types.RBDocTypesMainPanel;
import tradeterminal.references_books.measures.RBMeasuresMainPanel;
import tradeterminal.reports.ReportsMainPanel;
import tradeterminal.users.UsersMainPanel;

/**
 *
 * @author  kopychenko
 */
public class MainForm extends javax.swing.JFrame {

    private CardLayout cardLayout;
    // Карта панелей
    Map<String, JPanel> pagesHash = new HashMap<String, JPanel>();
    JPanel currentPanel = null;

    /** Creates new form MainForm */
    public MainForm() {
        initComponents();

        this.setTitle("TradeTerminal - " + User.login);

        addIPageToMainForm("ProductsSellingMainPanel", new ProductsSellingMainPanel());
        addIPageToMainForm("ReturnProductMainPanel", new ReturnProductMainPanel());
        addIPageToMainForm("ProductsMainPanel", new ProductsMainPanel());
        addIPageToMainForm("ReportsMainPanel", new ReportsMainPanel());

//        addIPageToMainForm("measures ARBControlPanel_MIdNamDes",
//                new ARBControlPanel_MIdNamDes(
//                "Товарные меры",
//                Setup.getSource(),
//                "rb_measures", "rb_measures_id_seq"));

        addIPageToMainForm("measures ARBControlPanel_MIdNamDes", new RBMeasuresMainPanel());
        addIPageToMainForm("RB Doc types", new RBDocTypesMainPanel());
        addIPageToMainForm("RB Customers", new CostomersMainPanel());

        addIPageToMainForm("MoneyOperationMainPanel", new MoneyOperationMainPanel());
        addIPageToMainForm("FirstPanel", new FirstPanel(this));

        cardLayout = (CardLayout) cardPanel.getLayout();

        // Показываем панель по умолчанию при старте приложения
        //showPanel("ProductsSellingMainPanel");
        showPanel("FirstPanel");

        addLookAndFeelMenu("");
        addLookAndFeelMenu("javax.swing.plaf.metal.MetalLookAndFeel");
        addLookAndFeelMenu("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        addLookAndFeelMenu("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        addLookAndFeelMenu("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

//        addSeparatorToLookAndFeelMenu();
        // Создаем меню Тем графического оформления интерфейса

//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceMangoLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel");

        // toned down skins
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceCremeLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceModerateLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceNebulaLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel");

        // dark skins 
//        addSeparatorToLookAndFeelMenu();
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceRavenLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceMagmaLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel");
//        addLookAndFeelMenu("org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel");

    }

    private void addIPageToMainForm(String sKey, JPanel p) {
        pagesHash.put(sKey, p);
        cardPanel.add(sKey, p);
    }

    public void showPanel(String sKey) {

        currentPanel = pagesHash.get(sKey);

        if (currentPanel != null) {
            cardLayout.show(cardPanel, sKey);
            updateCurrentPanel();

            cardLayout.show(cardPanel, sKey);

            currentPanel.requestFocus();
        }
    }

    @Action // Обновление текущей панели
    public void updateCurrentPanel() {
        if (currentPanel != null) {

            Class c = currentPanel.getClass();

            if (currentPanel instanceof IPage) {
                IPage ip = (IPage) currentPanel;

                ip.updateContent();

                hLabel.setText(ip.getCaptionText());
                captionIconLabel.setIcon(ip.getCaptionIcon());

                try {

                    currentActionMenu.removeAll();

                    for (JMenuItem jmi : ip.getMenuItemList()) {
                        currentActionMenu.add(jmi);
                    }

                } catch (UnsupportedOperationException ex) {
                }

            } else if (c == ARBControlPanel_MIdNamDes.class) {
                ARBControlPanel_MIdNamDes arb = (ARBControlPanel_MIdNamDes) currentPanel;

                arb.updateContent();
                hLabel.setText(arb.getCaptionText());
                captionIconLabel.setIcon(arb.getIcon());
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

        jToolBar1 = new javax.swing.JToolBar();
        navigationButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        captionIconLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        hLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        currentActionMenu = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        lafMenu = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getActionMap(MainForm.class, this);
        navigationButton.setAction(actionMap.get("showFirstPanel")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(MainForm.class);
        navigationButton.setText(resourceMap.getString("navigationButton.text")); // NOI18N
        navigationButton.setFocusable(false);
        navigationButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        navigationButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        navigationButton.setName("navigationButton"); // NOI18N
        jToolBar1.add(navigationButton);

        jButton1.setAction(actionMap.get("updateCurrentPanel")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setName("jButton1"); // NOI18N
        jToolBar1.add(jButton1);

        jPanel5.setName("jPanel5"); // NOI18N
        jToolBar1.add(jPanel5);

        jButton2.setAction(actionMap.get("quit")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setName("jButton2"); // NOI18N
        jToolBar1.add(jButton2);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout(5, 0));

        cardPanel.setName("cardPanel"); // NOI18N
        cardPanel.setLayout(new java.awt.CardLayout());
        jPanel1.add(cardPanel, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        captionIconLabel.setBackground(resourceMap.getColor("captionIconLabel.background")); // NOI18N
        captionIconLabel.setIcon(resourceMap.getIcon("captionIconLabel.icon")); // NOI18N
        captionIconLabel.setText(resourceMap.getString("captionIconLabel.text")); // NOI18N
        captionIconLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        captionIconLabel.setName("captionIconLabel"); // NOI18N
        jPanel4.add(captionIconLabel, java.awt.BorderLayout.LINE_START);

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        hLabel.setFont(resourceMap.getFont("hLabel.font")); // NOI18N
        hLabel.setText(resourceMap.getString("hLabel.text")); // NOI18N
        hLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        hLabel.setName("hLabel"); // NOI18N
        jPanel2.add(hLabel);

        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        menuBar.setName("menuBar"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem1.setAction(actionMap.get("quit")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        currentActionMenu.setText(resourceMap.getString("currentActionMenu.text")); // NOI18N
        currentActionMenu.setName("currentActionMenu"); // NOI18N
        menuBar.add(currentActionMenu);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        jMenuItem20.setAction(actionMap.get("showFirstPanel")); // NOI18N
        jMenuItem20.setName("jMenuItem20"); // NOI18N
        jMenu5.add(jMenuItem20);

        jMenuItem9.setAction(actionMap.get("updateCurrentPanel")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenu5.add(jMenuItem9);

        menuBar.add(jMenu5);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jMenuItem2.setAction(actionMap.get("showCassControl")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenu2.add(jMenuItem2);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jMenu2.add(jSeparator4);

        jMenuItem19.setAction(actionMap.get("addMoneyToCass")); // NOI18N
        jMenuItem19.setName("jMenuItem19"); // NOI18N
        jMenu2.add(jMenuItem19);

        jMenuItem18.setAction(actionMap.get("removeMoneyFromCass")); // NOI18N
        jMenuItem18.setName("jMenuItem18"); // NOI18N
        jMenu2.add(jMenuItem18);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jMenu2.add(jSeparator5);

        jMenuItem12.setAction(actionMap.get("makeZReportOperation")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenu2.add(jMenuItem12);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jMenu2.add(jSeparator3);

        jMenuItem16.setAction(actionMap.get("addMoneyToCustomerBalance")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenu2.add(jMenuItem16);

        jMenuItem17.setAction(actionMap.get("removMoneyFromCustomerBalance")); // NOI18N
        jMenuItem17.setName("jMenuItem17"); // NOI18N
        jMenu2.add(jMenuItem17);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu2.add(jSeparator1);

        jMenuItem3.setAction(actionMap.get("incomingProduct")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAction(actionMap.get("outcommingProduct")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenu2.add(jMenuItem4);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu2.add(jSeparator2);

        jMenuItem5.setAction(actionMap.get("sellingProducts")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAction(actionMap.get("returdProducts")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu2.add(jMenuItem6);

        menuBar.add(jMenu2);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jMenuItem7.setAction(actionMap.get("showRb_measures")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenu3.add(jMenuItem7);

        jMenuItem8.setAction(actionMap.get("showProducts")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        jMenu3.add(jMenuItem8);

        jMenuItem14.setAction(actionMap.get("showRbDocTypes")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenu3.add(jMenuItem14);

        jMenuItem15.setAction(actionMap.get("showRBCustomers")); // NOI18N
        jMenuItem15.setName("jMenuItem15"); // NOI18N
        jMenu3.add(jMenuItem15);

        menuBar.add(jMenu3);

        jMenu7.setText(resourceMap.getString("jMenu7.text")); // NOI18N
        jMenu7.setName("jMenu7"); // NOI18N

        jMenuItem13.setAction(actionMap.get("printZreport")); // NOI18N
        jMenuItem13.setText(resourceMap.getString("jMenuItem13.text")); // NOI18N
        jMenuItem13.setName("jMenuItem13"); // NOI18N
        jMenu7.add(jMenuItem13);

        menuBar.add(jMenu7);

        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setName("jMenu6"); // NOI18N

        jMenuItem10.setAction(actionMap.get("showUserControlDialog")); // NOI18N
        jMenuItem10.setName("jMenuItem10"); // NOI18N
        jMenu6.add(jMenuItem10);

        lafMenu.setIcon(resourceMap.getIcon("lafMenu.icon")); // NOI18N
        lafMenu.setText(resourceMap.getString("lafMenu.text")); // NOI18N
        lafMenu.setName("lafMenu"); // NOI18N
        jMenu6.add(lafMenu);

        menuBar.add(jMenu6);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem11.setAction(actionMap.get("showAboutDialog")); // NOI18N
        jMenuItem11.setName("jMenuItem11"); // NOI18N
        jMenu4.add(jMenuItem11);

        menuBar.add(jMenu4);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    class LaFActionListener implements java.awt.event.ActionListener {

        private String laf;
        private Component c;

        public LaFActionListener(String laf, Component c) {
            this.laf = laf;
            this.c = c;
        }

        public void actionPerformed(ActionEvent e) {
            GUISettings.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(c);
        }
    }

    private void addLookAndFeelMenu(String s) {

        JMenuItem item = new JMenuItem();
        item.setText(s);
        item.addActionListener(new LaFActionListener(s, this));

        lafMenu.add(item);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    @Action
    public void showRb_measures() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_VIEW)) {
            return;
        }

//        ARBControlPanel_MIdNamDes p = 
//                new ARBControlPanel_MIdNamDes(Setup.getSource(),
//                "rb_measures","rb_measures_id_seq");
//        AUniversalCloseDialog d = new AUniversalCloseDialog(p, null, true);
//        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/addbk_32.png")));
//        
//        d.setVisible(true); d.dispose();

        showPanel("measures ARBControlPanel_MIdNamDes");
    }

    @Action
    public void showCassControl() {
//        MoneyOperationControlPanel p = 
//                new MoneyOperationControlPanel();
//        AUniversalCloseDialog d = new AUniversalCloseDialog(p, null, true);
//        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/tools_32.png")));
//        
//        d.setVisible(true); d.dispose();  

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.SHOW_CASS)) {
            return;
        }

        showPanel("MoneyOperationMainPanel");
    }

    @Action
    public void showProducts() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_VIEW)) {
            return;
        }

//        ProductsControlPanel p = 
//                new ProductsControlPanel();
//        AUniversalCloseDialog d = new AUniversalCloseDialog(p, null, true);
//        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/addbk_32.png")));
//        
//        d.setVisible(true); d.dispose(); 

        showPanel("ProductsMainPanel");
    }

    @Action
    public void showUserControlDialog() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.USER_MANAGER)) {
            return;
        }

        UsersMainPanel p =
                new UsersMainPanel();
        AUniversalCloseDialog d = new AUniversalCloseDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/polzovateli.png")));
        d.setTitle("Управление пользователями программы");
        d.setTitleText("Управление пользователями програмы");
        d.setVisible(true);
        d.dispose();
    }

    @Action
    public void updateCurrentPage() {
    }

    @Action
    public void incomingProduct() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.INCOMMING_PRODUCT)) {
            return;
        }

        AUniversalAddDialog d;
        do {
            IncomAndOutcomProductsDPanel p = new IncomAndOutcomProductsDPanel(false);
            d = new AUniversalAddDialog(p, null, true);

            d.setVisible(true);
            d.dispose();

        } while (d.getReturnStatus() == ADialog.RET_OK);


    }

    @Action
    public void outcommingProduct() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.OUTCOMMING_PRODUCT)) {
            return;
        }

        AUniversalDelDialog d;
        do {
            IncomAndOutcomProductsDPanel p = new IncomAndOutcomProductsDPanel(true);
            d = new AUniversalDelDialog(p, null, true);

            d.setVisible(true);
            d.dispose();



        } while (d.getReturnStatus() == ADialog.RET_OK);
    }

    @Action
    public void sellingProducts() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.SELLING_PRODUCT)) {
            return;
        }

        showPanel("ProductsSellingMainPanel");
    }

    @Action
    public void returdProducts() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RETURN_PRODUCT)) {
            return;
        }

        showPanel("ReturnProductMainPanel");

    }

    // KeyListener
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    @Action
    public void showAboutDialog() {
        TradeTerminalAboutBox d = new TradeTerminalAboutBox(this);

        d.setVisible(true);
        d.dispose();


    }

    @Action
    public void makeZReportOperation() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.MAKE_Z_REPORT)) {
            return;
        }


        ZReportMainDPanel p =
                new ZReportMainDPanel();
        AUniversalDelDialog d = new AUniversalDelDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/64X64/vyruchka.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
        }

    }

    @Action
    public void printZreport() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.REPORT_VIEWER)) {
            return;
        }

        showPanel("ReportsMainPanel");

    }

    @Action
    public void setLookAndFeel() {
    }

    @Action
    public void showRbDocTypes() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_VIEW)) {
            return;
        }

        showPanel("RB Doc types");
    }

    @Action
    public void showRBCustomers() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_VIEW)) {
            return;
        }

        showPanel("RB Customers");
    }

    @Action
    public void addMoneyToCustomerBalance() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.ADD_MONEY_TO_CUSTOMER_BALANCE)) {
            return;
        }

        AddMoneyToCustomerBalance p =
                new AddMoneyToCustomerBalance();
        AUniversalDelDialog d = new AUniversalDelDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/64X64/balansvverh.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
        }
    }

    @Action
    public void removMoneyFromCustomerBalance() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.REMOV_MONEY_FROM_CUSTOMER_BALANCE)) {
            return;
        }

        RemoveMoneyFromCustomerBalance p =
                new RemoveMoneyFromCustomerBalance();
        AUniversalDelDialog d = new AUniversalDelDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/64X64/balansvniz.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
        }
    }

    @Action
    public void addMoneyToCass() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.ADD_MONEY)) {
            return;
        }

        AddMoneyToCassDPanel p =
                new AddMoneyToCassDPanel();
        AUniversalDelDialog d = new AUniversalDelDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/64X64/dengiplus.png")));
        d.setTitle("Внести деньги в кассу");

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
        }
    }

    @Action
    public void removeMoneyFromCass() {
        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.REMOV_MONEY)) {
            return;
        }

        RemoveMoneyFromCass p =
                new RemoveMoneyFromCass();
        AUniversalDelDialog d = new AUniversalDelDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/64X64/dengimin.png")));
        d.setTitle("Изъять деньги из кассы");

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {
        }

    }

    @Action
    public void showFirstPanel() {
        showPanel("FirstPanel");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel captionIconLabel;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JMenu currentActionMenu;
    private javax.swing.JLabel hLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu lafMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton navigationButton;
    // End of variables declaration//GEN-END:variables
}
