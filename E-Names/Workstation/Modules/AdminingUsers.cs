using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules
{
    public partial class AdminingUsers : UserControl
    {
        public AdminingUsers()
        {
            InitializeComponent();
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button5_Click(object sender, EventArgs e)
        {
            AdminingUsersInfo d = new AdminingUsersInfo();

            d.ShowDialog();
            d.Dispose();
        }
    }
}
