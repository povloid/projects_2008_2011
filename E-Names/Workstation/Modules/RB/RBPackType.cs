using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPackType : Workstation.Modules.A.AReferenceBook
    {
        RBPackTypeDialog currentDialog;

        public RBPackType()
        {
            InitializeComponent();

            currentDialog = new RBPackTypeDialog();

            setupChildElements(this.phmkDataSet.PACK_TYPE, this.pACKTYPEBindingSource, this.dataGridView, currentDialog);

            setupTextBox(currentDialog.TYPETextBox, "TYPE", 10, CharacterCasing.Upper);
            setupTextBox(currentDialog.DESCRTextBox, "DESCR", 50, CharacterCasing.Normal);

            vrefresh();

        }


        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            this.pACK_TYPETableAdapter.Fill(this.phmkDataSet.PACK_TYPE);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            this.pACK_TYPETableAdapter.Update(this.phmkDataSet.PACK_TYPE);
        }
    }
}

