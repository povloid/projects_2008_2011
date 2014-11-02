using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBMicrodistrict : Workstation.Modules.A.AReferenceBook
    {
        RBMicrodistrictDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.MICRODISTRICTDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.MICRODISTRICTTableAdapter tableAdapter;

        public RBMicrodistrict()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.MICRODISTRICT;
            tableAdapter = this.mICRODISTRICTTableAdapter;
            // (3) --
            bindingSource = this.mICRODISTRICTBindingSource;
            // ������������� �������
            currentDialog = new RBMicrodistrictDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView1, currentDialog);

            // (4) ��������� ��������� ����������
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

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

