namespace Workstation.Modules
{
    partial class DataWorkQuestionsAndAnswers
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
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView = new System.Windows.Forms.DataGridView();
            this.dataGridViewTextBoxColumn6 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn8 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.QUESTIONTYPENAME = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn5 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn3 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn7 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn4 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.qUESTIONNAMETextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter = new Workstation.phmkDataSetTableAdapters.CONSUMER_QUESTION_FOR_COMPAIGNTableAdapter();
            this.aNSWERComboBox = new System.Windows.Forms.ComboBox();
            this.aNSWERSDWBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.aNSWERS_DWTableAdapter = new Workstation.phmkDataSetTableAdapters.ANSWERS_DWTableAdapter();
            this.saveButton = new Workstation.Modules.PClasses.PButton();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.aNSWERSDWBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // cONSUMER_QUESTION_FOR_COMPAIGNDataGridView
            // 
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.AllowUserToAddRows = false;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.AllowUserToDeleteRows = false;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.AutoGenerateColumns = false;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dataGridViewTextBoxColumn6,
            this.dataGridViewTextBoxColumn8,
            this.QUESTIONTYPENAME,
            this.dataGridViewTextBoxColumn5,
            this.dataGridViewTextBoxColumn1,
            this.dataGridViewTextBoxColumn2,
            this.dataGridViewTextBoxColumn3,
            this.dataGridViewTextBoxColumn7,
            this.dataGridViewTextBoxColumn4});
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.DataSource = this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.Location = new System.Drawing.Point(0, 0);
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.MultiSelect = false;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.Name = "cONSUMER_QUESTION_FOR_COMPAIGNDataGridView";
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.ReadOnly = true;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.Size = new System.Drawing.Size(485, 131);
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.TabIndex = 2;
            this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.CurrentCellChanged += new System.EventHandler(this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView_CurrentCellChanged);
            // 
            // dataGridViewTextBoxColumn6
            // 
            this.dataGridViewTextBoxColumn6.DataPropertyName = "QUESTIONNAME";
            this.dataGridViewTextBoxColumn6.HeaderText = "Вопрос";
            this.dataGridViewTextBoxColumn6.Name = "dataGridViewTextBoxColumn6";
            this.dataGridViewTextBoxColumn6.ReadOnly = true;
            this.dataGridViewTextBoxColumn6.Width = 200;
            // 
            // dataGridViewTextBoxColumn8
            // 
            this.dataGridViewTextBoxColumn8.DataPropertyName = "ANSWERNAME";
            this.dataGridViewTextBoxColumn8.HeaderText = "Ответ";
            this.dataGridViewTextBoxColumn8.Name = "dataGridViewTextBoxColumn8";
            this.dataGridViewTextBoxColumn8.ReadOnly = true;
            this.dataGridViewTextBoxColumn8.Width = 150;
            // 
            // QUESTIONTYPENAME
            // 
            this.QUESTIONTYPENAME.DataPropertyName = "QUESTIONTYPENAME";
            this.QUESTIONTYPENAME.HeaderText = "Тип";
            this.QUESTIONTYPENAME.Name = "QUESTIONTYPENAME";
            this.QUESTIONTYPENAME.ReadOnly = true;
            this.QUESTIONTYPENAME.Width = 200;
            // 
            // dataGridViewTextBoxColumn5
            // 
            this.dataGridViewTextBoxColumn5.DataPropertyName = "ALTANSWER";
            this.dataGridViewTextBoxColumn5.HeaderText = "Альтернативный ответ";
            this.dataGridViewTextBoxColumn5.Name = "dataGridViewTextBoxColumn5";
            this.dataGridViewTextBoxColumn5.ReadOnly = true;
            this.dataGridViewTextBoxColumn5.Visible = false;
            this.dataGridViewTextBoxColumn5.Width = 150;
            // 
            // dataGridViewTextBoxColumn1
            // 
            this.dataGridViewTextBoxColumn1.DataPropertyName = "ID";
            this.dataGridViewTextBoxColumn1.HeaderText = "ID";
            this.dataGridViewTextBoxColumn1.Name = "dataGridViewTextBoxColumn1";
            this.dataGridViewTextBoxColumn1.ReadOnly = true;
            this.dataGridViewTextBoxColumn1.Visible = false;
            // 
            // dataGridViewTextBoxColumn2
            // 
            this.dataGridViewTextBoxColumn2.DataPropertyName = "GOMPAIGNID";
            this.dataGridViewTextBoxColumn2.HeaderText = "GOMPAIGNID";
            this.dataGridViewTextBoxColumn2.Name = "dataGridViewTextBoxColumn2";
            this.dataGridViewTextBoxColumn2.ReadOnly = true;
            this.dataGridViewTextBoxColumn2.Visible = false;
            // 
            // dataGridViewTextBoxColumn3
            // 
            this.dataGridViewTextBoxColumn3.DataPropertyName = "QUESTIONID";
            this.dataGridViewTextBoxColumn3.HeaderText = "QUESTIONID";
            this.dataGridViewTextBoxColumn3.Name = "dataGridViewTextBoxColumn3";
            this.dataGridViewTextBoxColumn3.ReadOnly = true;
            this.dataGridViewTextBoxColumn3.Visible = false;
            // 
            // dataGridViewTextBoxColumn7
            // 
            this.dataGridViewTextBoxColumn7.DataPropertyName = "QUESTIONTYPE";
            this.dataGridViewTextBoxColumn7.HeaderText = "QUESTIONTYPE";
            this.dataGridViewTextBoxColumn7.Name = "dataGridViewTextBoxColumn7";
            this.dataGridViewTextBoxColumn7.ReadOnly = true;
            this.dataGridViewTextBoxColumn7.Visible = false;
            this.dataGridViewTextBoxColumn7.Width = 60;
            // 
            // dataGridViewTextBoxColumn4
            // 
            this.dataGridViewTextBoxColumn4.DataPropertyName = "ANSWER";
            this.dataGridViewTextBoxColumn4.HeaderText = "ANSWER";
            this.dataGridViewTextBoxColumn4.Name = "dataGridViewTextBoxColumn4";
            this.dataGridViewTextBoxColumn4.ReadOnly = true;
            this.dataGridViewTextBoxColumn4.Visible = false;
            // 
            // cONSUMER_QUESTION_FOR_COMPAIGNBindingSource
            // 
            this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource.DataMember = "CONSUMER_QUESTION_FOR_COMPAIGN";
            this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // qUESTIONNAMETextBox
            // 
            this.qUESTIONNAMETextBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.qUESTIONNAMETextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource, "QUESTIONNAME", true));
            this.qUESTIONNAMETextBox.Location = new System.Drawing.Point(6, 150);
            this.qUESTIONNAMETextBox.Multiline = true;
            this.qUESTIONNAMETextBox.Name = "qUESTIONNAMETextBox";
            this.qUESTIONNAMETextBox.ReadOnly = true;
            this.qUESTIONNAMETextBox.Size = new System.Drawing.Size(476, 80);
            this.qUESTIONNAMETextBox.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 134);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(47, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Вопрос:";
            // 
            // cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter
            // 
            this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter.ClearBeforeFill = true;
            // 
            // aNSWERComboBox
            // 
            this.aNSWERComboBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.aNSWERComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource, "ANSWER", true));
            this.aNSWERComboBox.DataSource = this.aNSWERSDWBindingSource;
            this.aNSWERComboBox.DisplayMember = "ANSWER";
            this.aNSWERComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.aNSWERComboBox.FormattingEnabled = true;
            this.aNSWERComboBox.Location = new System.Drawing.Point(6, 249);
            this.aNSWERComboBox.Name = "aNSWERComboBox";
            this.aNSWERComboBox.Size = new System.Drawing.Size(380, 21);
            this.aNSWERComboBox.TabIndex = 9;
            this.aNSWERComboBox.ValueMember = "SID";
            this.aNSWERComboBox.SelectedIndexChanged += new System.EventHandler(this.aNSWERComboBox_SelectedIndexChanged);
            // 
            // aNSWERSDWBindingSource
            // 
            this.aNSWERSDWBindingSource.DataMember = "ANSWERS_DW";
            this.aNSWERSDWBindingSource.DataSource = this.phmkDataSet;
            // 
            // aNSWERS_DWTableAdapter
            // 
            this.aNSWERS_DWTableAdapter.ClearBeforeFill = true;
            // 
            // saveButton
            // 
            this.saveButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.saveButton.Image = global::Workstation.Properties.Resources.opts_16;
            this.saveButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.saveButton.Location = new System.Drawing.Point(392, 244);
            this.saveButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.saveButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.saveButton.Name = "saveButton";
            this.saveButton.Size = new System.Drawing.Size(90, 26);
            this.saveButton.TabIndex = 10;
            this.saveButton.Text = "Подтв.";
            this.saveButton.UseVisualStyleBackColor = true;
            this.saveButton.Click += new System.EventHandler(this.saveButton_Click);
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 233);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(37, 13);
            this.label2.TabIndex = 11;
            this.label2.Text = "Ответ";
            // 
            // DataWorkQuestionsAndAnswers
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.label2);
            this.Controls.Add(this.saveButton);
            this.Controls.Add(this.aNSWERComboBox);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.qUESTIONNAMETextBox);
            this.Controls.Add(this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView);
            this.Name = "DataWorkQuestionsAndAnswers";
            this.Size = new System.Drawing.Size(485, 273);
            ((System.ComponentModel.ISupportInitialize)(this.cONSUMER_QUESTION_FOR_COMPAIGNDataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.aNSWERSDWBindingSource)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private phmkDataSet phmkDataSet;
        private System.Windows.Forms.BindingSource cONSUMER_QUESTION_FOR_COMPAIGNBindingSource;
        private Workstation.phmkDataSetTableAdapters.CONSUMER_QUESTION_FOR_COMPAIGNTableAdapter cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter;
        private System.Windows.Forms.DataGridView cONSUMER_QUESTION_FOR_COMPAIGNDataGridView;
        private System.Windows.Forms.TextBox qUESTIONNAMETextBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox aNSWERComboBox;
        private System.Windows.Forms.BindingSource aNSWERSDWBindingSource;
        private Workstation.phmkDataSetTableAdapters.ANSWERS_DWTableAdapter aNSWERS_DWTableAdapter;
        private Workstation.Modules.PClasses.PButton saveButton;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn6;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn8;
        private System.Windows.Forms.DataGridViewTextBoxColumn QUESTIONTYPENAME;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn5;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn1;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn2;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn3;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn7;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn4;

    }
}
