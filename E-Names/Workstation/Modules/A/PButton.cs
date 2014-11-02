using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace Workstation.Modules.PClasses 
{
    class PButton : System.Windows.Forms.Button
    {

        new private Size Size { get { return Size; } set { Size = value; } }

        public PButton()
        {
            this.MaximumSize = new System.Drawing.Size(90,26);
            this.MinimumSize = new System.Drawing.Size(90,26);
            this.ImageAlign = ContentAlignment.MiddleLeft;
        }
    }
}
