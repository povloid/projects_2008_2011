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
    public partial class RBBrandFamily : Workstation.Modules.A.AReferenceBook
    {
        RBBrandFamilyDialog currentDialog;

        public RBBrandFamily()
        {
            InitializeComponent();

            currentDialog = new RBBrandFamilyDialog();

            setupChildElements(this.phmkDataSet.BRAND_FAMILY, this.bRANDFAMILYBindingSource, this.dataGridView, currentDialog);

            currentDialog.PMCODETextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.bRANDFAMILYBindingSource, "PMCODE", true));
            currentDialog.PMCODETextBox.MaxLength = 20;
            currentDialog.PMCODETextBox.CharacterCasing = CharacterCasing.Upper;

            currentDialog.DESCRTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.bRANDFAMILYBindingSource, "DESCR", true));
            currentDialog.DESCRTextBox.MaxLength = 50;


            vrefresh();

        }


        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            this.bRAND_FAMILYTableAdapter.Fill(this.phmkDataSet.BRAND_FAMILY);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            this.bRAND_FAMILYTableAdapter.Update(this.phmkDataSet.BRAND_FAMILY);
        }

       

    }
}
