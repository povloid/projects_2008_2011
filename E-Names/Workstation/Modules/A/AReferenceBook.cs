using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using System.Reflection;
using Microsoft.ReportingServices.ReportProcessing;

namespace Workstation.Modules.A
{
    public partial class AReferenceBook : UserControl
    {
        private DataTable dataTable;
        private System.Windows.Forms.BindingSource bindingSource;
        private ADialog dialog;
        private DataGridView dataGridView;

        protected void setupChildElements(DataTable dataTable,
            System.Windows.Forms.BindingSource bindingSource,
            DataGridView dataGridView,
            ADialog dialog)
        {
            this.dataTable = dataTable;

            this.bindingSource = bindingSource;
            

            // Настраивается виджет таблици
            this.dataGridView = dataGridView;
            this.dataGridView.ReadOnly = true;
            this.dataGridView.BorderStyle = BorderStyle.FixedSingle;
            this.dataGridView.Dock = DockStyle.Fill;
            this.dataGridView.ScrollBars = ScrollBars.Both;
            
            //this.dataGridView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView.MultiSelect = false;
            this.dataGridView.RowTemplate.ReadOnly = true;
            // настройка контекстного меню
            this.dataGridView.ContextMenuStrip = contextMenuStrip;
            

            // Настраивается диалог
            this.dialog = dialog;

            //resources.ApplyResources(this.capLabel, "capLabel");
        }

        [Localizable(true)]
        //[Browsable(true)]
        [DefaultValue("Caption")]
        //[DesignerSerializationVisibility(0)]
        public string capPanelText
        {
            get{return capLabel.Text;} 
            set{capLabel.Text = value;} 
 
        }

       

        // КОНСТРУКТОР КЛАССА ***********************
        public AReferenceBook()
        {
            InitializeComponent();

            // Стиль виджета 
            Dock = DockStyle.Fill;        
        }

      

        // Виртуальные методы методы
        // Добавить запись
        protected virtual void vbeforeAdd() { }
        protected virtual void vafterAddNow() { }
        protected virtual void vafterAddShowDialog(){}
        protected virtual void vafterAdd() { }
        // Редактировать запись
        protected virtual void vbeforeEdit() { }
        protected virtual void vafterEditShowDialog() { }
        protected virtual void vafterEdit() { }
        // Удалить запись
        protected virtual void vbeforeDel() { }
        protected virtual void vafterDel() { }


