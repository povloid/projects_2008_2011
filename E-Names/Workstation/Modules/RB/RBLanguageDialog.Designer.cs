namespace Workstation.Modules.RB
{
    partial class RBLanguageDialog
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
            System.Windows.Forms.Label label3;
            this.IDNumericUpDown = new System.Windows.Forms.NumericUpDown();
            this.NAMETextBox = new System.Windows.Forms.TextBox();
            this.LANGUAGECODETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.LANGUAGECODETextBox);
            this.groupBox.Controls.Add(label3);
            this.groupBox.Controls.Add(this.IDNumericUpDown);
            this.groupBox.Controls.Add(this.NAMETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(373, 72);
            this.groupBox.TabIndex = 0;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 45);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(60, 13);
            label2.TabIndex = 4;
            label2.Text = "Название:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 18);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 0;
            label1.Text = "Код:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(134, 18);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(148, 13);
            label3.TabIndex = 2;
            label3.Text = "Мнемонический код языка:";
            // 
            // IDNumericUpDown
            // 
            this.IDNumericUpDown.Location = new System.Drawing.Point(41, 16);
            this.IDNumericUpDown.Name = "IDNumericUpDown";
            this.IDNumericUpDown.Size = new System.Drawing.Size(87, 20);
            this.IDNumericUpDown.TabIndex = 1;
            this.IDNumericUpDown.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // NAMETextBox
            // 
            this.NAMETextBox.Location = new System.Drawing.Point(98, 42);
            this.NAMETextBox.Name = "NAMETextBox";
            this.NAMETextBox.Size = new System.Drawing.Size(268, 20);
            this.NAMETextBox.TabIndex = 5;
            // 
            // LANGUAGECODETextBox
            // 
            this.LANGUAGECODETextBox.Location = new System.Drawing.Point(284, 15);
            this.LANGUAGECODETextBox.Name = "LANGUAGECODETextBox";
            this.LANGUAGECODETextBox.Size = new System.Drawing.Size(83, 20);
            this.LANGUAGECODETextBox.TabIndex = 3;
            // 
            // RBLanguageDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(391, 154);
            this.Name = "RBLanguageDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.NumericUpDown IDNumericUpDown;
        public System.Windows.Forms.TextBox NAMETextBox;
        public System.Windows.Forms.TextBox LANGUAGECODETextBox;
    }
}
