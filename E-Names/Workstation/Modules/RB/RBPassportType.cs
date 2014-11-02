using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPassportType : Workstation.Modules.A.AReferenceBook
    {
        RBPassportTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.PASSPORT_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.PASSPORT_TYPETableAdapter tableAdapter;

        public RBPassportType()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.PASSPORT_TYPE;
            tableAdapter = this.pASSPORT_TYPETableAdapter;
            // (3) --
            bindingSource = this.pASSPORTTYPEBindingSource;
            // ������������� �������
            currentDialog = new RBPassportTypeDialog();

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

