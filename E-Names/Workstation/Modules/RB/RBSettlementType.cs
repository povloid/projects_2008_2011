using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBSettlementType : Workstation.Modules.A.AReferenceBook
    {
        RBSettlementTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.SETTLEMENT_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.SETTLEMENT_TYPETableAdapter tableAdapter;

        public RBSettlementType()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.SETTLEMENT_TYPE;
            tableAdapter = this.sETTLEMENT_TYPETableAdapter;
            // (3) --
            bindingSource = this.sETTLEMENTTYPEBindingSource;
            // Инициализация диалога
            currentDialog = new RBSettlementTypeDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            setupNumericUpDown(currentDialog.SETTLEMENTTYPEIDNumericUpDown, "SETTLEMENTTYPEID", new decimal(new int[] { 99999999, 0, 0, 0 }));

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

