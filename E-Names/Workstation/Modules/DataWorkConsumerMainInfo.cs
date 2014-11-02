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
    public partial class DataWorkConsumerMainInfo : UserControl
    {
        private string connectionString = global::Workstation.Properties.Settings.Default.phmkConnectionString;
        private SqlConnection conn;

        private Guid currentConsumerID;

        private string selectQuery = string.Format("SELECT  ID, AZ_INDENT_ID, SOURCE, GLOBAL_BLOCKING, COUNTRY_CODE, "
                        // 5
                        + "ADDITIONAL_INFORMATION, POSTAL_CODE, LANGUAGEID, ADDRES_BLOCKING, PROMOTIONALID, "
                        // 10
                        + "EMAIL, EMAIL_BLOCKING, PHONE_NUMBER, PHONE_BLOCKING, MOBILE_NUMBER, MOBILE_BLOCKING, MEDIA_CODE, OBLAST_ID, "
                        // 18
                        + "REGION_ID, POP_POINT_ID, SETTLEMENT_TYPE_ID, STREET_TYPE, STREET, HOUSE_NUMBER, APARTMENT_NUMBER, MICRODISTRICT "
                        + "FROM CONSUMER "
                        + "WHERE ID=@ID");


        private SqlCommand selectCmd;

        public DataWorkConsumerMainInfo()
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

            refreshDataElements();
        }

        public void updateForConsumerID(Guid id)
        {
            currentConsumerID = id;

            // Обновить элементы данных
            refreshDataElements();

            selectCmd.Parameters["@ID"].Value = currentConsumerID;

            SqlDataReader dataReader = selectCmd.ExecuteReader();

            if (dataReader.Read())
            {
                lANGUAGEIDComboBox.SelectedValue = dataReader.GetValue(7);
                gLOBAL_BLOCKINGComboBox.SelectedValue = dataReader.GetValue(3);

                oBLASTComboBox.SelectedValue = dataReader.GetValue(17);

                //if (oBLASTComboBox.SelectedIndex != -1)
                //    rEGIONTableAdapter.FillBy(phmkDataSet.REGION, (int)oBLASTComboBox.SelectedValue);
                //else
                //    rEGIONTableAdapter.FillBy(phmkDataSet.REGION, -1);
                rEGIONTableAdapter.Fill(phmkDataSet.REGION);

                rEGIONComboBox.SelectedValue = dataReader.GetValue(18);

                pOSTAL_CODEMTBox.Text = dataReader.GetString(6);

                sETTLEMENTTYPEIDComboBox.SelectedValue = dataReader.GetValue(20);


                //if (rEGIONComboBox.SelectedIndex != -1 && sETTLEMENTTYPEIDComboBox.SelectedIndex != -1)
                //    pOPULATED_POINTSTableAdapter.FillBy1(phmkDataSet.POPULATED_POINTS,
                //           (int)rEGIONComboBox.SelectedValue,
                //           (int)sETTLEMENTTYPEIDComboBox.SelectedValue);
                //else
                //    pOPULATED_POINTSTableAdapter.FillBy1(phmkDataSet.POPULATED_POINTS,
                //           -1,
                //           -1);
                pOPULATED_POINTSTableAdapter.FillByAll(phmkDataSet.POPULATED_POINTS);


                pOPULATED_POINTSComboBox.SelectedValue = dataReader.GetValue(19);


                mICRODISTRICTComboBox.SelectedValue = dataReader.GetValue(25);

                sTREET_TYPEСomboBox.SelectedValue = dataReader.GetValue(21);

                if (sTREET_TYPEСomboBox.SelectedIndex != -1)
                    sTREETTableAdapter.FillBy(phmkDataSet.STREET, (int)sTREET_TYPEСomboBox.SelectedValue);
                else
                    sTREETTableAdapter.FillBy(phmkDataSet.STREET, -1);

                sTREETcomboBox.SelectedValue = dataReader.GetValue(22);

                hOUSE_NUMBERTextBox.Text = dataReader.GetString(23);
                aPARTMENT_NUMBERTextBox.Text = dataReader.GetString(24);

                aDDITIONAL_INFORMATIONTextBox.Text = dataReader.GetString(5);

                aDDRES_BLOCKINGComboBox.SelectedValue = dataReader.GetValue(8);

                pHONE_NUMBERMaskedTextBox.Text = dataReader.GetString(12);
                pHONE_BLOCKINGComboBox.SelectedValue = dataReader.GetValue(13);

                mOBILE_NUMBERMaskedTextBox.Text = dataReader.GetString(14);
                mOBILE_BLOCKINGComboBox.SelectedValue = dataReader.GetValue(15);

                eMAILTextBox.Text = dataReader.GetString(10);
                eMAIL_BLOCKINGComboBox.SelectedValue = dataReader.GetValue(11);
            }
            else
                clearAllInputElements();

            dataReader.Close();

        }

        public void clearAllInputElements()
        {
            lANGUAGEIDComboBox.SelectedValue = -1;
            gLOBAL_BLOCKINGComboBox.SelectedValue = -1;
            oBLASTComboBox.SelectedValue = -1;
            rEGIONComboBox.SelectedValue = -1;
            pOSTAL_CODEMTBox.Text = "";
            sETTLEMENTTYPEIDComboBox.SelectedValue = -1;
            pOPULATED_POINTSComboBox.SelectedValue = -1;
            mICRODISTRICTComboBox.SelectedIndex = -1;
            sTREET_TYPEСomboBox.SelectedValue = -1;
            sTREETcomboBox.SelectedValue = -1;
            hOUSE_NUMBERTextBox.Text = "";
            aPARTMENT_NUMBERTextBox.Text = "";
            aDDITIONAL_INFORMATIONTextBox.Text = "";
            aDDRES_BLOCKINGComboBox.SelectedValue = -1;
            pHONE_NUMBERMaskedTextBox.Text = "";
            pHONE_BLOCKINGComboBox.SelectedValue = -1;
            mOBILE_NUMBERMaskedTextBox.Text = "";
            mOBILE_BLOCKINGComboBox.SelectedValue = -1;
            eMAILTextBox.Text = "";
            eMAIL_BLOCKINGComboBox.SelectedValue = -1;

            currentConsumerID = new Guid("00000000-0000-0000-0000-000000000000");
        }

        //Обновить элементы данных
        public void refreshDataElements()
        {
            lANGUAGETableAdapter.Fill(phmkDataSet.LANGUAGE);
            lANGUAGEIDComboBox.SelectedIndex = -1;

            gLOBAL_BLOCKINGTableAdapter.Fill(phmkDataSet.GLOBAL_BLOCKING);
            gLOBAL_BLOCKINGComboBox.SelectedIndex = -1;

            // Регионально
            oBLAST_DWTableAdapter.Fill(phmkDataSet.OBLAST_DW);
            oBLASTComboBox.SelectedIndex = -1;

            // Тип населенного нункта
            sETTLEMENT_TYPE_DWTableAdapter.Fill(phmkDataSet.SETTLEMENT_TYPE_DW);
            sETTLEMENTTYPEIDComboBox.SelectedIndex = -1;

            // По улицам
            sTREET_TYPETableAdapter.Fill(phmkDataSet.STREET_TYPE);
            sTREET_TYPEСomboBox.SelectedIndex = -1;

            // По блокированию
            aDDRESS_BLOCKINGTableAdapter.Fill(phmkDataSet.ADDRESS_BLOCKING);
            aDDRES_BLOCKINGComboBox.SelectedIndex = -1;

            pHONE_BLOCKINGTableAdapter.Fill(phmkDataSet.PHONE_BLOCKING);
            pHONE_BLOCKINGComboBox.SelectedIndex = -1;

            mOBILE_PHONE_BLOCKINGTableAdapter.Fill(phmkDataSet.MOBILE_PHONE_BLOCKING);
            mOBILE_BLOCKINGComboBox.SelectedIndex = -1;

            eMAIL_BLOCKINGTableAdapter.Fill(phmkDataSet.EMAIL_BLOCKING);
            eMAIL_BLOCKINGComboBox.SelectedIndex = -1;

            mICRODISTRICTTableAdapter.Fill(phmkDataSet.MICRODISTRICT);
            mICRODISTRICTComboBox.SelectedIndex = -1;
        }


        // Связи и обновления ------------------
        private void sTREET_TYPEСomboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (sTREET_TYPEСomboBox.Focused)
            {
                sTREETTableAdapter.FillBy(phmkDataSet.STREET, (int)sTREET_TYPEСomboBox.SelectedValue);
                sTREETcomboBox.SelectedIndex = -1;
            }   
        }

        private void oBLASTComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            //if (oBLASTComboBox.Focused)
            //{
            //    rEGIONComboBox.SelectedIndex = -1;
            //    pOPULATED_POINTSComboBox.SelectedIndex = -1;
            //}  
        }

        private void rEGIONComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            //if (rEGIONComboBox.Focused)
            //{
            //    pOPULATED_POINTSComboBox.SelectedIndex = -1;
            //}
        }

        private void sETTLEMENTTYPEIDComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            //if (sETTLEMENTTYPEIDComboBox.Focused)
            //{
            //    pOPULATED_POINTSComboBox.SelectedIndex = -1;
            //}
        }

        // Проверка правильности заполнения формы --------------
        public Boolean checkForm()
        {
            // Проверка правильности ввода языка
            if (lANGUAGEIDComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Язык неможет быть пустым!", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                lANGUAGEIDComboBox.Focus();
                return false;
            }

            // Проверка правильности ввода глобального блокирования
            if (gLOBAL_BLOCKINGComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Параметр не неможет быть пустым!", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                gLOBAL_BLOCKINGComboBox.Focus();
                return false;
            }

            //// Облать
            //if (oBLASTComboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("Параметр области неможет быть пустым!", "Внимание!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    oBLASTComboBox.Focus();
            //    return false;
            //}
            
            //// Регион
            //if (rEGIONComboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("Параметр региона неможет быть пустым!", "Внимание!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    rEGIONComboBox.Focus();
            //    return false;
            //}

            //if (sETTLEMENTTYPEIDComboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("Параметр типа населенного пункта неможет быть пустым!", "Внимание!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    sETTLEMENTTYPEIDComboBox.Focus();
            //    return false;
            //}

            //if (pOPULATED_POINTSComboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("Параметр названия населенного пункта неможет быть пустым!", "Внимание!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    pOPULATED_POINTSComboBox.Focus();
            //    return false;
            //}


            //if (sTREET_TYPEСomboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("Параметр типа улици неможет быть пустым!", "Внимание!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    sTREET_TYPEСomboBox.Focus();
            //    return false;
            //}

            //if (sTREETcomboBox.SelectedIndex == -1)
            //{
            //    MessageBox.Show("Параметр названия улицы неможет быть пустым!", "Внимание!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    sTREETcomboBox.Focus();
            //    return false;
            //}

            if (sTREETcomboBox.Text.Length > 0 && sTREET_TYPEСomboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Имеется название улици но неуказан ее тип.", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                sTREET_TYPEСomboBox.Focus();
                return false;
            }

            if (aDDRES_BLOCKINGComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Параметр неможет быть пустым!", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                aDDRES_BLOCKINGComboBox.Focus();
                return false;
            }
         
            if (pHONE_BLOCKINGComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Параметр неможет быть пустым!", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                pHONE_BLOCKINGComboBox.Focus();
                return false;
            }
         
            if (mOBILE_BLOCKINGComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Параметр неможет быть пустым!", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                mOBILE_BLOCKINGComboBox.Focus();
                return false;
            }
            
            if (eMAIL_BLOCKINGComboBox.SelectedIndex == -1)
            {
                MessageBox.Show("Параметр неможет быть пустым!", "Внимание!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);
                eMAIL_BLOCKINGComboBox.Focus();
                return false;
            }
  
            return true;
        }

        private void pOPULATED_POINTSComboBox_Enter(object sender, EventArgs e)
        {
           // MessageBox.Show("Параметр неможет быть пустым!", "Внимание!",
           //                     MessageBoxButtons.OK, MessageBoxIcon.Warning);
        }

        private void rEGIONComboBox_DropDown(object sender, EventArgs e)
        {
           //  MessageBox.Show("Параметр неможет быть пустым!", "Внимание!",
           //                     MessageBoxButtons.OK, MessageBoxIcon.Warning);

            //if (oBLASTComboBox.SelectedIndex == -1)
            //{
            //    rEGIONTableAdapter.Fill(phmkDataSet.REGION);
            //}
            //else
            //{
            //    rEGIONTableAdapter.FillBy(phmkDataSet.REGION, (int)oBLASTComboBox.SelectedValue);
            //}

            //pOPULATED_POINTSComboBox.SelectedIndex = -1;

            rEGIONTableAdapter.Fill(phmkDataSet.REGION);
        }

        private void pOPULATED_POINTSComboBox_DropDown(object sender, EventArgs e)
        {
            //if (rEGIONComboBox.SelectedIndex == -1 && sETTLEMENTTYPEIDComboBox.SelectedIndex == -1)
            //{
            //    pOPULATED_POINTSTableAdapter.FillByAll(phmkDataSet.POPULATED_POINTS);
            //}
            //else if (rEGIONComboBox.SelectedIndex == -1 && sETTLEMENTTYPEIDComboBox.SelectedIndex != -1)
            //{
            //    pOPULATED_POINTSTableAdapter.FillBySETTLEMENT_TYPE(phmkDataSet.POPULATED_POINTS
            //        , (int)sETTLEMENTTYPEIDComboBox.SelectedValue);
            //}
            //else if (rEGIONComboBox.SelectedIndex != -1 && sETTLEMENTTYPEIDComboBox.SelectedIndex != -1)
            //{
            //    pOPULATED_POINTSTableAdapter.FillBy1(phmkDataSet.POPULATED_POINTS
            //        , (int) rEGIONComboBox.SelectedValue
            //        , (int)sETTLEMENTTYPEIDComboBox.SelectedValue);
            //}
            //else if (rEGIONComboBox.SelectedIndex != -1 && sETTLEMENTTYPEIDComboBox.SelectedIndex == -1)
            //{
            //    pOPULATED_POINTSTableAdapter.FillBy(phmkDataSet.POPULATED_POINTS
            //        , (int)rEGIONComboBox.SelectedValue);
            //}

            pOPULATED_POINTSTableAdapter.FillByAll(phmkDataSet.POPULATED_POINTS);
        }
    }
}
