using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBStreetType : Workstation.Modules.A.AReferenceBook
    {
        RBStreetTypeDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.STREET_TYPEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.STREET_TYPETableAdapter tableAdapter;

        public RBStreetType()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.STREET_TYPE;
            tableAdapter = this.sTREET_TYPETableAdapter;
            // (3) --
            bindingSource = this.sTREETTYPEBindingSource;
            // ������������� �������
            currentDialog = new RBStreetTypeDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupNumericUpDown(currentDialog.STREETTYPEIDNumericUpDown, "STREETTYPEID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.STREETTYPENAMETextBox, "STREETTYPENAME", 50, CharacterCasing.Normal);

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

