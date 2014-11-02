using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBsQQuestions : Workstation.Modules.A.AReferenceBook
    {
        RBsQAnswers rBsQAnswers = null;

        RBsQQuestionsDialog currentDialog;
        BindingSource bindingSource;

        // (1) Указать правильно используемые типы
        Workstation.phmkDataSet.QUESTIONSDataTable dataTable;
        Workstation.phmkDataSetTableAdapters.QUESTIONSTableAdapter tableAdapter;

        public RBsQQuestions()
        {
            InitializeComponent();

            // (2) Инициализация переменных
            dataTable = this.phmkDataSet.QUESTIONS;
            tableAdapter = this.qUESTIONSTableAdapter;
            // (3) --
            bindingSource = this.qUESTIONSBindingSource;
            // Инициализация диалога
            currentDialog = new RBsQQuestionsDialog();

            // Настройка базового класса
            setupChildElements(dataTable, bindingSource, this.dataGridView, currentDialog);

            // (4) Настройка элементов управления
            setupNumericUpDown(currentDialog.QUESTIONIDNumericUpDown, "QUESTIONID", new decimal(new int[] { 99999999, 0, 0, 0 }));

            setupTextBox(currentDialog.QUESTIONNAMETextBox, "QUESTIONNAME", 400, CharacterCasing.Normal);

            setupCheckBox(currentDialog.QUESTIONISNULLCheckBox, "QUESTIONISNULL");
            setupCheckBox(currentDialog.ISDEFAULTCheckBox, "ISDEFAULT");

            vrefresh();

        }

        public void setupComboBoxes(System.Windows.Forms.BindingSource qUESTIONTYPESBindingSource)
        {
            //setupComboBox(currentDialog.BRANDFAMILYComboBox (Виджет), 
            //      BRANDFAMILYbindingSource(Внешний), 
            //      "BRANDFAMILY" (Рабоче поле текущее), 
            //      "DESCR" (Внешней таблици поле, отображающееся в виджете), 
            //      "PMCODE" (Значение, реальное (поле внешней таблици)));

            setupComboBox(currentDialog.QUESTIONTYPEComboBox, qUESTIONTYPESBindingSource, "QUESTIONTYPE", "NAME", "ID");
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


        public void setupRBsQAnswers(RBsQAnswers rBsQAnswers)
        {
            this.rBsQAnswers = rBsQAnswers;
        }

        private void dataGridView_CurrentCellChanged(object sender, EventArgs e)
        {
            updChildTable();
        }

        protected override void vafterAdd()
        {
            dataGridView.CurrentRow.Cells["QUESTIONTYPENAME"].Value = currentDialog.QUESTIONTYPEComboBox.Text;
            updChildTable();
        }

        protected override void vafterEdit()
        {
            dataGridView.CurrentRow.Cells["QUESTIONTYPENAME"].Value = currentDialog.QUESTIONTYPEComboBox.Text;
            updChildTable();
        }

        // Обновление ведомой таблици
        private void updChildTable()
        {

            int i = -1;
            string s = "...";

            try
            {
                i = (int)dataGridView.CurrentRow.Cells["qUESTIONIDDataGridViewTextBoxColumn"].Value;
                s = dataGridView.CurrentRow.Cells["qUESTIONNAMEDataGridViewTextBoxColumn"].Value.ToString();

                if (rBsQAnswers != null)
                    rBsQAnswers.updateParam(i, s);

            }
            catch (ArgumentException)
            {

            }
            catch (NullReferenceException)
            {

            }
            catch (InvalidCastException)
            {
                if (rBsQAnswers != null)
                    rBsQAnswers.updateParam(i, s);
            }
            catch (IndexOutOfRangeException)
            {
               
            }
        }
    }
}

