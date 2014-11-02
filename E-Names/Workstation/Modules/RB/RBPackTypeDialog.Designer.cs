namespace Workstation.Modules.RB
{
    partial class RBPackTypeDialog
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
            this.TYPETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.DESCRTextBox);
            this.groupBox.Controls.Add(this.TYPETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(370, 72);
            // 
            // DESCRTextBox
            // 
            this.DESCRTextBox.Location = new System.Drawing.Point(96, 45);
            this.DESCRTextBox.Name = "DESCRTextBox";
            this.DESCRTextBox.Size = new System.Drawing.Size(268, 20);
            this.DESCRTextBox.TabIndex = 23;
            // 
            // TYPETextBox
            // 
            this.TYPETextBox.Location = new System.Drawing.Point(96, 19);
            this.TYPETextBox.Name = "TYPETextBox";
            this.TYPETextBox.Size = new System.Drawing.Size(100, 20);
            this.TYPETextBox.TabIndex = 22;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(4, 48);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 21;
            label2.Text = "Наименование:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(4, 22);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 20;
            label1.Text = "Код:";
            // 
            // RBPackTypeDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(388, 154);
            this.Name = "RBPackTypeDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.TextBox DESCRTextBox;
        public System.Windows.Forms.TextBox TYPETextBox;
    }
}
