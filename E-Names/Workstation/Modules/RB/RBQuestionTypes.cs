using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBQuestionTypes : Workstation.Modules.A.AReferenceBook
    {
        RBQuestionTypesDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.QUESTION_TYPESDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.QUESTION_TYPESTableAdapter tableAdapter;

        public RBQuestionTypes()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.QUESTION_TYPES;
            tableAdapter = this.qUESTION_TYPESTableAdapter;
            // (3) --
            bindingSource = this.qUESTIONTYPESBindingSource;
            // ������������� �������
            currentDialog = new RBQuestionTypesDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.NAMETextBox, "NAME", 50, CharacterCasing.Normal);

            vrefresh();
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
    }
}

