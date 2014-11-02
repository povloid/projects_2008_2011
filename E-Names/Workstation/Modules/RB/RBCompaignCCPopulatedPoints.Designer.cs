namespace Workstation.Modules.RB
{
    partial class RBCompaignCCPopulatedPoints
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
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.rEGIONBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.rEGIONTableAdapter = new Workstation.phmkDataSetTableAdapters.REGIONTableAdapter();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.cOMPAIGNPOPULATEDPOINTSBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.pOPULATED_POINTSTableAdapter = new Workstation.phmkDataSetTableAdapters.POPULATED_POINTSTableAdapter();
            this.pOPULATEDPOINTSBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.cOMPAIGN_POPULATED_POINTSTableAdapter = new Workstation.phmkDataSetTableAdapters.COMPAIGN_POPULATED_POINTSTableAdapter();
            this.rEGIONNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pOPULATEDPOINTNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.rEGIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cOMPAIGNIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.rEGIONBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNPOPULATEDPOINTSBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pOPULATEDPOINTSBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.dataGridView);
            this.fillPanel.Size = new System.Drawing.Size(201, 188);
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // rEGIONBindingSource
            // 
            this.rEGIONBindingSource.DataMember = "REGION";
            this.rEGIONBindingSource.DataSource = this.phmkDataSet;
            // 
            // rEGIONTableAdapter
            // 
            this.rEGIONTableAdapter.ClearBeforeFill = true;
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.rEGIONNAMEDataGridViewTextBoxColumn,
            this.pOPULATEDPOINTNAMEDataGridViewTextBoxColumn,
            this.rEGIONIDDataGridViewTextBoxColumn,
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn,
            this.cOMPAIGNIDDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.cOMPAIGNPOPULATEDPOINTSBindingSource;
            this.dataGridView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView.Location = new System.Drawing.Point(0, 0);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(201, 188);
            this.dataGridView.TabIndex = 0;
            // 
            // cOMPAIGNPOPULATEDPOINTSBindingSource
            // 
            this.cOMPAIGNPOPULATEDPOINTSBindingSource.DataMember = "COMPAIGN_POPULATED_POINTS";
            this.cOMPAIGNPOPULATEDPOINTSBindingSource.DataSource = this.phmkDataSet;
            // 
            // pOPULATED_POINTSTableAdapter
            // 
            this.pOPULATED_POINTSTableAdapter.ClearBeforeFill = true;
            // 
            // pOPULATEDPOINTSBindingSource
            // 
            this.pOPULATEDPOINTSBindingSource.DataMember = "POPULATED_POINTS";
            this.pOPULATEDPOINTSBindingSource.DataSource = this.phmkDataSet;
            // 
            // cOMPAIGN_POPULATED_POINTSTableAdapter
            // 
            this.cOMPAIGN_POPULATED_POINTSTableAdapter.ClearBeforeFill = true;
            // 
            // rEGIONNAMEDataGridViewTextBoxColumn
            // 
            this.rEGIONNAMEDataGridViewTextBoxColumn.DataPropertyName = "REGION_NAME";
            this.rEGIONNAMEDataGridViewTextBoxColumn.HeaderText = "REGION_NAME";
            this.rEGIONNAMEDataGridViewTextBoxColumn.Name = "rEGIONNAMEDataGridViewTextBoxColumn";
            // 
            // pOPULATEDPOINTNAMEDataGridViewTextBoxColumn
            // 
            this.pOPULATEDPOINTNAMEDataGridViewTextBoxColumn.DataPropertyName = "POPULATED_POINT_NAME";
            this.pOPULATEDPOINTNAMEDataGridViewTextBoxColumn.HeaderText = "POPULATED_POINT_NAME";
            this.pOPULATEDPOINTNAMEDataGridViewTextBoxColumn.Name = "pOPULATEDPOINTNAMEDataGridViewTextBoxColumn";
            // 
            // rEGIONIDDataGridViewTextBoxColumn
            // 
            this.rEGIONIDDataGridViewTextBoxColumn.DataPropertyName = "REGIONID";
            this.rEGIONIDDataGridViewTextBoxColumn.HeaderText = "REGIONID";
            this.rEGIONIDDataGridViewTextBoxColumn.Name = "rEGIONIDDataGridViewTextBoxColumn";
            this.rEGIONIDDataGridViewTextBoxColumn.ReadOnly = true;
            this.rEGIONIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // pOPULATEDPOINTIDDataGridViewTextBoxColumn
            // 
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn.DataPropertyName = "POPULATED_POINTID";
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn.HeaderText = "POPULATED_POINTID";
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn.Name = "pOPULATEDPOINTIDDataGridViewTextBoxColumn";
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.pOPULATEDPOINTIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // cOMPAIGNIDDataGridViewTextBoxColumn
            // 
            this.cOMPAIGNIDDataGridViewTextBoxColumn.DataPropertyName = "COMPAIGNID";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.HeaderText = "COMPAIGNID";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.Name = "cOMPAIGNIDDataGridViewTextBoxColumn";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // RBCompaignCCPopulatedPoints
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "RBCompaignCCPopulatedPoints";
            this.Size = new System.Drawing.Size(207, 255);
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.rEGIONBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNPOPULATEDPOINTSBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pOPULATEDPOINTSBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private phmkDataSet phmkDataSet;
        private System.Windows.Forms.BindingSource rEGIONBindingSource;
        private Workstation.phmkDataSetTableAdapters.REGIONTableAdapter rEGIONTableAdapter;
        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource cOMPAIGNPOPULATEDPOINTSBindingSource;
        private Workstation.phmkDataSetTableAdapters.POPULATED_POINTSTableAdapter pOPULATED_POINTSTableAdapter;
        private System.Windows.Forms.BindingSource pOPULATEDPOINTSBindingSource;
        private Workstation.phmkDataSetTableAdapters.COMPAIGN_POPULATED_POINTSTableAdapter cOMPAIGN_POPULATED_POINTSTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn rEGIONNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn pOPULATEDPOINTNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn rEGIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn pOPULATEDPOINTIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cOMPAIGNIDDataGridViewTextBoxColumn;
    }
}
