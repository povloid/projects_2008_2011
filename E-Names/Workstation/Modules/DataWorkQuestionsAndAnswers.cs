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
    public partial class DataWorkQuestionsAndAnswers : UserControl
    {
        private string connectionString = global::Workstation.Properties.Settings.Default.phmkConnectionString;
        private SqlConnection conn;

        // Проверочный селект - Наличие сведений по данному потребителю и кампании
        // Если да то вернет 1
        private string selectQueryCCD = string.Format("SELECT  1 "
                       + "FROM  CONSUMER_COMPAIGN_DATA "
                       + "WHERE ID=@ID AND CAMPAIGNID=@GOMPAIGNID");

        private string insertInToCQFCQueryFromCCD = string.Format("insert into CONSUMER_QUESTION_FOR_COMPAIGN "
                    + "select @ID as ID,"
                    + "@GOMPAIGNID as GOMPAIGNID,"
                    + "QUESTIONID,"
                    + "null as ANSWER, "
                    + "null as ALTANSWER "
                    + "from QUESTION_FOR_COMPAIGN "
                    + "WHERE GOMPAIGNID = @GOMPAIGNID "
                    + "AND QUESTIONID NOT IN (select QUESTIONID from CONSUMER_QUESTION_FOR_COMPAIGN "
                    + "WHERE GOMPAIGNID = @GOMPAIGNID and ID=@ID)");


        private SqlCommand selectCmdCCD;
        private SqlCommand insertCmdInToCQFCQueryFromCCD; 

        //Наличие сведений по данному потребителю и кампании
        private bool testCONSUMER_COMPAIGN_DATA(Guid consumerID, Guid compaignID)
        {
            bool result = false;

            selectCmdCCD.Parameters["@ID"].Value = consumerID;
            selectCmdCCD.Parameters["@GOMPAIGNID"].Value = compaignID;

            SqlDataReader dataReader = selectCmdCCD.ExecuteReader();

            if (dataReader.Read())
            {
                result = true;
            }
            else
            {
                result = false;
            }

            dataReader.Close();

            return result;
        }


        public DataWorkQuestionsAndAnswers()
        {
            InitializeComponent();



            // Настройка соединения
            conn = new SqlConnection(connectionString);
            conn.Open();

            // Подготавливаем  SELECT
            selectCmdCCD = new SqlCommand(selectQueryCCD, conn);

           

            SqlParameter param = new SqlParameter();
            param.ParameterName = "@ID";
            param.SqlDbType = SqlDbType.UniqueIdentifier;
            selectCmdCCD.Parameters.Add(param);


            param = new SqlParameter();
            param.ParameterName = "@GOMPAIGNID";
            param.SqlDbType = SqlDbType.UniqueIdentifier;
            selectCmdCCD.Parameters.Add(param);


            // Подготавливаем второй запрос
            insertCmdInToCQFCQueryFromCCD = new SqlCommand(insertInToCQFCQueryFromCCD, conn);

            param = new SqlParameter();
            param.ParameterName = "@ID";
            param.SqlDbType = SqlDbType.UniqueIdentifier;
            insertCmdInToCQFCQueryFromCCD.Parameters.Add(param);

            param = new SqlParameter();
            param.ParameterName = "@GOMPAIGNID";
            param.SqlDbType = SqlDbType.UniqueIdentifier;
            insertCmdInToCQFCQueryFromCCD.Parameters.Add(param);


            

        }

        //private void cONSUMER_QUESTION_FOR_COMPAIGNBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        //{
        //    this.Validate();
        //    this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource.EndEdit();
        //    this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter.Update(this.phmkDataSet.CONSUMER_QUESTION_FOR_COMPAIGN);

        //}

        //private void fillToolStripButton_Click(object sender, EventArgs e)
        //{
        //    try
        //    {
        //        this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter.Fill(this.phmkDataSet.CONSUMER_QUESTION_FOR_COMPAIGN, ((int)(System.Convert.ChangeType(qUESTIONIDToolStripTextBox.Text, typeof(int)))), new System.Guid(gOMPAIGNIDToolStripTextBox.Text), new System.Guid(iDToolStripTextBox.Text));
        //    }
        //    catch (System.Exception ex)
        //    {
        //        System.Windows.Forms.MessageBox.Show(ex.Message);
        //    }

        //}

        public void selectAndShowInfoForCurrentCompaign( Guid consumerID , Guid compaignID )
        {
            if (testCONSUMER_COMPAIGN_DATA(consumerID, compaignID))
            {
                // Если появились новые вопросы то дозакидываем их
                insertCmdInToCQFCQueryFromCCD.Parameters["@ID"].Value = consumerID;
                insertCmdInToCQFCQueryFromCCD.Parameters["@GOMPAIGNID"].Value = compaignID;

                //MessageBox.Show("ID - " + consumerID.ToString()
                //     + "\nCOMPAIGNID - " + compaignID.ToString(),
                //           "Информация к размышлению", MessageBoxButtons.OK, MessageBoxIcon.Information);

                insertCmdInToCQFCQueryFromCCD.ExecuteNonQuery();

                this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter.Fill(this.phmkDataSet.CONSUMER_QUESTION_FOR_COMPAIGN,
                                                                    compaignID, consumerID);

            }
            else
            {
                clearAllElements();
            }

        }


        public void clearAllElements()
        {
            this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter.Fill(this.phmkDataSet.CONSUMER_QUESTION_FOR_COMPAIGN,
                                                                    new System.Guid("00000000000000000000000000000000"),
                                                                    new System.Guid("00000000000000000000000000000000"));
            aNSWERS_DWTableAdapter.Fill(phmkDataSet.ANSWERS_DW, -1);
        }

        // Выбор нужного вопроса
        private void cONSUMER_QUESTION_FOR_COMPAIGNDataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            // Необхобдимо обновить ответник

            //;

            if (cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.CurrentRow != null)
            {
                //try
                //{
                aNSWERS_DWTableAdapter.Fill(phmkDataSet.ANSWERS_DW, (int)cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.CurrentRow.Cells["dataGridViewTextBoxColumn3"].Value);
                aNSWERComboBox.SelectedValue = cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.CurrentRow.Cells["dataGridViewTextBoxColumn4"].Value;
                //} catch ()
            }
        }

        private void aNSWERComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            //if (aNSWERComboBox.Focused)
                
        }

        private void saveButton_Click(object sender, EventArgs e)
        {
            if (cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.RowCount > 0)
                cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.CurrentRow.Cells["dataGridViewTextBoxColumn8"].Value = aNSWERComboBox.Text;
        }

        public void save()
        {
            if (cONSUMER_QUESTION_FOR_COMPAIGNDataGridView.RowCount > 0)
            {
                this.Validate();
                this.cONSUMER_QUESTION_FOR_COMPAIGNBindingSource.EndEdit();
                this.cONSUMER_QUESTION_FOR_COMPAIGNTableAdapter.Update(this.phmkDataSet.CONSUMER_QUESTION_FOR_COMPAIGN);
            }
        }
    }
}
