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
            

            // ������������� ������ �������
            this.dataGridView = dataGridView;
            this.dataGridView.ReadOnly = true;
            this.dataGridView.BorderStyle = BorderStyle.FixedSingle;
            this.dataGridView.Dock = DockStyle.Fill;
            this.dataGridView.ScrollBars = ScrollBars.Both;
            
            //this.dataGridView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView.MultiSelect = false;
            this.dataGridView.RowTemplate.ReadOnly = true;
            // ��������� ������������ ����
            this.dataGridView.ContextMenuStrip = contextMenuStrip;
            

            // ������������� ������
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

       

        // ����������� ������ ***********************
        public AReferenceBook()
        {
            InitializeComponent();

            // ����� ������� 
            Dock = DockStyle.Fill;        
        }

      

        // ����������� ������ ������
        // �������� ������
        protected virtual void vbeforeAdd() { }
        protected virtual void vafterAddNow() { }
        protected virtual void vafterAddShowDialog(){}
        protected virtual void vafterAdd() { }
        // ������������� ������
        protected virtual void vbeforeEdit() { }
        protected virtual void vafterEditShowDialog() { }
        protected virtual void vafterEdit() { }
        // ������� ������
        protected virtual void vbeforeDel() { }
        protected virtual void vafterDel() { }


        // (+) �������� ������
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
                
                    // ������ ���������� ����� ������
                    bindingSource.AddNew();
                    vafterAddNow(); // ����������� �������.
                    
            }
            catch (ConstraintException ex)
            {
                MessageBox.Show("�������� ����������� �� ������������ �� ������!\n\n" + ex.ToString(), "������ �����!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            catch (NoNullAllowedException ex)
            {
            
                    MessageBox.Show("��������� ���� ������� ���� �������!\n\n" + ex.ToString(), "������ �����!",
                                MessageBoxButtons.OK, MessageBoxIcon.Warning);          
            }

            dialog.Text = "�������� ������...";
            dialog.capPictureBox.Image = global::Workstation.Properties.Resources.add_32;

            // ��� ��������� ����� ��� ����������� ��������� ���������� ��������� � ��� ������ ���� ��������
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
                // ��� ��������� ����� ��� ����������� ��������� ���������� ��������� � ��� ������ ���� ��������
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

        // (~) ������������� ������
        private void editButton_Click(object sender, EventArgs e)
        {
            try
            {
                vbeforeEdit();

                dialog.Text = "������������� ������...";
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
                        MessageBox.Show("������ �� ���������!\n\n" + ex.ToString(), "������ �����!",
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
                MessageBox.Show("��� �������!", "������ ��������������!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        // (-) ������� ������
        private void delButton_Click(object sender, EventArgs e)
        {
            
            //DataRow[] childRows = null;

            //DataTable childTable;

            //foreach (DataRelation relation in dataTable.ChildRelations)
            //{

            //    childTable = relation.ChildTable;
            //    //bool IsInitialized = childTable.IsInitialized;
            //    childTable.
                


            //    MessageBox.Show(childTable.Rows.Count.ToString(), "���������� � �����������!",
            //               MessageBoxButtons.OK, MessageBoxIcon.Information);

            //    foreach (DataRow row in childTable.Rows)
            //    {
            //        childRows = row.GetChildRows(relation);
            //        MessageBox.Show(">" + childRows.Length, "���������� � �����������!",
            //            MessageBoxButtons.OK, MessageBoxIcon.Information);
                
            //    }

            ////    //childRows = dataTable.Rows[1].GetChildRows(relation);

            ////    MessageBox.Show(childTable.Rows.Count.ToString(), "���������� � �����������!",
            ////            MessageBoxButtons.OK, MessageBoxIcon.Information);
            //}

            vbeforeDel();

            // ������ ��� �������� ������� �����������, ������ ��� ������
            setEnabletInputsElements(false);

            dialog.Text = "������� ������...";
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
                    MessageBox.Show("��� �������!\n\n" + ex.ToString(), "������ �����!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                    // ������ ��� �������� ������� ����� ���������
                    setEnabletInputsElements(true);

                    vafterDel();
                    return;
                }
              
            }

            // ������ ��� �������� ������� ����� ���������
            setEnabletInputsElements(true);

            vafterDel();
        }

        // ���������� ����������� ����� ���������
        protected void setEnabletInputsElements( bool b)
        {
            foreach (Control c in dialog.groupBox.Controls){
                if (c is TextBox || c is ComboBox || c is CheckBox || c is NumericUpDown)
                        c.Enabled = b;  
            }
        }

        // ��������� � ��������
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
                MessageBox.Show("��������� ���� �������� ������� ������!\n\n" + ex.ToString(), "������ �����!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                bindingSource.CancelEdit();
            }
            catch (ConstraintException ex)
            {
                MessageBox.Show("��������� ���� �������� �� ���������!\n\n" + ex.ToString(), "������ �����!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                bindingSource.CancelEdit();
            }
            catch (NoNullAllowedException ex)
            {
                MessageBox.Show("��������� ���� ������� ���� �������!\n\n" + ex.ToString(), "������ �����!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                bindingSource.CancelEdit();
            }

            try
            {
                vvupdate();
            }
            catch (System.Data.SqlClient.SqlException ex)
            {
                MessageBox.Show("�������� �������� ������� �����������!\n"
                + "�������� ���� ��� ��������� ��������� ������� ������������ � ������ ��������."
                + "\n\n" + ex.ToString(), "������ �����!",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);

                refresh();
            }
            catch (DBConcurrencyException ex)
            {
                MessageBox.Show("�������� �� ������� ������� ������\n\n" + ex.ToString(), "������ �����!",
                       MessageBoxButtons.OK, MessageBoxIcon.Error);

                refresh();
            }
        }


        // ������� ��������� � �������� �� ���� ������
        protected virtual void vrefresh() { }
        public void refresh()
        {
            this.bindingSource.Filter = ""; // ������ �������� 
                                            // ������������� ������ �� ������� 
                                            // �� ������ ���������
            vrefresh();
           
        }

        //�������� ������ � �������� ��������� ��������� 
        private void resetButton_Click(object sender, EventArgs e)
        {
            refresh();
        }


        // ��������� ������� ������������ ����
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


        // ����������� ������� ComboBox
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


        // ����������� TextBox
        protected void setupTextBox(TextBox tb, string value, int maxLength, CharacterCasing cCasting)
        {
            tb.DataBindings.Add(new System.Windows.Forms.Binding("Text", bindingSource, value, true));
            tb.MaxLength = maxLength;
            tb.CharacterCasing = cCasting;
        }

        // ����������� CheckBox
        protected void setupCheckBox(CheckBox cb, string value)
        {
            cb.DataBindings.Add(new System.Windows.Forms.Binding("CheckState", bindingSource, value, true));
        }

        // ����������� NumericUpDown
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

            //MessageBox.Show("������� ������� �����!\n\n", "��������� �������!",
            //                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
        }

        //private void numericValueChanged(object sender, EventArgs e)
        //{
        //    if (!(sender is NumericUpDown))
        //        return;

        //    NumericUpDown nud = (NumericUpDown)sender;
        //}

        // ����������� Date
        protected void setupDateTimePicker(DateTimePicker dtp, string value, DateTimePickerFormat format)
        {
            dtp.DataBindings.Add(new System.Windows.Forms.Binding("Text", bindingSource, value, true));

            dtp.Format = format;
        }

     
    }
}
