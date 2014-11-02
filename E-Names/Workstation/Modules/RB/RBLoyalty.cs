using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBLoyalty : Workstation.Modules.A.AReferenceBook
    {
        RBLoyaltyDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.LOYALTYDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.LOYALTYTableAdapter tableAdapter;
        
        public RBLoyalty()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.LOYALTY;
            tableAdapter = this.lOYALTYTableAdapter;
            // (3) --
            bindingSource = this.lOYALTYBindingSource;
            // Инициализация диалога
            currentDialog = new RBLoyaltyDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);

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

