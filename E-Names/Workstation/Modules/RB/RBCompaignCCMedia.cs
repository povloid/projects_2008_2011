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

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.COMPAIGN_MEDIADataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGN_MEDIATableAdapter tableAdapter;

        //BindingSource mEDIABindingSource;

        public RBCompaignCCMedia()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.COMPAIGN_MEDIA;
            tableAdapter = this.cOMPAIGN_MEDIATableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNMEDIABindingSource;
            // ������������� �������
            currentDialog = new ARContainComponentDialogC1("��������:");

            // ��������� �������� ������
            setupChildElementsARCC(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (������), 
            //      BRANDFAMILYbindingSource(�������), 
            //      "BRANDFAMILY" (������ ���� �������), 
            //      "DESCR" (������� ������� ����, �������������� � �������), 
            //      "PMCODE" (��������, �������� (���� ������� �������)));

            setupComboBox(currentDialog.comboBox, mEDIABindingSource, "MEDIAID", "NAME", "ID");

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["cOMPAIGNIDDataGridViewTextBoxColumn"].Value = cOMPAIGNID;
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
            mEDIATableAdapter.Fill(this.phmkDataSet.MEDIA);
        }

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }
    }
}
