using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBLoyalty : Workstation.Modules.A.AReferenceBook
    {
        RBLoyaltyDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.LOYALTYDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.LOYALTYTableAdapter tableAdapter;
        
        public RBLoyalty()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.LOYALTY;
            tableAdapter = this.lOYALTYTableAdapter;
            // (3) --
            bindingSource = this.lOYALTYBindingSource;
            // ������������� �������
            currentDialog = new RBLoyaltyDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);

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

