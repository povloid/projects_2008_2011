using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBMobilePhoneBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBMobilePhoneBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.MOBILE_PHONE_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.MOBILE_PHONE_BLOCKINGTableAdapter tableAdapter;

        public RBMobilePhoneBlocking()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.MOBILE_PHONE_BLOCKING;
            tableAdapter = this.mOBILE_PHONE_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.mOBILEPHONEBLOCKINGBindingSource;
            // ������������� �������
            currentDialog = new RBMobilePhoneBlockingDialog();

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

