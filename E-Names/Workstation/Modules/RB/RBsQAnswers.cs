using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBsQAnswers : Workstation.Modules.A.AReferenceBook
    {
        RBsQAnswersDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.ANSWERSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.ANSWERSTableAdapter tableAdapter;

        int vQUESTIONID = 0;
        string vQUESTIONNAME = "...";

        public RBsQAnswers()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.ANSWERS;
            tableAdapter = this.aNSWERSTableAdapter;
            // (3) --
            bindingSource = this.aNSWERSBindingSource;
            // Инициализация диалога
            currentDialog = new RBsQAnswersDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            //setupTextBox(currentDialog.ANSWERTextBox, "QUESTIONID", 50, CharacterCasing.Normal);

            setupNumericUpDown(currentDialog.IDNumericUpDown, "ID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.ANSWERTextBox, "ANSWER", 50, CharacterCasing.Normal);

            vrefresh();

        }


        protected override void vafterAddNow()
        {
            dataGridView.CurrentRow.Cells["QUESTIONID"].Value = vQUESTIONID;
        }

        protected override void vafterEdit()
        {
            dataGridView.CurrentRow.Cells["QUESTIONID"].Value = vQUESTIONID;
        }

        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            //tableAdapter.Fill(dataTable);
            tableAdapter.Fill(dataTable, vQUESTIONID);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            tableAdapter.Update(dataTable);
        }

        // Обновление по событию из другой таблици
        public void updateParam( int id , string s)
        {
            vQUESTIONID = id;
            vQUESTIONNAME = s;

            currentDialog.QUESTIONIDLabel.Text = id.ToString();
            currentDialog.QUESTIONNAMELabel.Text = s;

            vrefresh();
        }
    }
}

