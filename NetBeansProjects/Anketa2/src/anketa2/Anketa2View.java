/*
 * Anketa2View.java
 */
package anketa2;

import anketa2.renders.BoolCheckEditor;
import anketa2.renders.BoolCheckRender;
import anketa2.tab.ANTable;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The application's main frame.
 */
public final class Anketa2View extends FrameView {

    private int qIndex = 0;
    private ANTable anTable = new ANTable();
    //private QTableModel myTableModel = new QTableModel();
    private List<QTableModel> qModels = new ArrayList<QTableModel>();

    {

        for (int i = 0; i < 100; i++) {
            qModels.add(new QTableModel());
        }

    }

    public Anketa2View(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });


        // Построение

//        mainPanel.add(anTable, java.awt.BorderLayout.CENTER);
        mainScrollPane.setViewportView(anTable);

        anTable.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                // Получаем табличные координаты
                int tRow = anTable.getSelectedRow();
                int tCol = anTable.getSelectedColumn();

                // Получаем реальные координаты модели данных
                int mRow = anTable.convertRowIndexToModel(tRow);
                int mCol = anTable.convertColumnIndexToModel(tCol);

                Object o = anTable.getModel().getValueAt(mRow, mCol);


                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                    if (tCol == anTable.getColumnCount() - 1) {
                        //JOptionPane.showMessageDialog(null, "Событие", "Внимание...", JOptionPane.WARNING_MESSAGE);
                        setQuestion(qIndex + 1);
                        setFocusToAnTable();
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (o != null && o instanceof Boolean) {
                        boolean b = ((Boolean) o);
                        anTable.getModel().setValueAt(!b, mRow, mCol);
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                //System.out.println("code: " + e.getKeyCode());

                // Получаем табличные координаты
                int tRow = anTable.getSelectedRow();
                int tCol = anTable.getSelectedColumn();

                // Получаем реальные координаты модели данных
                int mRow = anTable.convertRowIndexToModel(tRow);
                int mCol = anTable.convertColumnIndexToModel(tCol);

                Object o = anTable.getModel().getValueAt(mRow, mCol);

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (tRow == 0 && tCol == 0) {
                        setFocusToAnTable(anTable.getRowCount() - 1, anTable.getColumnCount() - 1);
                    }
                } else {
                    if (mCol <= 1) {
                        //JOptionPane.showMessageDialog(null, "Событие", "Внимание...", JOptionPane.WARNING_MESSAGE);
                        setQuestion(qIndex - 1);

                        setFocusToAnTable();
                    }
                }
            }
        });


        anTable.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
                // Получаем табличные координаты
                int tRow = anTable.getSelectedRow();
                int tCol = anTable.getSelectedColumn();

                // Получаем реальные координаты модели данных
                int mRow = anTable.convertRowIndexToModel(tRow);
                int mCol = anTable.convertColumnIndexToModel(tCol);

                if (mCol <= 1) {
                    anTable.setColumnSelectionInterval(2, 2);
                    anTable.setRowSelectionInterval(tRow, tRow);
                }
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

