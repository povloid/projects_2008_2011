namespace Workstation.Modules.A
{
    partial class ARContainComponentDialogC1
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
            this.comboBox = new System.Windows.Forms.ComboBox();
            this.label = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.label);
            this.groupBox.Controls.Add(this.comboBox);
            this.groupBox.Size = new System.Drawing.Size(375, 52);
            // 
            // comboBox
            // 
            this.comboBox.FormattingEnabled = true;
            this.comboBox.Location = new System.Drawing.Point(141, 19);
            this.comboBox.Name = "comboBox";
            this.comboBox.Size = new System.Drawing.Size(225, 21);
            this.comboBox.TabIndex = 0;
            // 
            // label
            // 
            this.label.AutoSize = true;
            this.label.Location = new System.Drawing.Point(6, 22);
            this.label.Name = "label";
            this.label.Size = new System.Drawing.Size(28, 13);
            this.label.TabIndex = 1;
            this.label.Text = "Text";
            // 
            // ARContainComponentDialogC1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(393, 134);
            this.Name = "ARContainComponentDialogC1";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label;
        public System.Windows.Forms.ComboBox comboBox;
    }
}
