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
    public partial class DataWorkCompaignData : UserControl
    {
        private string connectionString = global::Workstation.Properties.Settings.Default.phmkConnectionString;
        private SqlConnection conn;

        private string selectQuery = string.Format("SELECT ID, CAMPAIGNID, SIGNATURE_TYPE, SIGNATURE, SIGNATURE_IMAGE, VISUAL_VERIFIED, HARD_COPY, ARCHIVAL_INDEX, RESPONSE_CHANEL, "
                + "RESPONSE_DATE, LOYALITY, ITEM, REDEMPTION, MEDIAID_CLUSTER, MEDIAID, MAINBRANDCODE, MAINBRANDAFFINITY, MAINBRANDDURATION, "
                + "OCCBRANDCODE, OCCBRANDAFFINITY, OCCBRANDDURATION, SWEEPSTAKE_PARTCIPATION, SWEEPSTAKE_WINNER, SWEEPSTAKE_ITEM "
                + "FROM CONSUMER_COMPAIGN_DATA "
                + "WHERE (ID = @ID) AND (CAMPAIGNID = @CAMPAIGNID)");

        private string insertQuery = string.Format("INSERT INTO CONSUMER_COMPAIGN_DATA "
                + "(ID, CAMPAIGNID, SIGNATURE_TYPE, SIGNATURE, SIGNATURE_IMAGE, VISUAL_VERIFIED, HARD_COPY, ARCHIVAL_INDEX, RESPONSE_CHANEL, "
                + "RESPONSE_DATE, LOYALITY, ITEM, REDEMPTION, MEDIAID_CLUSTER, MEDIAID, MAINBRANDCODE, MAINBRANDAFFINITY, MAINBRANDDURATION, "
                + "OCCBRANDCODE, OCCBRANDAFFINITY, OCCBRANDDURATION, SWEEPSTAKE_PARTCIPATION, SWEEPSTAKE_WINNER, SWEEPSTAKE_ITEM) "
                + "VALUES (@ID,@CAMPAIGNID,@SIGNATURE_TYPE,@SIGNATURE,@SIGNATURE_IMAGE,@VISUAL_VERIFIED,@HARD_COPY,@ARCHIVAL_INDEX,@RESPONSE_CHANEL,@RESPONSE_DATE,@LOYALITY,@ITEM,@REDEMPTION,@MEDIAID_CLUSTER,@MEDIAID,@MAINBRANDCODE,@MAINBRANDAFFINITY,@MAINBRANDDURATION,@OCCBRANDCODE,@OCCBRANDAFFINITY,@OCCBRANDDURATION,@SWEEPSTAKE_PARTCIPATION,@SWEEPSTAKE_WINNER,@SWEEPSTAKE_ITEM)");


        private string updateQuery = string.Format("UPDATE CONSUMER_COMPAIGN_DATA "
                + "SET ID = @ID, CAMPAIGNID = @CAMPAIGNID, SIGNATURE_TYPE = @SIGNATURE_TYPE, SIGNATURE = @SIGNATURE, "
                + "SIGNATURE_IMAGE = @SIGNATURE_IMAGE, VISUAL_VERIFIED = @VISUAL_VERIFIED, HARD_COPY = @HARD_COPY, "
                + "ARCHIVAL_INDEX = @ARCHIVAL_INDEX, RESPONSE_CHANEL = @RESPONSE_CHANEL, RESPONSE_DATE = @RESPONSE_DATE, "
                + "LOYALITY = @LOYALITY, ITEM = @ITEM, REDEMPTION = @REDEMPTION, MEDIAID_CLUSTER = @MEDIAID_CLUSTER, MEDIAID = @MEDIAID, "
                + "MAINBRANDCODE = @MAINBRANDCODE, MAINBRANDAFFINITY = @MAINBRANDAFFINITY, MAINBRANDDURATION = @MAINBRANDDURATION, "
                + "OCCBRANDCODE = @OCCBRANDCODE, OCCBRANDAFFINITY = @OCCBRANDAFFINITY, OCCBRANDDURATION = @OCCBRANDDURATION, "
                + "SWEEPSTAKE_PARTCIPATION = @SWEEPSTAKE_PARTCIPATION, SWEEPSTAKE_WINNER = @SWEEPSTAKE_WINNER, "
                + "SWEEPSTAKE_ITEM = @SWEEPSTAKE_ITEM "
                + "WHERE (ID = @ID) AND (CAMPAIGNID = @CAMPAIGNID)");

        private string deleteQuery = string.Format("DELETE FROM CONSUMER_COMPAIGN_DATA "
                + "WHERE (ID = @ID) AND (CAMPAIGNID = @CAMPAIGNID)");

        private SqlCommand selCmd;
        private SqlCommand insCmd;
        private SqlCommand updCmd;
        private SqlCommand delCmd;


        private Guid compaignId = new Guid("00000000-0000-0000-0000-000000000000");
        private Guid consumerId = new Guid("00000000-0000-0000-0000-000000000000");

        private bool enableWork = false;

        private DataWorkQuestionsAndAnswers dataWorkQuestionsAndAnswers = null;

        public void setDataWorkQuestionsAndAnswers(DataWorkQuestionsAndAnswers dataWorkQuestionsAndAnswers)
        {
            this.dataWorkQuestionsAndAnswers = dataWorkQuestionsAndAnswers;
        }

        // Конструктор
        public DataWorkCompaignData()
        {
            InitializeComponent();

            //clearAllInputElements();
            //enableInputElements(false);

            //delButton.Enabled = false;
            //capLabel.Text = "Нет данных.";



            // Поправки по требованию заказчика

            enableInputElements(true);

            addButton.Visible = false;
            delButton.Visible = false;

            capLabel.Text = "Данные по кампании";

            // Поправки ..



            // Настройка соединения
            conn = new SqlConnection(connectionString);
            conn.Open();

            // Подготавливаем SELECT
            selCmd = new SqlCommand(selectQuery, conn);
            setupSqlParameter(selCmd, "@ID", SqlDbType.UniqueIdentifier);
            setupSqlParameter(selCmd, "@CAMPAIGNID", SqlDbType.UniqueIdentifier);

            // Подготавливаем INSERT
            insCmd = new SqlCommand(insertQuery, conn);
            setupConsumerSqlCommand(insCmd);

            // Подготавливаем UPDATE
            updCmd = new SqlCommand(updateQuery, conn);
            setupConsumerSqlCommand(updCmd);

            // Подготавливаем DELETE
            delCmd = new SqlCommand(deleteQuery, conn);
            setupSqlParameter(delCmd, "@ID", SqlDbType.UniqueIdentifier);
            setupSqlParameter(delCmd, "@CAMPAIGNID", SqlDbType.UniqueIdentifier);

           

        }

        // Настройка параметризированных комманд по потребителю
        private void setupConsumerSqlCommand(SqlCommand cmd)
        {
            // Ключевые
            setupSqlParameter(cmd, "@ID", SqlDbType.UniqueIdentifier);
            setupSqlParameter(cmd, "@CAMPAIGNID", SqlDbType.UniqueIdentifier);

            setupSqlParameter(cmd, "@SIGNATURE_TYPE", SqlDbType.Int);
            setupSqlParameter(cmd, "@SIGNATURE", SqlDbType.Bit);
            setupSqlParameter(cmd, "@SIGNATURE_IMAGE", SqlDbType.VarChar);
            setupSqlParameter(cmd, "@VISUAL_VERIFIED", SqlDbType.Bit);
            setupSqlParameter(cmd, "@HARD_COPY", SqlDbType.Bit);
            setupSqlParameter(cmd, "@ARCHIVAL_INDEX", SqlDbType.VarChar);
            setupSqlParameter(cmd, "@RESPONSE_CHANEL", SqlDbType.Int);
            setupSqlParameter(cmd, "@RESPONSE_DATE", SqlDbType.DateTime);
            setupSqlParameter(cmd, "@LOYALITY", SqlDbType.Int);
            setupSqlParameter(cmd, "@ITEM", SqlDbType.Int);
            setupSqlParameter(cmd, "@REDEMPTION", SqlDbType.VarChar);
            setupSqlParameter(cmd, "@MEDIAID_CLUSTER", SqlDbType.Int);
            setupSqlParameter(cmd, "@MEDIAID", SqlDbType.Int);
            setupSqlParameter(cmd, "@MAINBRANDCODE", SqlDbType.Int);
            setupSqlParameter(cmd, "@MAINBRANDAFFINITY", SqlDbType.Int);
            setupSqlParameter(cmd, "@MAINBRANDDURATION", SqlDbType.Int);
            setupSqlParameter(cmd, "@OCCBRANDCODE", SqlDbType.Int);
            setupSqlParameter(cmd, "@OCCBRANDAFFINITY", SqlDbType.Int);
            setupSqlParameter(cmd, "@OCCBRANDDURATION", SqlDbType.Int);
            setupSqlParameter(cmd, "@SWEEPSTAKE_PARTCIPATION", SqlDbType.Bit);
            setupSqlParameter(cmd, "@SWEEPSTAKE_WINNER", SqlDbType.Bit);
            setupSqlParameter(cmd, "@SWEEPSTAKE_ITEM", SqlDbType.Int);

            //setupSqlParameter(cmd, "@", SqlDbType);
        }

        // Настройка параметра
        private void setupSqlParameter(SqlCommand cmd, string parameterName, SqlDbType sqlDbType)
        {
            SqlParameter param = new SqlParameter();
            param.ParameterName = parameterName;
            param.SqlDbType = sqlDbType;
            cmd.Parameters.Add(param);
        }



        //Обновить элементы данных ~~~~~~~~~~~~~~~~~~~~~~
        public void updateForCompaignID(Guid compaignId)
        {
            this.compaignId = compaignId;

            refreshDataElements();

            select();
        }

        public void updateForConsumerID(Guid consumerId)
        {
            this.consumerId = consumerId;

            refreshDataElements();

            select();
        }

        // Выбор данных из таблици
        private void select()
        {
            selCmd.Parameters["@ID"].Value = consumerId;
            selCmd.Parameters["@CAMPAIGNID"].Value = compaignId;

            SqlDataReader dataReader = selCmd.ExecuteReader();

            if (dataReader.Read())
            {
                sIGNATURE_TYPEComboBox.SelectedValue = dataReader.GetValue(2);

                sIGNATURECheckBox.Checked = dataReader.GetBoolean(3);
                sIGNATURE_IMAGETextBox.Text = dataReader.GetString(4);

                vISUAL_VERIFIEDCheckBox.Checked = dataReader.GetBoolean(5);
                hARD_COPYCheckBox.Checked = dataReader.GetBoolean(6);

                aRCHIVAL_INDEXMaskedTextBox.Text = dataReader.GetString(7);

                rESPONSE_CHANELСomboBox.SelectedValue = dataReader.GetValue(8);
                rESPONSE_DATEDateTimePicker.Value = dataReader.GetDateTime(9);

                lOYALITYComboBox.SelectedValue = dataReader.GetValue(10);
                iTEMСomboBox.SelectedValue = dataReader.GetValue(11);

                rEDEMPTIONMaskedTextBox.Text = dataReader.GetString(12);

                mEDIACLUSTERDWComboBox.SelectedValue = dataReader.GetValue(13);
                mEDIA_DWTableAdapter.Fill(phmkDataSet.MEDIA_DW, compaignId, (int)mEDIACLUSTERDWComboBox.SelectedValue);
                mEDIADWComboBox.SelectedValue = dataReader.GetValue(14);

                mAINBRANDCODEComboBox.SelectedValue = dataReader.GetValue(15);

                if (mAINBRANDCODEComboBox.SelectedIndex != -1)
                    bRANDS_DWTableAdapter.Fill(phmkDataSet.BRANDS_DW, (int)mAINBRANDCODEComboBox.SelectedValue);
                else
                    bRANDS_DWTableAdapter.Fill(phmkDataSet.BRANDS_DW, -1);

                mAINBRANDAFFINITYComboBox.SelectedValue = dataReader.GetValue(16);

                if (dataReader.GetValue(17) != DBNull.Value)
                    mAINBRANDDURATIONComboBox.SelectedIndex = (int)dataReader.GetValue(17);
                else
                    mAINBRANDDURATIONComboBox.SelectedIndex = -1;

                oCCBRANDCODEComboBox.SelectedValue = dataReader.GetValue(18);

                if (oCCBRANDCODEComboBox.SelectedIndex != -1)
                    bRANDS_DW1TableAdapter.Fill(phmkDataSet.BRANDS_DW1, (int)oCCBRANDCODEComboBox.SelectedValue);
                else
                    bRANDS_DW1TableAdapter.Fill(phmkDataSet.BRANDS_DW1, -1);

                oCCBRANDAFFINITYComboBox.SelectedValue = dataReader.GetValue(19);

                if (dataReader.GetValue(20) != DBNull.Value)
                    oCCBRANDDURATIONComboBox.SelectedIndex = (int)dataReader.GetValue(20);
                else
                    oCCBRANDDURATIONComboBox.SelectedIndex = -1;

                sWEEPSTAKE_PARTCIPATIONCheckBox.Checked = dataReader.GetBoolean(21);
                sWEEPSTAKE_WINNERCheckBox.Checked = dataReader.GetBoolean(22); ;
                sWEEPSTAKE_ITEMComboBox.SelectedValue = dataReader.GetValue(23);

                // Визуилация и состояние
                enableWork = true;

                addButton.Enabled = false;
                delButton.Enabled = true;

                enableInputElements(true);

                capLabel.Text = "Есть данные";

                if (dataWorkQuestionsAndAnswers != null)
                    dataWorkQuestionsAndAnswers.selectAndShowInfoForCurrentCompaign(consumerId,compaignId);
            }
            else
            {
                // Визуилация и состояние
                clearAllInputElements();

                //enableWork = false;

                //addButton.Enabled = true;
                //delButton.Enabled = false;

                //enableInputElements(false);

                capLabel.Text = "Нет данных";

                if (dataWorkQuestionsAndAnswers != null)
                    dataWorkQuestionsAndAnswers.clearAllElements();
            }

            dataReader.Close();
        }

        public void clearAllInputElementsBeforeNew()
        {
            clearAllInputElements();
            //enableInputElements(false);

            //delButton.Enabled = false;
            //addButton.Enabled = true;
            capLabel.Text = "Нет данных.";

            enableWork = false;
        }

        //Отчистить все элементы
        public void clearAllInputElements()
        {
            refreshDataElements();

            rESPONSE_DATEDateTimePicker.Value = DateTime.Now;

            mAINBRANDDURATIONComboBox.SelectedIndex = -1;
            oCCBRANDDURATIONComboBox.SelectedIndex = -1;

            sIGNATURECheckBox.Checked = false;
            vISUAL_VERIFIEDCheckBox.Checked = false;
            hARD_COPYCheckBox.Checked = false;
            sWEEPSTAKE_PARTCIPATIONCheckBox.Checked = false;
            sWEEPSTAKE_WINNERCheckBox.Checked = false;

            sIGNATURE_IMAGETextBox.Text = "";
            aRCHIVAL_INDEXMaskedTextBox.Text = "";
            rEDEMPTIONMaskedTextBox.Text = "";

        }

        // Обновить содержимое комбобоксов
        private void refreshDataElements()
        {
            // Канал опроса
            rESPONCE_CHANELS_DWTableAdapter.Fill(phmkDataSet.RESPONCE_CHANELS_DW);
            rESPONSE_CHANELСomboBox.SelectedIndex = -1;

            // Медиакластер
            mEDIA_CLUSTER_DWTableAdapter.Fill(phmkDataSet.MEDIA_CLUSTER_DW, compaignId);
            mEDIACLUSTERDWComboBox.SelectedIndex = -1;
            // медиакод
            mEDIA_DWTableAdapter.Fill(phmkDataSet.MEDIA_DW, compaignId, -1);
            mEDIADWComboBox.SelectedIndex = -1;

            // Теперь ро брендам
            // Основной
            bRAND_FAMILY_DWTableAdapter.Fill(phmkDataSet.BRAND_FAMILY_DW);
            mAINBRANDCODEComboBox.SelectedIndex = -1;
            bRANDS_DWTableAdapter.Fill(phmkDataSet.BRANDS_DW, -1);
            mAINBRANDAFFINITYComboBox.SelectedIndex = -1;

            // Альтернативный
            bRAND_FAMILY_DW1TableAdapter.Fill(phmkDataSet.BRAND_FAMILY_DW1);
            oCCBRANDCODEComboBox.SelectedIndex = -1;
            bRANDS_DW1TableAdapter.Fill(phmkDataSet.BRANDS_DW1, -1);
            oCCBRANDAFFINITYComboBox.SelectedIndex = -1;

            // Призы
            pRIZES_DWTableAdapter.Fill(phmkDataSet.PRIZES_DW, compaignId);
            iTEMСomboBox.SelectedIndex = -1;

            pRIZES_DW1TableAdapter.Fill(phmkDataSet.PRIZES_DW1, compaignId);
            sWEEPSTAKE_ITEMComboBox.SelectedIndex = -1;

            // Тип сигнатуры
            sIGNATURE_TYPETableAdapter.Fill(phmkDataSet.SIGNATURE_TYPE);
            sIGNATURE_TYPEComboBox.SelectedIndex = -1;

            // Лояльность
            lOYALTYTableAdapter.Fill(phmkDataSet.LOYALTY);
            lOYALITYComboBox.SelectedIndex = -1;
        }

        // Активность элементов ввода
        private void enableInputElements(bool b)
        {
            rESPONSE_CHANELСomboBox.Enabled = b;
            mEDIACLUSTERDWComboBox.Enabled = b;
            mEDIADWComboBox.Enabled = b;
            mAINBRANDCODEComboBox.Enabled = b;
            mAINBRANDAFFINITYComboBox.Enabled = b;
            oCCBRANDCODEComboBox.Enabled = b;
            oCCBRANDAFFINITYComboBox.Enabled = b;
            iTEMСomboBox.Enabled = b;
            sWEEPSTAKE_ITEMComboBox.Enabled = b;
            sIGNATURE_TYPEComboBox.Enabled = b;
            lOYALITYComboBox.Enabled = b;

            rESPONSE_DATEDateTimePicker.Enabled = b;

            mAINBRANDDURATIONComboBox.Enabled = b;
            oCCBRANDDURATIONComboBox.Enabled = b;

            sIGNATURECheckBox.Enabled = b;
            vISUAL_VERIFIEDCheckBox.Enabled = b;
            hARD_COPYCheckBox.Enabled = b;
            sWEEPSTAKE_PARTCIPATIONCheckBox.Enabled = b;
            sWEEPSTAKE_WINNERCheckBox.Enabled = b;

            sIGNATURE_IMAGETextBox.Enabled = b;
            aRCHIVAL_INDEXMaskedTextBox.Enabled = b;
            rEDEMPTIONMaskedTextBox.Enabled = b;
        }
    
        // События обновления элементов
        private void mEDIACLUSTERDWComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (mEDIACLUSTERDWComboBox.Focused)
                mEDIA_DWTableAdapter.Fill(phmkDataSet.MEDIA_DW, compaignId, (int)mEDIACLUSTERDWComboBox.SelectedValue);
        }

        private void mAINBRANDCODEComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (mAINBRANDCODEComboBox.Focused)
                bRANDS_DWTableAdapter.Fill(phmkDataSet.BRANDS_DW, (int)mAINBRANDCODEComboBox.SelectedValue);
        }

        private void oCCBRANDCODEComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (oCCBRANDCODEComboBox.Focused)
                bRANDS_DW1TableAdapter.Fill(phmkDataSet.BRANDS_DW1, (int)oCCBRANDCODEComboBox.SelectedValue);
        }
        // ..


        // Проверка правильности заполнения формы
        public Boolean checkForm()
        {
            //if (enableWork)
            if (rESPONSE_CHANELСomboBox.SelectedIndex != -1
            || mEDIACLUSTERDWComboBox.SelectedIndex != -1
            || mEDIADWComboBox.SelectedIndex != -1)
            {
                if (rESPONSE_CHANELСomboBox.SelectedIndex == -1)
                {
                    MessageBox.Show("Канал опроса неможет быть пустым!", "Внимание!",
                                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    rESPONSE_CHANELСomboBox.Focus();
                    return false;
                }

                if (mEDIACLUSTERDWComboBox.SelectedIndex == -1)
                {
                    MessageBox.Show("Медиакластер неможет быть пустым!", "Внимание!",
                                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    mEDIACLUSTERDWComboBox.Focus();
                    return false;
                }

                if (mEDIADWComboBox.SelectedIndex == -1)
                {
                    MessageBox.Show("Медиакод неможет быть пустым!", "Внимание!",
                                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    mEDIADWComboBox.Focus();
                    return false;
                }
            }

            return true;
            
        }

        // Возвращает текущее состояние
        public bool getEnableWork()
        {
            return enableWork;
        }


        // [+] Добавить информацию
        private void addButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Желаете добавить информацию по текущему потребителю?\n"
               , "Удаление!",
                           MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {

                enableWork = true;

                addButton.Enabled = false;
                delButton.Enabled = true;

                enableInputElements(true);

                capLabel.Text = "Есть данные";
            }
        }

        // [-] Удалить информацию
        private void delButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Желаете удалить информацию по текущему потребителю?\n"
               , "Удаление!",
                           MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                deleteCurrentInfo();
            }
        }


        // Удаление данных по текущему потребителю и кампании
        public void deleteCurrentInfo()
        {
            delCmd.Parameters["@ID"].Value = consumerId;
            delCmd.Parameters["@CAMPAIGNID"].Value = compaignId;

            delCmd.ExecuteNonQuery();

            clearAllInputElements();

            //enableWork = false;

            //addButton.Enabled = true;
            //delButton.Enabled = false;

            enableInputElements(false);

            capLabel.Text = "Нет данных";
        }


        // Заполнение параметризированного запроса
        private void fillingConsumerSqlCommand(SqlCommand cmd)
        {
            // Ключевые
            cmd.Parameters["@ID"].Value = consumerId;
            cmd.Parameters["@CAMPAIGNID"].Value = compaignId;

            cmd.Parameters["@SIGNATURE_TYPE"].Value = (sIGNATURE_TYPEComboBox.SelectedIndex != -1)
                ? sIGNATURE_TYPEComboBox.SelectedValue : DBNull.Value;

            cmd.Parameters["@SIGNATURE"].Value = sIGNATURECheckBox.Checked;
            cmd.Parameters["@SIGNATURE_IMAGE"].Value = sIGNATURE_IMAGETextBox.Text;
            cmd.Parameters["@VISUAL_VERIFIED"].Value = vISUAL_VERIFIEDCheckBox.Checked;
            cmd.Parameters["@HARD_COPY"].Value = hARD_COPYCheckBox.Checked;
            cmd.Parameters["@ARCHIVAL_INDEX"].Value = aRCHIVAL_INDEXMaskedTextBox.Text;

            cmd.Parameters["@RESPONSE_CHANEL"].Value = rESPONSE_CHANELСomboBox.SelectedValue;
            cmd.Parameters["@RESPONSE_DATE"].Value = rESPONSE_DATEDateTimePicker.Value;

            if (lOYALITYComboBox.SelectedIndex != -1)
                cmd.Parameters["@LOYALITY"].Value = lOYALITYComboBox.SelectedValue;
            else
                cmd.Parameters["@LOYALITY"].Value = DBNull.Value;

            if (iTEMСomboBox.SelectedIndex != -1)
                cmd.Parameters["@ITEM"].Value = iTEMСomboBox.SelectedValue;
            else
                cmd.Parameters["@ITEM"].Value = DBNull.Value;


            cmd.Parameters["@REDEMPTION"].Value = rEDEMPTIONMaskedTextBox.Text;
            cmd.Parameters["@MEDIAID_CLUSTER"].Value = mEDIACLUSTERDWComboBox.SelectedValue;
            cmd.Parameters["@MEDIAID"].Value = mEDIADWComboBox.SelectedValue;

            if (mAINBRANDCODEComboBox.SelectedIndex != -1)
                cmd.Parameters["@MAINBRANDCODE"].Value = mAINBRANDCODEComboBox.SelectedValue;
            else
                cmd.Parameters["@MAINBRANDCODE"].Value = DBNull.Value;

            if (mAINBRANDCODEComboBox.SelectedIndex != -1)
                cmd.Parameters["@MAINBRANDAFFINITY"].Value = mAINBRANDCODEComboBox.SelectedValue;
            else
                cmd.Parameters["@MAINBRANDAFFINITY"].Value = DBNull.Value;

            if (mAINBRANDDURATIONComboBox.SelectedIndex != -1)
                cmd.Parameters["@MAINBRANDDURATION"].Value = mAINBRANDDURATIONComboBox.SelectedIndex;
            else
                cmd.Parameters["@MAINBRANDDURATION"].Value = DBNull.Value;

            if (oCCBRANDCODEComboBox.SelectedIndex != -1)
                cmd.Parameters["@OCCBRANDCODE"].Value = oCCBRANDCODEComboBox.SelectedValue;
            else
                cmd.Parameters["@OCCBRANDCODE"].Value = DBNull.Value;

            if (oCCBRANDAFFINITYComboBox.SelectedIndex != -1)
                cmd.Parameters["@OCCBRANDAFFINITY"].Value = oCCBRANDAFFINITYComboBox.SelectedValue;
            else
                cmd.Parameters["@OCCBRANDAFFINITY"].Value = DBNull.Value;

            if (oCCBRANDDURATIONComboBox.SelectedIndex != -1)
                cmd.Parameters["@OCCBRANDDURATION"].Value = oCCBRANDDURATIONComboBox.SelectedIndex;
            else
                cmd.Parameters["@OCCBRANDDURATION"].Value = DBNull.Value;

            cmd.Parameters["@SWEEPSTAKE_PARTCIPATION"].Value = sWEEPSTAKE_PARTCIPATIONCheckBox.Checked;
            cmd.Parameters["@SWEEPSTAKE_WINNER"].Value = sWEEPSTAKE_WINNERCheckBox.Checked;

            if (sWEEPSTAKE_ITEMComboBox.SelectedIndex != -1)
                cmd.Parameters["@SWEEPSTAKE_ITEM"].Value = sWEEPSTAKE_ITEMComboBox.SelectedValue;
            else
                cmd.Parameters["@SWEEPSTAKE_ITEM"].Value = DBNull.Value;
        }


        // Эта функция необходима при вставке нового пользователя и сведений по кампании
        public void save(Guid consumerId)
        {
            this.consumerId = consumerId;

            save();
        }

        // Сохранить информацию
        public void save()
        {
            //if (enableWork)
            if (rESPONSE_CHANELСomboBox.SelectedIndex != -1
            || mEDIACLUSTERDWComboBox.SelectedIndex != -1
            || mEDIADWComboBox.SelectedIndex != -1)
            {

                if (compaignId.ToString() != "00000000-0000-0000-0000-000000000000"
                    && consumerId.ToString() != "00000000-0000-0000-0000-000000000000")
                {
                    selCmd.Parameters["@ID"].Value = consumerId;
                    selCmd.Parameters["@CAMPAIGNID"].Value = compaignId;

                    SqlDataReader dataReader = selCmd.ExecuteReader();

                    if (dataReader.Read())
                    { // Обновить то что уже имеется
                        dataReader.Close();

                        fillingConsumerSqlCommand(updCmd);
                        updCmd.ExecuteNonQuery();

                        if (dataWorkQuestionsAndAnswers != null)
                            dataWorkQuestionsAndAnswers.save();
                    }
                    else
                    { // Вставить новую если до этогоничего небыло
                        dataReader.Close();

                        fillingConsumerSqlCommand(insCmd);
                        insCmd.ExecuteNonQuery();

                        if (dataWorkQuestionsAndAnswers != null)
                            dataWorkQuestionsAndAnswers.selectAndShowInfoForCurrentCompaign(consumerId, compaignId);
                    }

                    
                }
            }
        }
    }
}
