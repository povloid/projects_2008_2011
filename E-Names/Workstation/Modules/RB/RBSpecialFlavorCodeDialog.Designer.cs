namespace Workstation.Modules.RB
{
    partial class RBSpecialFlavorCodeDialog
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
            this.CODETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.DESCRTextBox);
            this.groupBox.Controls.Add(this.CODETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(372, 71);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 48);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 9;
            label2.Text = "Наименование:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 22);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 8;
            label1.Text = "Код:";
            // 
            // DESCRTextBox
            // 
            this.DESCRTextBox.Location = new System.Drawing.Point(98, 45);
            this.DESCRTextBox.Name = "DESCRTextBox";
            this.DESCRTextBox.Size = new System.Drawing.Size(268, 20);
            this.DESCRTextBox.TabIndex = 11;
            // 
            // CODETextBox
            // 
            this.CODETextBox.Location = new System.Drawing.Point(98, 19);
            this.CODETextBox.Name = "CODETextBox";
            this.CODETextBox.Size = new System.Drawing.Size(100, 20);
            this.CODETextBox.TabIndex = 10;
            // 
            // RBSpecialFlavorCodeDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(390, 153);
            this.Name = "RBSpecialFlavorCodeDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.TextBox DESCRTextBox;
        public System.Windows.Forms.TextBox CODETextBox;
    }
}
