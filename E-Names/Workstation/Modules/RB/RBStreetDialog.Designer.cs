namespace Workstation.Modules.RB
{
    partial class RBStreetDialog
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
            this.STREET_TYPEIDComboBox = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.NAMETextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.STREET_TYPEIDComboBox);
            this.groupBox.Controls.Add(this.label3);
            this.groupBox.Controls.Add(this.NAMETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Size = new System.Drawing.Size(375, 73);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(7, 22);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(60, 13);
            label2.TabIndex = 24;
            label2.Text = "Название:";
            // 
            // STREET_TYPEIDComboBox
            // 
            this.STREET_TYPEIDComboBox.FormattingEnabled = true;
            this.STREET_TYPEIDComboBox.Location = new System.Drawing.Point(73, 45);
            this.STREET_TYPEIDComboBox.Name = "STREET_TYPEIDComboBox";
            this.STREET_TYPEIDComboBox.Size = new System.Drawing.Size(295, 21);
            this.STREET_TYPEIDComboBox.TabIndex = 27;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 48);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(61, 13);
            this.label3.TabIndex = 26;
            this.label3.Text = "Тип улици:";
            // 
            // NAMETextBox
            // 
            this.NAMETextBox.Location = new System.Drawing.Point(73, 19);
            this.NAMETextBox.Name = "NAMETextBox";
            this.NAMETextBox.Size = new System.Drawing.Size(295, 20);
            this.NAMETextBox.TabIndex = 25;
            // 
            // RBStreetDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(393, 155);
            this.Name = "RBStreetDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.ComboBox STREET_TYPEIDComboBox;
        private System.Windows.Forms.Label label3;
        public System.Windows.Forms.TextBox NAMETextBox;
    }
}
