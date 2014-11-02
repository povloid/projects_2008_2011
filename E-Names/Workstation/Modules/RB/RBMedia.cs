using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBMedia : Workstation.Modules.A.AReferenceBook
    {
        RBMediaDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.MEDIADataTable dataTable;
        Workstation.phmkDataSetTableAdapters.MEDIATableAdapter tableAdapter;

        public RBMedia(System.Windows.Forms.BindingSource mEDIACLUSTERBindingSource)
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.MEDIA;
            tableAdapter = this.mEDIATableAdapter;
            // (3) --
            bindingSource = this.mEDIABindingSource;
            // ������������� �������
            currentDialog = new RBMediaDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            //setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.NAMETextBox, "NAME", 100, CharacterCasing.Normal);

            setupComboBox(currentDialog.MEDIA_CLUSTERIDComboBox, mEDIACLUSTERBindingSource, "MEDIA_CLUSTERID", "NAME", "ID");

            vrefresh();

        }

        protected override void vafterAdd()
        {
            dataGridView.CurrentRow.Cells["MEDIA_CLUSTERNAME"].Value = currentDialog.MEDIA_CLUSTERIDComboBox.Text;
        }

        protected override void vafterEdit()
        {
            dataGridView.CurrentRow.Cells["MEDIA_CLUSTERNAME"].Value = currentDialog.MEDIA_CLUSTERIDComboBox.Text;
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
