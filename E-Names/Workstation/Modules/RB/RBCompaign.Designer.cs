namespace Workstation.Modules.RB
{
    partial class RBCompaign
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

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBCompaign));
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.cOMPAIGNBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.rbCompaignCCMedia1 = new Workstation.Modules.RB.RBCompaignCCMedia();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.rbCompaignCCQuestions1 = new Workstation.Modules.RB.RBCompaignCCQuestions();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.rbCompaignCCPopulatedPoints1 = new Workstation.Modules.RB.RBCompaignCCPopulatedPoints();
            this.tabPage4 = new System.Windows.Forms.TabPage();
            this.rbCompaignCCPrizes1 = new Workstation.Modules.RB.RBCompaignCCPrizes();
            this.tabPage5 = new System.Windows.Forms.TabPage();
            this.imageList1 = new System.Windows.Forms.ImageList(this.components);
            this.cOMPAIGNTableAdapter = new Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter();
            this.sTARTDATEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.eNDDATEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cAMPAIGNIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cAMPAIGNCODEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.aCTIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.vARIANTIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tARGETGROUPIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.aCTIONNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.vARIANTDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tARGETGROUPDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dESCRIPTIONDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.ID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fillPanel.SuspendLayout();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.tabPage3.SuspendLayout();
            this.tabPage4.SuspendLayout();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.splitContainer1);
            this.fillPanel.Size = new System.Drawing.Size(972, 360);
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.dataGridView);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.tabControl1);
            this.splitContainer1.Size = new System.Drawing.Size(972, 360);
            this.splitContainer1.SplitterDistance = 797;
            this.splitContainer1.TabIndex = 0;
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.sTARTDATEDataGridViewTextBoxColumn,
            this.eNDDATEDataGridViewTextBoxColumn,
            this.cAMPAIGNIDDataGridViewTextBoxColumn,
            this.cAMPAIGNCODEDataGridViewTextBoxColumn,
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn,
            this.aCTIONIDDataGridViewTextBoxColumn,
            this.vARIANTIDDataGridViewTextBoxColumn,
            this.tARGETGROUPIDDataGridViewTextBoxColumn,
            this.aCTIONNAMEDataGridViewTextBoxColumn,
            this.vARIANTDataGridViewTextBoxColumn,
            this.tARGETGROUPDataGridViewTextBoxColumn,
            this.dESCRIPTIONDataGridViewTextBoxColumn,
            this.Column2,
            this.ID});
            this.dataGridView.DataSource = this.cOMPAIGNBindingSource;
            this.dataGridView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView.Location = new System.Drawing.Point(0, 0);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(797, 360);
            this.dataGridView.TabIndex = 0;
            this.dataGridView.CurrentCellChanged += new System.EventHandler(this.dataGridView_CurrentCellChanged);
            // 
            // cOMPAIGNBindingSource
            // 
            this.cOMPAIGNBindingSource.DataMember = "COMPAIGN";
            this.cOMPAIGNBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Controls.Add(this.tabPage4);
            this.tabControl1.Controls.Add(this.tabPage5);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.ImageList = this.imageList1;
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Multiline = true;
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(171, 360);
            this.tabControl1.TabIndex = 0;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.rbCompaignCCMedia1);
            this.tabPage1.ImageIndex = 3;
            this.tabPage1.Location = new System.Drawing.Point(4, 80);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(163, 276);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Каналы";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // rbCompaignCCMedia1
            // 
            this.rbCompaignCCMedia1.capPanelText = "capLabel";
            this.rbCompaignCCMedia1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rbCompaignCCMedia1.Location = new System.Drawing.Point(3, 3);
            this.rbCompaignCCMedia1.Name = "rbCompaignCCMedia1";
            this.rbCompaignCCMedia1.Size = new System.Drawing.Size(157, 270);
            this.rbCompaignCCMedia1.TabIndex = 0;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.rbCompaignCCQuestions1);
            this.tabPage2.ImageIndex = 4;
            this.tabPage2.Location = new System.Drawing.Point(4, 80);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(163, 0);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Вопросы";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // rbCompaignCCQuestions1
            // 
            this.rbCompaignCCQuestions1.capPanelText = "capLabel";
            this.rbCompaignCCQuestions1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rbCompaignCCQuestions1.Location = new System.Drawing.Point(3, 3);
            this.rbCompaignCCQuestions1.Name = "rbCompaignCCQuestions1";
            this.rbCompaignCCQuestions1.Size = new System.Drawing.Size(157, 0);
            this.rbCompaignCCQuestions1.TabIndex = 0;
            // 
            // tabPage3
            // 
            this.tabPage3.Controls.Add(this.rbCompaignCCPopulatedPoints1);
            this.tabPage3.ImageIndex = 1;
            this.tabPage3.Location = new System.Drawing.Point(4, 80);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3.Size = new System.Drawing.Size(163, 0);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "Населенные пункты";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // rbCompaignCCPopulatedPoints1
            // 
            this.rbCompaignCCPopulatedPoints1.capPanelText = "capLabel";
            this.rbCompaignCCPopulatedPoints1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rbCompaignCCPopulatedPoints1.Location = new System.Drawing.Point(3, 3);
            this.rbCompaignCCPopulatedPoints1.Name = "rbCompaignCCPopulatedPoints1";
            this.rbCompaignCCPopulatedPoints1.Size = new System.Drawing.Size(157, 0);
            this.rbCompaignCCPopulatedPoints1.TabIndex = 0;
            // 
            // tabPage4
            // 
            this.tabPage4.Controls.Add(this.rbCompaignCCPrizes1);
            this.tabPage4.ImageIndex = 6;
            this.tabPage4.Location = new System.Drawing.Point(4, 80);
            this.tabPage4.Name = "tabPage4";
            this.tabPage4.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage4.Size = new System.Drawing.Size(163, 0);
            this.tabPage4.TabIndex = 3;
            this.tabPage4.Text = "Призы";
            this.tabPage4.UseVisualStyleBackColor = true;
            // 
            // rbCompaignCCPrizes1
            // 
            this.rbCompaignCCPrizes1.capPanelText = "capLabel";
            this.rbCompaignCCPrizes1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rbCompaignCCPrizes1.Location = new System.Drawing.Point(3, 3);
            this.rbCompaignCCPrizes1.Name = "rbCompaignCCPrizes1";
            this.rbCompaignCCPrizes1.Size = new System.Drawing.Size(157, 0);
            this.rbCompaignCCPrizes1.TabIndex = 0;
            // 
            // tabPage5
            // 
            this.tabPage5.ImageIndex = 5;
            this.tabPage5.Location = new System.Drawing.Point(4, 80);
            this.tabPage5.Name = "tabPage5";
            this.tabPage5.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage5.Size = new System.Drawing.Size(163, 0);
            this.tabPage5.TabIndex = 4;
            this.tabPage5.Text = "Пользователи";
            this.tabPage5.UseVisualStyleBackColor = true;
            // 
            // imageList1
            // 
            this.imageList1.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageList1.ImageStream")));
            this.imageList1.TransparentColor = System.Drawing.Color.Transparent;
            this.imageList1.Images.SetKeyName(0, "wiz_16.png");
            this.imageList1.Images.SetKeyName(1, "home_16.png");
            this.imageList1.Images.SetKeyName(2, "games_16.png");
            this.imageList1.Images.SetKeyName(3, "go_16.png");
            this.imageList1.Images.SetKeyName(4, "prefs_16.png");
            this.imageList1.Images.SetKeyName(5, "group_16.png");
            this.imageList1.Images.SetKeyName(6, "kthememgr.png");
            // 
            // cOMPAIGNTableAdapter
            // 
            this.cOMPAIGNTableAdapter.ClearBeforeFill = true;
            // 
            // sTARTDATEDataGridViewTextBoxColumn
            // 
            this.sTARTDATEDataGridViewTextBoxColumn.DataPropertyName = "STARTDATE";
            this.sTARTDATEDataGridViewTextBoxColumn.HeaderText = "Начало";
            this.sTARTDATEDataGridViewTextBoxColumn.Name = "sTARTDATEDataGridViewTextBoxColumn";
            // 
            // eNDDATEDataGridViewTextBoxColumn
            // 
            this.eNDDATEDataGridViewTextBoxColumn.DataPropertyName = "ENDDATE";
            this.eNDDATEDataGridViewTextBoxColumn.HeaderText = "Конец";
            this.eNDDATEDataGridViewTextBoxColumn.Name = "eNDDATEDataGridViewTextBoxColumn";
            // 
            // cAMPAIGNIDDataGridViewTextBoxColumn
            // 
            this.cAMPAIGNIDDataGridViewTextBoxColumn.DataPropertyName = "CAMPAIGNID";
            this.cAMPAIGNIDDataGridViewTextBoxColumn.HeaderText = "CAMPAIGNID";
            this.cAMPAIGNIDDataGridViewTextBoxColumn.Name = "cAMPAIGNIDDataGridViewTextBoxColumn";
            // 
            // cAMPAIGNCODEDataGridViewTextBoxColumn
            // 
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.DataPropertyName = "CAMPAIGNCODE";
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.HeaderText = "CAMPAIGNCODE";
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.Name = "cAMPAIGNCODEDataGridViewTextBoxColumn";
            // 
            // cAMPAIGNNAMEDataGridViewTextBoxColumn
            // 
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.DataPropertyName = "CAMPAIGNNAME";
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.HeaderText = "CAMPAIGNNAME";
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.Name = "cAMPAIGNNAMEDataGridViewTextBoxColumn";
            // 
            // aCTIONIDDataGridViewTextBoxColumn
            // 
            this.aCTIONIDDataGridViewTextBoxColumn.DataPropertyName = "ACTIONID";
            this.aCTIONIDDataGridViewTextBoxColumn.HeaderText = "ACTIONID";
            this.aCTIONIDDataGridViewTextBoxColumn.Name = "aCTIONIDDataGridViewTextBoxColumn";
            // 
            // vARIANTIDDataGridViewTextBoxColumn
            // 
            this.vARIANTIDDataGridViewTextBoxColumn.DataPropertyName = "VARIANTID";
            this.vARIANTIDDataGridViewTextBoxColumn.HeaderText = "VARIANTID";
            this.vARIANTIDDataGridViewTextBoxColumn.Name = "vARIANTIDDataGridViewTextBoxColumn";
            // 
            // tARGETGROUPIDDataGridViewTextBoxColumn
            // 
            this.tARGETGROUPIDDataGridViewTextBoxColumn.DataPropertyName = "TARGETGROUPID";
            this.tARGETGROUPIDDataGridViewTextBoxColumn.HeaderText = "TARGETGROUPID";
            this.tARGETGROUPIDDataGridViewTextBoxColumn.Name = "tARGETGROUPIDDataGridViewTextBoxColumn";
            // 
            // aCTIONNAMEDataGridViewTextBoxColumn
            // 
            this.aCTIONNAMEDataGridViewTextBoxColumn.DataPropertyName = "ACTIONNAME";
            this.aCTIONNAMEDataGridViewTextBoxColumn.HeaderText = "ACTIONNAME";
            this.aCTIONNAMEDataGridViewTextBoxColumn.Name = "aCTIONNAMEDataGridViewTextBoxColumn";
            // 
            // vARIANTDataGridViewTextBoxColumn
            // 
            this.vARIANTDataGridViewTextBoxColumn.DataPropertyName = "VARIANT";
            this.vARIANTDataGridViewTextBoxColumn.HeaderText = "VARIANT";
            this.vARIANTDataGridViewTextBoxColumn.Name = "vARIANTDataGridViewTextBoxColumn";
            // 
            // tARGETGROUPDataGridViewTextBoxColumn
            // 
            this.tARGETGROUPDataGridViewTextBoxColumn.DataPropertyName = "TARGETGROUP";
            this.tARGETGROUPDataGridViewTextBoxColumn.HeaderText = "TARGETGROUP";
            this.tARGETGROUPDataGridViewTextBoxColumn.Name = "tARGETGROUPDataGridViewTextBoxColumn";
            // 
            // dESCRIPTIONDataGridViewTextBoxColumn
            // 
            this.dESCRIPTIONDataGridViewTextBoxColumn.DataPropertyName = "DESCRIPTION";
            this.dESCRIPTIONDataGridViewTextBoxColumn.HeaderText = "DESCRIPTION";
            this.dESCRIPTIONDataGridViewTextBoxColumn.Name = "dESCRIPTIONDataGridViewTextBoxColumn";
            // 
            // Column2
            // 
            this.Column2.DataPropertyName = "ID";
            this.Column2.HeaderText = "ID";
            this.Column2.Name = "Column2";
            this.Column2.ReadOnly = true;
            this.Column2.Visible = false;
            // 
            // ID
            // 
            this.ID.DataPropertyName = "ID";
            this.ID.HeaderText = "ID";
            this.ID.Name = "ID";
            this.ID.ReadOnly = true;
            this.ID.Visible = false;
            // 
            // RBCompaign
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "RBCompaign";
            this.Size = new System.Drawing.Size(978, 427);
            this.fillPanel.ResumeLayout(false);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.tabPage3.ResumeLayout(false);
            this.tabPage4.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource cOMPAIGNBindingSource;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter cOMPAIGNTableAdapter;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.TabPage tabPage4;
        private System.Windows.Forms.TabPage tabPage5;
        private System.Windows.Forms.ImageList imageList1;
        private RBCompaignCCMedia rbCompaignCCMedia1;
        private RBCompaignCCPrizes rbCompaignCCPrizes1;
        private RBCompaignCCQuestions rbCompaignCCQuestions1;
        private RBCompaignCCPopulatedPoints rbCompaignCCPopulatedPoints1;
        private System.Windows.Forms.DataGridViewTextBoxColumn sTARTDATEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn eNDDATEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cAMPAIGNIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cAMPAIGNCODEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cAMPAIGNNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn aCTIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn vARIANTIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn tARGETGROUPIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn aCTIONNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn vARIANTDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn tARGETGROUPDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn dESCRIPTIONDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column2;
        private System.Windows.Forms.DataGridViewTextBoxColumn ID;
    }
}
