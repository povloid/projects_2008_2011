/*
 * ProgectsGroupsPanel.java
 *
 * Created on 6 Февраль 2008 г., 13:34
 */
package tradeterminal.products_groups;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import minersinstrument.ui.ADelDialog;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.AErrorDialog;
import minersinstrument.ui.AUniversalAddDialog;
import minersinstrument.ui.AUniversalEditDialog;
import minersinstrument.ui.IADialogPanel;
import org.jdesktop.application.Action;
import tradeterminal.Setup;
import tradeterminal.conf.AppAccessSettings;

/**
 *
 * @author  PKopychenko
 */
public class ProgectsGroupsMainPanel extends javax.swing.JPanel implements IADialogPanel {

    DefaultTreeModel treeModel;
    // Создаем корень
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("...");
    ArrayList<IUpdateForID> apdatedElementsForIdList = new ArrayList<IUpdateForID>();

    /** Creates new form ProgectsGroupsPanel */
    public ProgectsGroupsMainPanel() {
        initComponents();

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(32, 32));
        jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(32, 32));

        jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(32, 32));
        jScrollPane2.getHorizontalScrollBar().setPreferredSize(new Dimension(32, 32));

        treeModel = new DefaultTreeModel(root);
        jXTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jXTree1.putClientProperty("JTree.lineStyle", "Horizontal");
        jXTree1.setEditable(false);
        jXTree1.setModel(treeModel);

        update();

        //buttonsPanel.setVisible(false);

    }

    // Определение класса записи
    private class NodeRow extends DefaultMutableTreeNode {

        private int id;
        private int sub_id;
        private String name;
        private String description;

        public void setDescription(String description) {
            this.description = description;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getSub_id() {
            return sub_id;
        }

        public NodeRow(int id, int sub_id, String name, String description) {
            super(name);

            this.id = id;
            this.sub_id = sub_id;
            this.name = name;
            this.description = description;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
    private List<NodeRow> nodeRowsList = new ArrayList<NodeRow>();

    private void update() {

        Connection conn = null;

        try {
            // Выполняем запрос
            //PreparedStatement selectQueryStat = Setup.getSource().getConnection().prepareStatement("SELECT id, sub_id, name, description FROM products_groups");
            //ResultSet rs = selectQueryStat.executeQuery();

            conn = Setup.getSource().getConnection();
            conn.setAutoCommit(false);
            CallableStatement proc = conn.prepareCall("{ ? = call products_groups_select() }");
            proc.registerOutParameter(1, Types.OTHER);
            proc.execute();
            ResultSet rs = (ResultSet) proc.getObject(1);

            // Отчищаем список
            nodeRowsList.clear();
            root.removeAllChildren();


            while (rs.next()) {
                nodeRowsList.add(new NodeRow(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
            proc.close();

            // Заполняем корневые элементы
            for (NodeRow row : nodeRowsList) {
                if (row.getSub_id() > 0) {
                    searchParentNode(row).add(row);
                } else if (row.getSub_id() == 0) {
                    root.add(row);
                }
            }

            treeModel.reload();

        } catch (SQLException ex) {
            ex.printStackTrace();

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();
                }
            }
        }
    }

    private DefaultMutableTreeNode searchParentNode(NodeRow node) {
        int id = node.getSub_id();
        for (NodeRow row : nodeRowsList) {
            if (row.getId() == id) {
                return row;
            }
        }

        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centralPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTree1 = new org.jdesktop.swingx.JXTree();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        buttonsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        centralPanel.setName("centralPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getResourceMap(ProgectsGroupsMainPanel.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        descriptionTextArea.setColumns(15);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setMinimumSize(new java.awt.Dimension(30, 10));
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        jScrollPane2.setViewportView(descriptionTextArea);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jXTree1.setClosedIcon(resourceMap.getIcon("jXTree1.closedIcon")); // NOI18N
        jXTree1.setLeafIcon(resourceMap.getIcon("jXTree1.leafIcon")); // NOI18N
        jXTree1.setName("jXTree1"); // NOI18N
        jXTree1.setOpenIcon(resourceMap.getIcon("jXTree1.openIcon")); // NOI18N
        jXTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jXTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jXTree1);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tradeterminal.TradeTerminalApp.class).getContext().getActionMap(ProgectsGroupsMainPanel.class, this);
        jButton4.setAction(actionMap.get("expandAllNodes")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        jButton6.setAction(actionMap.get("collapseAllNodes")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N

        jButton5.setAction(actionMap.get("updateAll")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        javax.swing.GroupLayout centralPanelLayout = new javax.swing.GroupLayout(centralPanel);
        centralPanel.setLayout(centralPanelLayout);
        centralPanelLayout.setHorizontalGroup(
            centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addGroup(centralPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        centralPanelLayout.setVerticalGroup(
            centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(centralPanel, java.awt.BorderLayout.CENTER);

        buttonsPanel.setName("buttonsPanel"); // NOI18N
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jButton1.setAction(actionMap.get("addRecord")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        buttonsPanel.add(jButton1);

        jButton2.setAction(actionMap.get("editRecord")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        buttonsPanel.add(jButton2);

        jButton3.setAction(actionMap.get("delRecord")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        buttonsPanel.add(jButton3);

        add(buttonsPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jXTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXTree1ValueChanged

        DefaultMutableTreeNode sel = getSelectedNode();
        if (!sel.isRoot()) {
            NodeRow r = (ProgectsGroupsMainPanel.NodeRow) sel;
            descriptionTextArea.setText(r.getDescription());
            updateAllUpdatedForIdElements(r.id);
        } else {
            descriptionTextArea.setText("");
            updateAllUpdatedForIdElements(-1);
        }
    }//GEN-LAST:event_jXTree1ValueChanged

    public void addUpdatedForIdElement(IUpdateForID e) {
        apdatedElementsForIdList.add(e);
    }

    private void updateAllUpdatedForIdElements(int id) {
        for (IUpdateForID iUpdateForID : apdatedElementsForIdList) {
            iUpdateForID.updateForId(id);
        }
    }

    public boolean checkPanel() {



        return true;
    }

    public void openPanel() {
    }

    public void setSelectedGroupId(int id) {
        update();

        //int selected = -1;

        for (NodeRow row : nodeRowsList) {
            if (!row.isRoot() && id == row.getId()) {
                //System.out.println(id);
                TreeNode[] nodes = treeModel.getPathToRoot(row);
                TreePath path = new TreePath(nodes);
                jXTree1.setSelectionPath(path);
                jXTree1.scrollPathToVisible(path);
                jXTree1ValueChanged(null);
            }
        }

//        if(selected == -1){
//            
//        } else {
//            
//        }

    }

    public int getSelectedGroupId() {
        // Получение текуще записи
        DefaultMutableTreeNode dsel = getSelectedNode();

        // Необходимые проверки на возможность редактирования
        // Корень нельзя редактировать
        if (dsel.isRoot()) {
            return -1;
        }

        NodeRow sel = (ProgectsGroupsMainPanel.NodeRow) dsel;

        return sel.getId();
    }

    public String getSelectedGroupName() {
        // Получение текуще записи
        DefaultMutableTreeNode dsel = getSelectedNode();

        // Необходимые проверки на возможность редактирования
        // Корень нельзя редактировать
        if (dsel.isRoot()) {
            return "...";
        }

        NodeRow sel = (ProgectsGroupsMainPanel.NodeRow) dsel;

        return sel.getName();
    }

    private DefaultMutableTreeNode getSelectedNode() {
        Object obj = jXTree1.getLastSelectedPathComponent();

        if (obj == null) {
            obj = treeModel.getRoot();
        }

        return (DefaultMutableTreeNode) obj;
    }

    @Action
    public void addRecord() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_EDIT)) {
            return;
        }

        ProgectsGroupsDialog p = new ProgectsGroupsDialog();
        AUniversalAddDialog d = new AUniversalAddDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/guppaplus.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {


            DefaultMutableTreeNode sel = getSelectedNode();

            Connection conn = null;
            try {
                conn = Setup.getSource().getConnection();
                conn.setAutoCommit(false);

                CallableStatement proc = conn.prepareCall("{? = call products_groups_insert(?,?,?)}");
                // Результат
                proc.registerOutParameter(1, Types.INTEGER);
                // Параметры             

                int subId = 0;
                if (sel.isRoot()) {
                    proc.setObject(2, null);
                } else {
                    subId = ((NodeRow) sel).id;
                    proc.setInt(2, subId);
                }

                proc.setString(3, p.getUName());
                proc.setString(4, p.getDescription());
                proc.execute();

                int id = proc.getInt(1);
                proc.close();

                // Подтвердить транзакцию
                conn.commit();

                NodeRow newNodeRow = new NodeRow(id, subId, p.getUName(), p.getDescription());
                nodeRowsList.add(newNodeRow);

                // Добавляем  модель
                treeModel.insertNodeInto(newNodeRow, sel, sel.getChildCount());

                // Отображение 
                TreeNode[] nodes = treeModel.getPathToRoot(newNodeRow);
                TreePath path = new TreePath(nodes);
                jXTree1.scrollPathToVisible(path);

            } catch (SQLException ex) {
                Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex);

                AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
                der.setVisible(true);
                der.dispose();

                try {
                    conn.rollback();
                    // log error
                } catch (SQLException ex1) {
                    Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex1);

                    AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex1.getMessage());
                    der2.setVisible(true);
                    der2.dispose();

                }
                // log error
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex2) {
                        Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex2);

                        AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                        der.setVisible(true);
                        der.dispose();
                    }
                }
            }
        }
    }

    @Action
    public void editRecord() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_EDIT)) {
            return;
        }

        // Получение текуще записи
        DefaultMutableTreeNode dsel = getSelectedNode();

        // Необходимые проверки на возможность редактирования
        // Корень нельзя редактировать
        if (dsel.isRoot()) {
            return;
        }

        NodeRow sel = (ProgectsGroupsMainPanel.NodeRow) dsel;

        // Диалоговые дела
        ProgectsGroupsDialog p = new ProgectsGroupsDialog();
        AUniversalEditDialog d = new AUniversalEditDialog(p, null, true);
        p.setUName(sel.getName());
        p.setDescription(sel.getDescription());

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/gruppared.png")));

        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK && !sel.isRoot()) {

            Connection conn = null;
            try {
                conn = Setup.getSource().getConnection();
                conn.setAutoCommit(false);

                CallableStatement proc = conn.prepareCall("{call products_groups_update(?,?,?,?)}");

                // Параметры
                proc.setInt(1, sel.getId());

                if (sel.getSub_id() == 0) {
                    proc.setObject(2, null);
                } else {
                    proc.setInt(2, sel.getSub_id());
                }

                proc.setString(3, p.getUName());
                proc.setString(4, p.getDescription());
                proc.execute();

                proc.close();

                // Подтвердить транзакцию
                conn.commit();

                sel.setName(p.getUName());
                sel.setDescription(p.getDescription());

                treeModel.reload(sel);

                jXTree1ValueChanged(null);

            } catch (SQLException ex) {
                Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex);

                AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
                der.setVisible(true);
                der.dispose();

                try {
                    conn.rollback();
                    // log error
                } catch (SQLException ex1) {
                    Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex1);

                    AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
                    der2.setVisible(true);
                    der2.dispose();
                }
                // log error
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex2) {
                        Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex2);

                        AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                        der.setVisible(true);
                        der.dispose();
                    }
                }
            }
        }
    }

    @Action
    public void delRecord() {

        // Проверка прав доступа
        if (!AppAccessSettings.isCurrentUserHasAccessWithMessage(
                AppAccessSettings.RB_EDIT)) {
            return;
        }

        // Получение текуще записи
        DefaultMutableTreeNode dsel = getSelectedNode();

        // Необходимые проверки на возможность редактирования
        // Корень нельзя редактировать
        if (dsel.isRoot()) {
            return;
        }

        NodeRow sel = (ProgectsGroupsMainPanel.NodeRow) dsel;

        // Диалоговые дела 
        ADelDialog d = new ADelDialog(null, true);
        d.addPar("Наименование", sel.getName());
        d.addPar("Краткое описание", sel.getDescription());

        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/gruppadel.png")));
    
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK && !sel.isRoot()) {

            Connection conn = null;
            try {

                conn = Setup.getSource().getConnection();
                conn.setAutoCommit(false);

                CallableStatement proc = conn.prepareCall("{call products_groups_delete(?)}");

                // Параметры
                proc.setInt(1, sel.getId());
                proc.execute();

                proc.close();

                // Подтвердить транзакцию
                conn.commit();

                treeModel.removeNodeFromParent(sel);
                nodeRowsList.remove(sel);

                jXTree1ValueChanged(null);

            } catch (SQLException ex) {
                Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex);

                AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
                der.setVisible(true);
                der.dispose();

                try {
                    conn.rollback();
                    // log error
                } catch (SQLException ex1) {
                    Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex1);

                    AErrorDialog der2 = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
                    der.setVisible(true);
                    der.dispose();

                }
                // log error
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex2) {
                        Logger.getLogger(ProgectsGroupsMainPanel.class.getName()).log(Level.SEVERE, null, ex2);

                        AErrorDialog der = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                        der.setVisible(true);
                        der.dispose();

                    }
                }
            }
        }
    }

    @Action
    public void expandAllNodes() {
        jXTree1.expandAll();
    }

    @Action
    public void collapseAllNodes() {
        jXTree1.collapseAll();
    }

    @Action
    public void updateAll() {
        update();
    }

    /**
     * Отобразить кнопки редактирования
     * @param visible
     */
    public void setVisibleEditBattons(boolean visible) {
        //progectsGroupsPanel1.
        buttonsPanel.setVisible(visible);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel centralPanel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTree jXTree1;
    // End of variables declaration//GEN-END:variables
}