        // (+) Добавить запись
        private void addButton_Click(object sender, EventArgs e)
        {
            vbeforeAdd();

            try
            {
                Boolean b = false;
                Boolean c = false;

                try
                {
                    b = dataGridView.CurrentRow.IsNewRow;
                    c = bindingSource.Count == dataGridView.CurrentRow.Index + 1;
                }
                catch (NullReferenceException ex)
                {
                    ex.ToString();
                    b = true;
                    c = true;
                }

                if ( b && c)
                    bindingSource.CancelEdit();
                
                    // Начать добавление новой строки
                    bindingSource.AddNew();
                    vafterAddNow(); // Виртуальная функция.
                    
            }
            catch (ConstraintException ex)
            {
                MessageBox.Show("Нарушено ограничение по уникальности на запись!\n\n" + ex.ToString(), "Ошибка ввода!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            catch (NoNullAllowedException ex)
            {
            
                    MessageBox.Show("Некоторые поля немогут быть пустыми!\n\n" + ex.ToString(), "Ошибка ввода!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);          
            }

            dialog.Text = "Добавить запись...";
            dialog.capPictureBox.Image = global::Workstation.Properties.Resources.add_32;

            // Сия процедура нужна для исправления недоделок проклятого Билгейтса и его жалких псов миньенов
            foreach (Control c in dialog.groupBox.Controls)
            {
                if (c is NumericUpDown)
                {
                    NumericUpDown tc = (NumericUpDown)c;
                    tc.Value = 0;
                }
            }

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                // Сия процедура нужна для исправления недоделок проклятого Билгейтса и его жалких псов миньенов
                foreach (Control c in dialog.groupBox.Controls)
                    if (c is DateTimePicker)
                    {
                        DateTimePicker dtc = (DateTimePicker)c;
                        dtc.DataBindings[0].WriteValue();
                    }

                vafterAddShowDialog();
                vupdate();
            }
            else
            {
                bindingSource.CancelEdit();
                return;
            }

            vafterAdd();
        }

        // (~) Редактировать запись
        private void editButton_Click(object sender, EventArgs e)
        {
            try
            {
                vbeforeEdit();

                dialog.Text = "Редактировать запись...";
                dialog.capPictureBox.Image = global::Workstation.Properties.Resources.edit_32;

                if (dialog.ShowDialog() == DialogResult.OK)
                {
                    //bindingSource.ResetCurrentItem();
                    vafterEditShowDialog();

                    try
                    {
                        bindingSource.EndEdit();
                        vupdate();
                    }
                    catch (ConstraintException ex)
                    {
                        MessageBox.Show("Запись не уникальна!\n\n" + ex.ToString(), "Ошибка ввода!",
                            MessageBoxButtons.OK, MessageBoxIcon.Error);

                        bindingSource.CancelEdit();
                        return;
                    }
                }
                else
                    bindingSource.CancelEdit();

                vafterEdit();
            }
            catch (NullReferenceException)
            {
                MessageBox.Show("Нет записей!", "Ошибка редактирования!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        // (-) Удалить запись
        private void delButton_Click(object sender, EventArgs e)
        {
            
            //DataRow[] childRows = null;

            //DataTable childTable;

            //foreach (DataRelation relation in dataTable.ChildRelations)
            //{

            //    childTable = relation.ChildTable;
            //    //bool IsInitialized = childTable.IsInitialized;
            //    childTable.
                


            //    MessageBox.Show(childTable.Rows.Count.ToString(), "Информация к размышоению!",
            //               MessageBoxButtons.OK, MessageBoxIcon.Information);

            //    foreach (DataRow row in childTable.Rows)
            //    {
            //        childRows = row.GetChildRows(relation);
            //        MessageBox.Show(">" + childRows.Length, "Информация к размышоению!",
            //            MessageBoxButtons.OK, MessageBoxIcon.Information);
                
            //    }

            ////    //childRows = dataTable.Rows[1].GetChildRows(relation);

            ////    MessageBox.Show(childTable.Rows.Count.ToString(), "Информация к размышоению!",
            ////            MessageBoxButtons.OK, MessageBoxIcon.Information);
            //}

            vbeforeDel();

            // Делаем все элементы диалога неактивными, только для чтения
            setEnabletInputsElements(false);

            dialog.Text = "Удалить запись...";
            dialog.capPictureBox.Image = global::Workstation.Properties.Resources.del_32;

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    bindingSource.RemoveCurrent();
                    vupdate();
                }
                catch (InvalidOperationException ex)
                {
                    MessageBox.Show("Нет записей!\n\n" + ex.ToString(), "Ошибка ввода!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                    // Делаем все элементы диалога снова активными
                    setEnabletInputsElements(true);

                    vafterDel();
                    return;
                }
              
            }

            // Делаем все элементы диалога снова активными
            setEnabletInputsElements(true);

            vafterDel();
        }

        // Управление активностью ввода элементов
        protected void setEnabletInputsElements( bool b)
        {
            foreach (Control c in dialog.groupBox.Controls){
                if (c is TextBox || c is ComboBox || c is CheckBox || c is NumericUpDown)
                        c.Enabled = b;  
            }
        }

        // Сохранить и обновить
        public virtual void vvupdate() { }

        // *****************************************************************************************
        public void vupdate()
        {

            Validate();

            try
            {
                bindingSource.ResetCurrentItem();
                bindingSource.EndEdit();

            }
            catch (ArgumentException ex)
            {
                MessageBox.Show("Введенное вами значение слишком велико!\n\n" + ex.ToString(), "Ошибка ввода!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                bindingSource.CancelEdit();
            }
            catch (ConstraintException ex)
            {
                MessageBox.Show("Введенное вами значение не уникально!\n\n" + ex.ToString(), "Ошибка ввода!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                bindingSource.CancelEdit();
            }
            catch (NoNullAllowedException ex)
            {
                MessageBox.Show("Некоторые поля немогут быть пустыми!\n\n" + ex.ToString(), "Ошибка ввода!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                bindingSource.CancelEdit();
            }

            try
            {
                vvupdate();
            }
            catch (System.Data.SqlClient.SqlException ex)
            {
                MessageBox.Show("Возможно нарушены внешние зависимости!\n"
                + "Возможно одна или несколько удаленных записей используется в других таблицах."
                + "\n\n" + ex.ToString(), "Ошибка ввода!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                refresh();
            }
            catch (DBConcurrencyException ex)
            {
                MessageBox.Show("Возможно вы вводите слишком быстро\n\n" + ex.ToString(), "Ошибка ввода!",
                       MessageBoxButtons.OK, MessageBoxIcon.Error);

                refresh();
            }
        }


        // Отменть изменения и обновить из базы данных
        protected virtual void vrefresh() { }
        public void refresh()
        {
            this.bindingSource.Filter = ""; // Иногда элементы 
                                            // устанавливают нужние им фильтры 
                                            // из других контролов
            vrefresh();
           
        }

        //Обновить данные и отменить сделанные изменения 
        private void resetButton_Click(object sender, EventArgs e)
        {
            refresh();
        }


        // Обработка событий контекстного меню
        private void addToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //addButton.Focus();
            addButton_Click(null, null);
            //dataGridView.Focus();
        }

        private void editToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //editButton.Focus();
            editButton_Click(null, null);
            //dataGridView.Focus();
        }

        private void delToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //delButton.Focus();
            delButton_Click(null, null);
            //dataGridView.Focus();
        }

  

        private void resetToolStripMenuItem_Click(object sender, EventArgs e)
        {
            resetButton_Click(null, null);
        }
        // .

        //protected void AReferenceBook_KeyDown(object sender, KeyEventArgs e)
        //{
        //    MessageBox.Show(e.KeyCode.ToString() , "Key",
        //                                MessageBoxButtons.OKCancel, MessageBoxIcon.Information);
        //    switch (e.KeyCode)
        //    {
        //        case Keys.Insert:
        //            addButton_Click(null, null);
        //            break;
        //        case Keys.Delete:
        //            delButton_Click(null, null);
        //            break;


        //    }
        //}


        // Настраивает элемент ComboBox
        protected void setupComboBox(ComboBox cb,
                                        System.Windows.Forms.BindingSource bSource, 
                                        string selectedValue, 
                                        string displayMember, 
                                        string valueMamber)
        {
            cb.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", bindingSource, selectedValue, true));
            cb.DataSource = bSource;
            cb.DisplayMember = displayMember;
            cb.FormattingEnabled = true;
            cb.ValueMember = valueMamber;
            cb.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
        }

        protected void setupComboBox(ComboBox cb,
                                        System.Windows.Forms.BindingSource bSource,
                                        string displayMember,
                                        string valueMamber)
        {
            cb.DataSource = bSource;
            cb.DisplayMember = displayMember;
            cb.FormattingEnabled = true;
            cb.ValueMember = valueMamber;
            cb.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
        }


        // Настраивает TextBox
        protected void setupTextBox(TextBox tb, string value, int maxLength, CharacterCasing cCasting)
        {
            tb.DataBindings.Add(new System.Windows.Forms.Binding("Text", bindingSource, value, true));
            tb.MaxLength = maxLength;
            tb.CharacterCasing = cCasting;
        }

        // Настраивает CheckBox
        protected void setupCheckBox(CheckBox cb, string value)
        {
            cb.DataBindings.Add(new System.Windows.Forms.Binding("CheckState", bindingSource, value, true));
        }

        // Настраивает NumericUpDown
        protected void setupNumericUpDown(NumericUpDown nud, string value, decimal d)
        {
            nud.DataBindings.Add(new System.Windows.Forms.Binding("Value", bindingSource , value, true));
            nud.Maximum = d;
            nud.TextAlign = HorizontalAlignment.Right;

            nud.Enter += new System.EventHandler(this.numericUpDown_Enter);
            nud.GotFocus += new System.EventHandler(this.numericUpDown_Enter);
            //nud.ValueChanged += new System.EventHandler(this.numericValueChanged);
        }

        private void numericUpDown_Enter(object sender, EventArgs e)
        {
            if (!(sender is NumericUpDown))
                return;

            NumericUpDown nud = (NumericUpDown)sender;
            nud.Select(0,8);

            //MessageBox.Show("Элемент получил фокус!\n\n", "Произошло событие!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
        }

        //private void numericValueChanged(object sender, EventArgs e)
        //{
        //    if (!(sender is NumericUpDown))
        //        return;

        //    NumericUpDown nud = (NumericUpDown)sender;
        //}

        // Настраивает Date
        protected void setupDateTimePicker(DateTimePicker dtp, string value, DateTimePickerFormat format)
        {
            dtp.DataBindings.Add(new System.Windows.Forms.Binding("Text", bindingSource, value, true));

            dtp.Format = format;
        }

     
    }
}
