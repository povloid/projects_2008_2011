using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Workstation.Modules.RB
{
    public partial class RBsQuestions : Workstation.Modules.RB.AReferenceBooks
    {
        public RBsQuestions(System.Windows.Forms.BindingSource qUESTIONTYPESBindingSource)
        {
            InitializeComponent();

            //setCap1Text("�������:");
            //setCap2Text("�������� �������:");

            rBsQQuestions.setupComboBoxes(qUESTIONTYPESBindingSource);
            rBsQQuestions.setupRBsQAnswers(rBsQAnswers1);
        }

        // ���������� ������ ����������
        public override void refresh()
        {
            rBsQQuestions.refresh();
        }
    }
}

