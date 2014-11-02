using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBRegion : Workstation.Modules.A.AReferenceBook
    {
        RBRegionDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.REGIONDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.REGIONTableAdapter tableAdapter;

        public RBRegion(System.Windows.Forms.BindingSource oBLASTBindingSource)
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.REGION;
            tableAdapter = this.rEGIONTableAdapter;
            // (3) --
            bindingSource = this.rEGIONBindingSource;
            // Инициализация диалога
            currentDialog = new RBRegionDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupNumericUpDown(currentDialog.REGIONIDNumericUpDown, "REGIONID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupComboBox(currentDialog.OBLASTIDComboBox, oBLASTBindingSource, "OBLASTID", "NAME", "OBLASTID");

            setupTextBox(currentDialog.NAMETextBox, "NAME", 50, CharacterCasing.Normal);

            vrefresh();

        }

        protected override void vafterAdd() 
        {
            //if (currentDialog.OBLASTIDComboBox.Text.Length > 0)
                dataGridView.CurrentRow.Cells["OBLNAME"].Value = currentDialog.OBLASTIDComboBox.Text;
        }

        protected override void vafterEdit() 
        {
            //if (currentDialog.OBLASTIDComboBox.Text.Length > 0)
                dataGridView.CurrentRow.Cells["OBLNAME"].Value = currentDialog.OBLASTIDComboBox.Text;
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

