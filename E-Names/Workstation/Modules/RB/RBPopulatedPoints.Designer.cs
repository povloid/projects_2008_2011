namespace Workstation.Modules.RB
{
    partial class RBPopulatedPoints
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
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle4 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle5 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle3 = new System.Windows.Forms.DataGridViewCellStyle();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RBPopulatedPoints));
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.rEGIONNAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.iDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dESCRDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sETTLEMENTTYPENAMEDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sETTLEMENTTYPEIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.rEGIONIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pOPULATEDPOINTSBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.oblLabel = new System.Windows.Forms.Label();
            this.OBLASTÑomboBox = new System.Windows.Forms.ComboBox();
            this.pOPULATED_POINTSTableAdapter = new Workstation.phmkDataSetTableAdapters.POPULATED_POINTSTableAdapter();
            this.rEGIONbindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.label1 = new System.Windows.Forms.Label();
            this.allCheckBox = new System.Windows.Forms.CheckBox();
            this.fillPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pOPULATEDPOINTSBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.rEGIONbindingSource1)).BeginInit();
            this.tableLayoutPanel3.SuspendLayout();
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Controls.Add(this.tableLayoutPanel3);
            resources.ApplyResources(this.fillPanel, "fillPanel");
            // 
            // dataGridView
            // 
            this.dataGridView.AutoGenerateColumns = false;
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
            this.rEGIONNAMEDataGridViewTextBoxColumn,
            this.iDDataGridViewTextBoxColumn,
            this.nAMEDataGridViewTextBoxColumn,
            this.dESCRDataGridViewTextBoxColumn,
            this.sETTLEMENTTYPENAMEDataGridViewTextBoxColumn,
            this.sETTLEMENTTYPEIDDataGridViewTextBoxColumn,
            this.rEGIONIDDataGridViewTextBoxColumn});
            this.tableLayoutPanel3.SetColumnSpan(this.dataGridView, 4);
            this.dataGridView.DataSource = this.pOPULATEDPOINTSBindingSource;
            dataGridViewCellStyle4.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle4.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle4.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle4.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle4.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle4.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.dataGridView.DefaultCellStyle = dataGridViewCellStyle4;
            resources.ApplyResources(this.dataGridView, "dataGridView");
            this.dataGridView.Name = "dataGridView";
            dataGridViewCellStyle5.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle5.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle5.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle5.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle5.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle5.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle5.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dataGridView.RowHeadersDefaultCellStyle = dataGridViewCellStyle5;
            // 
            // rEGIONNAMEDataGridViewTextBoxColumn
            // 
            this.rEGIONNAMEDataGridViewTextBoxColumn.DataPropertyName = "REGIONNAME";
            dataGridViewCellStyle2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.rEGIONNAMEDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle2;
            resources.ApplyResources(this.rEGIONNAMEDataGridViewTextBoxColumn, "rEGIONNAMEDataGridViewTextBoxColumn");
            this.rEGIONNAMEDataGridViewTextBoxColumn.Name = "rEGIONNAMEDataGridViewTextBoxColumn";
            // 
            // iDDataGridViewTextBoxColumn
            // 
            this.iDDataGridViewTextBoxColumn.DataPropertyName = "ID";
            dataGridViewCellStyle3.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
            dataGridViewCellStyle3.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.iDDataGridViewTextBoxColumn.DefaultCellStyle = dataGridViewCellStyle3;
            resources.ApplyResources(this.iDDataGridViewTextBoxColumn, "iDDataGridViewTextBoxColumn");
            this.iDDataGridViewTextBoxColumn.Name = "iDDataGridViewTextBoxColumn";
            // 
            // nAMEDataGridViewTextBoxColumn
            // 
            this.nAMEDataGridViewTextBoxColumn.DataPropertyName = "NAME";
            resources.ApplyResources(this.nAMEDataGridViewTextBoxColumn, "nAMEDataGridViewTextBoxColumn");
            this.nAMEDataGridViewTextBoxColumn.Name = "nAMEDataGridViewTextBoxColumn";
            // 
            // dESCRDataGridViewTextBoxColumn
            // 
            this.dESCRDataGridViewTextBoxColumn.DataPropertyName = "DESCR";
            resources.ApplyResources(this.dESCRDataGridViewTextBoxColumn, "dESCRDataGridViewTextBoxColumn");
            this.dESCRDataGridViewTextBoxColumn.Name = "dESCRDataGridViewTextBoxColumn";
            // 
            // sETTLEMENTTYPENAMEDataGridViewTextBoxColumn
            // 
            this.sETTLEMENTTYPENAMEDataGridViewTextBoxColumn.DataPropertyName = "SETTLEMENTTYPENAME";
            resources.ApplyResources(this.sETTLEMENTTYPENAMEDataGridViewTextBoxColumn, "sETTLEMENTTYPENAMEDataGridViewTextBoxColumn");
            this.sETTLEMENTTYPENAMEDataGridViewTextBoxColumn.Name = "sETTLEMENTTYPENAMEDataGridViewTextBoxColumn";
            // 
            // sETTLEMENTTYPEIDDataGridViewTextBoxColumn
            // 
            this.sETTLEMENTTYPEIDDataGridViewTextBoxColumn.DataPropertyName = "SETTLEMENTTYPEID";
            resources.ApplyResources(this.sETTLEMENTTYPEIDDataGridViewTextBoxColumn, "sETTLEMENTTYPEIDDataGridViewTextBoxColumn");
            this.sETTLEMENTTYPEIDDataGridViewTextBoxColumn.Name = "sETTLEMENTTYPEIDDataGridViewTextBoxColumn";
            // 
            // rEGIONIDDataGridViewTextBoxColumn
            // 
            this.rEGIONIDDataGridViewTextBoxColumn.DataPropertyName = "REGIONID";
            resources.ApplyResources(this.rEGIONIDDataGridViewTextBoxColumn, "rEGIONIDDataGridViewTextBoxColumn");
            this.rEGIONIDDataGridViewTextBoxColumn.Name = "rEGIONIDDataGridViewTextBoxColumn";
            // 
            // pOPULATEDPOINTSBindingSource
            // 
            this.pOPULATEDPOINTSBindingSource.DataMember = "POPULATED_POINTS";
            this.pOPULATEDPOINTSBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // oblLabel
            // 
            resources.ApplyResources(this.oblLabel, "oblLabel");
            this.oblLabel.Name = "oblLabel";
            // 
            // OBLASTÑomboBox
            // 
            resources.ApplyResources(this.OBLASTÑomboBox, "OBLASTÑomboBox");
            this.OBLASTÑomboBox.FormattingEnabled = true;
            this.OBLASTÑomboBox.Name = "OBLASTÑomboBox";
            this.OBLASTÑomboBox.SelectedIndexChanged += new System.EventHandler(this.OBLASTÑomboBox_SelectedIndexChanged);
            // 
            // pOPULATED_POINTSTableAdapter
            // 
            this.pOPULATED_POINTSTableAdapter.ClearBeforeFill = true;
            // 
            // rEGIONbindingSource1
            // 
            this.rEGIONbindingSource1.DataMember = "REGION";
            this.rEGIONbindingSource1.DataSource = this.phmkDataSet;
            // 
            // tableLayoutPanel3
            // 
            resources.ApplyResources(this.tableLayoutPanel3, "tableLayoutPanel3");
            this.tableLayoutPanel3.Controls.Add(this.dataGridView, 0, 1);
            this.tableLayoutPanel3.Controls.Add(this.oblLabel, 0, 0);
            this.tableLayoutPanel3.Controls.Add(this.OBLASTÑomboBox, 1, 0);
            this.tableLayoutPanel3.Controls.Add(this.label1, 2, 0);
            this.tableLayoutPanel3.Controls.Add(this.allCheckBox, 3, 0);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            // 
            // label1
            // 
            resources.ApplyResources(this.label1, "label1");
            this.label1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.label1.Name = "label1";
            // 
            // allCheckBox
            // 
            resources.ApplyResources(this.allCheckBox, "allCheckBox");
            this.allCheckBox.Name = "allCheckBox";
            this.allCheckBox.UseVisualStyleBackColor = true;
            this.allCheckBox.Click += new System.EventHandler(this.allCheckBox_Click);
            // 
            // RBPopulatedPoints
            // 
            resources.ApplyResources(this, "$this");
            this.Name = "RBPopulatedPoints";
            this.fillPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pOPULATEDPOINTSBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.rEGIONbindingSource1)).EndInit();
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.BindingSource pOPULATEDPOINTSBindingSource;
        private phmkDataSet phmkDataSet;
        private Workstation.phmkDataSetTableAdapters.POPULATED_POINTSTableAdapter pOPULATED_POINTSTableAdapter;
        private System.Windows.Forms.ComboBox OBLASTÑomboBox;
        private System.Windows.Forms.Label oblLabel;
        private System.Windows.Forms.BindingSource rEGIONbindingSource1;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridViewTextBoxColumn rEGIONNAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn iDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn nAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn dESCRDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sETTLEMENTTYPENAMEDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sETTLEMENTTYPEIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn rEGIONIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.CheckBox allCheckBox;
    }
}
