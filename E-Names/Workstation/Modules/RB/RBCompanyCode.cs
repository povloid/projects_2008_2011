using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Workstation.Modules.RB;

namespace Workstation.Modules
{
    public partial class RBCompanyCode : Workstation.Modules.A.AReferenceBook
    {
        RBCompanyCodeDialog currentDialog;

        public RBCompanyCode()
        {
            InitializeComponent();

            currentDialog = new RBCompanyCodeDialog();

            setupChildElements(this.phmkDataSet.COMPANY_CODE, this.cOMPANYCODEBindingSource, this.dataGridView, currentDialog);

            setupTextBox(currentDialog.PMCODETextBox, "PMCODE", 20, CharacterCasing.Upper);
            setupTextBox(currentDialog.DESCRIPTIONTextBox, "DESCRIPTION", 50, CharacterCasing.Normal);

            vrefresh();
        }

        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            this.cOMPANY_CODETableAdapter.Fill(this.phmkDataSet.COMPANY_CODE);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            this.cOMPANY_CODETableAdapter.Update(this.phmkDataSet.COMPANY_CODE);
        }

        private void cOMPANY_CODEBindingSource_CurrentChanged(object sender, EventArgs e)
        {

        }
    }
}

