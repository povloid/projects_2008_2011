using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Workstation.Modules;


namespace Workstation
{
    public partial class MainForm : Form
    {

        Workstation.Modules.RB.ReferencesBooksControl referencesBooksControl;

        DataWork dataWork;

        YouAsUserInSystem youAsUserInSystem;
        YouAsUserYouWork youAsUserYouWork;
        YouAsUserYouAdditionalInfo youAsUserYouAdditionalInfo;
        YouAsUserYouHome youAsUserYouHome;
        YouAsUserYouFoto youAsUserYouFoto;

        AdminingUsers adminingUsers;
        AdminingRoles adminingRoles;
        
        public MainForm()
        {
            DoubleBuffered = true;

            InitializeComponent();

            dataWork = new DataWork();
            dataWork.Dock = DockStyle.Fill;

            referencesBooksControl = new Workstation.Modules.RB.ReferencesBooksControl();
            referencesBooksControl.Dock = DockStyle.Fill;

            youAsUserInSystem = new YouAsUserInSystem();
            youAsUserInSystem.Dock = DockStyle.Fill;

            youAsUserYouWork = new YouAsUserYouWork();
            youAsUserYouWork.Dock = DockStyle.Fill;

            youAsUserYouAdditionalInfo = new YouAsUserYouAdditionalInfo();
            youAsUserYouAdditionalInfo.Dock = DockStyle.Fill;

            youAsUserYouHome = new YouAsUserYouHome();
            youAsUserYouHome.Dock = DockStyle.Fill;

            youAsUserYouFoto = new YouAsUserYouFoto();
            youAsUserYouFoto.Dock = DockStyle.Fill;

            adminingUsers = new AdminingUsers();
            adminingUsers.Dock = DockStyle.Fill;

            adminingRoles = new AdminingRoles();
            adminingRoles.Dock = DockStyle.Fill;
            //userControl1 = new UserControl1();
            //userControl2 = new UserControl2();
            //s1 = new S1();

            //splitContainer1.Panel2.Controls.Add(userControl1);
            //NavBarLookAndFeelMenu menu = new NavBarLookAndFeelMenu(this, defaultLookAndFeel1, "Navigation Pane for the XtraNavBar by Developer Express inc.");
        }

        private void toolStripContainer1_TopToolStripPanel_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            
        }

        private void splitContainer1_Panel2_Paint(object sender, PaintEventArgs e)
        {

        }

        private void navBarControl1_Click(object sender, EventArgs e)
        {
            
        }
    
        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            //UserControl1 userControl1 = new UserControl1();

            //mainSplitContainer.Panel2.Controls.Add(userControl1);
        }

        private void navBarItem1_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            //mainSplitContainer.Panel2.Controls.Clear();
            //mainSplitContainer.Panel2.Controls.Add(userControl1);
        }

        private void navBarItem2_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            //mainSplitContainer.Panel2.Controls.Clear();
            //mainSplitContainer.Panel2.Controls.Add(s1);
            //s1.Dock = DockStyle.Fill;
        }

        private void fileToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void панельToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }


        // Справочники
        private void dataNavBarItem2_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(referencesBooksControl);
        }

       
        private void clearAndAddWidgetToPano(UserControl uc)
        {
            panoPanel.Controls.Clear();
            panoPanel.Controls.Add(uc);
                
        }

        private void userNavBarItem2_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(youAsUserYouWork);
            
        }

        private void userNavBarItem3_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(youAsUserYouAdditionalInfo);
            
        }

        private void userNavBarItem4_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(youAsUserYouHome);
            
        }

        private void userNavBarItem5_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(youAsUserYouFoto);
           
        }


        private void userNavBarItem1_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(youAsUserInSystem);
            
        }

        // Переключение на модуль администрирования пользователей
        private void adminNavBarItem1_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(adminingUsers);
            
        }
        // Переключение на модуль управления ролями
        private void adminNavBarItem2_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(adminingRoles);
           
        }

        // Скрытие и закрытие панели навигации
        private void vanvNPanelMenuItem_Click(object sender, EventArgs e)
        {
            if (vanvNPanelMenuItem.Checked)
                mainSplitContainer.Panel1Collapsed = true;
            else
                mainSplitContainer.Panel1Collapsed = false;

            vanvNPanelMenuItem.Checked = !vanvNPanelMenuItem.Checked;
        }

        
        // Автоматическая установка названия заголовка и иконки в заголовке
        private void mainNavBarControl_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            capPictureBox.Image = e.Link.Item.SmallImage;
            mainCapLabel.Text = e.Link.Item.Caption;
        }
        
        // Скрытие и закрытие панели статуса
        private void vanvToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (vanvToolStripMenuItem.Checked)
                statusStrip.Visible = false;
            else
                statusStrip.Visible = true;

            vanvToolStripMenuItem.Checked = !vanvToolStripMenuItem.Checked;
        }

        // Менюшные переходы
        private void вСистемеENamesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            
        }

        private void dataNavBarItem1_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            clearAndAddWidgetToPano(dataWork);
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Close();
        }

    }
}