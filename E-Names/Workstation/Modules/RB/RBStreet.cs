using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBStreet : Workstation.Modules.A.AReferenceBook
    {
        RBStreetDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.STREETDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.STREETTableAdapter tableAdapter;

        public RBStreet(System.Windows.Forms.BindingSource sTREETTYPEBindingSource)
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.STREET;
            tableAdapter = this.sTREETTableAdapter;
            // (3) --
            bindingSource = this.sTREETBindingSource;
            // Инициализация диалога
            currentDialog = new RBStreetDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupComboBox(currentDialog.STREET_TYPEIDComboBox, sTREETTYPEBindingSource, "STREET_TYPEID", "STREETTYPENAME", "STREETTYPEID");

            setupTextBox(currentDialog.NAMETextBox, "NAME", 50, CharacterCasing.Normal);

            vrefresh();
        }

        protected override void vafterAdd() 
        {
            dataGridView.CurrentRow.Cells["sTREETTYPENAMEDataGridViewTextBoxColumn"].Value = currentDialog.STREET_TYPEIDComboBox.Text;
        }

        protected override void vafterEdit() 
        {
            dataGridView.CurrentRow.Cells["sTREETTYPENAMEDataGridViewTextBoxColumn"].Value = currentDialog.STREET_TYPEIDComboBox.Text;
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

