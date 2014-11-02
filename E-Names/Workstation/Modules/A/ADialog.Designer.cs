namespace Workstation.Modules.A
{
    partial class ADialog
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
        /// 
        

        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ADialog));
            this.capLabel = new System.Windows.Forms.Label();
            this.tableLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.cancelButton = new Workstation.Modules.PClasses.PButton();
            this.OkButton = new Workstation.Modules.PClasses.PButton();
            this.capPictureBox = new System.Windows.Forms.PictureBox();
            this.panel = new System.Windows.Forms.Panel();
            this.groupBox = new System.Windows.Forms.GroupBox();
            this.tableLayoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.capPictureBox)).BeginInit();
            this.panel.SuspendLayout();
            this.SuspendLayout();
            // 
            // capLabel
            // 
            this.capLabel.AccessibleDescription = null;
            this.capLabel.AccessibleName = null;
            resources.ApplyResources(this.capLabel, "capLabel");
            this.tableLayoutPanel.SetColumnSpan(this.capLabel, 3);
            this.capLabel.Name = "capLabel";
            // 
            // tableLayoutPanel
            // 
            this.tableLayoutPanel.AccessibleDescription = null;
            this.tableLayoutPanel.AccessibleName = null;
            resources.ApplyResources(this.tableLayoutPanel, "tableLayoutPanel");
            this.tableLayoutPanel.BackgroundImage = null;
            this.tableLayoutPanel.Controls.Add(this.cancelButton, 3, 2);
            this.tableLayoutPanel.Controls.Add(this.OkButton, 2, 2);
            this.tableLayoutPanel.Controls.Add(this.capLabel, 1, 0);
            this.tableLayoutPanel.Controls.Add(this.capPictureBox, 0, 0);
            this.tableLayoutPanel.Controls.Add(this.panel, 0, 1);
            this.tableLayoutPanel.Font = null;
            this.tableLayoutPanel.Name = "tableLayoutPanel";
            // 
            // cancelButton
            // 
            this.cancelButton.AccessibleDescription = null;
            this.cancelButton.AccessibleName = null;
            resources.ApplyResources(this.cancelButton, "cancelButton");
            this.cancelButton.BackgroundImage = null;
            this.cancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.cancelButton.Font = null;
            this.cancelButton.Image = global::Workstation.Properties.Resources.cancl_16;
            this.cancelButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.cancelButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.cancelButton.Name = "cancelButton";
            this.cancelButton.UseVisualStyleBackColor = true;
            // 
            // OkButton
            // 
            this.OkButton.AccessibleDescription = null;
            this.OkButton.AccessibleName = null;
            resources.ApplyResources(this.OkButton, "OkButton");
            this.OkButton.BackgroundImage = null;
            this.OkButton.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.OkButton.Font = null;
            this.OkButton.Image = global::Workstation.Properties.Resources.opts_16;
            this.OkButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.OkButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.OkButton.Name = "OkButton";
            this.OkButton.UseVisualStyleBackColor = true;
            // 
            // capPictureBox
            // 
            this.capPictureBox.AccessibleDescription = null;
            this.capPictureBox.AccessibleName = null;
            resources.ApplyResources(this.capPictureBox, "capPictureBox");
            this.capPictureBox.BackgroundImage = null;
            this.capPictureBox.Font = null;
            this.capPictureBox.ImageLocation = null;
            this.capPictureBox.Name = "capPictureBox";
            this.capPictureBox.TabStop = false;
            // 
            // panel
            // 
            this.panel.AccessibleDescription = null;
            this.panel.AccessibleName = null;
            resources.ApplyResources(this.panel, "panel");
            this.panel.BackgroundImage = null;
            this.tableLayoutPanel.SetColumnSpan(this.panel, 4);
            this.panel.Controls.Add(this.groupBox);
            this.panel.Font = null;
            this.panel.Name = "panel";
            // 
            // groupBox
            // 
            this.groupBox.AccessibleDescription = null;
            this.groupBox.AccessibleName = null;
            resources.ApplyResources(this.groupBox, "groupBox");
            this.groupBox.BackgroundImage = null;
            this.groupBox.Font = null;
            this.groupBox.Name = "groupBox";
            this.groupBox.TabStop = false;
            // 
            // ADialog
            // 
            this.AcceptButton = this.OkButton;
            this.AccessibleDescription = null;
            this.AccessibleName = null;
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = null;
            this.CancelButton = this.cancelButton;
            this.Controls.Add(this.tableLayoutPanel);
            this.Font = null;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Icon = null;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ADialog";
            this.ShowInTaskbar = false;
            this.Activated += new System.EventHandler(this.ADialog_Activated);
            this.tableLayoutPanel.ResumeLayout(false);
            this.tableLayoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.capPictureBox)).EndInit();
            this.panel.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        
        public System.Windows.Forms.PictureBox capPictureBox;
        public System.Windows.Forms.Label capLabel;
        private Workstation.Modules.PClasses.PButton cancelButton;
        private Workstation.Modules.PClasses.PButton OkButton;
        public System.Windows.Forms.TableLayoutPanel tableLayoutPanel;
        private System.Windows.Forms.Panel panel;
        public System.Windows.Forms.GroupBox groupBox;


    }
}