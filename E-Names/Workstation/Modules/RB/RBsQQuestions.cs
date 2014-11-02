using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBsQQuestions : Workstation.Modules.A.AReferenceBook
    {
        RBsQAnswers rBsQAnswers = null;

        RBsQQuestionsDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.QUESTIONSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.QUESTIONSTableAdapter tableAdapter;

        public RBsQQuestions()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.QUESTIONS;
            tableAdapter = this.qUESTIONSTableAdapter;
            // (3) --
            bindingSource = this.qUESTIONSBindingSource;
            // ������������� �������
            currentDialog = new RBsQQuestionsDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupNumericUpDown(currentDialog.QUESTIONIDNumericUpDown, "QUESTIONID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.QUESTIONNAMETextBox, "QUESTIONNAME", 400, CharacterCasing.Normal);

            setupCheckBox(currentDialog.QUESTIONISNULLCheckBox, "QUESTIONISNULL");
            setupCheckBox(currentDialog.ISDEFAULTCheckBox, "ISDEFAULT");

            vrefresh();

        }

        public void setupComboBoxes(System.Windows.Forms.BindingSource qUESTIONTYPESBindingSource)
        {
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (������), 
            //      BRANDFAMILYbindingSource(�������), 
            //      "BRANDFAMILY" (������ ���� �������), 
            //      "DESCR" (������� ������� ����, �������������� � �������), 
            //      "PMCODE" (��������, �������� (���� ������� �������)));

            setupComboBox(currentDialog.QUESTIONTYPEComboBox, qUESTIONTYPESBindingSource, "QUESTIONTYPE", "NAME", "ID");
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


        public void setupRBsQAnswers(RBsQAnswers rBsQAnswers)
        {
            this.rBsQAnswers = rBsQAnswers;
        }

        private void dataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            updChildTable();
        }

        protected override void vafterAdd()
        {
            dataGridView.CurrentRow.Cells["QUESTIONTYPENAME"].Value = currentDialog.QUESTIONTYPEComboBox.Text;
            updChildTable();
        }

        protected override void vafterEdit()
        {
            dataGridView.CurrentRow.Cells["QUESTIONTYPENAME"].Value = currentDialog.QUESTIONTYPEComboBox.Text;
            updChildTable();
        }

        // ���������� ������� �������
        private void updChildTable()
        {

            int i = -1;
            string s = "...";

            try
            {
                i = (int)dataGridView.CurrentRow.Cells["qUESTIONIDDataGridViewTextBoxColumn"].Value;
                s = dataGridView.CurrentRow.Cells["qUESTIONNAMEDataGridViewTextBoxColumn"].Value.ToString();

                if (rBsQAnswers != null)
                    rBsQAnswers.updateParam(i, s);

            }
            catch (ArgumentException)
            {

            }
            catch (NullReferenceException)
            {

            }
            catch (InvalidCastException)
            {
                if (rBsQAnswers != null)
                    rBsQAnswers.updateParam(i, s);
            }
            catch (IndexOutOfRangeException)
            {
               
            }
        }
    }
}

