namespace Workstation.Modules.RB
{
    partial class RBPopulatedPointsDialog
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
            System.Windows.Forms.Label label5;
            this.REGIONIDComboBox = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.NAMETextBox = new System.Windows.Forms.TextBox();
            this.SETTLEMENTTYPEIDComboBox = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.DESCRTextBox = new System.Windows.Forms.TextBox();
            label2 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.DESCRTextBox);
            this.groupBox.Controls.Add(label5);
            this.groupBox.Controls.Add(this.SETTLEMENTTYPEIDComboBox);
            this.groupBox.Controls.Add(this.label4);
            this.groupBox.Controls.Add(this.REGIONIDComboBox);
            this.groupBox.Controls.Add(this.label3);
            this.groupBox.Controls.Add(this.NAMETextBox);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Size = new System.Drawing.Size(375, 123);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(7, 17);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(86, 13);
            label2.TabIndex = 2;
            label2.Text = "Наименование:";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(7, 43);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(60, 13);
            label5.TabIndex = 4;
            label5.Text = "Описание:";
            // 
            // REGIONIDComboBox
            // 
            this.REGIONIDComboBox.FormattingEnabled = true;
            this.REGIONIDComboBox.Location = new System.Drawing.Point(99, 66);
            this.REGIONIDComboBox.Name = "REGIONIDComboBox";
            this.REGIONIDComboBox.Size = new System.Drawing.Size(268, 21);
            this.REGIONIDComboBox.TabIndex = 7;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(7, 69);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(41, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Район:";
            // 
            // NAMETextBox
            // 
            this.NAMETextBox.Location = new System.Drawing.Point(99, 14);
            this.NAMETextBox.Name = "NAMETextBox";
            this.NAMETextBox.Size = new System.Drawing.Size(268, 20);
            this.NAMETextBox.TabIndex = 3;
            // 
            // SETTLEMENTTYPEIDComboBox
            // 
            this.SETTLEMENTTYPEIDComboBox.FormattingEnabled = true;
            this.SETTLEMENTTYPEIDComboBox.Location = new System.Drawing.Point(99, 93);
            this.SETTLEMENTTYPEIDComboBox.Name = "SETTLEMENTTYPEIDComboBox";
            this.SETTLEMENTTYPEIDComboBox.Size = new System.Drawing.Size(268, 21);
            this.SETTLEMENTTYPEIDComboBox.TabIndex = 9;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(7, 96);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(90, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Тип нас. пункта:";
            // 
            // DESCRTextBox
            // 
            this.DESCRTextBox.Location = new System.Drawing.Point(99, 40);
            this.DESCRTextBox.Name = "DESCRTextBox";
            this.DESCRTextBox.Size = new System.Drawing.Size(268, 20);
            this.DESCRTextBox.TabIndex = 5;
            // 
            // RBPopulatedPointsDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(393, 205);
            this.Name = "RBPopulatedPointsDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.ComboBox REGIONIDComboBox;
        private System.Windows.Forms.Label label3;
        public System.Windows.Forms.TextBox NAMETextBox;
        public System.Windows.Forms.ComboBox SETTLEMENTTYPEIDComboBox;
        private System.Windows.Forms.Label label4;
        public System.Windows.Forms.TextBox DESCRTextBox;
    }
}
