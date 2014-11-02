namespace Workstation.Modules.RB
{
    partial class RBCompaignCCQuestions
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
            this.qUESTIONFORCOMPAIGNBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.qUESTION_FOR_COMPAIGNTableAdapter = new Workstation.phmkDataSetTableAdapters.QUESTION_FOR_COMPAIGNTableAdapter();
            this.qUESTIONSBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.qUESTIONSTableAdapter = new Workstation.phmkDataSetTableAdapters.QUESTIONSTableAdapter();
            this.qUESTIONNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.qUESTIONTYPENAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.iSDEFAULTDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.qUESTIONTYPEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.qUESTIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.gOMPAIGNIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.qUESTIONFORCOMPAIGNBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.qUESTIONSBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.dataGridView);
            this.fillPanel.Size = new System.Drawing.Size(209, 238);
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.qUESTIONNAMEDataGridViewTextBoxColumn,
            this.qUESTIONTYPENAMEDataGridViewTextBoxColumn,
            this.iSDEFAULTDataGridViewCheckBoxColumn,
            this.qUESTIONTYPEDataGridViewTextBoxColumn,
            this.qUESTIONIDDataGridViewTextBoxColumn,
            this.gOMPAIGNIDDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.qUESTIONFORCOMPAIGNBindingSource;
            this.dataGridView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView.Location = new System.Drawing.Point(0, 0);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(209, 238);
            this.dataGridView.TabIndex = 0;
            // 
            // qUESTIONFORCOMPAIGNBindingSource
            // 
            this.qUESTIONFORCOMPAIGNBindingSource.DataMember = "QUESTION_FOR_COMPAIGN";
            this.qUESTIONFORCOMPAIGNBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // qUESTION_FOR_COMPAIGNTableAdapter
            // 
            this.qUESTION_FOR_COMPAIGNTableAdapter.ClearBeforeFill = true;
            // 
            // qUESTIONSBindingSource
            // 
            this.qUESTIONSBindingSource.DataMember = "QUESTIONS";
            this.qUESTIONSBindingSource.DataSource = this.phmkDataSet;
            // 
            // qUESTIONSTableAdapter
            // 
            this.qUESTIONSTableAdapter.ClearBeforeFill = true;
            // 
            // qUESTIONNAMEDataGridViewTextBoxColumn
            // 
            this.qUESTIONNAMEDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONNAME";
            this.qUESTIONNAMEDataGridViewTextBoxColumn.HeaderText = "QUESTIONNAME";
            this.qUESTIONNAMEDataGridViewTextBoxColumn.Name = "qUESTIONNAMEDataGridViewTextBoxColumn";
            // 
            // qUESTIONTYPENAMEDataGridViewTextBoxColumn
            // 
            this.qUESTIONTYPENAMEDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONTYPE_NAME";
            this.qUESTIONTYPENAMEDataGridViewTextBoxColumn.HeaderText = "QUESTIONTYPE_NAME";
            this.qUESTIONTYPENAMEDataGridViewTextBoxColumn.Name = "qUESTIONTYPENAMEDataGridViewTextBoxColumn";
            // 
            // iSDEFAULTDataGridViewCheckBoxColumn
            // 
            this.iSDEFAULTDataGridViewCheckBoxColumn.DataPropertyName = "ISDEFAULT";
            this.iSDEFAULTDataGridViewCheckBoxColumn.HeaderText = "ISDEFAULT";
            this.iSDEFAULTDataGridViewCheckBoxColumn.Name = "iSDEFAULTDataGridViewCheckBoxColumn";
            // 
            // qUESTIONTYPEDataGridViewTextBoxColumn
            // 
            this.qUESTIONTYPEDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONTYPE";
            this.qUESTIONTYPEDataGridViewTextBoxColumn.HeaderText = "QUESTIONTYPE";
            this.qUESTIONTYPEDataGridViewTextBoxColumn.Name = "qUESTIONTYPEDataGridViewTextBoxColumn";
            this.qUESTIONTYPEDataGridViewTextBoxColumn.Visible = false;
            // 
            // qUESTIONIDDataGridViewTextBoxColumn
            // 
            this.qUESTIONIDDataGridViewTextBoxColumn.DataPropertyName = "QUESTIONID";
            this.qUESTIONIDDataGridViewTextBoxColumn.HeaderText = "QUESTIONID";
            this.qUESTIONIDDataGridViewTextBoxColumn.Name = "qUESTIONIDDataGridViewTextBoxColumn";
            this.qUESTIONIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // gOMPAIGNIDDataGridViewTextBoxColumn
            // 
            this.gOMPAIGNIDDataGridViewTextBoxColumn.DataPropertyName = "GOMPAIGNID";
            this.gOMPAIGNIDDataGridViewTextBoxColumn.HeaderText = "GOMPAIGNID";
            this.gOMPAIGNIDDataGridViewTextBoxColumn.Name = "gOMPAIGNIDDataGridViewTextBoxColumn";
            this.gOMPAIGNIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // RBCompaignCCQuestions
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "RBCompaignCCQuestions";
            this.Size = new System.Drawing.Size(215, 305);
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.qUESTIONFORCOMPAIGNBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.qUESTIONSBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource qUESTIONFORCOMPAIGNBindingSource;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.QUESTION_FOR_COMPAIGNTableAdapter qUESTION_FOR_COMPAIGNTableAdapter;
        private System.Windows.Forms.BindingSource qUESTIONSBindingSource;
        private Workstation.phmkDataSetTableAdapters.QUESTIONSTableAdapter qUESTIONSTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONTYPENAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn iSDEFAULTDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONTYPEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn qUESTIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn gOMPAIGNIDDataGridViewTextBoxColumn;
    }
}
