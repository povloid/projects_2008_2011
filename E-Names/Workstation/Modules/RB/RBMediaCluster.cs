using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBMediaCluster : Workstation.Modules.A.AReferenceBook
    {
        RBMediaClusterDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.MEDIA_CLUSTERDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.MEDIA_CLUSTERTableAdapter tableAdapter;

        public RBMediaCluster()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.MEDIA_CLUSTER;
            tableAdapter = this.mEDIA_CLUSTERTableAdapter;
            // (3) --
            bindingSource = this.mEDIACLUSTERBindingSource;
            // ������������� �������
            currentDialog = new RBMediaClusterDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.NAMETextBox, "NAME", 30, CharacterCasing.Normal);
            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 100, CharacterCasing.Normal);

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

