namespace Workstation.Modules.RB
{
    partial class RBRegionDialog
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
            this.NAMETextBox = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.OBLASTIDComboBox = new System.Windows.Forms.ComboBox();
            label2 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.OBLASTIDComboBox);
            this.groupBox.Controls.Add(this.label3);
            this.groupBox.Controls.Add(this.NAMETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Size = new System.Drawing.Size(379, 75);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(13, 22);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 18;
            label2.Text = "Наименование:";
            // 
            // NAMETextBox
            // 
            this.NAMETextBox.Location = new System.Drawing.Point(105, 19);
            this.NAMETextBox.Name = "NAMETextBox";
            this.NAMETextBox.Size = new System.Drawing.Size(268, 20);
            this.NAMETextBox.TabIndex = 19;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 48);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 13);
            this.label3.TabIndex = 20;
            this.label3.Text = "Область:";
            // 
            // OBLASTIDComboBox
            // 
            this.OBLASTIDComboBox.FormattingEnabled = true;
            this.OBLASTIDComboBox.Location = new System.Drawing.Point(105, 45);
            this.OBLASTIDComboBox.Name = "OBLASTIDComboBox";
            this.OBLASTIDComboBox.Size = new System.Drawing.Size(268, 21);
            this.OBLASTIDComboBox.TabIndex = 21;
            // 
            // RBRegionDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(397, 157);
            this.Name = "RBRegionDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label3;
        public System.Windows.Forms.TextBox NAMETextBox;
        public System.Windows.Forms.ComboBox OBLASTIDComboBox;
    }
}
