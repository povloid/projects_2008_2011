using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Data;

namespace Workstation.Modules.A
{
    class ARContainComponent : AReferenceBook
    {
        public ARContainComponent()
        {
            editButton.Visible = false;
            resetButton.Visible = false;
            this.tableLayoutPanel1.Controls.Add(delButton, 1, 0);
            this.Dock = System.Windows.Forms.DockStyle.None;

            // Удаляем ненужные пункты контекстного меню и связанные с ними 
            // комбинации клавишь
            ((ToolStripMenuItem)contextMenuStrip.Items[1]).ShortcutKeys = Keys.None;
            contextMenuStrip.Items.RemoveAt(1);

            ((ToolStripMenuItem)contextMenuStrip.Items[3]).ShortcutKeys = Keys.None;
            contextMenuStrip.Items.RemoveAt(3);

            contextMenuStrip.Items.RemoveAt(2);

            
        }

    protected void setupChildElementsARCC(DataTable dataTable,
            System.Windows.Forms.BindingSource bindingSource,
            DataGridView dataGridView,
            ADialog dialog)
    {
        setupChildElements( dataTable,
            bindingSource,
            dataGridView,
            dialog);

        //dataGridView.ColumnHeadersVisible = false;
    }

        private void InitializeComponent()
        {
            this.SuspendLayout();
            // 
            // fillPanel
            // 
            this.fillPanel.Size = new System.Drawing.Size(967, 520);
            // 
            // ARContainComponent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.Name = "ARContainComponent";
            this.Size = new System.Drawing.Size(973, 587);
            this.ResumeLayout(false);

        }
    }
}
