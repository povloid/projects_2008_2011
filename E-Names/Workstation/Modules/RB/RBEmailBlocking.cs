using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBEmailBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBEmailBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.EMAIL_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.EMAIL_BLOCKINGTableAdapter tableAdapter;

        public RBEmailBlocking()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.EMAIL_BLOCKING;
            tableAdapter = this.eMAIL_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.eMAILBLOCKINGBindingSource;
            // ������������� �������
            currentDialog = new RBEmailBlockingDialog();

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

