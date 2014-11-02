namespace Workstation.Modules.RB
{
    partial class RBStreetType
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBStreetType));
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.sTREETTYPEIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sTREETTYPENAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sTREETTYPEBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.sTREET_TYPETableAdapter = new Workstation.phmkDataSetTableAdapters.STREET_TYPETableAdapter();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sTREETTYPEBindingSource)).BeginInit();
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
            this.sTREETTYPEIDDataGridViewTextBoxColumn,
            this.sTREETTYPENAMEDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.sTREETTYPEBindingSource;
            this.dataGridView.Font = null;
            this.dataGridView.Name = "dataGridView";
            // 
            // sTREETTYPEIDDataGridViewTextBoxColumn
            // 
            this.sTREETTYPEIDDataGridViewTextBoxColumn.DataPropertyName = "STREETTYPEID";
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.sTREETTYPEIDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            resources.ApplyResources(this.sTREETTYPEIDDataGridViewTextBoxColumn, "sTREETTYPEIDDataGridViewTextBoxColumn");
            this.sTREETTYPEIDDataGridViewTextBoxColumn.Name = "sTREETTYPEIDDataGridViewTextBoxColumn";
            // 
            // sTREETTYPENAMEDataGridViewTextBoxColumn
            // 
            this.sTREETTYPENAMEDataGridViewTextBoxColumn.DataPropertyName = "STREETTYPENAME";
            resources.ApplyResources(this.sTREETTYPENAMEDataGridViewTextBoxColumn, "sTREETTYPENAMEDataGridViewTextBoxColumn");
            this.sTREETTYPENAMEDataGridViewTextBoxColumn.Name = "sTREETTYPENAMEDataGridViewTextBoxColumn";
            // 
            // sTREETTYPEBindingSource
            // 
            this.sTREETTYPEBindingSource.DataMember = "STREET_TYPE";
            this.sTREETTYPEBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // sTREET_TYPETableAdapter
            // 
            this.sTREET_TYPETableAdapter.ClearBeforeFill = true;
            // 
            // RBStreetType
            // 
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.BackgroundImage = null;
            this.Font = null;
            this.Name = "RBStreetType";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sTREETTYPEBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.STREET_TYPETableAdapter sTREET_TYPETableAdapter;
        public System.Windows.Forms.BindingSource sTREETTYPEBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn sTREETTYPEIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sTREETTYPENAMEDataGridViewTextBoxColumn;
    }
}
