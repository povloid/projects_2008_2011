using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.A
{
    public partial class ARContainComponentDialogC2 : Workstation.Modules.A.ADialog
    {
        public ARContainComponentDialogC2(string s1, string s2)
        {
            InitializeComponent();

            // Устанавливаем текст
            label1.Text = s1;
            // Устанавливаем текст
            label2.Text = s2;
        }
    }
}

