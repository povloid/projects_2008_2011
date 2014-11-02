namespace Workstation.Modules.RB
{
    partial class RBStreetTypeDialog
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
            this.STREETTYPEIDNumericUpDown = new System.Windows.Forms.NumericUpDown();
            this.STREETTYPENAMETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.STREETTYPEIDNumericUpDown)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.STREETTYPEIDNumericUpDown);
            this.groupBox.Controls.Add(this.STREETTYPENAMETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(374, 70);
            // 
            // STREETTYPEIDNumericUpDown
            // 
            this.STREETTYPEIDNumericUpDown.Location = new System.Drawing.Point(98, 15);
            this.STREETTYPEIDNumericUpDown.Name = "STREETTYPEIDNumericUpDown";
            this.STREETTYPEIDNumericUpDown.Size = new System.Drawing.Size(87, 20);
            this.STREETTYPEIDNumericUpDown.TabIndex = 17;
            this.STREETTYPEIDNumericUpDown.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // STREETTYPENAMETextBox
            // 
            this.STREETTYPENAMETextBox.Location = new System.Drawing.Point(98, 41);
            this.STREETTYPENAMETextBox.Name = "STREETTYPENAMETextBox";
            this.STREETTYPENAMETextBox.Size = new System.Drawing.Size(268, 20);
            this.STREETTYPENAMETextBox.TabIndex = 19;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 44);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 18;
            label2.Text = "Наименование:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 17);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 16;
            label1.Text = "Код:";
            // 
            // RBStreetTypeDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(392, 152);
            this.Name = "RBStreetTypeDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.STREETTYPEIDNumericUpDown)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.NumericUpDown STREETTYPEIDNumericUpDown;
        public System.Windows.Forms.TextBox STREETTYPENAMETextBox;
    }
}
