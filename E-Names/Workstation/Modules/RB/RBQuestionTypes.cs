using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBQuestionTypes : Workstation.Modules.A.AReferenceBook
    {
        RBQuestionTypesDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.QUESTION_TYPESDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.QUESTION_TYPESTableAdapter tableAdapter;

        public RBQuestionTypes()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.QUESTION_TYPES;
            tableAdapter = this.qUESTION_TYPESTableAdapter;
            // (3) --
            bindingSource = this.qUESTIONTYPESBindingSource;
            // Инициализация диалога
            currentDialog = new RBQuestionTypesDialog();

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

