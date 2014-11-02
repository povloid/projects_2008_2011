namespace Workstation.Modules.RB
{
    partial class RBCompaignCCMedia
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
            this.cOMPAIGNMEDIABindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.mEDIABindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.mEDIATableAdapter = new Workstation.phmkDataSetTableAdapters.MEDIATableAdapter();
            this.cOMPAIGN_MEDIATableAdapter = new Workstation.phmkDataSetTableAdapters.COMPAIGN_MEDIATableAdapter();
            this.mEDIAIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cOMPAIGNIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.NAME = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNMEDIABindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.mEDIABindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.dataGridView);
            this.fillPanel.Size = new System.Drawing.Size(202, 234);
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.mEDIAIDDataGridViewTextBoxColumn,
            this.cOMPAIGNIDDataGridViewTextBoxColumn,
            this.NAME});
            this.dataGridView.DataSource = this.cOMPAIGNMEDIABindingSource;
            this.dataGridView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView.Location = new System.Drawing.Point(0, 0);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(202, 234);
            this.dataGridView.TabIndex = 0;
            // 
            // cOMPAIGNMEDIABindingSource
            // 
            this.cOMPAIGNMEDIABindingSource.DataMember = "COMPAIGN_MEDIA";
            this.cOMPAIGNMEDIABindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // mEDIABindingSource
            // 
            this.mEDIABindingSource.DataMember = "MEDIA";
            this.mEDIABindingSource.DataSource = this.phmkDataSet;
            // 
            // mEDIATableAdapter
            // 
            this.mEDIATableAdapter.ClearBeforeFill = true;
            // 
            // cOMPAIGN_MEDIATableAdapter
            // 
            this.cOMPAIGN_MEDIATableAdapter.ClearBeforeFill = true;
            // 
            // mEDIAIDDataGridViewTextBoxColumn
            // 
            this.mEDIAIDDataGridViewTextBoxColumn.DataPropertyName = "MEDIAID";
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.mEDIAIDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            this.mEDIAIDDataGridViewTextBoxColumn.HeaderText = "MEDIAID";
            this.mEDIAIDDataGridViewTextBoxColumn.Name = "mEDIAIDDataGridViewTextBoxColumn";
            this.mEDIAIDDataGridViewTextBoxColumn.Width = 30;
            // 
            // cOMPAIGNIDDataGridViewTextBoxColumn
            // 
            this.cOMPAIGNIDDataGridViewTextBoxColumn.DataPropertyName = "COMPAIGNID";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.HeaderText = "COMPAIGNID";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.Name = "cOMPAIGNIDDataGridViewTextBoxColumn";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // NAME
            // 
            this.NAME.DataPropertyName = "NAME";
            this.NAME.HeaderText = "NAME";
            this.NAME.Name = "NAME";
            this.NAME.Width = 130;
            // 
            // RBCompaignCCMedia
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "RBCompaignCCMedia";
            this.Size = new System.Drawing.Size(208, 301);
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNMEDIABindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.mEDIABindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private phmkDataSet phmkDataSet;
        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource mEDIABindingSource;
        private Workstation.phmkDataSetTableAdapters.MEDIATableAdapter mEDIATableAdapter;
        private System.Windows.Forms.BindingSource cOMPAIGNMEDIABindingSource;
        private Workstation.phmkDataSetTableAdapters.COMPAIGN_MEDIATableAdapter cOMPAIGN_MEDIATableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn mEDIAIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cOMPAIGNIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn NAME;
    }
}
