using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBSignatureType : Workstation.Modules.A.AReferenceBook
    {
        RBSignatureTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.SIGNATURE_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.SIGNATURE_TYPETableAdapter tableAdapter;
        
        public RBSignatureType()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.SIGNATURE_TYPE;
            tableAdapter = this.sIGNATURE_TYPETableAdapter;
            // (3) --
            bindingSource = this.sIGNATURETYPEBindingSource;
            // Инициализация диалога
            currentDialog = new RBSignatureTypeDialog();

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

