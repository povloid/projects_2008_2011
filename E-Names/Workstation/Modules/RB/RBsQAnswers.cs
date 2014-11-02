using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBsQAnswers : Workstation.Modules.A.AReferenceBook
    {
        RBsQAnswersDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.ANSWERSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.ANSWERSTableAdapter tableAdapter;

        int vQUESTIONID = 0;
        string vQUESTIONNAME = "...";

        public RBsQAnswers()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.ANSWERS;
            tableAdapter = this.aNSWERSTableAdapter;
            // (3) --
            bindingSource = this.aNSWERSBindingSource;
            // ������������� �������
            currentDialog = new RBsQAnswersDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupTextBox(currentDialog.ANSWERTextBox, "QUESTIONID", 50, CharacterCasing.Normal);

            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.ANSWERTextBox, "ANSWER", 50, CharacterCasing.Normal);

            vrefresh();

        }


        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["QUESTIONID"].Value = vQUESTIONID;
        }

        protected override void vafterEdit()
        {
            dataGridView.CurrentRow.Cells["QUESTIONID"].Value = vQUESTIONID;
        }

        // ���������� ������� ������ ��������� ��������� � ����������
        protected override void vrefresh()
        {
            //tableAdapter.Fill(dataTable);
            tableAdapter.Fill(dataTable, vQUESTIONID);
        }

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }

        // ���������� �� ������� �� ������ �������
        public void updateParam( int id , string s)
        {
            vQUESTIONID = id;
            vQUESTIONNAME = s;

            currentDialog.QUESTIONIDLabel.Text = id.ToString();
            currentDialog.QUESTIONNAMELabel.Text = s;

            vrefresh();
        }
    }
}

