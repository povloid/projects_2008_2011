namespace Workstation.Modules
{
    partial class DataWorkConsumer
    {
        /// <summary> 
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.userGroupBox = new System.Windows.Forms.GroupBox();
            this.userTableLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.findConsumerButton = new Workstation.Modules.PClasses.PButton();
            this.iD_CARD_NUMBERMTBox = new System.Windows.Forms.MaskedTextBox();
            this.rNNMTBox = new System.Windows.Forms.MaskedTextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.fIRST_NAMETextBox = new System.Windows.Forms.TextBox();
            this.mIDDLE_NAMETextBox = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.lAST_NAMETextBox = new System.Windows.Forms.TextBox();
            this.dATE_OF_BIRTHDateTimePicker = new System.Windows.Forms.DateTimePicker();
            this.label8 = new System.Windows.Forms.Label();
            this.gENDERComboBox = new System.Windows.Forms.ComboBox();
            this.pASSPORTTYPEComboBox = new System.Windows.Forms.ComboBox();
            this.pASSPORTTYPEBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.label9 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.pASSPORT_TYPETableAdapter = new Workstation.phmkDataSetTableAdapters.PASSPORT_TYPETableAdapter();
            this.userGroupBox.SuspendLayout();
            this.userTableLayoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pASSPORTTYPEBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // userGroupBox
            // 
            this.userGroupBox.BackColor = System.Drawing.SystemColors.Control;
            this.userGroupBox.Controls.Add(this.userTableLayoutPanel);
            this.userGroupBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.userGroupBox.Location = new System.Drawing.Point(0, 0);
            this.userGroupBox.Name = "userGroupBox";
            this.userGroupBox.Size = new System.Drawing.Size(596, 136);
            this.userGroupBox.TabIndex = 19;
            this.userGroupBox.TabStop = false;
            this.userGroupBox.Text = "Потребитель:";
            // 
            // userTableLayoutPanel
            // 
            this.userTableLayoutPanel.ColumnCount = 6;
            this.userTableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 80F));
            this.userTableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33334F));
            this.userTableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 80F));
            this.userTableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.userTableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 80F));
            this.userTableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.userTableLayoutPanel.Controls.Add(this.findConsumerButton, 5, 0);
            this.userTableLayoutPanel.Controls.Add(this.iD_CARD_NUMBERMTBox, 1, 0);
            this.userTableLayoutPanel.Controls.Add(this.rNNMTBox, 1, 2);
            this.userTableLayoutPanel.Controls.Add(this.label2, 0, 0);
            this.userTableLayoutPanel.Controls.Add(this.label4, 0, 4);
            this.userTableLayoutPanel.Controls.Add(this.label5, 2, 4);
            this.userTableLayoutPanel.Controls.Add(this.fIRST_NAMETextBox, 1, 4);
            this.userTableLayoutPanel.Controls.Add(this.mIDDLE_NAMETextBox, 3, 4);
            this.userTableLayoutPanel.Controls.Add(this.label6, 4, 4);
            this.userTableLayoutPanel.Controls.Add(this.lAST_NAMETextBox, 5, 4);
            this.userTableLayoutPanel.Controls.Add(this.dATE_OF_BIRTHDateTimePicker, 3, 6);
            this.userTableLayoutPanel.Controls.Add(this.label8, 4, 6);
            this.userTableLayoutPanel.Controls.Add(this.gENDERComboBox, 5, 6);
            this.userTableLayoutPanel.Controls.Add(this.pASSPORTTYPEComboBox, 3, 0);
            this.userTableLayoutPanel.Controls.Add(this.label9, 2, 0);
            this.userTableLayoutPanel.Controls.Add(this.label7, 1, 6);
            this.userTableLayoutPanel.Controls.Add(this.label3, 0, 2);
            this.userTableLayoutPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.userTableLayoutPanel.Location = new System.Drawing.Point(3, 16);
            this.userTableLayoutPanel.Name = "userTableLayoutPanel";
            this.userTableLayoutPanel.RowCount = 7;
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 5F));
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 5F));
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 5F));
            this.userTableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.userTableLayoutPanel.Size = new System.Drawing.Size(590, 117);
            this.userTableLayoutPanel.TabIndex = 0;
            // 
            // findConsumerButton
            // 
            this.findConsumerButton.Dock = System.Windows.Forms.DockStyle.Right;
            this.findConsumerButton.Image = global::Workstation.Properties.Resources.srch_16;
            this.findConsumerButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.findConsumerButton.Location = new System.Drawing.Point(497, 3);
            this.findConsumerButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.findConsumerButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.findConsumerButton.Name = "findConsumerButton";
            this.findConsumerButton.Size = new System.Drawing.Size(90, 26);
            this.findConsumerButton.TabIndex = 2;
            this.findConsumerButton.Text = "Найти";
            this.findConsumerButton.UseVisualStyleBackColor = true;
            this.findConsumerButton.Click += new System.EventHandler(this.findConsumerButton_Click);
            // 
            // iD_CARD_NUMBERMTBox
            // 
            this.iD_CARD_NUMBERMTBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.iD_CARD_NUMBERMTBox.Location = new System.Drawing.Point(83, 3);
            this.iD_CARD_NUMBERMTBox.Mask = "00000000000000000000";
            this.iD_CARD_NUMBERMTBox.MaximumSize = new System.Drawing.Size(126, 25);
            this.iD_CARD_NUMBERMTBox.Name = "iD_CARD_NUMBERMTBox";
            this.iD_CARD_NUMBERMTBox.Size = new System.Drawing.Size(110, 20);
            this.iD_CARD_NUMBERMTBox.TabIndex = 0;
            this.iD_CARD_NUMBERMTBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // rNNMTBox
            // 
            this.rNNMTBox.Dock = System.Windows.Forms.DockStyle.Left;
            this.rNNMTBox.Location = new System.Drawing.Point(83, 33);
            this.rNNMTBox.Mask = "000000000000";
            this.rNNMTBox.Name = "rNNMTBox";
            this.rNNMTBox.Size = new System.Drawing.Size(80, 20);
            this.rNNMTBox.TabIndex = 3;
            this.rNNMTBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // label2
            // 
            this.label2.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(5, 6);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(72, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "Документ №";
            // 
            // label4
            // 
            this.label4.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(21, 66);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(56, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "Фамилия";
            // 
            // label5
            // 
            this.label5.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(244, 66);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(29, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "Имя";
            // 
            // fIRST_NAMETextBox
            // 
            this.fIRST_NAMETextBox.CharacterCasing = System.Windows.Forms.CharacterCasing.Upper;
            this.fIRST_NAMETextBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.fIRST_NAMETextBox.Location = new System.Drawing.Point(83, 63);
            this.fIRST_NAMETextBox.Name = "fIRST_NAMETextBox";
            this.fIRST_NAMETextBox.Size = new System.Drawing.Size(110, 20);
            this.fIRST_NAMETextBox.TabIndex = 4;
            // 
            // mIDDLE_NAMETextBox
            // 
            this.mIDDLE_NAMETextBox.CharacterCasing = System.Windows.Forms.CharacterCasing.Upper;
            this.mIDDLE_NAMETextBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.mIDDLE_NAMETextBox.Location = new System.Drawing.Point(279, 63);
            this.mIDDLE_NAMETextBox.Name = "mIDDLE_NAMETextBox";
            this.mIDDLE_NAMETextBox.Size = new System.Drawing.Size(110, 20);
            this.mIDDLE_NAMETextBox.TabIndex = 5;
            // 
            // label6
            // 
            this.label6.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(415, 66);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(54, 13);
            this.label6.TabIndex = 5;
            this.label6.Text = "Отчество";
            // 
            // lAST_NAMETextBox
            // 
            this.lAST_NAMETextBox.CharacterCasing = System.Windows.Forms.CharacterCasing.Upper;
            this.lAST_NAMETextBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lAST_NAMETextBox.Location = new System.Drawing.Point(475, 63);
            this.lAST_NAMETextBox.Name = "lAST_NAMETextBox";
            this.lAST_NAMETextBox.Size = new System.Drawing.Size(112, 20);
            this.lAST_NAMETextBox.TabIndex = 6;
            // 
            // dATE_OF_BIRTHDateTimePicker
            // 
            this.dATE_OF_BIRTHDateTimePicker.CustomFormat = "dd.MM.yyyy";
            this.dATE_OF_BIRTHDateTimePicker.Dock = System.Windows.Forms.DockStyle.Left;
            this.dATE_OF_BIRTHDateTimePicker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dATE_OF_BIRTHDateTimePicker.Location = new System.Drawing.Point(279, 93);
            this.dATE_OF_BIRTHDateTimePicker.Name = "dATE_OF_BIRTHDateTimePicker";
            this.dATE_OF_BIRTHDateTimePicker.Size = new System.Drawing.Size(81, 20);
            this.dATE_OF_BIRTHDateTimePicker.TabIndex = 8;
            // 
            // label8
            // 
            this.label8.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label8.Location = new System.Drawing.Point(395, 90);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(74, 27);
            this.label8.TabIndex = 11;
            this.label8.Text = "Пол";
            this.label8.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // gENDERComboBox
            // 
            this.gENDERComboBox.Dock = System.Windows.Forms.DockStyle.Left;
            this.gENDERComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.gENDERComboBox.FormattingEnabled = true;
            this.gENDERComboBox.Items.AddRange(new object[] {
            "мужской",
            "женский",
            "неизвестный"});
            this.gENDERComboBox.Location = new System.Drawing.Point(475, 93);
            this.gENDERComboBox.Name = "gENDERComboBox";
            this.gENDERComboBox.Size = new System.Drawing.Size(83, 21);
            this.gENDERComboBox.TabIndex = 9;
            // 
            // pASSPORTTYPEComboBox
            // 
            this.userTableLayoutPanel.SetColumnSpan(this.pASSPORTTYPEComboBox, 2);
            this.pASSPORTTYPEComboBox.DataSource = this.pASSPORTTYPEBindingSource;
            this.pASSPORTTYPEComboBox.DisplayMember = "NAME";
            this.pASSPORTTYPEComboBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pASSPORTTYPEComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.pASSPORTTYPEComboBox.FormattingEnabled = true;
            this.pASSPORTTYPEComboBox.Location = new System.Drawing.Point(279, 3);
            this.pASSPORTTYPEComboBox.Name = "pASSPORTTYPEComboBox";
            this.pASSPORTTYPEComboBox.Size = new System.Drawing.Size(190, 21);
            this.pASSPORTTYPEComboBox.TabIndex = 1;
            this.pASSPORTTYPEComboBox.ValueMember = "ID";
            // 
            // pASSPORTTYPEBindingSource
            // 
            this.pASSPORTTYPEBindingSource.DataMember = "PASSPORT_TYPE";
            this.pASSPORTTYPEBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // label9
            // 
            this.label9.Dock = System.Windows.Forms.DockStyle.Right;
            this.label9.Location = new System.Drawing.Point(199, 0);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(74, 25);
            this.label9.TabIndex = 16;
            this.label9.Text = "Тип докум.";
            this.label9.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label7
            // 
            this.userTableLayoutPanel.SetColumnSpan(this.label7, 2);
            this.label7.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label7.Location = new System.Drawing.Point(83, 90);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(190, 27);
            this.label7.TabIndex = 7;
            this.label7.Text = "Дата рождения";
            this.label7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label3
            // 
            this.label3.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(47, 36);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(30, 13);
            this.label3.TabIndex = 1;
            this.label3.Text = "РНН";
            // 
            // pASSPORT_TYPETableAdapter
            // 
            this.pASSPORT_TYPETableAdapter.ClearBeforeFill = true;
            // 
            // DataWorkConsumer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.userGroupBox);
            this.Name = "DataWorkConsumer";
            this.Size = new System.Drawing.Size(596, 136);
            this.userGroupBox.ResumeLayout(false);
            this.userTableLayoutPanel.ResumeLayout(false);
            this.userTableLayoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pASSPORTTYPEBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox userGroupBox;
        private System.Windows.Forms.TableLayoutPanel userTableLayoutPanel;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label3;
        internal Workstation.Modules.PClasses.PButton findConsumerButton;
        private System.Windows.Forms.BindingSource pASSPORTTYPEBindingSource;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.PASSPORT_TYPETableAdapter pASSPORT_TYPETableAdapter;
        public System.Windows.Forms.MaskedTextBox iD_CARD_NUMBERMTBox;
        public System.Windows.Forms.MaskedTextBox rNNMTBox;
        public System.Windows.Forms.TextBox fIRST_NAMETextBox;
        public System.Windows.Forms.TextBox mIDDLE_NAMETextBox;
        public System.Windows.Forms.TextBox lAST_NAMETextBox;
        public System.Windows.Forms.DateTimePicker dATE_OF_BIRTHDateTimePicker;
        public System.Windows.Forms.ComboBox gENDERComboBox;
        public System.Windows.Forms.ComboBox pASSPORTTYPEComboBox;
    }
}
