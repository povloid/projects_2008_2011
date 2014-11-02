using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBPopulatedPointsDialog : Workstation.Modules.A.ADialog
    {
        public RBPopulatedPointsDialog()
        {
            InitializeComponent();
        }


        protected override void OnClosing(CancelEventArgs e)
        {

            //if (this.DialogResult == DialogResult.OK)
            //{
            //    if (NAMETextBox.TextLength == 0)
            //    {
            //        NAMETextBox.Focus();
            //        e.Cancel = true;
            //        return;
            //    }
            //}
            //else
                e.Cancel = false;
        }

    }
}

