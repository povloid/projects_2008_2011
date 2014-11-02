namespace Workstation.Modules.RB
{
    partial class RBBrandFamily
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBBrandFamily));
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.bRANDFAMILYBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.bRAND_FAMILYTableAdapter = new Workstation.phmkDataSetTableAdapters.BRAND_FAMILYTableAdapter();
            this.dESCRDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pMCODEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bRANDFAMILYBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.AccessibleDescription = null;
            this.fillPanel.AccessibleName = null;
            resources.ApplyResources(this.fillPanel, "fillPanel");
            this.fillPanel.BackgroundImage = null;
            this.fillPanel.Controls.Add(this.dataGridView);
            this.fillPanel.Font = null;
            // 
            // bRANDFAMILYBindingSource
            // 
            this.bRANDFAMILYBindingSource.DataMember = "BRAND_FAMILY";
            this.bRANDFAMILYBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // bRAND_FAMILYTableAdapter
            // 
            this.bRAND_FAMILYTableAdapter.ClearBeforeFill = true;
            // 
            // dESCRDataGridViewTextBoxColumn
            // 
            this.dESCRDataGridViewTextBoxColumn.DataPropertyName = "DESCR";
            resources.ApplyResources(this.dESCRDataGridViewTextBoxColumn, "dESCRDataGridViewTextBoxColumn");
            this.dESCRDataGridViewTextBoxColumn.Name = "dESCRDataGridViewTextBoxColumn";
            // 
            // pMCODEDataGridViewTextBoxColumn
            // 
            this.pMCODEDataGridViewTextBoxColumn.DataPropertyName = "PMCODE";
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.pMCODEDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            resources.ApplyResources(this.pMCODEDataGridViewTextBoxColumn, "pMCODEDataGridViewTextBoxColumn");
            this.pMCODEDataGridViewTextBoxColumn.Name = "pMCODEDataGridViewTextBoxColumn";
            // 
            // dataGridView
            // 
            this.dataGridView.AccessibleDescription = null;
            this.dataGridView.AccessibleName = null;
            resources.ApplyResources(this.dataGridView, "dataGridView");
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.BackgroundImage = null;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.pMCODEDataGridViewTextBoxColumn,
            this.dESCRDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.bRANDFAMILYBindingSource;
            this.dataGridView.Font = null;
            this.dataGridView.Name = "dataGridView";
            // 
            // RBBrandFamily
            // 
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.BackgroundImage = null;
            this.Font = null;
            this.Name = "RBBrandFamily";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.bRANDFAMILYBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.BRAND_FAMILYTableAdapter bRAND_FAMILYTableAdapter;
        public System.Windows.Forms.BindingSource bRANDFAMILYBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn dESCRDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn pMCODEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridView dataGridView;
    }
}
