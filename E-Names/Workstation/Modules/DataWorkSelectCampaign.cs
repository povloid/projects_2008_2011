using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules
{
    public partial class DataWorkSelectCampaign : Workstation.Modules.A.ADialog
    {

        private Guid id;

        public DataWorkSelectCampaign()
        {
            InitializeComponent();
        }

        private void DataWorkSelectCampaign_Load(object sender, EventArgs e)
        {
            
            this.cOMPAIGNTableAdapter.Fill(this.phmkDataSet.COMPAIGN);

        }

        // Выбор потребителя в таблице
        private void dataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            if (dataGridView.RowCount > 0)
                try
                {
                    id = (Guid)dataGridView.CurrentRow.Cells["iDDataGridViewTextBoxColumn"].Value;

                    DESCRIPTIONTextBox.Text = (string)dataGridView.CurrentRow.Cells["dESCRIPTIONDataGridViewTextBoxColumn"].Value;

                }
                catch (FormatException)
                { }
                catch (NullReferenceException)
                { }
                catch (InvalidCastException)
                { }
                
        }

        public Guid getID()
        {
            return id;
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);

            e.Cancel = false;
        }

        public string getCompaignName()
        {
            return (string)dataGridView.CurrentRow.Cells["cAMPAIGNNAMEDataGridViewTextBoxColumn"].Value;
        }

        public string getCompaignDescription()
        {
            return (string)dataGridView.CurrentRow.Cells["dESCRIPTIONDataGridViewTextBoxColumn"].Value;
        }

    }
}

