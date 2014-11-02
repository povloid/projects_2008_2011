using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBThicknessCategory : Workstation.Modules.A.AReferenceBook
    {
        RBThicknessCategoryDialog currentDialog;

        public RBThicknessCategory()
        {
            InitializeComponent();

            currentDialog = new RBThicknessCategoryDialog();

            setupChildElements(this.phmkDataSet.THICKNESS_CATEGORY, this.tHICKNESSCATEGORYBindingSource, this.dataGridView, currentDialog);

            setupTextBox(currentDialog.CATEGORYTextBox, "CATEGORY", 20, CharacterCasing.Upper);
            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);

            vrefresh();

        }

        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            this.tHICKNESS_CATEGORYTableAdapter.Fill(this.phmkDataSet.THICKNESS_CATEGORY);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            this.tHICKNESS_CATEGORYTableAdapter.Update(this.phmkDataSet.THICKNESS_CATEGORY);
        }
    }
}
