using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBOblast : Workstation.Modules.A.AReferenceBook
    {
        RBOblastDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.OBLASTDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.OBLASTTableAdapter tableAdapter;

        public RBOblast()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.OBLAST;
            tableAdapter = this.oBLASTTableAdapter;
            // (3) --
            bindingSource = this.oBLASTBindingSource;
            // ������������� �������
            currentDialog = new RBOblastDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupNumericUpDown(currentDialog.OBLASTIDNumericUpDown, "OBLASTID", new decimal(new int[] { 99999999, 0, 0, 0 }));

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

