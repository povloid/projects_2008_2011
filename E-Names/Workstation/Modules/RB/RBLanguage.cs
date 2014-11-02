using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBLanguage : Workstation.Modules.A.AReferenceBook
    {
        RBLanguageDialog currentDialog;
        BindingSource bindingSource;

        // (1) ������� ��������� ������������ ����
        Workstation.phmkDataSet.LANGUAGEDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.LANGUAGETableAdapter tableAdapter;

        public RBLanguage()
        {
            InitializeComponent();

            // (2) ������������� ����������
            dataTable = this.phmkDataSet.LANGUAGE;
            tableAdapter = this.lANGUAGETableAdapter;
            // (3) --
            bindingSource = this.lANGUAGEBindingSource;
            // ������������� �������
            currentDialog = new RBLanguageDialog();

            // ��������� �������� ������
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) ��������� ��������� ����������
            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.LANGUAGECODETextBox, "LANGUAGECODE", 3, CharacterCasing.Upper);
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

