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

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.QUESTION_FOR_COMPAIGNDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.QUESTION_FOR_COMPAIGNTableAdapter tableAdapter;

        //BindingSource mEDIABindingSource;

        public RBCompaignCCQuestions()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.QUESTION_FOR_COMPAIGN;
            tableAdapter = this.qUESTION_FOR_COMPAIGNTableAdapter;
            // (3) --
            bindingSource = this.qUESTIONFORCOMPAIGNBindingSource;
            // ������������� �������
            currentDialog = new ARContainComponentDialogC1("������:");

            // ��������� �������� ������
            setupChildElementsARCC(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (������), 
            //      BRANDFAMILYbindingSource(�������), 
            //      "BRANDFAMILY" (������ ���� �������), 
            //      "DESCR" (������� ������� ����, �������������� � �������), 
            //      "PMCODE" (��������, �������� (���� ������� �������)));

            setupComboBox(currentDialog.comboBox, qUESTIONSBindingSource, "QUESTIONID", "QUESTIONNAME", "QUESTIONID");

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["gOMPAIGNIDDataGridViewTextBoxColumn"].Value = cOMPAIGNID;
        }


        // ���������� ������� ������ ��������� ��������� � ����������
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

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }
    }
}

