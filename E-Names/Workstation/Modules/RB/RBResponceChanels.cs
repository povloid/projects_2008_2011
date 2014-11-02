using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBResponceChanels : Workstation.Modules.A.AReferenceBook
    {
        RBResponceChanelsDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.RESPONCE_CHANELSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.RESPONCE_CHANELSTableAdapter tableAdapter;

        public RBResponceChanels()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.RESPONCE_CHANELS;
            tableAdapter = this.rESPONCE_CHANELSTableAdapter;
            // (3) --
            bindingSource = this.rESPONCECHANELSBindingSource;
            // Инициализация диалога
            currentDialog = new RBResponceChanelsDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

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

