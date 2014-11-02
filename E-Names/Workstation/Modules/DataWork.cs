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
    public partial class DataWork : UserControl
    {
        private string connectionString = global::Workstation.Properties.Settings.Default.phmkConnectionString;
        private SqlConnection conn;

        private string insertQuery = string.Format("INSERT INTO CONSUMER"
            + "(AZ_INDENT_ID, SOURCE, GLOBAL_BLOCKING, GENDER, FIRST_NAME, MIDDLE_NAME, LAST_NAME, COUNTRY_CODE, TYPE_OF_ID_CARD, "
            + "ID_CARD_NUMBER, RNN, ADDITIONAL_INFORMATION, POSTAL_CODE, DATE_OF_BIRTH, LANGUAGEID, ADDRES_BLOCKING, PROMOTIONALID, "
            + "EMAIL, EMAIL_BLOCKING, PHONE_NUMBER, PHONE_BLOCKING, MOBILE_NUMBER, MOBILE_BLOCKING, MEDIA_CODE, ID, OBLAST_ID, "
            + "REGION_ID, POP_POINT_ID, SETTLEMENT_TYPE_ID, STREET_TYPE, STREET, HOUSE_NUMBER, APARTMENT_NUMBER, MICRODISTRICT) "
            + "VALUES (@AZ_INDENT_ID,@SOURCE,@GLOBAL_BLOCKING,@GENDER,@FIRST_NAME,@MIDDLE_NAME,@LAST_NAME,"
            + "@COUNTRY_CODE,@TYPE_OF_ID_CARD,@ID_CARD_NUMBER,@RNN,@ADDITIONAL_INFORMATION,@POSTAL_CODE,"
            + "@DATE_OF_BIRTH,@LANGUAGEID,@ADDRES_BLOCKING,@PROMOTIONALID,@EMAIL,@EMAIL_BLOCKING,"
            + "@PHONE_NUMBER,@PHONE_BLOCKING,@MOBILE_NUMBER,@MOBILE_BLOCKING,@MEDIA_CODE,@ID,"
            + "@OBLAST_ID,@REGION_ID,@POP_POINT_ID,@SETTLEMENT_TYPE_ID,@STREET_TYPE,@STREET,"
            + "@HOUSE_NUMBER,@APARTMENT_NUMBER,@MICRODISTRICT)");


        private string updateQuery = string.Format("UPDATE    CONSUMER "
            + "SET "
            + "AZ_INDENT_ID = @AZ_INDENT_ID, SOURCE = @SOURCE, GLOBAL_BLOCKING = @GLOBAL_BLOCKING, GENDER = @GENDER, "
            + "FIRST_NAME = @FIRST_NAME, MIDDLE_NAME = @MIDDLE_NAME, LAST_NAME = @LAST_NAME, COUNTRY_CODE = @COUNTRY_CODE, "
            + "TYPE_OF_ID_CARD = @TYPE_OF_ID_CARD, ID_CARD_NUMBER = @ID_CARD_NUMBER, RNN = @RNN, "
            + "ADDITIONAL_INFORMATION = @ADDITIONAL_INFORMATION, POSTAL_CODE = @POSTAL_CODE, DATE_OF_BIRTH = @DATE_OF_BIRTH, "
            + "LANGUAGEID = @LANGUAGEID, ADDRES_BLOCKING = @ADDRES_BLOCKING, PROMOTIONALID = @PROMOTIONALID, EMAIL = @EMAIL, "
            + "EMAIL_BLOCKING = @EMAIL_BLOCKING, PHONE_NUMBER = @PHONE_NUMBER, PHONE_BLOCKING = @PHONE_BLOCKING, "
            + "MOBILE_NUMBER = @MOBILE_NUMBER, MOBILE_BLOCKING = @MOBILE_BLOCKING, MEDIA_CODE = @MEDIA_CODE, "
            + "OBLAST_ID = @OBLAST_ID, REGION_ID = @REGION_ID, POP_POINT_ID = @POP_POINT_ID, SETTLEMENT_TYPE_ID = @SETTLEMENT_TYPE_ID, "
            + "STREET_TYPE = @STREET_TYPE, STREET = @STREET, HOUSE_NUMBER = @HOUSE_NUMBER, "
            + "APARTMENT_NUMBER = @APARTMENT_NUMBER, MICRODISTRICT = @MICRODISTRICT "
            + "WHERE     (ID = @ID)");

        private string deleteQuery = string.Format("DELETE FROM CONSUMER WHERE ID=@ID");

        private SqlCommand insCmd;
        private SqlCommand updCmd;
        private SqlCommand delCmd;


        // Для сервиса
        // Улици
        private string selectCheckStreet = string.Format("SELECT  1 "
                       + "FROM  STREET "
                       + "WHERE STREET_TYPEID=@STREET_TYPEID AND NAME=@NAME");

        private string insertStreet = string.Format("INSERT INTO STREET "
                        + "(STREET_TYPEID, NAME) "
                        + "VALUES (@STREET_TYPEID,@NAME); SELECT ID FROM STREET WHERE (ID=SCOPE_IDENTITY())");

        private SqlCommand checkStreetCmd;
        private SqlCommand insertStreetCmd;

        // Микрорайоны
        private string selectCheckMicrodistrict = string.Format("SELECT  1 "
                      + "FROM  MICRODISTRICT "
                      + "WHERE NAME=@NAME");

        private string insertMicrodistrict = string.Format("INSERT INTO MICRODISTRICT "
                        + "(NAME) "
                        + "VALUES (@NAME); SELECT ID FROM MICRODISTRICT WHERE (ID=SCOPE_IDENTITY())");

        private SqlCommand checkMicrodistrictCmd;
        private SqlCommand insertMicrodistrictCmd;


        // Переменная, содержащая текущую кампанию
        private Guid compaignID = new Guid("00000000-0000-0000-0000-000000000000");

        // Конструктор
        public DataWork()
        {
            InitializeComponent();

            // Настройка соединения
            conn = new SqlConnection(connectionString);
            conn.Open();

            // Подготавливаем INSERT
            insCmd = new SqlCommand(insertQuery, conn);
            setupConsumerSqlCommand(insCmd);

            // Подготавливаем UPDATE
            updCmd = new SqlCommand(updateQuery, conn);
            setupConsumerSqlCommand(updCmd);

            // Подготавливаем DELETE
            delCmd = new SqlCommand(deleteQuery, conn);
            setupSqlParameter(delCmd, "@ID", SqlDbType.UniqueIdentifier,false);

            
            // Для проверки улиц
            checkStreetCmd = new SqlCommand(selectCheckStreet, conn);
            setupSqlParameter(checkStreetCmd, "@STREET_TYPEID", SqlDbType.Int, false);
            setupSqlParameter(checkStreetCmd, "@NAME", SqlDbType.VarChar, false);

            insertStreetCmd = new SqlCommand(insertStreet, conn);
            setupSqlParameter(insertStreetCmd, "@STREET_TYPEID", SqlDbType.Int, false);
            setupSqlParameter(insertStreetCmd, "@NAME", SqlDbType.VarChar, false);

            // Для проверки и вствки по микрорайонам
            checkMicrodistrictCmd = new SqlCommand(selectCheckMicrodistrict, conn);
            setupSqlParameter(checkMicrodistrictCmd, "@NAME", SqlDbType.VarChar, false);

            insertMicrodistrictCmd = new SqlCommand(insertMicrodistrict, conn);
            setupSqlParameter(insertMicrodistrictCmd, "@NAME", SqlDbType.VarChar, false);

            //setupSqlParameter(insCmd, "@", SqlDbType);

            dataWorkConsumer.setDataWorkConsumerMainInfo(dataWorkConsumerMainInfo);
            dataWorkConsumer.setDataWorkCompaignData(dataWorkCompaignData);
            dataWorkCompaignData.setDataWorkQuestionsAndAnswers(dataWorkQuestionsAndAnswers);

            // Обновить
            refresh();
        }


        // Настройка параметризированных комманд по потребителю
        private void setupConsumerSqlCommand(SqlCommand cmd)
        {
            setupSqlParameter(cmd, "@AZ_INDENT_ID", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@SOURCE", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@GLOBAL_BLOCKING", SqlDbType.Int,false);
            setupSqlParameter(cmd, "@GENDER", SqlDbType.VarChar,false);
            setupSqlParameter(cmd, "@FIRST_NAME", SqlDbType.VarChar,false);
            setupSqlParameter(cmd, "@MIDDLE_NAME", SqlDbType.VarChar,false);
            setupSqlParameter(cmd, "@LAST_NAME", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@COUNTRY_CODE", SqlDbType.VarChar,false);
            setupSqlParameter(cmd, "@TYPE_OF_ID_CARD", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@ID_CARD_NUMBER", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@RNN", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@ADDITIONAL_INFORMATION", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@POSTAL_CODE", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@DATE_OF_BIRTH", SqlDbType.DateTime,false);
            setupSqlParameter(cmd, "@LANGUAGEID", SqlDbType.Int,false);
            setupSqlParameter(cmd, "@ADDRES_BLOCKING", SqlDbType.Int,false);
            setupSqlParameter(cmd, "@PROMOTIONALID", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@EMAIL", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@EMAIL_BLOCKING", SqlDbType.Int,false);
            setupSqlParameter(cmd, "@PHONE_NUMBER", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@PHONE_BLOCKING", SqlDbType.Int,false);
            setupSqlParameter(cmd, "@MOBILE_NUMBER", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@MOBILE_BLOCKING", SqlDbType.Int,false);
            setupSqlParameter(cmd, "@MEDIA_CODE", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@ID", SqlDbType.UniqueIdentifier,false);
            setupSqlParameter(cmd, "@OBLAST_ID", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@REGION_ID", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@POP_POINT_ID", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@SETTLEMENT_TYPE_ID", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@STREET_TYPE", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@STREET", SqlDbType.Int,true);
            setupSqlParameter(cmd, "@HOUSE_NUMBER", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@APARTMENT_NUMBER", SqlDbType.VarChar,true);
            setupSqlParameter(cmd, "@MICRODISTRICT", SqlDbType.Int,true);
        }

        // Настройка параметра
        private void setupSqlParameter(SqlCommand cmd, string parameterName, SqlDbType sqlDbType, bool b)
        {
            SqlParameter param = new SqlParameter();
            param.ParameterName = parameterName;
            param.SqlDbType = sqlDbType;
            param.IsNullable = b;
            cmd.Parameters.Add(param);
        }

        // Глобальное обновление текущего блока
        public void refresh()
        {
            cOMPAIGNTableAdapter.Fill(phmkDataSet.COMPAIGN);
            //compaignNameComboBox.SelectedIndex = -1;    // Устанавливаем невыбранное состояние
        }
      

        // (+) Добавить нового потребителя в базу данных
        private void newButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Желаете ввести нового потребителя?\n"
                , "Новый потребитель...",
                            MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {


                dataWorkConsumer.clearAllInputElements();
                dataWorkConsumerMainInfo.clearAllInputElements();

                dataWorkCompaignData.clearAllInputElementsBeforeNew();

                dataWorkQuestionsAndAnswers.clearAllElements();

                dataWorkConsumer.iD_CARD_NUMBERMTBox.Focus();


            }

        }

        // [+~] -Вставка/Сохранение данных потребителя
        private void saveButton_Click(object sender, EventArgs e)
        {
            if (dataWorkConsumer.checkForm() && dataWorkConsumerMainInfo.checkForm() && dataWorkCompaignData.checkForm())
                if (MessageBox.Show("Желаете сохранить данные по текущему потребителю?\n"
                    , "Сохранить...",
                                MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                   

                    try
                    {
                        if (dataWorkConsumer.getID().ToString() == "00000000-0000-0000-0000-000000000000")
                        {
                            Guid consumerNewID = Guid.NewGuid();

                            fillingConsumerSqlCommand(insCmd, consumerNewID);
                            insCmd.ExecuteNonQuery();

                            dataWorkConsumer.setID(consumerNewID);
                            dataWorkCompaignData.save(consumerNewID);
                        }
                        else
                        {
                            fillingConsumerSqlCommand(updCmd, dataWorkConsumer.getID());
                            updCmd.ExecuteNonQuery();

                            dataWorkCompaignData.save();
                        }

                        MessageBox.Show("Данные пользователя:\n\tимя - " + dataWorkConsumer.fIRST_NAMETextBox.Text
                            + "\n\tфамилия - " + dataWorkConsumer.mIDDLE_NAMETextBox.Text
                            + "\n\tимя - " + dataWorkConsumer.lAST_NAMETextBox.Text 
                            + "\n\nуспешно сохранены!"
                            , "Данные успешно сохранены",
                                MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                    catch (System.Data.SqlClient.SqlException ex)
                    {
                        MessageBox.Show("Возможно нарушены ограничения базы данных!\n"
                        + "\n\n" + ex.ToString(), "Ошибка ввода!",
                                MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                    finally
                    { }
                }
            
        }

        private void fillingConsumerSqlCommand(SqlCommand cmd, Guid guid)
        {
            cmd.Parameters["@AZ_INDENT_ID"].Value = "none";
            cmd.Parameters["@SOURCE"].Value = 1;

            cmd.Parameters["@GLOBAL_BLOCKING"].Value = dataWorkConsumerMainInfo.gLOBAL_BLOCKINGComboBox.SelectedValue;

            cmd.Parameters["@GENDER"].Value = dataWorkConsumer.gENDERComboBox.SelectedIndex;
            cmd.Parameters["@FIRST_NAME"].Value = dataWorkConsumer.fIRST_NAMETextBox.Text;
            cmd.Parameters["@MIDDLE_NAME"].Value = dataWorkConsumer.mIDDLE_NAMETextBox.Text;

            cmd.Parameters["@LAST_NAME"].Value = dataWorkConsumer.lAST_NAMETextBox.Text;

            cmd.Parameters["@COUNTRY_CODE"].Value = "KZ";

            if (dataWorkConsumer.pASSPORTTYPEComboBox.SelectedIndex != -1)
                cmd.Parameters["@TYPE_OF_ID_CARD"].Value = dataWorkConsumer.pASSPORTTYPEComboBox.SelectedValue;
            else
                cmd.Parameters["@TYPE_OF_ID_CARD"].Value = DBNull.Value;

            if (dataWorkConsumer.iD_CARD_NUMBERMTBox.TextLength > 0)
                cmd.Parameters["@ID_CARD_NUMBER"].Value = dataWorkConsumer.iD_CARD_NUMBERMTBox.Text;
            else
                cmd.Parameters["@ID_CARD_NUMBER"].Value = DBNull.Value;

            if (dataWorkConsumer.rNNMTBox.TextLength > 0)
                cmd.Parameters["@RNN"].Value = dataWorkConsumer.rNNMTBox.Text;
            else
                cmd.Parameters["@RNN"].Value = DBNull.Value;

            cmd.Parameters["@ADDITIONAL_INFORMATION"].Value = dataWorkConsumerMainInfo.aDDITIONAL_INFORMATIONTextBox.Text;
            cmd.Parameters["@POSTAL_CODE"].Value = dataWorkConsumerMainInfo.pOSTAL_CODEMTBox.Text;
            cmd.Parameters["@DATE_OF_BIRTH"].Value = dataWorkConsumer.dATE_OF_BIRTHDateTimePicker.Value;
            cmd.Parameters["@LANGUAGEID"].Value = dataWorkConsumerMainInfo.lANGUAGEIDComboBox.SelectedValue;
            cmd.Parameters["@ADDRES_BLOCKING"].Value = dataWorkConsumerMainInfo.aDDRES_BLOCKINGComboBox.SelectedValue;
            cmd.Parameters["@PROMOTIONALID"].Value = "none";
            cmd.Parameters["@EMAIL"].Value = dataWorkConsumerMainInfo.eMAILTextBox.Text;
            cmd.Parameters["@EMAIL_BLOCKING"].Value = dataWorkConsumerMainInfo.eMAIL_BLOCKINGComboBox.SelectedValue;
            cmd.Parameters["@PHONE_NUMBER"].Value = dataWorkConsumerMainInfo.pHONE_NUMBERMaskedTextBox.Text.Trim();
            cmd.Parameters["@PHONE_BLOCKING"].Value = dataWorkConsumerMainInfo.pHONE_BLOCKINGComboBox.SelectedValue;
            cmd.Parameters["@MOBILE_NUMBER"].Value = dataWorkConsumerMainInfo.mOBILE_NUMBERMaskedTextBox.Text.Trim();
            cmd.Parameters["@MOBILE_BLOCKING"].Value = dataWorkConsumerMainInfo.mOBILE_BLOCKINGComboBox.SelectedValue;
            cmd.Parameters["@MEDIA_CODE"].Value = 1;

            if (dataWorkConsumerMainInfo.oBLASTComboBox.SelectedIndex != -1)
                cmd.Parameters["@OBLAST_ID"].Value = dataWorkConsumerMainInfo.oBLASTComboBox.SelectedValue;
            else
                cmd.Parameters["@OBLAST_ID"].Value = DBNull.Value;

            if (dataWorkConsumerMainInfo.rEGIONComboBox.SelectedIndex != -1)
                cmd.Parameters["@REGION_ID"].Value = dataWorkConsumerMainInfo.rEGIONComboBox.SelectedValue;
            else
                cmd.Parameters["@REGION_ID"].Value = DBNull.Value;

            if (dataWorkConsumerMainInfo.pOPULATED_POINTSComboBox.SelectedIndex != -1)
                cmd.Parameters["@POP_POINT_ID"].Value = dataWorkConsumerMainInfo.pOPULATED_POINTSComboBox.SelectedValue;
            else
                cmd.Parameters["@POP_POINT_ID"].Value = DBNull.Value;

            if (dataWorkConsumerMainInfo.sETTLEMENTTYPEIDComboBox.SelectedIndex != -1)
                cmd.Parameters["@SETTLEMENT_TYPE_ID"].Value = dataWorkConsumerMainInfo.sETTLEMENTTYPEIDComboBox.SelectedValue;
            else
                cmd.Parameters["@SETTLEMENT_TYPE_ID"].Value = DBNull.Value;


            // Приколы с улицами ------------------------------------------------------------------------------
            if (dataWorkConsumerMainInfo.sTREET_TYPEСomboBox.SelectedIndex != -1)
                cmd.Parameters["@STREET_TYPE"].Value = dataWorkConsumerMainInfo.sTREET_TYPEСomboBox.SelectedValue;
            else
                cmd.Parameters["@STREET_TYPE"].Value = DBNull.Value;


            if (cmd.Parameters["@STREET_TYPE"].Value != DBNull.Value)
            {
                checkStreetCmd.Parameters["@STREET_TYPEID"].Value = dataWorkConsumerMainInfo.sTREET_TYPEСomboBox.SelectedValue;
                checkStreetCmd.Parameters["@NAME"].Value = dataWorkConsumerMainInfo.sTREETcomboBox.Text;

                SqlDataReader ckReader = checkStreetCmd.ExecuteReader();
                

                if (ckReader.Read())
                {
                    ckReader.Close();

                    cmd.Parameters["@STREET"].Value = (dataWorkConsumerMainInfo.sTREETcomboBox.SelectedIndex != -1)?
                         dataWorkConsumerMainInfo.sTREETcomboBox.SelectedValue : DBNull.Value;
                }
                else
                {
                    ckReader.Close();
                    //MessageBox.Show("Такого значениия нету!\n", "Ошибка ввода!",
                    //               MessageBoxButtons.OK, MessageBoxIcon.Error);

                    insertStreetCmd.Parameters["@STREET_TYPEID"].Value = dataWorkConsumerMainInfo.sTREET_TYPEСomboBox.SelectedValue;
                    insertStreetCmd.Parameters["@NAME"].Value = dataWorkConsumerMainInfo.sTREETcomboBox.Text;

                    SqlDataReader r = insertStreetCmd.ExecuteReader();

                    if (r.Read())
                    {
                        cmd.Parameters["@STREET"].Value = r.GetValue(0);
                    }

                    r.Close();
                } 
            }
            else
                cmd.Parameters["@STREET"].Value = DBNull.Value;

            // Приколы с улицами ...

            cmd.Parameters["@HOUSE_NUMBER"].Value = dataWorkConsumerMainInfo.hOUSE_NUMBERTextBox.Text;
            cmd.Parameters["@APARTMENT_NUMBER"].Value = dataWorkConsumerMainInfo.aPARTMENT_NUMBERTextBox.Text;


            // Приколы с микрорайонами ----------------------------------------------------

           

            {

                checkMicrodistrictCmd.Parameters["@NAME"].Value = dataWorkConsumerMainInfo.mICRODISTRICTComboBox.Text;

                SqlDataReader ckReader = checkMicrodistrictCmd.ExecuteReader();

                if (ckReader.Read())
                {
                    ckReader.Close();

                    cmd.Parameters["@MICRODISTRICT"].Value = (dataWorkConsumerMainInfo.mICRODISTRICTComboBox.SelectedIndex != -1) ?
                        dataWorkConsumerMainInfo.mICRODISTRICTComboBox.SelectedValue : DBNull.Value;
                }
                else
                {
                    ckReader.Close();

                    //MessageBox.Show("Такого значениия нету!\n", "Ошибка ввода!",
                    //               MessageBoxButtons.OK, MessageBoxIcon.Error);

                    insertMicrodistrictCmd.Parameters["@NAME"].Value = dataWorkConsumerMainInfo.mICRODISTRICTComboBox.Text;

                    SqlDataReader r = insertMicrodistrictCmd.ExecuteReader();

                    if (r.Read())
                    {
                        cmd.Parameters["@MICRODISTRICT"].Value = r.GetValue(0);
                    }

                    r.Close();
                }
            }

            // Приколы с микрорайонами ..


            //cmd.Parameters["@MICRODISTRICT"].Value = dataWorkConsumerMainInfo.mICRODISTRICTTextBox.Text;

            cmd.Parameters["@ID"].Value = guid;
        }


        // [-] - Удаление
        private void delButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Желаете удалить текущего потребителя?\n"
                 + "\n\tфамилия - " + dataWorkConsumer.mIDDLE_NAMETextBox.Text
                 + "\n\tимя - " + dataWorkConsumer.lAST_NAMETextBox.Text
                , "Удаление!",
                            MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                try
                {
                    delCmd.Parameters["@ID"].Value = dataWorkConsumer.getID();    
                    delCmd.ExecuteNonQuery();

                    dataWorkConsumer.clearAllInputElements();
                    dataWorkConsumerMainInfo.clearAllInputElements();
                    dataWorkCompaignData.clearAllInputElements();
                    dataWorkQuestionsAndAnswers.clearAllElements();
                }
                catch (System.Data.SqlClient.SqlException ex)
                {
                    MessageBox.Show("Возможно нарушены ограничения базы данных!\n"
                    + "\n\n" + ex.ToString(), "Ошибка ввода!",
                            MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                finally
                { }

            }
        }

        // Выбор кампании
        private void selectCampaignButton_Click(object sender, EventArgs e)
        {
            DataWorkSelectCampaign dialog = new DataWorkSelectCampaign();

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                compaignID = dialog.getID();
                compaignName.Text = dialog.getCompaignName();
                CompaignDescriptionTextBox.Text = dialog.getCompaignDescription();

                dataWorkCompaignData.updateForCompaignID(compaignID);

                //dataWorkQuestionsAndAnswers.selectAndShowInfoForCurrentCompaign(dataWorkConsumer.getID(), compaignID);
            }
            
            dialog.Dispose();
        }

        // Информация по кампании
        private void compaignInfiButton_Click(object sender, EventArgs e)
        {
            DataWorkCompaignInfo dialog = new DataWorkCompaignInfo();
            dialog.selectAndShowInfoForCurrentCompaign(compaignID);

            dialog.ShowDialog();

            dialog.Dispose();
        }

       
    }
}
