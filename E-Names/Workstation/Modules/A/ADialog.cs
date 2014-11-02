using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.A
{
    public partial class ADialog : Form
    {
        protected List<Control> orderedControls;
        protected bool isControlsSetuped = false;

        public ADialog()
        {
            InitializeComponent();

            orderedControls = new List<Control>();
        }

        // Установка фокуса на первый элемент ввода
        private void ADialog_Activated(object sender, EventArgs e)
        {
            
        }


        // текст в заголовке окна
        [Localizable(true)]
        [DefaultValue("The window caption text")]
        public string windowCaptionText
        {
            get { return Text; }
            set { Text = value; }

        }

        // capPictureBox
        public Image pictureInHead        
        {
            get { return capPictureBox.Image; }
            set { capPictureBox.Image = value; }

        }

        // Текст в заголовке
        [Localizable(true)]
        [DefaultValue("The text in head")]
        public string textInHead
        {
            get { return capLabel.Text; }
            set { capLabel.Text = value; }

        }


        protected override void OnShown(EventArgs e)
        {
            base.OnShown(e);

            if (groupBox.Controls.Count > 0)
            {
                if (!isControlsSetuped) // Упорядочиваем только один раз
                {
                    Control c = groupBox.Controls[0];

                    for (int i = 0; i < groupBox.Controls.Count; i++)
                    {
                        if ((groupBox.Controls[i].Location.X < c.Location.X
                            || groupBox.Controls[i].Location.Y < c.Location.Y)
                            && (groupBox.Controls[i] is TextBox
                                || groupBox.Controls[i] is ComboBox
                                || groupBox.Controls[i] is CheckBox
                                || groupBox.Controls[i] is NumericUpDown))
                            c = groupBox.Controls[i];
                    }
                    orderedControls.Add(c);

                    Control ct = c;
                    bool looped = true;
                    while (looped)
                    {
                        c = this.GetNextControl(ct, true);

                        if (c != orderedControls[0])
                        {
                            if (c is TextBox
                               || c is ComboBox
                               || c is CheckBox
                               || c is NumericUpDown)
                                orderedControls.Add(c);

                            ct = c;
                        }
                        else
                            looped = false;
                    }

                    isControlsSetuped = true;

                 
                }
                
                // Установка фокуса на первый элемент ввода
                orderedControls[0].Focus();
            }
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);


            if (this.DialogResult == DialogResult.OK)
            {
                for (int i = 0; i < orderedControls.Count; i++)
                {

                    if (orderedControls[i] is TextBox)
                        if (((TextBox)orderedControls[i]).TextLength == 0)
                        {
                            orderedControls[i].Focus();
                            e.Cancel = true;
                            return;
                        }

                    if (orderedControls[i] is ComboBox)
                        if (((ComboBox)orderedControls[i]).SelectedIndex == -1)
                        {
                            orderedControls[i].Focus();
                            e.Cancel = true;
                            return;
                        }

                    // Закоментировано потомучто хотят вводить записи с нулевым кодом
                    //if (orderedControls[i] is NumericUpDown)
                    //    if (((NumericUpDown)orderedControls[i]).Value == 0)
                    //    {
                    //        orderedControls[i].Focus();
                    //        e.Cancel = true;
                    //        return;
                    //    }

                    if (orderedControls[i] is NumericUpDown)
                        if (((NumericUpDown)orderedControls[i]).Value == 0)
                        {
                            ((NumericUpDown)orderedControls[i]).Value = 1;
                            ((NumericUpDown)orderedControls[i]).Value = 0;
                        }


                    if (orderedControls[i] is CheckBox)
                        if (((CheckBox)orderedControls[i]).CheckState == CheckState.Indeterminate)
                        {
                            orderedControls[i].Focus();
                            e.Cancel = true;
                            return;
                        }

                    orderedControls[i].Focus(); // Выглядит глупо, но надо для борьбы с глюком NumericUpDown
                }
            }
            else
                e.Cancel = false;
        }
 
    }
}