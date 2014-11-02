using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBMicrodistrict : Workstation.Modules.A.AReferenceBook
    {
        RBMicrodistrictDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.MICRODISTRICTDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.MICRODISTRICTTableAdapter tableAdapter;

        public RBMicrodistrict()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.MICRODISTRICT;
            tableAdapter = this.mICRODISTRICTTableAdapter;
            // (3) --
            bindingSource = this.mICRODISTRICTBindingSource;
            // Инициализация диалога
            currentDialog = new RBMicrodistrictDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView1, currentDialog);

            // (4) Настройка элементов управления
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

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

