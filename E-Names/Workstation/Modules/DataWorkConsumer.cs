using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Workstation.Modules
{
    public partial class DataWorkConsumer : UserControl
    {
        private string connectionString = global::Workstation.Properties.Settings.Default.phmkConnectionString;
        private SqlConnection conn;

        private Guid currentConsumerID;

        private string selectQuery = string.Format("SELECT  ID, "
                        + "GENDER, FIRST_NAME, MIDDLE_NAME, LAST_NAME, TYPE_OF_ID_CARD, "
                        + "ID_CARD_NUMBER, RNN, DATE_OF_BIRTH  FROM  CONSUMER "
                        + "WHERE ID=@ID");

        private SqlCommand selectCmd;

        // ����������� ��������
        private DataWorkConsumerMainInfo dataWorkConsumerMainInfo = null;
        private DataWorkCompaignData dataWorkCompaignData = null;

        public void setDataWorkConsumerMainInfo(DataWorkConsumerMainInfo dataWorkConsumerMainInfo)
        {
            this.dataWorkConsumerMainInfo = dataWorkConsumerMainInfo;
        }

        public void setDataWorkCompaignData(DataWorkCompaignData dataWorkCompaignData)
        {
            this.dataWorkCompaignData = dataWorkCompaignData;
        }
        // ..


        public DataWorkConsumer()
        {
            InitializeComponent();


            // ��������� ����������
            conn = new SqlConnection(connectionString);
            conn.Open();

            // �������������� INSERT
            selectCmd = new SqlCommand(selectQuery, conn);

            SqlParameter param = new SqlParameter();
            param.ParameterName = "@ID";
            param.SqlDbType = SqlDbType.UniqueIdentifier;
            selectCmd.Parameters.Add(param);

            // �������� ������
            refreshDataElements();
        }

        // ����� ������� �������
        private void findConsumerButton_Click(object sender, EventArgs e)
        {
            DataWorkFindConsumer dialog = new DataWorkFindConsumer(
                iD_CARD_NUMBERMTBox.Text,
                rNNMTBox.Text,
                fIRST_NAMETextBox.Text,
                mIDDLE_NAMETextBox.Text,
                lAST_NAMETextBox.Text );


            if (dialog.ShowDialog() == DialogResult.OK)
            {
                updateForConsumerID(dialog.getID());
            }

            dialog.Dispose();
        }


        public void updateForConsumerID(Guid id)
        {
            currentConsumerID = id;

            // �������� �������� ������
            refreshDataElements();

            selectCmd.Parameters["@ID"].Value = currentConsumerID;

            SqlDataReader dataReader = selectCmd.ExecuteReader();



            if (dataReader.Read())
            {
                pASSPORTTYPEComboBox.SelectedValue = dataReader.GetValue(5);
                iD_CARD_NUMBERMTBox.Text = dataReader.GetString(6);
                rNNMTBox.Text = dataReader.GetString(7);

                fIRST_NAMETextBox.Text = dataReader.GetString(2);
                mIDDLE_NAMETextBox.Text = dataReader.GetString(3);
                lAST_NAMETextBox.Text = dataReader.GetString(4);

                gENDERComboBox.SelectedIndex = Convert.ToInt16(dataReader.GetString(1));


                dATE_OF_BIRTHDateTimePicker.Value = dataReader.GetDateTime(8);

                
            }
            else
                clearAllInputElements();

            if (dataWorkConsumerMainInfo != null)
                dataWorkConsumerMainInfo.updateForConsumerID(currentConsumerID);

            if (dataWorkCompaignData != null)
                dataWorkCompaignData.updateForConsumerID(currentConsumerID);



            dataReader.Close();
        }

        public void clearAllInputElements()
        {
            pASSPORTTYPEComboBox.SelectedValue = -1;
            iD_CARD_NUMBERMTBox.Text = "";
            rNNMTBox.Text = "";

            fIRST_NAMETextBox.Text = "";
            mIDDLE_NAMETextBox.Text = "";
            lAST_NAMETextBox.Text = "";

            gENDERComboBox.SelectedIndex = -1;


            dATE_OF_BIRTHDateTimePicker.Value = DateTime.Today;

            currentConsumerID = new Guid("00000000-0000-0000-0000-000000000000");

            //if (dataWorkConsumerMainInfo != null)
            //    dataWorkConsumerMainInfo.clearAllInputElements();
        }

        public Guid getID()
        {
            return currentConsumerID;
        }

        public void setID(Guid currentConsumerID)
        {
            this.currentConsumerID = currentConsumerID;
        }

        //�������� �������� ������
        public void refreshDataElements()
        {
            pASSPORT_TYPETableAdapter.Fill(phmkDataSet.PASSPORT_TYPE);
            pASSPORTTYPEComboBox.SelectedIndex = -1;
        }

        // �������� ������������ ���������� �����
        public Boolean checkForm()
        {
            //// �������� ������������ ����� ������ ���������
            //if (iD_CARD_NUMBERMTBox.Text == "")
            //{
            //    MessageBox.Show("����� ��������� ������� ���� ������!", "��������!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    iD_CARD_NUMBERMTBox.Focus();
            //    return false;
            //}
            
            //// �������� ������������ ����� ���� ���������
            //if (pASSPORTTYPEComboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("�������� ��� ���������!", "��������!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    pASSPORTTYPEComboBox.Focus();
            //    return false;
            //}

            //// �������� ������������ ����� ������ ���
            //if (rNNMTBox.Text == "")
            //{
            //    MessageBox.Show("����� ��� ������� ���� ������!", "��������!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    rNNMTBox.Focus();
            //    return false;
            //}

            // �������� ������������ ����� ����� �����������
            if (fIRST_NAMETextBox.Text == "")
            {
                MessageBox.Show("��� ����������� ������� ���� ������!", "��������!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                fIRST_NAMETextBox.Focus();
                return false;
            }

            // �������� ������������ ����� ������� �����������
            if (mIDDLE_NAMETextBox.Text == "")
            {
                MessageBox.Show("������� ����������� ������� ���� ������!", "��������!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                mIDDLE_NAMETextBox.Focus();
                return false;
            }

            //// �������� ������������ ����� �������� �����������
            //if (lAST_NAMETextBox.Text == "")
            //{
            //    MessageBox.Show("�������� ����������� ������� ���� ������!", "��������!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    lAST_NAMETextBox.Focus();
            //    return false;
            //}

            // �������� ������������ ����� ���� �����������
            if (gENDERComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("�������� ��� �����������!", "��������!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                gENDERComboBox.Focus();
                return false;
            }

            return true;
        }
    }
}
