namespace Workstation.Modules.RB
{
    partial class RBSignatureTypeDialog
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
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label label1;
            this.DESCRTextBox = new System.Windows.Forms.TextBox();
            this.IDNumericUpDown = new System.Windows.Forms.NumericUpDown();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.IDNumericUpDown);
            this.groupBox.Controls.Add(this.DESCRTextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Cursor = System.Windows.Forms.Cursors.Arrow;
            this.groupBox.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.groupBox.Size = new System.Drawing.Size(373, 74);
            this.groupBox.TabIndex = 0;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(4, 48);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 2;
            label2.Text = "������������:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(4, 22);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 0;
            label1.Text = "���:";
            // 
            // DESCRTextBox
            // 
            this.DESCRTextBox.Location = new System.Drawing.Point(96, 45);
            this.DESCRTextBox.Name = "DESCRTextBox";
            this.DESCRTextBox.Size = new System.Drawing.Size(268, 20);
            this.DESCRTextBox.TabIndex = 3;
            this.DESCRTextBox.TextChanged += new System.EventHandler(this.DESCRTextBox_TextChanged);
            // 
            // IDNumericUpDown
            // 
            this.IDNumericUpDown.Location = new System.Drawing.Point(96, 15);
            this.IDNumericUpDown.Name = "IDNumericUpDown";
            this.IDNumericUpDown.Size = new System.Drawing.Size(87, 20);
            this.IDNumericUpDown.TabIndex = 1;
            this.IDNumericUpDown.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // RBSignatureTypeDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(391, 156);
            this.Name = "RBSignatureTypeDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.TextBox DESCRTextBox;
        public System.Windows.Forms.NumericUpDown IDNumericUpDown;
    }
}
