namespace Workstation.Modules
{
    partial class AdminingUsersInfo
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(AdminingUsersInfo));
            this.button1 = new System.Windows.Forms.Button();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.youAsUserInSystem1 = new Workstation.Modules.YouAsUserInSystem();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.youAsUserYouWork1 = new Workstation.Modules.YouAsUserYouWork();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.youAsUserYouAdditionalInfo1 = new Workstation.Modules.YouAsUserYouAdditionalInfo();
            this.tabPage4 = new System.Windows.Forms.TabPage();
            this.youAsUserYouHome1 = new Workstation.Modules.YouAsUserYouHome();
            this.tabPage5 = new System.Windows.Forms.TabPage();
            this.youAsUserYouFoto1 = new Workstation.Modules.YouAsUserYouFoto();
            this.imageList = new System.Windows.Forms.ImageList(this.components);
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.tabPage3.SuspendLayout();
            this.tabPage4.SuspendLayout();
            this.tabPage5.SuspendLayout();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.button1.Image = global::Workstation.Properties.Resources.close_16;
            this.button1.Location = new System.Drawing.Point(422, 382);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(101, 31);
            this.button1.TabIndex = 0;
            this.button1.Text = "Закрыть";
            this.button1.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Controls.Add(this.tabPage4);
            this.tabControl1.Controls.Add(this.tabPage5);
            this.tabControl1.ImageList = this.imageList;
            this.tabControl1.Location = new System.Drawing.Point(12, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(511, 364);
            this.tabControl1.TabIndex = 1;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.youAsUserInSystem1);
            this.tabPage1.ImageIndex = 3;
            this.tabPage1.Location = new System.Drawing.Point(4, 31);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(503, 329);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "В системе E-Names";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // youAsUserInSystem1
            // 
            this.youAsUserInSystem1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.youAsUserInSystem1.Location = new System.Drawing.Point(3, 3);
            this.youAsUserInSystem1.Name = "youAsUserInSystem1";
            this.youAsUserInSystem1.Size = new System.Drawing.Size(497, 323);
            this.youAsUserInSystem1.TabIndex = 0;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.youAsUserYouWork1);
            this.tabPage2.ImageIndex = 0;
            this.tabPage2.Location = new System.Drawing.Point(4, 31);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(503, 329);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Информация о работе";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // youAsUserYouWork1
            // 
            this.youAsUserYouWork1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.youAsUserYouWork1.Location = new System.Drawing.Point(3, 3);
            this.youAsUserYouWork1.Name = "youAsUserYouWork1";
            this.youAsUserYouWork1.Size = new System.Drawing.Size(497, 323);
            this.youAsUserYouWork1.TabIndex = 0;
            // 
            // tabPage3
            // 
            this.tabPage3.Controls.Add(this.youAsUserYouAdditionalInfo1);
            this.tabPage3.ImageIndex = 1;
            this.tabPage3.Location = new System.Drawing.Point(4, 31);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3.Size = new System.Drawing.Size(503, 329);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "Дополнительная информация";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // youAsUserYouAdditionalInfo1
            // 
            this.youAsUserYouAdditionalInfo1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.youAsUserYouAdditionalInfo1.Location = new System.Drawing.Point(3, 3);
            this.youAsUserYouAdditionalInfo1.Name = "youAsUserYouAdditionalInfo1";
            this.youAsUserYouAdditionalInfo1.Size = new System.Drawing.Size(497, 323);
            this.youAsUserYouAdditionalInfo1.TabIndex = 0;
            // 
            // tabPage4
            // 
            this.tabPage4.Controls.Add(this.youAsUserYouHome1);
            this.tabPage4.ImageIndex = 4;
            this.tabPage4.Location = new System.Drawing.Point(4, 31);
            this.tabPage4.Name = "tabPage4";
            this.tabPage4.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage4.Size = new System.Drawing.Size(503, 329);
            this.tabPage4.TabIndex = 3;
            this.tabPage4.Text = "Домашняя информация";
            this.tabPage4.UseVisualStyleBackColor = true;
            // 
            // youAsUserYouHome1
            // 
            this.youAsUserYouHome1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.youAsUserYouHome1.Location = new System.Drawing.Point(3, 3);
            this.youAsUserYouHome1.Name = "youAsUserYouHome1";
            this.youAsUserYouHome1.Size = new System.Drawing.Size(497, 323);
            this.youAsUserYouHome1.TabIndex = 0;
            // 
            // tabPage5
            // 
            this.tabPage5.Controls.Add(this.youAsUserYouFoto1);
            this.tabPage5.ImageIndex = 2;
            this.tabPage5.Location = new System.Drawing.Point(4, 31);
            this.tabPage5.Name = "tabPage5";
            this.tabPage5.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage5.Size = new System.Drawing.Size(503, 329);
            this.tabPage5.TabIndex = 4;
            this.tabPage5.Text = "Фотография";
            this.tabPage5.UseVisualStyleBackColor = true;
            // 
            // youAsUserYouFoto1
            // 
            this.youAsUserYouFoto1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.youAsUserYouFoto1.Location = new System.Drawing.Point(3, 3);
            this.youAsUserYouFoto1.Name = "youAsUserYouFoto1";
            this.youAsUserYouFoto1.Size = new System.Drawing.Size(497, 323);
            this.youAsUserYouFoto1.TabIndex = 0;
            // 
            // imageList
            // 
            this.imageList.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageList.ImageStream")));
            this.imageList.TransparentColor = System.Drawing.Color.Transparent;
            this.imageList.Images.SetKeyName(0, "fax_24.png");
            this.imageList.Images.SetKeyName(1, "about_24.png");
            this.imageList.Images.SetKeyName(2, "image_24.png");
            this.imageList.Images.SetKeyName(3, "apps_24.png");
            this.imageList.Images.SetKeyName(4, "home_24.png");
            // 
            // AdminingUsersInfo
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(535, 425);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.button1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "AdminingUsersInfo";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Информация о пользователе";
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.tabPage3.ResumeLayout(false);
            this.tabPage4.ResumeLayout(false);
            this.tabPage5.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.ImageList imageList;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.TabPage tabPage4;
        private YouAsUserInSystem youAsUserInSystem1;
        private YouAsUserYouWork youAsUserYouWork1;
        private YouAsUserYouAdditionalInfo youAsUserYouAdditionalInfo1;
        private YouAsUserYouHome youAsUserYouHome1;
        private System.Windows.Forms.TabPage tabPage5;
        private YouAsUserYouFoto youAsUserYouFoto1;
    }
}