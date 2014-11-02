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
    internal partial class RBCompaignCCPrizes : Workstation.Modules.A.ARContainComponent
    {
        Guid cOMPAIGNID;

        ARContainComponentDialogC1 currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.COMPAIGN_PRIZESDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGN_PRIZESTableAdapter tableAdapter;

        //BindingSource mEDIABindingSource;

        public RBCompaignCCPrizes()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.COMPAIGN_PRIZES;
            tableAdapter = this.cOMPAIGN_PRIZESTableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNPRIZESBindingSource;
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

            setupComboBox(currentDialog.comboBox, pRIZESBindingSource, "PRIZESID", "NAME", "ID");

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
            pRIZESTableAdapter.Fill(this.phmkDataSet.PRIZES);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }
    }
}

