namespace Workstation.Modules
{
    partial class RBCompanyCode
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
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.pMCODEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dESCRIPTIONDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cOMPANYCODEBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.cOMPANY_CODETableAdapter = new Workstation.phmkDataSetTableAdapters.COMPANY_CODETableAdapter();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPANYCODEBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.dataGridView);
            this.fillPanel.Size = new System.Drawing.Size(480, 147);
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.pMCODEDataGridViewTextBoxColumn,
            this.dESCRIPTIONDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.cOMPANYCODEBindingSource;
            this.dataGridView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView.Location = new System.Drawing.Point(0, 0);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(480, 147);
            this.dataGridView.TabIndex = 1;
            // 
            // pMCODEDataGridViewTextBoxColumn
            // 
            this.pMCODEDataGridViewTextBoxColumn.DataPropertyName = "PMCODE";
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.pMCODEDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            this.pMCODEDataGridViewTextBoxColumn.HeaderText = "Код";
            this.pMCODEDataGridViewTextBoxColumn.Name = "pMCODEDataGridViewTextBoxColumn";
            this.pMCODEDataGridViewTextBoxColumn.Width = 60;
            // 
            // dESCRIPTIONDataGridViewTextBoxColumn
            // 
            this.dESCRIPTIONDataGridViewTextBoxColumn.DataPropertyName = "DESCRIPTION";
            this.dESCRIPTIONDataGridViewTextBoxColumn.HeaderText = "Название";
            this.dESCRIPTIONDataGridViewTextBoxColumn.Name = "dESCRIPTIONDataGridViewTextBoxColumn";
            this.dESCRIPTIONDataGridViewTextBoxColumn.Width = 250;
            // 
            // cOMPANYCODEBindingSource
            // 
            this.cOMPANYCODEBindingSource.DataMember = "COMPANY_CODE";
            this.cOMPANYCODEBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // cOMPANY_CODETableAdapter
            // 
            this.cOMPANY_CODETableAdapter.ClearBeforeFill = true;
            // 
            // RBCompanyCode
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.capPanelText = "Производитель";
            this.Name = "RBCompanyCode";
            this.Size = new System.Drawing.Size(486, 214);
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPANYCODEBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.COMPANY_CODETableAdapter cOMPANY_CODETableAdapter;
        public System.Windows.Forms.BindingSource cOMPANYCODEBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn pMCODEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn dESCRIPTIONDataGridViewTextBoxColumn;
    }
}
