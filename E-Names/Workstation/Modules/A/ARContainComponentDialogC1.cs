using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.A
{
    public partial class ARContainComponentDialogC1 : Workstation.Modules.A.ADialog
    {
        public ARContainComponentDialogC1(string s)
        {
            InitializeComponent();

            // Устанавливаем текст
            label.Text = s;
        }
    }
}
