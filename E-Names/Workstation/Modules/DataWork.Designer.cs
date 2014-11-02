namespace Workstation.Modules
{
    partial class DataWork
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DataWork));
            this.cOMPAIGNBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.phmkDataSet = new Workstation.phmkDataSet();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.dataWorkConsumerMainInfo = new Workstation.Modules.DataWorkConsumerMainInfo();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.dataWorkCompaignData = new Workstation.Modules.DataWorkCompaignData();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.imageList1 = new System.Windows.Forms.ImageList(this.components);
            this.cOMPAIGNTableAdapter = new Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter();
            this.label1 = new System.Windows.Forms.Label();
            this.compaignName = new System.Windows.Forms.Label();
            this.campaignSPanel = new System.Windows.Forms.Panel();
            this.CompaignDescriptionTextBox = new System.Windows.Forms.TextBox();
            this.selectCampaignButton = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.compaignInfiButton = new System.Windows.Forms.Button();
            this.dataWorkConsumer = new Workstation.Modules.DataWorkConsumer();
            this.delButton = new Workstation.Modules.PClasses.PButton();
            this.pButton5 = new Workstation.Modules.PClasses.PButton();
            this.saveButton = new Workstation.Modules.PClasses.PButton();
            this.newButton = new Workstation.Modules.PClasses.PButton();
            this.dataWorkQuestionsAndAnswers = new Workstation.Modules.DataWorkQuestionsAndAnswers();
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).BeginInit();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage3.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.campaignSPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // cOMPAIGNBindingSource
            // 
            this.cOMPAIGNBindingSource.DataMember = "COMPAIGN";
            this.cOMPAIGNBindingSource.DataSource = this.phmkDataSet;
            // 
            // phmkDataSet
            // 
            this.phmkDataSet.DataSetName = "phmkDataSet";
            this.phmkDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // tabControl1
            // 
            this.tabControl1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.ImageList = this.imageList1;
            this.tabControl1.Location = new System.Drawing.Point(3, 226);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(630, 273);
            this.tabControl1.TabIndex = 11;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.dataWorkConsumerMainInfo);
            this.tabPage1.ImageIndex = 1;
            this.tabPage1.Location = new System.Drawing.Point(4, 23);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(622, 246);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Основная информация:";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // dataWorkConsumerMainInfo
            // 
            this.dataWorkConsumerMainInfo.AutoScroll = true;
            this.dataWorkConsumerMainInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataWorkConsumerMainInfo.Location = new System.Drawing.Point(3, 3);
            this.dataWorkConsumerMainInfo.Name = "dataWorkConsumerMainInfo";
            this.dataWorkConsumerMainInfo.Size = new System.Drawing.Size(616, 240);
            this.dataWorkConsumerMainInfo.TabIndex = 0;
            // 
            // tabPage3
            // 
            this.tabPage3.AutoScroll = true;
            this.tabPage3.Controls.Add(this.dataWorkCompaignData);
            this.tabPage3.ImageIndex = 2;
            this.tabPage3.Location = new System.Drawing.Point(4, 23);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3.Size = new System.Drawing.Size(622, 246);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "Данные по бренду:";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // dataWorkCompaignData
            // 
            this.dataWorkCompaignData.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataWorkCompaignData.Location = new System.Drawing.Point(3, 3);
            this.dataWorkCompaignData.Name = "dataWorkCompaignData";
            this.dataWorkCompaignData.Size = new System.Drawing.Size(616, 240);
            this.dataWorkCompaignData.TabIndex = 0;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.dataWorkQuestionsAndAnswers);
            this.tabPage2.ImageIndex = 0;
            this.tabPage2.Location = new System.Drawing.Point(4, 23);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(622, 246);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Ответы на вопросы:";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // imageList1
            // 
            this.imageList1.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageList1.ImageStream")));
            this.imageList1.TransparentColor = System.Drawing.Color.Transparent;
            this.imageList1.Images.SetKeyName(0, "prefs_16.png");
            this.imageList1.Images.SetKeyName(1, "sinfo_16.png");
            this.imageList1.Images.SetKeyName(2, "flag_16.png");
            // 
            // cOMPAIGNTableAdapter
            // 
            this.cOMPAIGNTableAdapter.ClearBeforeFill = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(80, 11);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(61, 13);
            this.label1.TabIndex = 21;
            this.label1.Text = "Кампания:";
            // 
            // compaignName
            // 
            this.compaignName.Dock = System.Windows.Forms.DockStyle.Fill;
            this.compaignName.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.compaignName.Location = new System.Drawing.Point(0, 0);
            this.compaignName.Name = "compaignName";
            this.compaignName.Size = new System.Drawing.Size(147, 26);
            this.compaignName.TabIndex = 26;
            this.compaignName.Text = "...";
            this.compaignName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // campaignSPanel
            // 
            this.campaignSPanel.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.campaignSPanel.BackColor = System.Drawing.Color.White;
            this.campaignSPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.campaignSPanel.Controls.Add(this.compaignName);
            this.campaignSPanel.Location = new System.Drawing.Point(146, 3);
            this.campaignSPanel.Name = "campaignSPanel";
            this.campaignSPanel.Size = new System.Drawing.Size(149, 28);
            this.campaignSPanel.TabIndex = 28;
            // 
            // CompaignDescriptionTextBox
            // 
            this.CompaignDescriptionTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.CompaignDescriptionTextBox.Location = new System.Drawing.Point(79, 37);
            this.CompaignDescriptionTextBox.Multiline = true;
            this.CompaignDescriptionTextBox.Name = "CompaignDescriptionTextBox";
            this.CompaignDescriptionTextBox.ReadOnly = true;
            this.CompaignDescriptionTextBox.Size = new System.Drawing.Size(550, 41);
            this.CompaignDescriptionTextBox.TabIndex = 29;
            // 
            // selectCampaignButton
            // 
            this.selectCampaignButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.selectCampaignButton.Image = global::Workstation.Properties.Resources.flag_16;
            this.selectCampaignButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.selectCampaignButton.Location = new System.Drawing.Point(301, 3);
            this.selectCampaignButton.Name = "selectCampaignButton";
            this.selectCampaignButton.Size = new System.Drawing.Size(144, 28);
            this.selectCampaignButton.TabIndex = 27;
            this.selectCampaignButton.Text = "Выбрать кампанию...";
            this.selectCampaignButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.selectCampaignButton.UseVisualStyleBackColor = true;
            this.selectCampaignButton.Click += new System.EventHandler(this.selectCampaignButton_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Workstation.Properties.Resources.kontact_todo;
            this.pictureBox1.Location = new System.Drawing.Point(7, 11);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(66, 67);
            this.pictureBox1.TabIndex = 24;
            this.pictureBox1.TabStop = false;
            // 
            // compaignInfiButton
            // 
            this.compaignInfiButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.compaignInfiButton.Image = global::Workstation.Properties.Resources.about_16;
            this.compaignInfiButton.Location = new System.Drawing.Point(451, 3);
            this.compaignInfiButton.Name = "compaignInfiButton";
            this.compaignInfiButton.Size = new System.Drawing.Size(178, 28);
            this.compaignInfiButton.TabIndex = 23;
            this.compaignInfiButton.Text = "Информация по кампании...";
            this.compaignInfiButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.compaignInfiButton.UseVisualStyleBackColor = true;
            this.compaignInfiButton.Click += new System.EventHandler(this.compaignInfiButton_Click);
            // 
            // dataWorkConsumer
            // 
            this.dataWorkConsumer.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.dataWorkConsumer.Location = new System.Drawing.Point(3, 84);
            this.dataWorkConsumer.Name = "dataWorkConsumer";
            this.dataWorkConsumer.Size = new System.Drawing.Size(630, 136);
            this.dataWorkConsumer.TabIndex = 18;
            // 
            // delButton
            // 
            this.delButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.delButton.Image = global::Workstation.Properties.Resources.del_16;
            this.delButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.delButton.Location = new System.Drawing.Point(195, 505);
            this.delButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.delButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.delButton.Name = "delButton";
            this.delButton.Size = new System.Drawing.Size(90, 26);
            this.delButton.TabIndex = 16;
            this.delButton.Text = "Удалить";
            this.delButton.UseVisualStyleBackColor = true;
            this.delButton.Click += new System.EventHandler(this.delButton_Click);
            // 
            // pButton5
            // 
            this.pButton5.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.pButton5.Image = global::Workstation.Properties.Resources.ref_16;
            this.pButton5.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.pButton5.Location = new System.Drawing.Point(540, 505);
            this.pButton5.MaximumSize = new System.Drawing.Size(90, 26);
            this.pButton5.MinimumSize = new System.Drawing.Size(90, 26);
            this.pButton5.Name = "pButton5";
            this.pButton5.Size = new System.Drawing.Size(90, 26);
            this.pButton5.TabIndex = 15;
            this.pButton5.Text = "Обновить";
            this.pButton5.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.pButton5.UseVisualStyleBackColor = true;
            // 
            // saveButton
            // 
            this.saveButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.saveButton.Image = global::Workstation.Properties.Resources.save_16;
            this.saveButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.saveButton.Location = new System.Drawing.Point(99, 505);
            this.saveButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.saveButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.saveButton.Name = "saveButton";
            this.saveButton.Size = new System.Drawing.Size(90, 26);
            this.saveButton.TabIndex = 13;
            this.saveButton.Text = "Сохранить";
            this.saveButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.saveButton.UseVisualStyleBackColor = true;
            this.saveButton.Click += new System.EventHandler(this.saveButton_Click);
            // 
            // newButton
            // 
            this.newButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.newButton.Image = global::Workstation.Properties.Resources.add_16;
            this.newButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.newButton.Location = new System.Drawing.Point(3, 505);
            this.newButton.MaximumSize = new System.Drawing.Size(90, 26);
            this.newButton.MinimumSize = new System.Drawing.Size(90, 26);
            this.newButton.Name = "newButton";
            this.newButton.Size = new System.Drawing.Size(90, 26);
            this.newButton.TabIndex = 12;
            this.newButton.Text = "Новый";
            this.newButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.newButton.UseVisualStyleBackColor = true;
            this.newButton.Click += new System.EventHandler(this.newButton_Click);
            // 
            // dataWorkQuestionsAndAnswers1
            // 
            this.dataWorkQuestionsAndAnswers.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataWorkQuestionsAndAnswers.Location = new System.Drawing.Point(3, 3);
            this.dataWorkQuestionsAndAnswers.Name = "dataWorkQuestionsAndAnswers1";
            this.dataWorkQuestionsAndAnswers.Size = new System.Drawing.Size(616, 240);
            this.dataWorkQuestionsAndAnswers.TabIndex = 0;
            // 
            // DataWork
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.CompaignDescriptionTextBox);
            this.Controls.Add(this.campaignSPanel);
            this.Controls.Add(this.selectCampaignButton);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.compaignInfiButton);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataWorkConsumer);
            this.Controls.Add(this.delButton);
            this.Controls.Add(this.pButton5);
            this.Controls.Add(this.saveButton);
            this.Controls.Add(this.newButton);
            this.Controls.Add(this.tabControl1);
            this.DoubleBuffered = true;
            this.Name = "DataWork";
            this.Size = new System.Drawing.Size(636, 534);
            ((System.ComponentModel.ISupportInitialize)(this.cOMPAIGNBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phmkDataSet)).EndInit();
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage3.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.campaignSPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private phmkDataSet phmkDataSet;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private Workstation.Modules.PClasses.PButton newButton;
        private Workstation.Modules.PClasses.PButton saveButton;
        private Workstation.Modules.PClasses.PButton pButton5;
        private Workstation.Modules.PClasses.PButton delButton;
        private System.Windows.Forms.ImageList imageList1;
        private System.Windows.Forms.BindingSource cOMPAIGNBindingSource;
        private Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter cOMPAIGNTableAdapter;
        private DataWorkConsumer dataWorkConsumer;
        private System.Windows.Forms.Button compaignInfiButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.PictureBox pictureBox1;
        private DataWorkConsumerMainInfo dataWorkConsumerMainInfo;
        private System.Windows.Forms.Label compaignName;
        private System.Windows.Forms.Button selectCampaignButton;
        private System.Windows.Forms.Panel campaignSPanel;
        private System.Windows.Forms.TextBox CompaignDescriptionTextBox;
        private System.Windows.Forms.TabPage tabPage3;
        private DataWorkCompaignData dataWorkCompaignData;
        private DataWorkQuestionsAndAnswers dataWorkQuestionsAndAnswers;


    }
}
