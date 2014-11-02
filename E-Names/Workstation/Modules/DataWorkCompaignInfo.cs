using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Workstation.Modules
{
    public partial class DataWorkCompaignInfo : Form
    {
        private string connectionString = global::Workstation.Properties.Settings.Default.phmkConnectionString;
        private SqlConnection conn;

        private string selectQuery = string.Format("SELECT  DESCRIPTION, " 
                            + "STARTDATE, ENDDATE, "
                            + "CAMPAIGNCODE, CAMPAIGNNAME, CAMPAIGNID, " 
                            + "ACTIONID, VARIANTID, TARGETGROUPID, ACTIONNAME, "
                            + " VARIANT, TARGETGROUP, ID "
                            + "FROM COMPAIGN "
                            + "WHERE ID=@ID");

        private SqlCommand selectCmd;

        public DataWorkCompaignInfo()
        {
            InitializeComponent();

            // Настройка соединения
            conn = new SqlConnection(connectionString);
            conn.Open();

            // Подготавливаем INSERT
            selectCmd = new SqlCommand(selectQuery, conn);

            SqlParameter param = new SqlParameter();
            param.ParameterName = "@ID";
            param.SqlDbType = SqlDbType.UniqueIdentifier;
            selectCmd.Parameters.Add(param);
        }

        public void selectAndShowInfoForCurrentCompaign(Guid id)
        {
            selectCmd.Parameters["@ID"].Value = id;

            SqlDataReader dataReader = selectCmd.ExecuteReader();

            if (dataReader.Read())
            {
                campaignNameLabel.Text = dataReader.GetString(5);

                infoTextBox.AppendText("id = " + dataReader.GetGuid(12).ToString() + "\n\n");
                infoTextBox.AppendText("Дата с " + dataReader.GetDateTime(1).ToShortDateString()
                    + " по " + dataReader.GetDateTime(2).ToShortDateString() + ".\n");
                infoTextBox.AppendText("\nКампания:\n\tкод - " + dataReader.GetString(3)
                                + "\n\tидентификатор - " + dataReader.GetString(5)
                                + "\n\tимя - " + dataReader.GetString(4)
                               
                                + "\n\nАкция:" 
                                + "\n\tидентификатор - " + dataReader.GetString(6)
                                + "\n\tимя - " + dataReader.GetString(9)

                                + "\n\nВариант:"
                                + "\n\tидентификатор - " + dataReader.GetString(7)
                                + "\n\tимя - " + dataReader.GetString(10)

                                + "\n\nЦелевая группа:"
                                + "\n\tидентификатор - " + dataReader.GetString(8)
                                + "\n\tимя - " + dataReader.GetString(11)

                                + "\n\nОписание:\n"
                                + dataReader.GetString(0));

                
            }

            dataReader.Close();

        }

        // Закрыть
        private void closeButton_Click(object sender, EventArgs e)
        {
            Close();
        }

     }
}