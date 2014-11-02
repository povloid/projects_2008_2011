namespace Workstation.Modules.RB
{
    partial class RBsQQuestions
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBsQQuestions));
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.qUESTIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.qUESTIONNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.QUESTIONTYPENAME = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.qUESTIONISNULLDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.iSDEFAULTDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.qUESTIONTYPEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.qUESTIONSBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.qUESTIONSTableAdapter = new Workstation.phmkDataSetTableAdapters.QUESTIONSTableAdapter();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.qUESTIONSBindingSource)).BeginInit();
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
            this.qUESTIONIDDataGridViewTextBoxColumn,
            this.qUESTIONNAMEDataGridViewTextBoxColumn,
            this.QUESTIONTYPENAME,
            this.qUESTIONISNULLDataGridViewCheckBoxColumn,
            this.iSDEFAULTDataGridViewCheckBoxColumn,
            this.qUESTIONTYPEDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.qUESTIONSBindingSource;
            this.dataGridView.Font = null;
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.CurrentCellChanged += new System.EventHandler(this.dataGridView_CurrentCellChanged);
            // 
            // qUESTIONIDDataGridViewTextBoxColumn
            // 
            this.qUESTIONIDDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONID";
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.qUESTIONIDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            resources.ApplyResources(this.qUESTIONIDDataGridViewTextBoxColumn, "qUESTIONIDDataGridViewTextBoxColumn");
            this.qUESTIONIDDataGridViewTextBoxColumn.Name = "qUESTIONIDDataGridViewTextBoxColumn";
            // 
            // qUESTIONNAMEDataGridViewTextBoxColumn
            // 
            this.qUESTIONNAMEDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONNAME";
            resources.ApplyResources(this.qUESTIONNAMEDataGridViewTextBoxColumn, "qUESTIONNAMEDataGridViewTextBoxColumn");
            this.qUESTIONNAMEDataGridViewTextBoxColumn.Name = "qUESTIONNAMEDataGridViewTextBoxColumn";
            // 
            // QUESTIONTYPENAME
            // 
            this.QUESTIONTYPENAME.DataPropertyName = "QUESTIONTYPENAME";
            dataGridViewCellStyle2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.QUESTIONTYPENAME.DefaultCellStyle = dataGridViewCellStyle2;
            resources.ApplyResources(this.QUESTIONTYPENAME, "QUESTIONTYPENAME");
            this.QUESTIONTYPENAME.Name = "QUESTIONTYPENAME";
            // 
            // qUESTIONISNULLDataGridViewCheckBoxColumn
            // 
            this.qUESTIONISNULLDataGridViewCheckBoxColumn.DataPropertyName = "QUESTIONISNULL";
            resources.ApplyResources(this.qUESTIONISNULLDataGridViewCheckBoxColumn, "qUESTIONISNULLDataGridViewCheckBoxColumn");
            this.qUESTIONISNULLDataGridViewCheckBoxColumn.Name = "qUESTIONISNULLDataGridViewCheckBoxColumn";
            // 
            // iSDEFAULTDataGridViewCheckBoxColumn
            // 
            this.iSDEFAULTDataGridViewCheckBoxColumn.DataPropertyName = "ISDEFAULT";
            resources.ApplyResources(this.iSDEFAULTDataGridViewCheckBoxColumn, "iSDEFAULTDataGridViewCheckBoxColumn");
            this.iSDEFAULTDataGridViewCheckBoxColumn.Name = "iSDEFAULTDataGridViewCheckBoxColumn";
            // 
            // qUESTIONTYPEDataGridViewTextBoxColumn
            // 
            this.qUESTIONTYPEDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONTYPE";
            resources.ApplyResources(this.qUESTIONTYPEDataGridViewTextBoxColumn, "qUESTIONTYPEDataGridViewTextBoxColumn");
            this.qUESTIONTYPEDataGridViewTextBoxColumn.Name = "qUESTIONTYPEDataGridViewTextBoxColumn";
            // 
            // qUESTIONSBindingSource
            // 
            this.qUESTIONSBindingSource.DataMember = "QUESTIONS";
            this.qUESTIONSBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // qUESTIONSTableAdapter
            // 
            this.qUESTIONSTableAdapter.ClearBeforeFill = true;
            // 
            // RBsQQuestions
            // 
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.BackgroundImage = null;
            this.Font = null;
            this.Name = "RBsQQuestions";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.qUESTIONSBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource qUESTIONSBindingSource;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.QUESTIONSTableAdapter qUESTIONSTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn QUESTIONTYPENAME;
        private System.Windows.Forms.DataGridViewCheckBoxColumn qUESTIONISNULLDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn iSDEFAULTDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONTYPEDataGridViewTextBoxColumn;
    }
}
