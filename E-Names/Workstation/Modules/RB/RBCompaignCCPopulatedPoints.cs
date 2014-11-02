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
    internal partial class RBCompaignCCPopulatedPoints : Workstation.Modules.A.ARContainComponent
    {
        Guid cOMPAIGNID;

        ARContainComponentDialogC2 currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.COMPAIGN_POPULATED_POINTSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGN_POPULATED_POINTSTableAdapter tableAdapter;

        public RBCompaignCCPopulatedPoints()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.COMPAIGN_POPULATED_POINTS;
            tableAdapter = this.cOMPAIGN_POPULATED_POINTSTableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNPOPULATEDPOINTSBindingSource;
            // ������������� �������
            currentDialog = new ARContainComponentDialogC2("������:","���������� �����:");

            // ��������� �������� ������
            setupChildElementsARCC(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (������), 
            //      BRANDFAMILYbindingSource(�������), 
            //      "BRANDFAMILY" (������ ���� �������), 
            //      "DESCR" (������� ������� ����, �������������� � �������), 
            //      "PMCODE" (��������, �������� (���� ������� �������)));

            setupComboBox(currentDialog.comboBox1, rEGIONBindingSource, "REGIONID", "NAME", "REGIONID");
            currentDialog.comboBox1.SelectedIndexChanged += new System.EventHandler(rEGION�omboBox_SelectedIndexChanged);

            setupComboBox(currentDialog.comboBox2, pOPULATEDPOINTSBindingSource, "POPULATED_POINTID", "NAME", "ID");

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
            rEGIONTableAdapter.Fill(this.phmkDataSet.REGION);
            pOPULATED_POINTSTableAdapter.FillByAll(this.phmkDataSet.POPULATED_POINTS);
        }

        private void rEGION�omboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (currentDialog.comboBox1.Focused)
            {
                pOPULATED_POINTSTableAdapter.FillBy(this.phmkDataSet.POPULATED_POINTS, Convert.ToInt16(currentDialog.comboBox1.SelectedValue));
                currentDialog.comboBox2.SelectedIndex = -1;
            }
       
        }

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
            pOPULATED_POINTSTableAdapter.FillByAll(this.phmkDataSet.POPULATED_POINTS);
        }
    }
}