//        anTable.getSelectionModel().addListSelectionListener(
//                new ListSelectionListener() {
//
//                    public void valueChanged(ListSelectionEvent e) {
//                        if (!e.getValueIsAdjusting()) {
//
//                            // Получаем индекc в таблице
//                            int tRow = anTable.getSelectedRow();
//                            int tCol = anTable.getSelectedColumn();
//
//                            int mRow = anTable.convertRowIndexToModel(tRow);
//                            int mCol = anTable.convertColumnIndexToModel(tCol);
//
//                            if (mCol <= 1 && mRow != 0) {
//                                anTable.setColumnSelectionInterval(2, 2);
//                                anTable.setRowSelectionInterval(tRow, tRow);
//                            }
//                        }
//                    }
//                });



    }

    /**
     * Установка модели 
     * @param id
     */
    private void setQuestion(int id) {

        if (id < 0 || id > qModels.size() - 1) {
            return;
        }

        QTableModel qt = qModels.get(id);

        anTable.setModel(qt);

        anTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //anTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        //anTable.setCellSelectionEnabled(true);
        anTable.setRowHeight(24);
        anTable.setDoubleBuffered(true);

        anTable.setModel(qt);

        anTable.getColumnModel().getColumn(0).setMaxWidth(40);
        anTable.getColumnModel().getColumn(1).setPreferredWidth(300);

        for (int i = 2; i < qt.getColumnCount(); i++) {

            anTable.getColumnModel().getColumn(i).setPreferredWidth(50);
            anTable.getColumnModel().getColumn(i).setCellRenderer(new BoolCheckRender(true));
            anTable.getColumnModel().getColumn(i).setCellEditor(new BoolCheckEditor());
        }

        nLabel.setText("№" + qt.getId());
        qTextPane.setText(qt.getQuestion());
        descrLabel.setText(qt.getDescription());


        qIndex = id;





        qIndexSpinner.setValue(qIndex + 1);


    }

    /**
     * Установка фокуса таблици
     */
    private void setFocusToAnTable() {
        setFocusToAnTable(0, 2);
        anTable.requestFocus();
    }

    /**
     * Установка фокуса
     * @param tRow
     * @param tCol
     */
    private void setFocusToAnTable(int tRow, int tCol) {
        anTable.setColumnSelectionInterval(tCol, tCol);
        anTable.setRowSelectionInterval(tRow, tRow);
        anTable.requestFocus();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Anketa2App.getApplication().getMainFrame();
            aboutBox = new Anketa2AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Anketa2App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        northPanel = new javax.swing.JPanel();
        qPanel = new javax.swing.JPanel();
        nLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        qTextPane = new javax.swing.JTextPane();
        descrLabel = new javax.swing.JLabel();
        mainScrollPane = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        openSchemeButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        toFirstButton = new javax.swing.JButton();
        previusButton = new javax.swing.JButton();
        qIndexSpinner = new javax.swing.JSpinner();
        qCountLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();
        toEndButton = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());

        northPanel.setName("northPanel"); // NOI18N
        northPanel.setLayout(new javax.swing.BoxLayout(northPanel, javax.swing.BoxLayout.Y_AXIS));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(anketa2.Anketa2App.class).getContext().getResourceMap(Anketa2View.class);
        qPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("qPanel.border.title"))); // NOI18N
        qPanel.setToolTipText(resourceMap.getString("qPanel.toolTipText")); // NOI18N
        qPanel.setMinimumSize(new java.awt.Dimension(132, 100));
        qPanel.setName("qPanel"); // NOI18N
        qPanel.setLayout(new java.awt.BorderLayout(5, 5));

        nLabel.setFont(resourceMap.getFont("nLabel.font")); // NOI18N
        nLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nLabel.setText(resourceMap.getString("nLabel.text")); // NOI18N
        nLabel.setName("nLabel"); // NOI18N
        qPanel.add(nLabel, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(18, 100));

        qTextPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        qTextPane.setEditable(false);
        qTextPane.setText(resourceMap.getString("qTextPane.text")); // NOI18N
        qTextPane.setName("qTextPane"); // NOI18N
        jScrollPane1.setViewportView(qTextPane);

        qPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        descrLabel.setText(resourceMap.getString("descrLabel.text")); // NOI18N
        descrLabel.setName("descrLabel"); // NOI18N
        qPanel.add(descrLabel, java.awt.BorderLayout.PAGE_END);

        northPanel.add(qPanel);

        mainPanel.add(northPanel, java.awt.BorderLayout.NORTH);

        mainScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("mainScrollPane.border.title"))); // NOI18N
        mainScrollPane.setName("mainScrollPane"); // NOI18N
        mainPanel.add(mainScrollPane, java.awt.BorderLayout.CENTER);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(anketa2.Anketa2App.class).getContext().getActionMap(Anketa2View.class, this);
        jMenuItem1.setAction(actionMap.get("openSchemeAction")); // NOI18N
        jMenuItem1.setName("openSchemeMenuItem"); // NOI18N
        fileMenu.add(jMenuItem1);

        jMenuItem2.setAction(actionMap.get("saveDataToDisk")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        fileMenu.add(jMenuItem2);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenu1.add(jMenuItem7);

        menuBar.add(jMenu1);

        helpMenu.setAction(actionMap.get("showAboutBox")); // NOI18N
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 625, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        openSchemeButton.setAction(actionMap.get("openSchemeAction")); // NOI18N
        openSchemeButton.setText(resourceMap.getString("openSchemeAction.Action.text")); // NOI18N
        openSchemeButton.setActionCommand(resourceMap.getString("openSchemeButton.actionCommand")); // NOI18N
        openSchemeButton.setFocusable(false);
        openSchemeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openSchemeButton.setName("openSchemeButton"); // NOI18N
        openSchemeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(openSchemeButton);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButton1);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1);

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setFont(resourceMap.getFont("anketNumFormattedTextField.font")); // NOI18N
        jFormattedTextField1.setMinimumSize(new java.awt.Dimension(100, 47));
        jFormattedTextField1.setName("anketNumFormattedTextField"); // NOI18N
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(150, 47));
        jPanel2.add(jFormattedTextField1);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButton2);

        jButton3.setAction(actionMap.get("saveDataToDisk")); // NOI18N
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButton3);

        jButton4.setAction(actionMap.get("quit")); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButton4);

        jPanel1.add(jPanel2);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        toFirstButton.setAction(actionMap.get("toFirst")); // NOI18N
        toFirstButton.setText(resourceMap.getString("toFirst.Action.text")); // NOI18N
        toFirstButton.setFocusable(false);
        toFirstButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toFirstButton.setName("toFirstButton"); // NOI18N
        toFirstButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(toFirstButton);

        previusButton.setAction(actionMap.get("previus")); // NOI18N
        previusButton.setText(resourceMap.getString("previus.Action.text")); // NOI18N
        previusButton.setFocusable(false);
        previusButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        previusButton.setName("previusButton"); // NOI18N
        previusButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(previusButton);

        qIndexSpinner.setFont(resourceMap.getFont("qIndexSpinner.font")); // NOI18N
        qIndexSpinner.setMinimumSize(new java.awt.Dimension(150, 28));
        qIndexSpinner.setName("qIndexSpinner"); // NOI18N
        qIndexSpinner.setPreferredSize(new java.awt.Dimension(150, 48));
        qIndexSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qIndexSpinnerStateChanged(evt);
            }
        });
        jPanel3.add(qIndexSpinner);

        qCountLabel.setFont(resourceMap.getFont("qCountLabel.font")); // NOI18N
        qCountLabel.setText(resourceMap.getString("qCountLabel.text")); // NOI18N
        qCountLabel.setName("qCountLabel"); // NOI18N
        jPanel3.add(qCountLabel);

        nextButton.setAction(actionMap.get("next")); // NOI18N
        nextButton.setText(resourceMap.getString("next.Action.text")); // NOI18N
        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton.setName("nextButton"); // NOI18N
        nextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(nextButton);

        toEndButton.setAction(actionMap.get("toEnd")); // NOI18N
        toEndButton.setText(resourceMap.getString("toEnd.Action.text")); // NOI18N
        toEndButton.setFocusable(false);
        toEndButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toEndButton.setName("toEndButton"); // NOI18N
        toEndButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(toEndButton);

        jPanel1.add(jPanel3);

        jToolBar1.add(jPanel1);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(jToolBar1);
    }// </editor-fold>//GEN-END:initComponents

    private void qIndexSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qIndexSpinnerStateChanged
        qIndex = ((Integer)qIndexSpinner.getValue()) - 1;
        setQuestion(qIndex);
        setFocusToAnTable();
    }//GEN-LAST:event_qIndexSpinnerStateChanged

    /**
     * Открытие файла схемы заполнения анкеты
     */
    @Action
    public void openSchemeAction() {

        JFileChooser fc = new JFileChooser();

        fc.setDialogTitle("Выбрать фаил, в котором будут сохранены данные");
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileFilter(new FileNameExtensionFilter("Scheme file", "txt"));
        fc.setAcceptAllFileFilterUsed(false);


        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {

            qModels.clear();

            InputStream fis = null;
            try {

                System.out.println("Открытие файла: " + fc.getSelectedFile().getCanonicalPath());
                File file = new File(fc.getSelectedFile().getCanonicalPath());
                fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));


                QTableModel cQ = null;
                while (br.ready()) {

                    String s = br.readLine();

                    if (s.charAt(0) == 'В') {

                        String qs[] = s.substring(1).trim().replaceAll("   ", " ").split(" ", 2);

                        System.out.println(qs[0]);
                        System.out.println(qs[1]);

                        cQ = new QTableModel(Integer.parseInt(qs[0].replace(".", "")), qs[1], "Пояснение...");
                        cQ.getColumns().add("Значение");


                        qModels.add(cQ);

                    } else {

                        String as[] = s.substring(1).trim().split(" ", 2);

                        System.out.println(as[0]);
                        System.out.println(as[1]);

                        Object[] os = new Object[3];
                        os[0] = Integer.parseInt(as[0].replace(".", ""));
                        os[1] = as[1];
                        os[2] = false;

                        cQ.addQRow(os);

                    }

                }

                qIndex = 0;
                setQuestion(qIndex);

                qCountLabel.setText("из " + qModels.size());

                SpinnerNumberModel sm = new SpinnerNumberModel( 1, 1, qModels.size(), 1);

                qIndexSpinner.setModel(sm);


            } catch (IOException ex) {
                Logger.getLogger(Anketa2View.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Anketa2View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * на начало анкеты
     */
    @Action
    public void toFirst() {
        setQuestion(0);
        setFocusToAnTable();
    }

    /**
     * предидущий вопрос
     */
    @Action
    public void previus() {
        setQuestion(qIndex - 1);
        setFocusToAnTable();
    }

    /**
     * следующий запрос
     */
    @Action
    public void next() {
        setQuestion(qIndex + 1);
        setFocusToAnTable();
    }

    /**
     * перейти на последний вопрос
     */
    @Action
    public void toEnd() {
        setQuestion(qModels.size() - 1);
        setFocusToAnTable();
    }

    /**
     * Сохраняет данные на диск
     */
    @Action
    public void saveDataToDisk() {
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            File outFile = new File("out.txt");
            os = new FileOutputStream(outFile);
            pw = new PrintWriter(new OutputStreamWriter(os));

            int i=0;
            for (QTableModel qt : qModels) {
                pw.print(++i + " ");
                for (ArrayList row : qt.getRows()) {
                    //Object[] om = (Object[]) o;
                    Boolean b = (Boolean) row.get(2);

                    if(b){
                        pw.print("1");
                    } else {
                        pw.print("0");
                    }
                }
                pw.println();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Anketa2View.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pw.close();
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(Anketa2View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descrLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel northPanel;
    private javax.swing.JButton openSchemeButton;
    private javax.swing.JButton previusButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel qCountLabel;
    private javax.swing.JSpinner qIndexSpinner;
    private javax.swing.JPanel qPanel;
    private javax.swing.JTextPane qTextPane;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton toEndButton;
    private javax.swing.JButton toFirstButton;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
