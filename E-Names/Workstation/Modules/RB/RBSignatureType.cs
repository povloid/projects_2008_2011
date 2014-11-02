using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBSignatureType : Workstation.Modules.A.AReferenceBook
    {
        RBSignatureTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.SIGNATURE_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.SIGNATURE_TYPETableAdapter tableAdapter;
        
        public RBSignatureType()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.SIGNATURE_TYPE;
            tableAdapter = this.sIGNATURE_TYPETableAdapter;
            // (3) --
            bindingSource = this.sIGNATURETYPEBindingSource;
            // ������������� �������
            currentDialog = new RBSignatureTypeDialog();

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

