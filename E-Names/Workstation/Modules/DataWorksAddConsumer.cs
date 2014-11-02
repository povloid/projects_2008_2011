using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules
{
    public partial class DataWorksAddConsumer : Workstation.Modules.A.ADialog
    {
        public DataWorksAddConsumer()
        {
            InitializeComponent();

            dataWorkConsumer.findConsumerButton.Visible = false;
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);

            if (this.DialogResult == DialogResult.OK)
            {
                if (!dataWorkConsumer.checkForm() || !dataWorkConsumerMainInfo.checkForm())
                    e.Cancel = true;
            }
            else
                e.Cancel = false;
        }
    }
}
