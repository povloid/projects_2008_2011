using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBGlobalBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBGlobalBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.GLOBAL_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.GLOBAL_BLOCKINGTableAdapter tableAdapter;

        public RBGlobalBlocking()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.GLOBAL_BLOCKING;
            tableAdapter = this.gLOBAL_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.gLOBALBLOCKINGBindingSource;
            // Инициализация диалога
            currentDialog = new RBGlobalBlockingDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.NAMETextBox, "NAME", 50, CharacterCasing.Normal);

            vrefresh();
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

