namespace Workstation.Modules.RB
{
    partial class RBCompanyCodeDialog
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
            this.DESCRIPTIONTextBox = new System.Windows.Forms.TextBox();
            this.PMCODETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.DESCRIPTIONTextBox);
            this.groupBox.Controls.Add(this.PMCODETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(373, 66);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 42);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(60, 13);
            label2.TabIndex = 5;
            label2.Text = "Название:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 16);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 4;
            label1.Text = "Код:";
            // 
            // DESCRIPTIONTextBox
            // 
            this.DESCRIPTIONTextBox.Location = new System.Drawing.Point(98, 39);
            this.DESCRIPTIONTextBox.Name = "DESCRIPTIONTextBox";
            this.DESCRIPTIONTextBox.Size = new System.Drawing.Size(268, 20);
            this.DESCRIPTIONTextBox.TabIndex = 7;
            // 
            // PMCODETextBox
            // 
            this.PMCODETextBox.Location = new System.Drawing.Point(98, 13);
            this.PMCODETextBox.Name = "PMCODETextBox";
            this.PMCODETextBox.Size = new System.Drawing.Size(100, 20);
            this.PMCODETextBox.TabIndex = 6;
            // 
            // RBCompanyCodeDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(391, 148);
            this.Name = "RBCompanyCodeDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.TextBox DESCRIPTIONTextBox;
        public System.Windows.Forms.TextBox PMCODETextBox;
    }
}
