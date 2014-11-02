namespace Workstation.Modules
{
    partial class AdminingRoles
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
            this.label1 = new System.Windows.Forms.Label();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.panel = new System.Windows.Forms.Panel();
            this.delButton = new System.Windows.Forms.Button();
            this.editButton = new System.Windows.Forms.Button();
            this.addButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.panel.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Dock = System.Windows.Forms.DockStyle.Top;
            this.label1.Location = new System.Drawing.Point(0, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(35, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Роли:";
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView1.Location = new System.Drawing.Point(0, 13);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(427, 321);
            this.dataGridView1.TabIndex = 1;
            // 
            // panel
            // 
            this.panel.Controls.Add(this.delButton);
            this.panel.Controls.Add(this.editButton);
            this.panel.Controls.Add(this.addButton);
            this.panel.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel.Location = new System.Drawing.Point(0, 269);
            this.panel.Name = "panel";
            this.panel.Size = new System.Drawing.Size(427, 65);
            this.panel.TabIndex = 2;
            // 
            // delButton
            // 
            this.delButton.Image = global::Workstation.Properties.Resources.del_16;
            this.delButton.Location = new System.Drawing.Point(116, 34);
            this.delButton.Name = "delButton";
            this.delButton.Size = new System.Drawing.Size(138, 25);
            this.delButton.TabIndex = 2;
            this.delButton.Text = "Удалить роль";
            this.delButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.delButton.UseVisualStyleBackColor = true;
            // 
            // editButton
            // 
            this.editButton.Image = global::Workstation.Properties.Resources.edit_16;
            this.editButton.Location = new System.Drawing.Point(116, 3);
            this.editButton.Name = "editButton";
            this.editButton.Size = new System.Drawing.Size(138, 25);
            this.editButton.TabIndex = 1;
            this.editButton.Text = "Изменить роль";
            this.editButton.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.editButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.editButton.UseVisualStyleBackColor = true;
            // 
            // addButton
            // 
            this.addButton.Image = global::Workstation.Properties.Resources.add_32;
            this.addButton.Location = new System.Drawing.Point(3, 3);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(107, 56);
            this.addButton.TabIndex = 0;
            this.addButton.Text = "Добавить роль";
            this.addButton.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.addButton.UseVisualStyleBackColor = true;
            // 
            // AdminingRoles
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.panel);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.label1);
            this.Name = "AdminingRoles";
            this.Size = new System.Drawing.Size(427, 334);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.panel.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Panel panel;
        private System.Windows.Forms.Button delButton;
        private System.Windows.Forms.Button editButton;
        private System.Windows.Forms.Button addButton;
    }
}
