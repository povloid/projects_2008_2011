namespace Workstation.Modules.RB
{
    partial class RBCompaignCCPrizes
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
            this.cOMPAIGNPRIZESBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.cOMPAIGN_PRIZESTableAdapter = new Workstation.phmkDataSetTableAdapters.COMPAIGN_PRIZESTableAdapter();
            this.pRIZESBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.pRIZESTableAdapter = new Workstation.phmkDataSetTableAdapters.PRIZESTableAdapter();
            this.pRIZESIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cOMPAIGNIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNPRIZESBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pRIZESBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.dataGridView);
            this.fillPanel.Size = new System.Drawing.Size(200, 164);
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.pRIZESIDDataGridViewTextBoxColumn,
            this.nAMEDataGridViewTextBoxColumn,
            this.cOMPAIGNIDDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.cOMPAIGNPRIZESBindingSource;
            this.dataGridView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView.Location = new System.Drawing.Point(0, 0);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(200, 164);
            this.dataGridView.TabIndex = 0;
            // 
            // cOMPAIGNPRIZESBindingSource
            // 
            this.cOMPAIGNPRIZESBindingSource.DataMember = "COMPAIGN_PRIZES";
            this.cOMPAIGNPRIZESBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // cOMPAIGN_PRIZESTableAdapter
            // 
            this.cOMPAIGN_PRIZESTableAdapter.ClearBeforeFill = true;
            // 
            // pRIZESBindingSource
            // 
            this.pRIZESBindingSource.DataMember = "PRIZES";
            this.pRIZESBindingSource.DataSource = this.phmkDataSet;
            // 
            // pRIZESTableAdapter
            // 
            this.pRIZESTableAdapter.ClearBeforeFill = true;
            // 
            // pRIZESIDDataGridViewTextBoxColumn
            // 
            this.pRIZESIDDataGridViewTextBoxColumn.DataPropertyName = "PRIZESID";
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.pRIZESIDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle1;
            this.pRIZESIDDataGridViewTextBoxColumn.HeaderText = "PRIZESID";
            this.pRIZESIDDataGridViewTextBoxColumn.Name = "pRIZESIDDataGridViewTextBoxColumn";
            this.pRIZESIDDataGridViewTextBoxColumn.Width = 30;
            // 
            // nAMEDataGridViewTextBoxColumn
            // 
            this.nAMEDataGridViewTextBoxColumn.DataPropertyName = "NAME";
            this.nAMEDataGridViewTextBoxColumn.HeaderText = "NAME";
            this.nAMEDataGridViewTextBoxColumn.Name = "nAMEDataGridViewTextBoxColumn";
            this.nAMEDataGridViewTextBoxColumn.Width = 130;
            // 
            // cOMPAIGNIDDataGridViewTextBoxColumn
            // 
            this.cOMPAIGNIDDataGridViewTextBoxColumn.DataPropertyName = "COMPAIGNID";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.HeaderText = "COMPAIGNID";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.Name = "cOMPAIGNIDDataGridViewTextBoxColumn";
            this.cOMPAIGNIDDataGridViewTextBoxColumn.Visible = false;
            // 
            // RBCompaignCCPrizes
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "RBCompaignCCPrizes";
            this.Size = new System.Drawing.Size(206, 231);
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNPRIZESBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pRIZESBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private phmkDataSet phmkDataSet;
        private System.Windows.Forms.BindingSource cOMPAIGNPRIZESBindingSource;
        private Workstation.phmkDataSetTableAdapters.COMPAIGN_PRIZESTableAdapter cOMPAIGN_PRIZESTableAdapter;
        private System.Windows.Forms.BindingSource pRIZESBindingSource;
        private Workstation.phmkDataSetTableAdapters.PRIZESTableAdapter pRIZESTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn pRIZESIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn nAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cOMPAIGNIDDataGridViewTextBoxColumn;
    }
}
