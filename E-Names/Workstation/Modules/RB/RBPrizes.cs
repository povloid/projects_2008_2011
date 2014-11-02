using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPrizes : Workstation.Modules.A.AReferenceBook
    {
        RBPrizesDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.PRIZESDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.PRIZESTableAdapter tableAdapter;

        public RBPrizes()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.PRIZES;
            tableAdapter = this.pRIZESTableAdapter;
            // (3) --
            bindingSource = this.pRIZESBindingSource;
            // ������������� �������
            currentDialog = new RBPrizesDialog();

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

