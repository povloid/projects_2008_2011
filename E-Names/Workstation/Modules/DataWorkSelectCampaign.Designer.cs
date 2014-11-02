namespace Workstation.Modules
{
    partial class DataWorkSelectCampaign
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
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.cOMPAIGNBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.DESCRIPTIONTextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.cOMPAIGNTableAdapter = new Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter();
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.aCTIONNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.vARIANTDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tARGETGROUPDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cAMPAIGNCODEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.iDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dESCRIPTIONDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sTARTDATEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.eNDDATEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cAMPAIGNIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.aCTIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.vARIANTIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tARGETGROUPIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.label1);
            this.groupBox.Controls.Add(this.DESCRIPTIONTextBox);
            this.groupBox.Controls.Add(this.dataGridView);
            this.groupBox.Size = new System.Drawing.Size(607, 439);
            // 
            // dataGridView
            // 
            this.dataGridView.AllowUserToAddRows = false;
            this.dataGridView.AllowUserToDeleteRows = false;
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn,
            this.aCTIONNAMEDataGridViewTextBoxColumn,
            this.vARIANTDataGridViewTextBoxColumn,
            this.tARGETGROUPDataGridViewTextBoxColumn,
            this.cAMPAIGNCODEDataGridViewTextBoxColumn,
            this.iDDataGridViewTextBoxColumn,
            this.dESCRIPTIONDataGridViewTextBoxColumn,
            this.sTARTDATEDataGridViewTextBoxColumn,
            this.eNDDATEDataGridViewTextBoxColumn,
            this.cAMPAIGNIDDataGridViewTextBoxColumn,
            this.aCTIONIDDataGridViewTextBoxColumn,
            this.vARIANTIDDataGridViewTextBoxColumn,
            this.tARGETGROUPIDDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.cOMPAIGNBindingSource;
            this.dataGridView.Location = new System.Drawing.Point(6, 19);
            this.dataGridView.MultiSelect = false;
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.ReadOnly = true;
            this.dataGridView.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView.Size = new System.Drawing.Size(595, 275);
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
            // DESCRIPTIONTextBox
            // 
            this.DESCRIPTIONTextBox.Location = new System.Drawing.Point(6, 313);
            this.DESCRIPTIONTextBox.Multiline = true;
            this.DESCRIPTIONTextBox.Name = "DESCRIPTIONTextBox";
            this.DESCRIPTIONTextBox.ReadOnly = true;
            this.DESCRIPTIONTextBox.Size = new System.Drawing.Size(595, 120);
            this.DESCRIPTIONTextBox.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 297);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(71, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "О кампании:";
            // 
            // cOMPAIGNTableAdapter
            // 
            this.cOMPAIGNTableAdapter.ClearBeforeFill = true;
            // 
            // cAMPAIGNNAMEDataGridViewTextBoxColumn
            // 
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.DataPropertyName = "CAMPAIGNNAME";
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.HeaderText = "CampaignName";
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.Name = "cAMPAIGNNAMEDataGridViewTextBoxColumn";
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.ReadOnly = true;
            this.cAMPAIGNNAMEDataGridViewTextBoxColumn.Width = 130;
            // 
            // aCTIONNAMEDataGridViewTextBoxColumn
            // 
            this.aCTIONNAMEDataGridViewTextBoxColumn.DataPropertyName = "ACTIONNAME";
            this.aCTIONNAMEDataGridViewTextBoxColumn.HeaderText = "ActionName";
            this.aCTIONNAMEDataGridViewTextBoxColumn.Name = "aCTIONNAMEDataGridViewTextBoxColumn";
            this.aCTIONNAMEDataGridViewTextBoxColumn.ReadOnly = true;
            this.aCTIONNAMEDataGridViewTextBoxColumn.Width = 130;
            // 
            // vARIANTDataGridViewTextBoxColumn
            // 
            this.vARIANTDataGridViewTextBoxColumn.DataPropertyName = "VARIANT";
            this.vARIANTDataGridViewTextBoxColumn.HeaderText = "VariantName";
            this.vARIANTDataGridViewTextBoxColumn.Name = "vARIANTDataGridViewTextBoxColumn";
            this.vARIANTDataGridViewTextBoxColumn.ReadOnly = true;
            this.vARIANTDataGridViewTextBoxColumn.Width = 130;
            // 
            // tARGETGROUPDataGridViewTextBoxColumn
            // 
            this.tARGETGROUPDataGridViewTextBoxColumn.DataPropertyName = "TARGETGROUP";
            this.tARGETGROUPDataGridViewTextBoxColumn.HeaderText = "TargetGroupName";
            this.tARGETGROUPDataGridViewTextBoxColumn.Name = "tARGETGROUPDataGridViewTextBoxColumn";
            this.tARGETGROUPDataGridViewTextBoxColumn.ReadOnly = true;
            this.tARGETGROUPDataGridViewTextBoxColumn.Width = 130;
            // 
            // cAMPAIGNCODEDataGridViewTextBoxColumn
            // 
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.DataPropertyName = "CAMPAIGNCODE";
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.HeaderText = "CCOD";
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.Name = "cAMPAIGNCODEDataGridViewTextBoxColumn";
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.ReadOnly = true;
            this.cAMPAIGNCODEDataGridViewTextBoxColumn.Visible = false;
            // 
            // iDDataGridViewTextBoxColumn
            // 
            this.iDDataGridViewTextBoxColumn.DataPropertyName = "ID";
            this.iDDataGridViewTextBoxColumn.HeaderText = "ID";
            this.iDDataGridViewTextBoxColumn.Name = "iDDataGridViewTextBoxColumn";
            this.iDDataGridViewTextBoxColumn.ReadOnly = true;
            this.iDDataGridViewTextBoxColumn.Visible = false;
            // 
            // dESCRIPTIONDataGridViewTextBoxColumn
            // 
            this.dESCRIPTIONDataGridViewTextBoxColumn.DataPropertyName = "DESCRIPTION";
            this.dESCRIPTIONDataGridViewTextBoxColumn.HeaderText = "DESCRIPTION";
            this.dESCRIPTIONDataGridViewTextBoxColumn.Name = "dESCRIPTIONDataGridViewTextBoxColumn";
            this.dESCRIPTIONDataGridViewTextBoxColumn.ReadOnly = true;
            this.dESCRIPTIONDataGridViewTextBoxColumn.Visible = false;
            // 
            // sTARTDATEDataGridViewTextBoxColumn
            // 
            this.sTARTDATEDataGridViewTextBoxColumn.DataPropertyName = "STARTDATE";
            this.sTARTDATEDataGridViewTextBoxColumn.HeaderText = "STARTDATE";
            this.sTARTDATEDataGridViewTextBoxColumn.Name = "sTARTDATEDataGridViewTextBoxColumn";
            this.sTARTDATEDataGridViewTextBoxColumn.ReadOnly = true;
            this.sTARTDATEDataGridViewTextBoxColumn.Visible = false;
            // 
            // eNDDATEDataGridViewTextBoxColumn
            // 
            this.eNDDATEDataGridViewTextBoxColumn.DataPropertyName = "ENDDATE";
            this.eNDDATEDataGridViewTextBoxColumn.HeaderText = "ENDDATE";
            this.eNDDATEDataGridViewTextBoxColumn.Name = "eNDDATEDataGridViewTextBoxColumn";
            this.eNDDATEDataGridViewTextBoxColumn.ReadOnly = true;
            this.eNDDATEDataGridViewTextBoxColumn.Visible = false;
            // 
            // cAMPAIGNIDDataGridViewTextBoxColumn
            // 
            this.cAMPAIGNIDDataGridViewTextBoxColumn.DataPropertyName = "CAMPAIGNID";
            this.cAMPAIGNIDDataGridViewTextBoxColumn.HeaderText = "CAMPAIGNID";
            this.cAMPAIGNIDDataGridViewTextBoxColumn.Name = "cAMPAIGNIDDataGridViewTextBoxColumn";
            this.cAMPAIGNIDDataGridViewTextBoxColumn.ReadOnly = true;
            this.cAMPAIGNIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // aCTIONIDDataGridViewTextBoxColumn
            // 
            this.aCTIONIDDataGridViewTextBoxColumn.DataPropertyName = "ACTIONID";
            this.aCTIONIDDataGridViewTextBoxColumn.HeaderText = "ACTIONID";
            this.aCTIONIDDataGridViewTextBoxColumn.Name = "aCTIONIDDataGridViewTextBoxColumn";
            this.aCTIONIDDataGridViewTextBoxColumn.ReadOnly = true;
            this.aCTIONIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // vARIANTIDDataGridViewTextBoxColumn
            // 
            this.vARIANTIDDataGridViewTextBoxColumn.DataPropertyName = "VARIANTID";
            this.vARIANTIDDataGridViewTextBoxColumn.HeaderText = "VARIANTID";
            this.vARIANTIDDataGridViewTextBoxColumn.Name = "vARIANTIDDataGridViewTextBoxColumn";
            this.vARIANTIDDataGridViewTextBoxColumn.ReadOnly = true;
            this.vARIANTIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // tARGETGROUPIDDataGridViewTextBoxColumn
            // 
            this.tARGETGROUPIDDataGridViewTextBoxColumn.DataPropertyName = "TARGETGROUPID";
            this.tARGETGROUPIDDataGridViewTextBoxColumn.HeaderText = "TARGETGROUPID";
            this.tARGETGROUPIDDataGridViewTextBoxColumn.Name = "tARGETGROUPIDDataGridViewTextBoxColumn";
            this.tARGETGROUPIDDataGridViewTextBoxColumn.ReadOnly = true;
            this.tARGETGROUPIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // DataWorkSelectCampaign
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(625, 521);
            this.Name = "DataWorkSelectCampaign";
            this.pictureInHead = global::Workstation.Properties.Resources.flag_32;
            this.Text = "Выбрать кампанию...";
            this.textInHead = "Выберите кампанию из списка";
            this.windowCaptionText = "Выбрать кампанию...";
            this.Load += new System.EventHandler(this.DataWorkSelectCampaign_Load);
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TextBox DESCRIPTIONTextBox;
        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.Label label1;
        private phmkDataSet phmkDataSet;
        private System.Windows.Forms.BindingSource cOMPAIGNBindingSource;
        private Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter cOMPAIGNTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn cAMPAIGNNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn aCTIONNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn vARIANTDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn tARGETGROUPDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cAMPAIGNCODEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn iDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn dESCRIPTIONDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sTARTDATEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn eNDDATEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cAMPAIGNIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn aCTIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn vARIANTIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn tARGETGROUPIDDataGridViewTextBoxColumn;
    }
}
