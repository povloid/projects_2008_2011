using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBSettlementType : Workstation.Modules.A.AReferenceBook
    {
        RBSettlementTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.SETTLEMENT_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.SETTLEMENT_TYPETableAdapter tableAdapter;

        public RBSettlementType()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.SETTLEMENT_TYPE;
            tableAdapter = this.sETTLEMENT_TYPETableAdapter;
            // (3) --
            bindingSource = this.sETTLEMENTTYPEBindingSource;
            // ������������� �������
            currentDialog = new RBSettlementTypeDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupNumericUpDown(currentDialog.SETTLEMENTTYPEIDNumericUpDown, "SETTLEMENTTYPEID", new decimal(new int[] { 99999999, 0, 0, 0 }));

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

