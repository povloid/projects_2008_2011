using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Workstation.Modules.A;

namespace Workstation.Modules.RB
{
    internal partial class RBCompaignCCPopulatedPoints : Workstation.Modules.A.ARContainComponent
    {
        Guid cOMPAIGNID;

        ARContainComponentDialogC2 currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.COMPAIGN_POPULATED_POINTSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.COMPAIGN_POPULATED_POINTSTableAdapter tableAdapter;

        public RBCompaignCCPopulatedPoints()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.COMPAIGN_POPULATED_POINTS;
            tableAdapter = this.cOMPAIGN_POPULATED_POINTSTableAdapter;
            // (3) --
            bindingSource = this.cOMPAIGNPOPULATEDPOINTSBindingSource;
            // Инициализация диалога
            currentDialog = new ARContainComponentDialogC2("Регион:","Населенный пункт:");

            // Настройка базового класса
            setupChildElementsARCC(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (Виджет), 
            //      BRANDFAMILYbindingSource(Внешний), 
            //      "BRANDFAMILY" (Рабоче поле текущее), 
            //      "DESCR" (Внешней таблици поле, отображающееся в виджете), 
            //      "PMCODE" (Значение, реальное (поле внешней таблици)));

            setupComboBox(currentDialog.comboBox1, rEGIONBindingSource, "REGIONID", "NAME", "REGIONID");
            currentDialog.comboBox1.SelectedIndexChanged += new System.EventHandler(rEGIONСomboBox_SelectedIndexChanged);

            setupComboBox(currentDialog.comboBox2, pOPULATEDPOINTSBindingSource, "POPULATED_POINTID", "NAME", "ID");

            vrefresh();
        }

        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["cOMPAIGNIDDataGridViewTextBoxColumn"].Value = cOMPAIGNID;
        }

        
        // Реализация полного сброса последних изменений и обновления
        public void vrefresh(Guid cOMPAIGNID)
        {
            this.cOMPAIGNID = cOMPAIGNID;
            vrefresh();
        }

        protected override void vrefresh()
        {
            tableAdapter.Fill(dataTable, cOMPAIGNID);
            rEGIONTableAdapter.Fill(this.phmkDataSet.REGION);
            pOPULATED_POINTSTableAdapter.FillByAll(this.phmkDataSet.POPULATED_POINTS);
        }

        private void rEGIONСomboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (currentDialog.comboBox1.Focused)
            {
                pOPULATED_POINTSTableAdapter.FillBy(this.phmkDataSet.POPULATED_POINTS, Convert.ToInt16(currentDialog.comboBox1.SelectedValue));
                currentDialog.comboBox2.SelectedIndex = -1;
            }
       
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
            pOPULATED_POINTSTableAdapter.FillByAll(this.phmkDataSet.POPULATED_POINTS);
        }
    }
}

