namespace Workstation.Modules.RB
{
    partial class RBSignatureType
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBSignatureType));
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.iDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dESCRDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sIGNATURETYPEBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.sIGNATURE_TYPETableAdapter = new Workstation.phmkDataSetTableAdapters.SIGNATURE_TYPETableAdapter();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sIGNATURETYPEBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
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
            // dataGridView
            // 
            this.dataGridView.AccessibleDescription = null;
            this.dataGridView.AccessibleName = null;
            resources.ApplyResources(this.dataGridView, "dataGridView");
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.BackgroundImage = null;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.iDDataGridViewTextBoxColumn,
            this.dESCRDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.sIGNATURETYPEBindingSource;
            this.dataGridView.Font = null;
            this.dataGridView.Name = "dataGridView";
            // 
            // iDDataGridViewTextBoxColumn
            // 
            this.iDDataGridViewTextBoxColumn.DataPropertyName = "ID";
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.iDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            resources.ApplyResources(this.iDDataGridViewTextBoxColumn, "iDDataGridViewTextBoxColumn");
            this.iDDataGridViewTextBoxColumn.Name = "iDDataGridViewTextBoxColumn";
            // 
            // dESCRDataGridViewTextBoxColumn
            // 
            this.dESCRDataGridViewTextBoxColumn.DataPropertyName = "DESCR";
            resources.ApplyResources(this.dESCRDataGridViewTextBoxColumn, "dESCRDataGridViewTextBoxColumn");
            this.dESCRDataGridViewTextBoxColumn.Name = "dESCRDataGridViewTextBoxColumn";
            // 
            // sIGNATURETYPEBindingSource
            // 
            this.sIGNATURETYPEBindingSource.DataMember = "SIGNATURE_TYPE";
            this.sIGNATURETYPEBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // sIGNATURE_TYPETableAdapter
            // 
            this.sIGNATURE_TYPETableAdapter.ClearBeforeFill = true;
            // 
            // RBSignatureType
            // 
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.BackgroundImage = null;
            this.Font = null;
            this.Name = "RBSignatureType";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sIGNATURETYPEBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.SIGNATURE_TYPETableAdapter sIGNATURE_TYPETableAdapter;
        public System.Windows.Forms.BindingSource sIGNATURETYPEBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn iDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn dESCRDataGridViewTextBoxColumn;
    }
}
