namespace Workstation.Modules.RB
{
    partial class RBBrands
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBBrands));
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle10 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle11 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle3 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle4 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle5 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle6 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle7 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle8 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle9 = new System.Windows.Forms.DataGridViewCellStyle();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.bRANDFAMILYDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tHICKCATDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.lENGTHCATDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.bRANDDIFDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mENTHINDDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.pACKTYPEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fILTINDDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.iTEMSPACKDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mARKBCDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mBLONGDESCRDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.bLENDTCDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sPECFLAVCDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.cOMPANYCODEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.bRANDSBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.bRANDSTableAdapter = new Workstation.phmkDataSetTableAdapters.BRANDSTableAdapter();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.bRANDSBindingSource)).BeginInit();
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
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle1.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle1.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle1.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle1.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle1.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dataGridView.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle1;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.bRANDFAMILYDataGridViewTextBoxColumn,
            this.tHICKCATDataGridViewTextBoxColumn,
            this.lENGTHCATDataGridViewTextBoxColumn,
            this.bRANDDIFDataGridViewTextBoxColumn,
            this.mENTHINDDataGridViewCheckBoxColumn,
            this.pACKTYPEDataGridViewTextBoxColumn,
            this.fILTINDDataGridViewCheckBoxColumn,
            this.iTEMSPACKDataGridViewTextBoxColumn,
            this.mARKBCDataGridViewTextBoxColumn,
            this.mBLONGDESCRDataGridViewTextBoxColumn,
            this.bLENDTCDataGridViewTextBoxColumn,
            this.sPECFLAVCDataGridViewTextBoxColumn,
            this.cOMPANYCODEDataGridViewTextBoxColumn});
            this.dataGridView.DataSource = this.bRANDSBindingSource;
            dataGridViewCellStyle10.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle10.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle10.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle10.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle10.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle10.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle10.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.dataGridView.DefaultCellStyle = dataGridViewCellStyle10;
            this.dataGridView.Font = null;
            this.dataGridView.Name = "dataGridView";
            dataGridViewCellStyle11.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle11.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle11.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle11.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle11.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle11.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle11.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dataGridView.RowHeadersDefaultCellStyle = dataGridViewCellStyle11;
            // 
            // bRANDFAMILYDataGridViewTextBoxColumn
            // 
            this.bRANDFAMILYDataGridViewTextBoxColumn.DataPropertyName = "BRANDFAMILY";
            dataGridViewCellStyle2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.bRANDFAMILYDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle2;
            resources.ApplyResources(this.bRANDFAMILYDataGridViewTextBoxColumn, "bRANDFAMILYDataGridViewTextBoxColumn");
            this.bRANDFAMILYDataGridViewTextBoxColumn.Name = "bRANDFAMILYDataGridViewTextBoxColumn";
            // 
            // tHICKCATDataGridViewTextBoxColumn
            // 
            this.tHICKCATDataGridViewTextBoxColumn.DataPropertyName = "THICKCAT";
            dataGridViewCellStyle3.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.tHICKCATDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle3;
            resources.ApplyResources(this.tHICKCATDataGridViewTextBoxColumn, "tHICKCATDataGridViewTextBoxColumn");
            this.tHICKCATDataGridViewTextBoxColumn.Name = "tHICKCATDataGridViewTextBoxColumn";
            // 
            // lENGTHCATDataGridViewTextBoxColumn
            // 
            this.lENGTHCATDataGridViewTextBoxColumn.DataPropertyName = "LENGTHCAT";
            dataGridViewCellStyle4.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.lENGTHCATDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle4;
            resources.ApplyResources(this.lENGTHCATDataGridViewTextBoxColumn, "lENGTHCATDataGridViewTextBoxColumn");
            this.lENGTHCATDataGridViewTextBoxColumn.Name = "lENGTHCATDataGridViewTextBoxColumn";
            // 
            // bRANDDIFDataGridViewTextBoxColumn
            // 
            this.bRANDDIFDataGridViewTextBoxColumn.DataPropertyName = "BRANDDIF";
            resources.ApplyResources(this.bRANDDIFDataGridViewTextBoxColumn, "bRANDDIFDataGridViewTextBoxColumn");
            this.bRANDDIFDataGridViewTextBoxColumn.Name = "bRANDDIFDataGridViewTextBoxColumn";
            // 
            // mENTHINDDataGridViewCheckBoxColumn
            // 
            this.mENTHINDDataGridViewCheckBoxColumn.DataPropertyName = "MENTHIND";
            resources.ApplyResources(this.mENTHINDDataGridViewCheckBoxColumn, "mENTHINDDataGridViewCheckBoxColumn");
            this.mENTHINDDataGridViewCheckBoxColumn.Name = "mENTHINDDataGridViewCheckBoxColumn";
            // 
            // pACKTYPEDataGridViewTextBoxColumn
            // 
            this.pACKTYPEDataGridViewTextBoxColumn.DataPropertyName = "PACKTYPE";
            dataGridViewCellStyle5.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.pACKTYPEDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle5;
            resources.ApplyResources(this.pACKTYPEDataGridViewTextBoxColumn, "pACKTYPEDataGridViewTextBoxColumn");
            this.pACKTYPEDataGridViewTextBoxColumn.Name = "pACKTYPEDataGridViewTextBoxColumn";
            // 
            // fILTINDDataGridViewCheckBoxColumn
            // 
            this.fILTINDDataGridViewCheckBoxColumn.DataPropertyName = "FILTIND";
            resources.ApplyResources(this.fILTINDDataGridViewCheckBoxColumn, "fILTINDDataGridViewCheckBoxColumn");
            this.fILTINDDataGridViewCheckBoxColumn.Name = "fILTINDDataGridViewCheckBoxColumn";
            // 
            // iTEMSPACKDataGridViewTextBoxColumn
            // 
            this.iTEMSPACKDataGridViewTextBoxColumn.DataPropertyName = "ITEMSPACK";
            dataGridViewCellStyle6.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            this.iTEMSPACKDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle6;
            resources.ApplyResources(this.iTEMSPACKDataGridViewTextBoxColumn, "iTEMSPACKDataGridViewTextBoxColumn");
            this.iTEMSPACKDataGridViewTextBoxColumn.Name = "iTEMSPACKDataGridViewTextBoxColumn";
            // 
            // mARKBCDataGridViewTextBoxColumn
            // 
            this.mARKBCDataGridViewTextBoxColumn.DataPropertyName = "MARKBC";
            dataGridViewCellStyle7.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle7.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            dataGridViewCellStyle7.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle7.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(192)))));
            this.mARKBCDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle7;
            resources.ApplyResources(this.mARKBCDataGridViewTextBoxColumn, "mARKBCDataGridViewTextBoxColumn");
            this.mARKBCDataGridViewTextBoxColumn.Name = "mARKBCDataGridViewTextBoxColumn";
            // 
            // mBLONGDESCRDataGridViewTextBoxColumn
            // 
            this.mBLONGDESCRDataGridViewTextBoxColumn.DataPropertyName = "MBLONGDESCR";
            resources.ApplyResources(this.mBLONGDESCRDataGridViewTextBoxColumn, "mBLONGDESCRDataGridViewTextBoxColumn");
            this.mBLONGDESCRDataGridViewTextBoxColumn.Name = "mBLONGDESCRDataGridViewTextBoxColumn";
            // 
            // bLENDTCDataGridViewTextBoxColumn
            // 
            this.bLENDTCDataGridViewTextBoxColumn.DataPropertyName = "BLENDTC";
            resources.ApplyResources(this.bLENDTCDataGridViewTextBoxColumn, "bLENDTCDataGridViewTextBoxColumn");
            this.bLENDTCDataGridViewTextBoxColumn.Name = "bLENDTCDataGridViewTextBoxColumn";
            // 
            // sPECFLAVCDataGridViewTextBoxColumn
            // 
            this.sPECFLAVCDataGridViewTextBoxColumn.DataPropertyName = "SPECFLAVC";
            dataGridViewCellStyle8.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.sPECFLAVCDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle8;
            resources.ApplyResources(this.sPECFLAVCDataGridViewTextBoxColumn, "sPECFLAVCDataGridViewTextBoxColumn");
            this.sPECFLAVCDataGridViewTextBoxColumn.Name = "sPECFLAVCDataGridViewTextBoxColumn";
            // 
            // cOMPANYCODEDataGridViewTextBoxColumn
            // 
            this.cOMPANYCODEDataGridViewTextBoxColumn.DataPropertyName = "COMPANYCODE";
            dataGridViewCellStyle9.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.cOMPANYCODEDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle9;
            resources.ApplyResources(this.cOMPANYCODEDataGridViewTextBoxColumn, "cOMPANYCODEDataGridViewTextBoxColumn");
            this.cOMPANYCODEDataGridViewTextBoxColumn.Name = "cOMPANYCODEDataGridViewTextBoxColumn";
            // 
            // bRANDSBindingSource
            // 
            this.bRANDSBindingSource.DataMember = "BRANDS";
            this.bRANDSBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // bRANDSTableAdapter
            // 
            this.bRANDSTableAdapter.ClearBeforeFill = true;
            // 
            // RBBrands
            // 
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.BackgroundImage = null;
            this.Font = null;
            this.Name = "RBBrands";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.bRANDSBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource bRANDSBindingSource;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.BRANDSTableAdapter bRANDSTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn bRANDFAMILYDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn tHICKCATDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn lENGTHCATDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn bRANDDIFDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn mENTHINDDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn pACKTYPEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn fILTINDDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn iTEMSPACKDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn mARKBCDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn mBLONGDESCRDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn bLENDTCDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sPECFLAVCDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn cOMPANYCODEDataGridViewTextBoxColumn;
    }
}
