using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBOblast : Workstation.Modules.A.AReferenceBook
    {
        RBOblastDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.OBLASTDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.OBLASTTableAdapter tableAdapter;

        public RBOblast()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.OBLAST;
            tableAdapter = this.oBLASTTableAdapter;
            // (3) --
            bindingSource = this.oBLASTBindingSource;
            // Инициализация диалога
            currentDialog = new RBOblastDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupNumericUpDown(currentDialog.OBLASTIDNumericUpDown, "OBLASTID", new decimal(new int[] { 99999999, 0, 0, 0 }));

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

