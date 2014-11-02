using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Workstation.Modules.A;

namespace Workstation.Modules.RB
{
    internal partial class RBCompaignCCMedia : Workstation.Modules.A.ARContainComponent
    {

        Guid cOMPAIGNID;

        ARContainComponentDialogC1 currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.COMPAIGN_MEDIADataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGN_MEDIATableAdapter tableAdapter;

        //BindingSource mEDIABindingSource;

        public RBCompaignCCMedia()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.COMPAIGN_MEDIA;
            tableAdapter = this.cOMPAIGN_MEDIATableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNMEDIABindingSource;
            // Инициализация диалога
            currentDialog = new ARContainComponentDialogC1("Кампания:");

            // Настройка базового класса
            setupChildElementsARCC(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (Виджет), 
            //      BRANDFAMILYbindingSource(Внешний), 
            //      "BRANDFAMILY" (Рабоче поле текущее), 
            //      "DESCR" (Внешней таблици поле, отображающееся в виджете), 
            //      "PMCODE" (Значение, реальное (поле внешней таблици)));

            setupComboBox(currentDialog.comboBox, mEDIABindingSource, "MEDIAID", "NAME", "ID");

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["cOMPAIGNIDDataGridViewTextBoxColumn"].Value = cOMPAIGNID;
        }


        // Реализация полного сброса последних изменений и обновления
        public void vrefresh(Guid cOMPAIGNID)
        {
            this.cOMPAIGNID = cOMPAIGNID;
            vrefresh();
        }

        protected override void vrefresh()
        {
            tableAdapter.Fill(dataTable, cOMPAIGNID);
            mEDIATableAdapter.Fill(this.phmkDataSet.MEDIA);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }
    }
}
