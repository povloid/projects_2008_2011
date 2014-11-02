using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPopulatedPoints : Workstation.Modules.A.AReferenceBook
    {
        RBPopulatedPointsDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.POPULATED_POINTSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.POPULATED_POINTSTableAdapter tableAdapter;

        System.Windows.Forms.BindingSource rEGIONBindingSource;

        private int vOBLASTID = -1;

        public RBPopulatedPoints(System.Windows.Forms.BindingSource oBLASTBindingSource,
            System.Windows.Forms.BindingSource rEGIONBindingSource,
            System.Windows.Forms.BindingSource sETTLEMENTTYPEBindingSource)
        {
            this.rEGIONBindingSource = rEGIONBindingSource;
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.POPULATED_POINTS;
            tableAdapter = this.pOPULATED_POINTSTableAdapter;
            // (3) --
            bindingSource = this.pOPULATEDPOINTSBindingSource;
            // ������������� �������
            currentDialog = new RBPopulatedPointsDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupComboBox(currentDialog.REGIONIDComboBox, rEGIONBindingSource, "REGIONID", "NAME", "REGIONID");
            setupComboBox(currentDialog.SETTLEMENTTYPEIDComboBox, sETTLEMENTTYPEBindingSource, "SETTLEMENTTYPEID", "DESCR", "SETTLEMENTTYPEID");

            setupTextBox(currentDialog.NAMETextBox, "NAME", 30, CharacterCasing.Normal);
            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);

            // ����������� ��������� �� ��������
            setupComboBox(OBLAST�omboBox, oBLASTBindingSource, "NAME", "OBLASTID");

            vrefresh();

        }

        
        protected override void vafterAddShowDialog()
        {
            //currentDialog.ShowDialog();
            //MessageBox.Show(currentDialog.REGIONIDComboBox.Text , "��������� �������!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            dataGridView.CurrentRow.Cells["rEGIONNAMEDataGridViewTextBoxColumn"].Value = currentDialog.REGIONIDComboBox.Text;
            dataGridView.CurrentRow.Cells["sETTLEMENTTYPENAMEDataGridViewTextBoxColumn"].Value = currentDialog.SETTLEMENTTYPEIDComboBox.Text;
        }

        protected override void vafterEditShowDialog() 
        {   
            dataGridView.CurrentRow.Cells["rEGIONNAMEDataGridViewTextBoxColumn"].Value = currentDialog.REGIONIDComboBox.Text;
            dataGridView.CurrentRow.Cells["sETTLEMENTTYPENAMEDataGridViewTextBoxColumn"].Value = currentDialog.SETTLEMENTTYPEIDComboBox.Text;   
        }


        // ���������� ������� ������ ��������� ��������� � ����������
        protected override void vrefresh()
        {
            if (!allCheckBox.Checked)
            {
                try
                {
                    vOBLASTID = Convert.ToInt16(OBLAST�omboBox.SelectedValue);
                    rEGIONBindingSource.Filter = "OBLASTID='" + OBLAST�omboBox.SelectedValue.ToString() + "'";
                }
                catch (InvalidCastException)
                {
                    vOBLASTID = -1;
                    rEGIONBindingSource.Filter = "OBLASTID='-1'";
                }
                catch (NullReferenceException)
                {
                    vOBLASTID = -1;
                    rEGIONBindingSource.Filter = "OBLASTID='-1'";
                }

                label1.Text = vOBLASTID.ToString();

                tableAdapter.Fill(dataTable, vOBLASTID);
            }
            else
            {

                tableAdapter.FillByAll(dataTable);
            }
        }

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }

        private void OBLAST�omboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (OBLAST�omboBox.Focused)
                vrefresh();
        }

        private void allCheckBox_Click(object sender, EventArgs e)
        {
            vrefresh();


            OBLAST�omboBox.Enabled = !allCheckBox.Checked;
        }
    }
}

