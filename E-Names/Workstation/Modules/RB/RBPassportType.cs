using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPassportType : Workstation.Modules.A.AReferenceBook
    {
        RBPassportTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.PASSPORT_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.PASSPORT_TYPETableAdapter tableAdapter;

        public RBPassportType()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.PASSPORT_TYPE;
            tableAdapter = this.pASSPORT_TYPETableAdapter;
            // (3) --
            bindingSource = this.pASSPORTTYPEBindingSource;
            // Инициализация диалога
            currentDialog = new RBPassportTypeDialog();

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

