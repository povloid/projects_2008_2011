namespace Workstation.Modules.RB
{
    partial class RBsQAnswersDialog
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
            System.Windows.Forms.Label label3;
            this.IDNumericUpDown = new System.Windows.Forms.NumericUpDown();
            this.ANSWERTextBox = new System.Windows.Forms.TextBox();
            this.QUESTIONNAMELabel = new System.Windows.Forms.Label();
            this.QUESTIONIDLabel = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            this.groupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.QUESTIONIDLabel);
            this.groupBox.Controls.Add(this.QUESTIONNAMELabel);
            this.groupBox.Controls.Add(label3);
            this.groupBox.Controls.Add(this.IDNumericUpDown);
            this.groupBox.Controls.Add(this.ANSWERTextBox);
            this.groupBox.Controls.Add(label1);
            this.groupBox.Controls.Add(label2);
            this.groupBox.Size = new System.Drawing.Size(498, 197);
            this.groupBox.TabIndex = 0;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(6, 173);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(40, 13);
            label2.TabIndex = 5;
            label2.Text = "Ответ:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(6, 146);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(29, 13);
            label1.TabIndex = 3;
            label1.Text = "Код:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(6, 17);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(47, 13);
            label3.TabIndex = 0;
            label3.Text = "Вопрос:";
            // 
            // IDNumericUpDown
            // 
            this.IDNumericUpDown.Location = new System.Drawing.Point(95, 144);
            this.IDNumericUpDown.Name = "IDNumericUpDown";
            this.IDNumericUpDown.Size = new System.Drawing.Size(119, 20);
            this.IDNumericUpDown.TabIndex = 4;
            this.IDNumericUpDown.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // ANSWERTextBox
            // 
            this.ANSWERTextBox.Location = new System.Drawing.Point(95, 170);
            this.ANSWERTextBox.Name = "ANSWERTextBox";
            this.ANSWERTextBox.Size = new System.Drawing.Size(396, 20);
            this.ANSWERTextBox.TabIndex = 6;
            // 
            // QUESTIONNAMELabel
            // 
            this.QUESTIONNAMELabel.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.QUESTIONNAMELabel.Location = new System.Drawing.Point(95, 37);
            this.QUESTIONNAMELabel.Name = "QUESTIONNAMELabel";
            this.QUESTIONNAMELabel.Size = new System.Drawing.Size(396, 92);
            this.QUESTIONNAMELabel.TabIndex = 2;
            this.QUESTIONNAMELabel.Text = "wefnuiweh iuhwe iuhwe ivuh wieuvh weiuvh weiuhviweuhv iweuvh weiuvh weiuhv iweuv " +
                "iwe";
            // 
            // QUESTIONIDLabel
            // 
            this.QUESTIONIDLabel.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.QUESTIONIDLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.QUESTIONIDLabel.Location = new System.Drawing.Point(95, 13);
            this.QUESTIONIDLabel.Name = "QUESTIONIDLabel";
            this.QUESTIONIDLabel.Size = new System.Drawing.Size(80, 20);
            this.QUESTIONIDLabel.TabIndex = 1;
            this.QUESTIONIDLabel.Text = "12";
            this.QUESTIONIDLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // RBsQAnswersDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(516, 279);
            this.Name = "RBsQAnswersDialog";
            this.groupBox.ResumeLayout(false);
            this.groupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.IDNumericUpDown)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.NumericUpDown IDNumericUpDown;
        public System.Windows.Forms.TextBox ANSWERTextBox;
        public System.Windows.Forms.Label QUESTIONIDLabel;
        public System.Windows.Forms.Label QUESTIONNAMELabel;
    }
}
