using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules
{
    public partial class DataWorkFindConsumer : Workstation.Modules.A.ADialog
    {
        private Guid id;

        public DataWorkFindConsumer()
        {
            InitializeComponent();

            CARDMTBox.Enabled = false;
            RNNMTBox.Enabled = false;
            FNAMETextBox.Enabled = false;
            MNAMETextBox.Enabled = false;
            LNAMETextBox.Enabled = false;

            selectButton.Enabled = false;
        }

        public DataWorkFindConsumer(string sCARD, 
            string sRNN, 
            string sFNAME, 
            string sMNAME, 
            string sLNAME)
        {
            InitializeComponent();

            CARDMTBox.Text = sCARD;
            RNNMTBox.Text = sRNN;
            FNAMETextBox.Text = sFNAME;
            MNAMETextBox.Text = sMNAME;
            LNAMETextBox.Text = sLNAME;

            CARDMTBox.Enabled = false;
            RNNMTBox.Enabled = false;
            FNAMETextBox.Enabled = false;
            MNAMETextBox.Enabled = false;
            LNAMETextBox.Enabled = false;

            selectButton.Enabled = false;
        }



        private void radioButtons_CheckedChanged(object sender, EventArgs e)
        {
            if (sender is RadioButton)
            {
                selectButton.Enabled = true;

                CARDMTBox.Enabled = false;
                RNNMTBox.Enabled = false;
                FNAMETextBox.Enabled = false;
                MNAMETextBox.Enabled = false;
                LNAMETextBox.Enabled = false;

                RadioButton rb = (RadioButton)sender;

                if (rb.Name == "forDocRadioButton")
                {
                    CARDMTBox.Enabled = true;
                }
                else if (rb.Name == "forRNNRadioButton")
                {
                    RNNMTBox.Enabled = true;
                }
                else if (rb.Name == "forNAMERadioButton")
                {
                    FNAMETextBox.Enabled = true;
                    MNAMETextBox.Enabled = true;
                    LNAMETextBox.Enabled = true;
                }
            }
        }


        // Выбор SELECT ***
        private void selectButton_Click(object sender, EventArgs e)
        {
            if (forDocRadioButton.Checked)
                cONSUMERTableAdapter.FillByCARD_NUMBER(phmkDataSet.CONSUMER, CARDMTBox.Text);
            else if (forRNNRadioButton.Checked)
                cONSUMERTableAdapter.FillByRNN(phmkDataSet.CONSUMER, RNNMTBox.Text);
            else if (forNAMERadioButton.Checked)
                cONSUMERTableAdapter.FillByFMLNAME(phmkDataSet.CONSUMER,
                    FNAMETextBox.Text,
                    MNAMETextBox.Text,
                    LNAMETextBox.Text);
        }

        private void DataWorkFindConsumer_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'phmkDataSet.CONSUMER' table. You can move, or remove it, as needed.
            //this.cONSUMERTableAdapter.Fill(this.phmkDataSet.CONSUMER);
        }


        // Выбор потребителя в таблице
        private void dataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            if (dataGridView.RowCount > 0 )
                try
                {
                    id = (Guid)dataGridView.CurrentRow.Cells["iDDataGridViewTextBoxColumn"].Value;
                }
                catch (FormatException)
                { }
                catch (NullReferenceException)
                { }
                catch (InvalidCastException)
                { }
                finally
                { }
        }

        public Guid getID()
        {
             return id;
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);
            if (this.DialogResult == DialogResult.OK)
            {
                // (!forDocRadioButton.Checked)
                if (!(forDocRadioButton.Checked || forRNNRadioButton.Checked || forNAMERadioButton.Checked))
                {
                    e.Cancel = true;
                    return;
                }
            }
            e.Cancel = false;
        }

        // Отчистить элементы ввода
        private void clearButton_Click(object sender, EventArgs e)
        {
            CARDMTBox.Text = "";
            RNNMTBox.Text = "";
            FNAMETextBox.Text = "";
            MNAMETextBox.Text = "";
            LNAMETextBox.Text = "";
        }

    }
}

