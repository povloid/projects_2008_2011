namespace Workstation.Modules.RB
{
    partial class RBsQuestions
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
            this.rBsQQuestions = new Workstation.Modules.RB.RBsQQuestions();
            this.rBsQAnswers1 = new Workstation.Modules.RB.RBsQAnswers();
            this.splitContainer.Panel1.SuspendLayout();
            this.splitContainer.Panel2.SuspendLayout();
            this.splitContainer.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer
            // 
            // 
            // splitContainer.Panel1
            // 
            this.splitContainer.Panel1.Controls.Add(this.rBsQQuestions);
            // 
            // splitContainer.Panel2
            // 
            this.splitContainer.Panel2.Controls.Add(this.rBsQAnswers1);
            this.splitContainer.Size = new System.Drawing.Size(1028, 737);
            this.splitContainer.SplitterDistance = 500;
            // 
            // rBsQQuestions
            // 
            this.rBsQQuestions.capPanelText = "capLabel";
            this.rBsQQuestions.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rBsQQuestions.Location = new System.Drawing.Point(0, 0);
            this.rBsQQuestions.Name = "rBsQQuestions";
            this.rBsQQuestions.Padding = new System.Windows.Forms.Padding(2);
            this.rBsQQuestions.Size = new System.Drawing.Size(1028, 500);
            this.rBsQQuestions.TabIndex = 1;
            // 
            // rBsQAnswers1
            // 
            this.rBsQAnswers1.capPanelText = "capLabel";
            this.rBsQAnswers1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rBsQAnswers1.Location = new System.Drawing.Point(0, 0);
            this.rBsQAnswers1.Name = "rBsQAnswers1";
            this.rBsQAnswers1.Padding = new System.Windows.Forms.Padding(2);
            this.rBsQAnswers1.Size = new System.Drawing.Size(1028, 233);
            this.rBsQAnswers1.TabIndex = 0;
            // 
            // RBsQuestions
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "RBsQuestions";
            this.Size = new System.Drawing.Size(1028, 737);
            this.splitContainer.Panel1.ResumeLayout(false);
            this.splitContainer.Panel2.ResumeLayout(false);
            this.splitContainer.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private RBsQQuestions rBsQQuestions;
        private RBsQAnswers rBsQAnswers1;
    }
}
