using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBResponceChanels : Workstation.Modules.A.AReferenceBook
    {
        RBResponceChanelsDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.RESPONCE_CHANELSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.RESPONCE_CHANELSTableAdapter tableAdapter;

        public RBResponceChanels()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.RESPONCE_CHANELS;
            tableAdapter = this.rESPONCE_CHANELSTableAdapter;
            // (3) --
            bindingSource = this.rESPONCECHANELSBindingSource;
            // ������������� �������
            currentDialog = new RBResponceChanelsDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

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

