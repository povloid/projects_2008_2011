using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPhoneBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBPhoneBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.PHONE_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.PHONE_BLOCKINGTableAdapter tableAdapter;

        public RBPhoneBlocking()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.PHONE_BLOCKING;
            tableAdapter = this.pHONE_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.pHONEBLOCKINGBindingSource;
            // ������������� �������
            currentDialog = new RBPhoneBlockingDialog();

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

