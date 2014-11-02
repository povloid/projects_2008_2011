using System;
using System.Windows.Forms;

namespace Workstation
{
    public class NavBarLookAndFeelMenu : DevExpress.Utils.LookAndFeelMenu
    {
        public NavBarLookAndFeelMenu(Form frm, DevExpress.LookAndFeel.DefaultLookAndFeel lookAndFeel, string text)
            : base(frm, lookAndFeel, text)
        {
            //for (int i = 0; i < 5; i++)
            //    if (this.miLookAndFeel.MenuItems[i].Text.IndexOf("Office") == -1)
            //        this.miLookAndFeel.MenuItems[i].Visible = false;
        }
    }
}

