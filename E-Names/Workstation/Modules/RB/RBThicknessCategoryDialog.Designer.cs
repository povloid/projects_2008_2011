namespace Workstation.Modules.RB
{
    partial class RBThicknessCategoryDialog
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
            this.CATEGORYTextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.DESCRTextBox);
            this.groupBox.Controls.Add(this.CATEGORYTextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(373, 73);
            // 
            // DESCRTextBox
            // 
            this.DESCRTextBox.Location = new System.Drawing.Point(98, 45);
            this.DESCRTextBox.Name = "DESCRTextBox";
            this.DESCRTextBox.Size = new System.Drawing.Size(268, 20);
            this.DESCRTextBox.TabIndex = 15;
            // 
            // CATEGORYTextBox
            // 
            this.CATEGORYTextBox.Location = new System.Drawing.Point(98, 19);
            this.CATEGORYTextBox.Name = "CATEGORYTextBox";
            this.CATEGORYTextBox.Size = new System.Drawing.Size(100, 20);
            this.CATEGORYTextBox.TabIndex = 14;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 48);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 13;
            label2.Text = "Наименование:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 22);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 12;
            label1.Text = "Код:";
            // 
            // RBThicknessCategoryDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(391, 155);
            this.Name = "RBThicknessCategoryDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.TextBox DESCRTextBox;
        public System.Windows.Forms.TextBox CATEGORYTextBox;
    }
}
