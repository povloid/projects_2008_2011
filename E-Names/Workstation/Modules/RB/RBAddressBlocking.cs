using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBAddressBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBAddressBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.ADDRESS_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.ADDRESS_BLOCKINGTableAdapter tableAdapter;

        public RBAddressBlocking()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.ADDRESS_BLOCKING;
            tableAdapter = this.aDDRESS_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.aDDRESSBLOCKINGBindingSource;
            // Инициализация диалога
            currentDialog = new RBAddressBlockingDialog();

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

