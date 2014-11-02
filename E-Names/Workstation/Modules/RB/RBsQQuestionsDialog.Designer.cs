namespace Workstation.Modules.RB
{
    partial class RBsQQuestionsDialog
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
            this.QUESTIONTYPEComboBox = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.QUESTIONIDNumericUpDown = new System.Windows.Forms.NumericUpDown();
            this.QUESTIONNAMETextBox = new System.Windows.Forms.TextBox();
            this.QUESTIONISNULLCheckBox = new System.Windows.Forms.CheckBox();
            this.ISDEFAULTCheckBox = new System.Windows.Forms.CheckBox();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.QUESTIONIDNumericUpDown)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.ISDEFAULTCheckBox);
            this.groupBox.Controls.Add(this.QUESTIONISNULLCheckBox);
            this.groupBox.Controls.Add(this.QUESTIONNAMETextBox);
            this.groupBox.Controls.Add(this.QUESTIONTYPEComboBox);
            this.groupBox.Controls.Add(this.label3);
            this.groupBox.Controls.Add(this.QUESTIONIDNumericUpDown);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Size = new System.Drawing.Size(455, 229);
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 42);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(47, 13);
            label2.TabIndex = 2;
            label2.Text = "Вопрос:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 15);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 0;
            label1.Text = "Код:";
            // 
            // QUESTIONTYPEComboBox
            // 
            this.QUESTIONTYPEComboBox.FormattingEnabled = true;
            this.QUESTIONTYPEComboBox.Location = new System.Drawing.Point(98, 179);
            this.QUESTIONTYPEComboBox.Name = "QUESTIONTYPEComboBox";
            this.QUESTIONTYPEComboBox.Size = new System.Drawing.Size(348, 21);
            this.QUESTIONTYPEComboBox.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 182);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(74, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "Тип вопроса:";
            // 
            // QUESTIONIDNumericUpDown
            // 
            this.QUESTIONIDNumericUpDown.Location = new System.Drawing.Point(98, 13);
            this.QUESTIONIDNumericUpDown.Name = "QUESTIONIDNumericUpDown";
            this.QUESTIONIDNumericUpDown.Size = new System.Drawing.Size(87, 20);
            this.QUESTIONIDNumericUpDown.TabIndex = 1;
            this.QUESTIONIDNumericUpDown.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // QUESTIONNAMETextBox
            // 
            this.QUESTIONNAMETextBox.Location = new System.Drawing.Point(98, 42);
            this.QUESTIONNAMETextBox.Multiline = true;
            this.QUESTIONNAMETextBox.Name = "QUESTIONNAMETextBox";
            this.QUESTIONNAMETextBox.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.QUESTIONNAMETextBox.Size = new System.Drawing.Size(348, 131);
            this.QUESTIONNAMETextBox.TabIndex = 3;
            // 
            // QUESTIONISNULLCheckBox
            // 
            this.QUESTIONISNULLCheckBox.AutoSize = true;
            this.QUESTIONISNULLCheckBox.Location = new System.Drawing.Point(98, 206);
            this.QUESTIONISNULLCheckBox.Name = "QUESTIONISNULLCheckBox";
            this.QUESTIONISNULLCheckBox.Size = new System.Drawing.Size(159, 17);
            this.QUESTIONISNULLCheckBox.TabIndex = 6;
            this.QUESTIONISNULLCheckBox.Text = "Обязательный для ответа";
            this.QUESTIONISNULLCheckBox.UseVisualStyleBackColor = true;
            // 
            // ISDEFAULTCheckBox
            // 
            this.ISDEFAULTCheckBox.AutoSize = true;
            this.ISDEFAULTCheckBox.Location = new System.Drawing.Point(263, 206);
            this.ISDEFAULTCheckBox.Name = "ISDEFAULTCheckBox";
            this.ISDEFAULTCheckBox.Size = new System.Drawing.Size(61, 17);
            this.ISDEFAULTCheckBox.TabIndex = 7;
            this.ISDEFAULTCheckBox.Text = "Общий";
            this.ISDEFAULTCheckBox.UseVisualStyleBackColor = true;
            // 
            // RBsQQuestionsDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(473, 311);
            this.Name = "RBsQQuestionsDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.QUESTIONIDNumericUpDown)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.ComboBox QUESTIONTYPEComboBox;
        private System.Windows.Forms.Label label3;
        public System.Windows.Forms.NumericUpDown QUESTIONIDNumericUpDown;
        public System.Windows.Forms.TextBox QUESTIONNAMETextBox;
        public System.Windows.Forms.CheckBox QUESTIONISNULLCheckBox;
        public System.Windows.Forms.CheckBox ISDEFAULTCheckBox;
    }
}
