namespace Workstation.Modules.RB
{
    partial class RBGlobalBlockingDialog
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
            this.IDNumericUpDown = new System.Windows.Forms.NumericUpDown();
            this.NAMETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.IDNumericUpDown);
            this.groupBox.Controls.Add(this.NAMETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(373, 68);
            // 
            // IDNumericUpDown
            // 
            this.IDNumericUpDown.Location = new System.Drawing.Point(98, 14);
            this.IDNumericUpDown.Name = "IDNumericUpDown";
            this.IDNumericUpDown.Size = new System.Drawing.Size(87, 20);
            this.IDNumericUpDown.TabIndex = 25;
            this.IDNumericUpDown.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // NAMETextBox
            // 
            this.NAMETextBox.Location = new System.Drawing.Point(98, 40);
            this.NAMETextBox.Name = "NAMETextBox";
            this.NAMETextBox.Size = new System.Drawing.Size(268, 20);
            this.NAMETextBox.TabIndex = 27;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 43);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 26;
            label2.Text = "������������:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 16);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 24;
            label1.Text = "���:";
            // 
            // RBGlobalBlockingDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(391, 150);
            this.Name = "RBGlobalBlockingDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.NumericUpDown IDNumericUpDown;
        public System.Windows.Forms.TextBox NAMETextBox;
    }
}
