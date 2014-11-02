namespace Workstation.Modules.RB
{
    partial class RBRegion
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBRegion));
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.OBLNAME = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.rEGIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.oBLASTIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.rEGIONBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.rEGIONTableAdapter = new Workstation.phmkDataSetTableAdapters.REGIONTableAdapter();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.rEGIONBindingSource)).BeginInit();
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
            this.OBLNAME,
            this.rEGIONIDDataGridViewTextBoxColumn,
            this.nAMEDataGridViewTextBoxColumn,
            this.oBLASTIDDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.rEGIONBindingSource;
            this.dataGridView.Font = null;
            this.dataGridView.Name = "dataGridView";
            // 
            // OBLNAME
            // 
            this.OBLNAME.DataPropertyName = "OBLNAME";
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.OBLNAME.DefaultCellStyle = dataGridViewCellStyle1;
            this.OBLNAME.Frozen = true;
            resources.ApplyResources(this.OBLNAME, "OBLNAME");
            this.OBLNAME.Name = "OBLNAME";
            this.OBLNAME.ReadOnly = true;
            // 
            // rEGIONIDDataGridViewTextBoxColumn
            // 
            this.rEGIONIDDataGridViewTextBoxColumn.DataPropertyName = "REGIONID";
            dataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.rEGIONIDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle2;
            this.rEGIONIDDataGridViewTextBoxColumn.Frozen = true;
            resources.ApplyResources(this.rEGIONIDDataGridViewTextBoxColumn, "rEGIONIDDataGridViewTextBoxColumn");
            this.rEGIONIDDataGridViewTextBoxColumn.Name = "rEGIONIDDataGridViewTextBoxColumn";
            // 
            // nAMEDataGridViewTextBoxColumn
            // 
            this.nAMEDataGridViewTextBoxColumn.DataPropertyName = "NAME";
            this.nAMEDataGridViewTextBoxColumn.Frozen = true;
            resources.ApplyResources(this.nAMEDataGridViewTextBoxColumn, "nAMEDataGridViewTextBoxColumn");
            this.nAMEDataGridViewTextBoxColumn.Name = "nAMEDataGridViewTextBoxColumn";
            // 
            // oBLASTIDDataGridViewTextBoxColumn
            // 
            this.oBLASTIDDataGridViewTextBoxColumn.DataPropertyName = "OBLASTID";
            this.oBLASTIDDataGridViewTextBoxColumn.Frozen = true;
            resources.ApplyResources(this.oBLASTIDDataGridViewTextBoxColumn, "oBLASTIDDataGridViewTextBoxColumn");
            this.oBLASTIDDataGridViewTextBoxColumn.Name = "oBLASTIDDataGridViewTextBoxColumn";
            this.oBLASTIDDataGridViewTextBoxColumn.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            // 
            // rEGIONBindingSource
            // 
            this.rEGIONBindingSource.DataMember = "REGION";
            this.rEGIONBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // rEGIONTableAdapter
            // 
            this.rEGIONTableAdapter.ClearBeforeFill = true;
            // 
            // RBRegion
            // 
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.BackgroundImage = null;
            this.Font = null;
            this.Name = "RBRegion";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.rEGIONBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.REGIONTableAdapter rEGIONTableAdapter;
        public System.Windows.Forms.BindingSource rEGIONBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn rEGIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn nAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn OBLNAME;
        private System.Windows.Forms.DataGridViewTextBoxColumn oBLASTIDDataGridViewTextBoxColumn;

    }
}
