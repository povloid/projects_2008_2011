using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{

    public partial class RBCompaign : Workstation.Modules.A.AReferenceBook
    {
        RBCompaignDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.COMPAIGNDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGNTableAdapter tableAdapter;

        public RBCompaign()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.COMPAIGN;
            tableAdapter = this.cOMPAIGNTableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNBindingSource;
            // Инициализация диалога
            currentDialog = new RBCompaignDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            setupTextBox(currentDialog.cAMPAIGNIDTextBox, "CAMPAIGNID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.cAMPAIGNCODETextBox, "CAMPAIGNCODE", 10, CharacterCasing.Upper);
            setupTextBox(currentDialog.cAMPAIGNNAMETextBox, "CAMPAIGNNAME", 100, CharacterCasing.Upper);
            setupTextBox(currentDialog.dESCRIPTIONTextBox, "DESCRIPTION", 255, CharacterCasing.Normal);

            setupDateTimePicker(currentDialog.sTARTDATEDateTimePicker, "STARTDATE", DateTimePickerFormat.Short);
            setupDateTimePicker(currentDialog.eNDDATEDateTimePicker, "ENDDATE", DateTimePickerFormat.Short);

            setupTextBox(currentDialog.aCTIONIDTextBox, "ACTIONID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.aCTIONNAMETextBox, "ACTIONNAME", 100, CharacterCasing.Normal);

            setupTextBox(currentDialog.vARIANTIDTextBox, "VARIANTID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.vARIANTTextBox, "VARIANT", 100, CharacterCasing.Normal);

            setupTextBox(currentDialog.tARGETGROUPIDTextBox, "TARGETGROUPID", 3, CharacterCasing.Upper);
            setupTextBox(currentDialog.tARGETGROUPTextBox, "TARGETGROUP", 100, CharacterCasing.Normal);

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["ID"].Value = Guid.NewGuid();
        }

        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            tableAdapter.Fill(dataTable);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }

        private void dataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            try
            {
                rbCompaignCCMedia1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
                rbCompaignCCPrizes1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
                rbCompaignCCQuestions1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
                rbCompaignCCPopulatedPoints1.vrefresh((Guid)dataGridView.CurrentRow.Cells["ID"].Value);
            }
            catch (ArgumentException)
            {

            }
            catch (NullReferenceException)
            {

            }
            catch (InvalidCastException)
            {
                
            }
            catch (IndexOutOfRangeException)
            {

            }
        }
    }
}
