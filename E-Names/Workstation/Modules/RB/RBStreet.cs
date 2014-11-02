using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBStreet : Workstation.Modules.A.AReferenceBook
    {
        RBStreetDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.STREETDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.STREETTableAdapter tableAdapter;

        public RBStreet(System.Windows.Forms.BindingSource sTREETTYPEBindingSource)
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.STREET;
            tableAdapter = this.sTREETTableAdapter;
            // (3) --
            bindingSource = this.sTREETBindingSource;
            // ������������� �������
            currentDialog = new RBStreetDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupComboBox(currentDialog.STREET_TYPEIDComboBox, sTREETTYPEBindingSource, "STREET_TYPEID", "STREETTYPENAME", "STREETTYPEID");

            setupTextBox(currentDialog.NAMETextBox, "NAME", 50, CharacterCasing.Normal);

            vrefresh();
        }

        protected override void vafterAdd() 
        {
            dataGridView.CurrentRow.Cells["sTREETTYPENAMEDataGridViewTextBoxColumn"].Value = currentDialog.STREET_TYPEIDComboBox.Text;
        }

        protected override void vafterEdit() 
        {
            dataGridView.CurrentRow.Cells["sTREETTYPENAMEDataGridViewTextBoxColumn"].Value = currentDialog.STREET_TYPEIDComboBox.Text;
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

