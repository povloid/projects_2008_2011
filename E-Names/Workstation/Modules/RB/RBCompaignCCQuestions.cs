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
    internal partial class RBCompaignCCQuestions : Workstation.Modules.A.ARContainComponent
    {
        Guid cOMPAIGNID;

        ARContainComponentDialogC1 currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.QUESTION_FOR_COMPAIGNDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.QUESTION_FOR_COMPAIGNTableAdapter tableAdapter;

        //BindingSource mEDIABindingSource;

        public RBCompaignCCQuestions()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.QUESTION_FOR_COMPAIGN;
            tableAdapter = this.qUESTION_FOR_COMPAIGNTableAdapter;
            // (3) --
            bindingSource = this.qUESTIONFORCOMPAIGNBindingSource;
            // Инициализация диалога
            currentDialog = new ARContainComponentDialogC1("Вопрос:");

            // Настройка базового класса
            setupChildElementsARCC(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (Виджет), 
            //      BRANDFAMILYbindingSource(Внешний), 
            //      "BRANDFAMILY" (Рабоче поле текущее), 
            //      "DESCR" (Внешней таблици поле, отображающееся в виджете), 
            //      "PMCODE" (Значение, реальное (поле внешней таблици)));

            setupComboBox(currentDialog.comboBox, qUESTIONSBindingSource, "QUESTIONID", "QUESTIONNAME", "QUESTIONID");

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["gOMPAIGNIDDataGridViewTextBoxColumn"].Value = cOMPAIGNID;
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
            qUESTIONSTableAdapter.Fill(this.phmkDataSet.QUESTIONS);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }
    }
}

