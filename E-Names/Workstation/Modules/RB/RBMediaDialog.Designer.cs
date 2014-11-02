namespace Workstation.Modules.RB
{
    partial class RBMediaDialog
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
            this.MEDIA_CLUSTERIDComboBox = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.NAMETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.MEDIA_CLUSTERIDComboBox);
            this.groupBox.Controls.Add(this.NAMETextBox);
            this.groupBox.Controls.Add(this.label3);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Size = new System.Drawing.Size(374, 74);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 22);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(60, 13);
            label2.TabIndex = 30;
            label2.Text = "Название:";
            // 
            // MEDIA_CLUSTERIDComboBox
            // 
            this.MEDIA_CLUSTERIDComboBox.FormattingEnabled = true;
            this.MEDIA_CLUSTERIDComboBox.Location = new System.Drawing.Point(72, 45);
            this.MEDIA_CLUSTERIDComboBox.Name = "MEDIA_CLUSTERIDComboBox";
            this.MEDIA_CLUSTERIDComboBox.Size = new System.Drawing.Size(295, 21);
            this.MEDIA_CLUSTERIDComboBox.TabIndex = 33;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 48);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(52, 13);
            this.label3.TabIndex = 32;
            this.label3.Text = "Кластер:";
            // 
            // NAMETextBox
            // 
            this.NAMETextBox.Location = new System.Drawing.Point(72, 19);
            this.NAMETextBox.Name = "NAMETextBox";
            this.NAMETextBox.Size = new System.Drawing.Size(295, 20);
            this.NAMETextBox.TabIndex = 31;
            // 
            // RBMediaDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(392, 156);
            this.Name = "RBMediaDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.ComboBox MEDIA_CLUSTERIDComboBox;
        private System.Windows.Forms.Label label3;
        public System.Windows.Forms.TextBox NAMETextBox;
    }
}
