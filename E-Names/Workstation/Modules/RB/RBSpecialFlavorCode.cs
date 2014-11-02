using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Workstation.Modules.RB;

namespace Workstation.Modules.RB
{
    public partial class RBSpecialFlavorCode : Workstation.Modules.A.AReferenceBook
    {
        RBSpecialFlavorCodeDialog currentDialog;

        public RBSpecialFlavorCode()
        {
            InitializeComponent();

            currentDialog = new RBSpecialFlavorCodeDialog();

            setupChildElements(this.phmkDataSet.SPECIAL_FLAVOR_CODE, this.sPECIALFLAVORCODEBindingSource, this.dataGridView, currentDialog);

            setupTextBox(currentDialog.CODETextBox, "CODE", 20, CharacterCasing.Upper);
            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);



            vrefresh();
        }

        // ���������� ������� ������ ��������� ��������� � ����������
        protected override void vrefresh()
        {
            this.sPECIAL_FLAVOR_CODETableAdapter.Fill(this.phmkDataSet.SPECIAL_FLAVOR_CODE);
        }

        // ���������� ������������� ���������
        public override void vvupdate()
        {
            this.sPECIAL_FLAVOR_CODETableAdapter.Update(this.phmkDataSet.SPECIAL_FLAVOR_CODE);
        }
    }
}
