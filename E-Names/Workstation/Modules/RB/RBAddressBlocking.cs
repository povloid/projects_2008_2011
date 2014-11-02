using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBAddressBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBAddressBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.ADDRESS_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.ADDRESS_BLOCKINGTableAdapter tableAdapter;

        public RBAddressBlocking()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.ADDRESS_BLOCKING;
            tableAdapter = this.aDDRESS_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.aDDRESSBLOCKINGBindingSource;
            // ������������� �������
            currentDialog = new RBAddressBlockingDialog();

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

