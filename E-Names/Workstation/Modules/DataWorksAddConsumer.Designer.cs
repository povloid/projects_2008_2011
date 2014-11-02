namespace Workstation.Modules
{
    partial class DataWorksAddConsumer
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.dataWorkConsumerMainInfo = new Workstation.Modules.DataWorkConsumerMainInfo();
            this.dataWorkConsumer = new Workstation.Modules.DataWorkConsumer();
            this.groupBox.SuspendLayout();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox
            // 
            this.groupBox.Controls.Add(this.panel1);
            this.groupBox.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.groupBox.Size = new System.Drawing.Size(630, 533);
            // 
            // panel1
            // 
            this.panel1.AutoScroll = true;
            this.panel1.Controls.Add(this.dataWorkConsumerMainInfo);
            this.panel1.Controls.Add(this.dataWorkConsumer);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(3, 16);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(624, 514);
            this.panel1.TabIndex = 0;
            // 
            // dataWorkConsumerMainInfo
            // 
            this.dataWorkConsumerMainInfo.Location = new System.Drawing.Point(3, 145);
            this.dataWorkConsumerMainInfo.Name = "dataWorkConsumerMainInfo";
            this.dataWorkConsumerMainInfo.Size = new System.Drawing.Size(600, 553);
            this.dataWorkConsumerMainInfo.TabIndex = 1;
            // 
            // dataWorkConsumer
            // 
            this.dataWorkConsumer.Location = new System.Drawing.Point(3, 3);
            this.dataWorkConsumer.Name = "dataWorkConsumer";
            this.dataWorkConsumer.Size = new System.Drawing.Size(596, 136);
            this.dataWorkConsumer.TabIndex = 0;
            // 
            // DataWorksAddConsumer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(648, 615);
            this.Name = "DataWorksAddConsumer";
            this.pictureInHead = global::Workstation.Properties.Resources.add_32;
            this.Text = "Добавить потребителя...";
            this.textInHead = "Добавить потребителя в базу данных";
            this.windowCaptionText = "Добавить потребителя...";
            this.groupBox.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        public DataWorkConsumerMainInfo dataWorkConsumerMainInfo;
        public DataWorkConsumer dataWorkConsumer;
    }
}
