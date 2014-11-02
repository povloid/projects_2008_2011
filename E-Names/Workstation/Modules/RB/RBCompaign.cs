using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{

    public partial class RBCompaign : Workstation.Modules.A.AReferenceBook
    {
        RBCompaignDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.COMPAIGNDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter tableAdapter;

        public RBCompaign()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.COMPAIGN;
            tableAdapter = this.cOMPAIGNTableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNBindingSource;
            // ������������� �������
            currentDialog = new RBCompaignDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupTextBox(currentDialog.cAMPAIGNIDTextBox, "CAMPAIGNID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.cAMPAIGNCODETextBox, "CAMPAIGNCODE", 10, CharacterCasing.Upper);
            setupTextBox(currentDialog.cAMPAIGNNAMETextBox, "CAMPAIGNNAME", 100, CharacterCasing.Upper);
            setupTextBox(currentDialog.dESCRIPTIONTextBox, "DESCRIPTION", 255, CharacterCasing.Normal);

            setupDateTimePicker(currentDialog.sTARTDATEDateTimePicker, "STARTDATE", DateTimePickerFormat.Short);
            setupDateTimePicker(currentDialog.eNDDATEDateTimePicker, "ENDDATE", DateTimePickerFormat.Short);

            setupTextBox(currentDialog.aCTIONIDTextBox, "ACTIONID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.aCTIONNAMETextBox, "ACTIONNAME", 100, CharacterCasing.Normal);

            setupTextBox(currentDialog.vARIANTIDTextBox, "VARIANTID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.vARIANTTextBox, "VARIANT", 100, CharacterCasing.Normal);

            setupTextBox(currentDialog.tARGETGROUPIDTextBox, "TARGETGROUPID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.tARGETGROUPTextBox, "TARGETGROUP", 100, CharacterCasing.Normal);

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["ID"].Value = Guid.NewGuid();
        }

        // ���������� ������� ������ ��������� ��������� � ����������
        protected override void vrefresh()
        {
            tableAdapter.Fill(dataTable);
        }

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }

        private void dataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            try
            {
                rbCompaignCCMedia1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
                rbCompaignCCPrizes1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
                rbCompaignCCQuestions1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
                rbCompaignCCPopulatedPoints1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
            }
            catch (ArgumentException)
            {

            }
            catch (NullReferenceException)
            {

            }
            catch (InvalidCastException)
            {
                
            }
            catch (IndexOutOfRangeException)
            {

            }
        }
    }
}
