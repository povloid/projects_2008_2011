namespace Workstation.Modules.A
{
    partial class AReferenceBook
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.Windows.Forms.ToolStripMenuItem resetToolStripMenuItem;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(AReferenceBook));
            this.contextMenuStrip = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.addToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.editToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.delToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.panel = new System.Windows.Forms.Panel();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.resetButton = new Workstation.Modules.PClasses.PButton();
            this.editButton = new Workstation.Modules.PClasses.PButton();
            this.delButton = new Workstation.Modules.PClasses.PButton();
            this.addButton = new Workstation.Modules.PClasses.PButton();
            this.pano = new System.Windows.Forms.Panel();
            this.fillPanel = new System.Windows.Forms.Panel();
            this.panel1 = new System.Windows.Forms.Panel();
            this.capLabel = new System.Windows.Forms.Label();
            resetToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.contextMenuStrip.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.panel.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            this.pano.SuspendLayout();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // resetToolStripMenuItem
            // 
            resources.ApplyResources(resetToolStripMenuItem, "resetToolStripMenuItem");
            resetToolStripMenuItem.Name = "resetToolStripMenuItem";
            resetToolStripMenuItem.Click += new System.EventHandler(this.resetToolStripMenuItem_Click);
            // 
            // contextMenuStrip
            // 
            this.contextMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addToolStripMenuItem,
            this.editToolStripMenuItem,
            this.delToolStripMenuItem,
            this.toolStripSeparator1,
            resetToolStripMenuItem});
            this.contextMenuStrip.Name = "contextMenuStrip1";
            resources.ApplyResources(this.contextMenuStrip, "contextMenuStrip");
            // 
            // addToolStripMenuItem
            // 
            resources.ApplyResources(this.addToolStripMenuItem, "addToolStripMenuItem");
            this.addToolStripMenuItem.Name = "addToolStripMenuItem";
            this.addToolStripMenuItem.Click += new System.EventHandler(this.addToolStripMenuItem_Click);
            // 
            // editToolStripMenuItem
            // 
            resources.ApplyResources(this.editToolStripMenuItem, "editToolStripMenuItem");
            this.editToolStripMenuItem.Name = "editToolStripMenuItem";
            this.editToolStripMenuItem.Click += new System.EventHandler(this.editToolStripMenuItem_Click);
            // 
            // delToolStripMenuItem
            // 
            resources.ApplyResources(this.delToolStripMenuItem, "delToolStripMenuItem");
            this.delToolStripMenuItem.Name = "delToolStripMenuItem";
            this.delToolStripMenuItem.Click += new System.EventHandler(this.delToolStripMenuItem_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            resources.ApplyResources(this.toolStripSeparator1, "toolStripSeparator1");
            // 
            // tableLayoutPanel2
            // 
            resources.ApplyResources(this.tableLayoutPanel2, "tableLayoutPanel2");
            this.tableLayoutPanel2.Controls.Add(this.panel, 0, 2);
            this.tableLayoutPanel2.Controls.Add(this.pano, 0, 1);
            this.tableLayoutPanel2.Controls.Add(this.panel1, 0, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            // 
            // panel
            // 
            this.panel.ContextMenuStrip = this.contextMenuStrip;
            this.panel.Controls.Add(this.tableLayoutPanel1);
            resources.ApplyResources(this.panel, "panel");
            this.panel.Name = "panel";
            // 
            // tableLayoutPanel1
            // 
            resources.ApplyResources(this.tableLayoutPanel1, "tableLayoutPanel1");
            this.tableLayoutPanel1.Controls.Add(this.resetButton, 4, 0);
            this.tableLayoutPanel1.Controls.Add(this.editButton, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.delButton, 2, 0);
            this.tableLayoutPanel1.Controls.Add(this.addButton, 0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            // 
            // resetButton
            // 
            this.resetButton.ContextMenuStrip = this.contextMenuStrip;
            resources.ApplyResources(this.resetButton, "resetButton");
            this.resetButton.MaximumSize = new System.Drawing.Size(20, 20);
            this.resetButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.resetButton.Name = "resetButton";
            this.resetButton.UseVisualStyleBackColor = true;
            this.resetButton.Click += new System.EventHandler(this.resetButton_Click);
            // 
            // editButton
            // 
            this.editButton.ContextMenuStrip = this.contextMenuStrip;
            resources.ApplyResources(this.editButton, "editButton");
            this.editButton.MaximumSize = new System.Drawing.Size(20, 20);
            this.editButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.editButton.Name = "editButton";
            this.editButton.UseVisualStyleBackColor = true;
            this.editButton.Click += new System.EventHandler(this.editButton_Click);
            // 
            // delButton
            // 
            this.delButton.ContextMenuStrip = this.contextMenuStrip;
            resources.ApplyResources(this.delButton, "delButton");
            this.delButton.MaximumSize = new System.Drawing.Size(20, 20);
            this.delButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.delButton.Name = "delButton";
            this.delButton.UseVisualStyleBackColor = true;
            this.delButton.Click += new System.EventHandler(this.delButton_Click);
            // 
            // addButton
            // 
            this.addButton.ContextMenuStrip = this.contextMenuStrip;
            resources.ApplyResources(this.addButton, "addButton");
            this.addButton.MaximumSize = new System.Drawing.Size(20, 20);
            this.addButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.addButton.Name = "addButton";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // pano
            // 
            this.pano.Controls.Add(this.fillPanel);
            resources.ApplyResources(this.pano, "pano");
            this.pano.Name = "pano";
            // 
            // fillPanel
            // 
            resources.ApplyResources(this.fillPanel, "fillPanel");
            this.fillPanel.Name = "fillPanel";
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.SystemColors.Window;
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.capLabel);
            resources.ApplyResources(this.panel1, "panel1");
            this.panel1.Name = "panel1";
            // 
            // capLabel
            // 
            resources.ApplyResources(this.capLabel, "capLabel");
            this.capLabel.Name = "capLabel";
            // 
            // AReferenceBook
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel2);
            this.Name = "AReferenceBook";
            this.contextMenuStrip.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.panel.ResumeLayout(false);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.pano.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);

        }
        
        #endregion

        private System.Windows.Forms.ToolStripMenuItem addToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem editToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem delToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        protected System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        protected System.Windows.Forms.Panel pano;
        protected System.Windows.Forms.Panel fillPanel;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label capLabel;
        private System.Windows.Forms.Panel panel;
        protected System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        internal Workstation.Modules.PClasses.PButton resetButton;
        internal Workstation.Modules.PClasses.PButton editButton;
        internal Workstation.Modules.PClasses.PButton delButton;
        internal Workstation.Modules.PClasses.PButton addButton;
        protected System.Windows.Forms.ContextMenuStrip contextMenuStrip;
    }
}
