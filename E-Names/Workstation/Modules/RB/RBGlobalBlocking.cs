using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBGlobalBlocking : Workstation.Modules.A.AReferenceBook
    {
        RBGlobalBlockingDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.GLOBAL_BLOCKINGDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.GLOBAL_BLOCKINGTableAdapter tableAdapter;

        public RBGlobalBlocking()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.GLOBAL_BLOCKING;
            tableAdapter = this.gLOBAL_BLOCKINGTableAdapter;
            // (3) --
            bindingSource = this.gLOBALBLOCKINGBindingSource;
            // ������������� �������
            currentDialog = new RBGlobalBlockingDialog();

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

