using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBMedia : Workstation.Modules.A.AReferenceBook
    {
        RBMediaDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.MEDIADataTable dataTable;
        Workstation.phmkDataSetTableAdapters.MEDIATableAdapter tableAdapter;

        public RBMedia(System.Windows.Forms.BindingSource mEDIACLUSTERBindingSource)
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.MEDIA;
            tableAdapter = this.mEDIATableAdapter;
            // (3) --
            bindingSource = this.mEDIABindingSource;
            // Инициализация диалога
            currentDialog = new RBMediaDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.NAMETextBox, "NAME", 100, CharacterCasing.Normal);

            setupComboBox(currentDialog.MEDIA_CLUSTERIDComboBox, mEDIACLUSTERBindingSource, "MEDIA_CLUSTERID", "NAME", "ID");

            vrefresh();

        }

        protected override void vafterAdd()
        {
            dataGridView.CurrentRow.Cells["MEDIA_CLUSTERNAME"].Value = currentDialog.MEDIA_CLUSTERIDComboBox.Text;
        }

        protected override void vafterEdit()
        {
            dataGridView.CurrentRow.Cells["MEDIA_CLUSTERNAME"].Value = currentDialog.MEDIA_CLUSTERIDComboBox.Text;
        }

        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            tableAdapter.Fill(dataTable);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }
    }
}
