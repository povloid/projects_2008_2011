using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBLengthCategory : Workstation.Modules.A.AReferenceBook
    {
        RBLengthCategoryDialog currentDialog;

        public RBLengthCategory()
        {
            InitializeComponent();

            currentDialog = new RBLengthCategoryDialog();

            setupChildElements(this.phmkDataSet.LENGTH_CATEGORY, this.lENGTHCATEGORYBindingSource, this.dataGridView, currentDialog);

            setupTextBox(currentDialog.CATEGORYTextBox, "CATEGORY", 20, CharacterCasing.Upper);
            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);

            vrefresh();

        }


        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            this.lENGTH_CATEGORYTableAdapter.Fill(this.phmkDataSet.LENGTH_CATEGORY);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            this.lENGTH_CATEGORYTableAdapter.Update(this.phmkDataSet.LENGTH_CATEGORY);
        }
    }
}

