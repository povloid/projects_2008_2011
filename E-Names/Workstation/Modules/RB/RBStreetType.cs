using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBStreetType : Workstation.Modules.A.AReferenceBook
    {
        RBStreetTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.STREET_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.STREET_TYPETableAdapter tableAdapter;

        public RBStreetType()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.STREET_TYPE;
            tableAdapter = this.sTREET_TYPETableAdapter;
            // (3) --
            bindingSource = this.sTREETTYPEBindingSource;
            // Инициализация диалога
            currentDialog = new RBStreetTypeDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            setupNumericUpDown(currentDialog.STREETTYPEIDNumericUpDown, "STREETTYPEID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.STREETTYPENAMETextBox, "STREETTYPENAME", 50, CharacterCasing.Normal);

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

