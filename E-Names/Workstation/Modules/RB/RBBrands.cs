using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Microsoft.ReportingServices.ReportProcessing;

namespace Workstation.Modules.RB
{
    public partial class RBBrands : Workstation.Modules.A.AReferenceBook
    {
        RBBrandsDialog currentDialog;

        public RBBrands(System.Windows.Forms.BindingSource BRANDFAMILYbindingSource,
            System.Windows.Forms.BindingSource THICKCATbindingSource,
            System.Windows.Forms.BindingSource LENGTHCATbindingSource,
            System.Windows.Forms.BindingSource PACKTYPEbindingSource,
            System.Windows.Forms.BindingSource SPECFLAVCbindingSource,
            System.Windows.Forms.BindingSource COMPANYCODEbindingSource)
        {
            InitializeComponent();

            currentDialog = new RBBrandsDialog();
            setupChildElements(this.phmkDataSet.BRANDS, this.bRANDSBindingSource, this.dataGridView, currentDialog);

            // Настраиваем элементы

            // С отображаемыми описаниями
            //setupComboBox(currentDialog.BRANDFAMILYComboBox, BRANDFAMILYbindingSource, "BRANDFAMILY", "DESCR", "PMCODE");
            //setupComboBox(currentDialog.THICKCATComboBox, THICKCATbindingSource, "THICKCAT", "DESCR", "CATEGORY");
            //setupComboBox(currentDialog.LENGTHCATComboBox, LENGTHCATbindingSource, "LENGTHCAT", "DESCR", "CATEGORY");
            //setupComboBox(currentDialog.PACKTYPEComboBox, PACKTYPEbindingSource, "PACKTYPE", "DESCR", "TYPE");
            //setupComboBox(currentDialog.SPECFLAVCComboBox, SPECFLAVCbindingSource, "SPECFLAVC", "DESCR", "CODE");
            //setupComboBox(currentDialog.COMPANYCODEComboBox, COMPANYCODEbindingSource, "COMPANYCODE", "DESCRIPTION", "PMCODE");

            // С отображаемыми вносимыми реально значениями
            setupComboBox(currentDialog.BRANDFAMILYComboBox, BRANDFAMILYbindingSource, "BRANDFAMILY", "PMCODE", "PMCODE");
            setupComboBox(currentDialog.THICKCATComboBox, THICKCATbindingSource, "THICKCAT", "CATEGORY", "CATEGORY");
            setupComboBox(currentDialog.LENGTHCATComboBox, LENGTHCATbindingSource, "LENGTHCAT", "CATEGORY", "CATEGORY");
            setupComboBox(currentDialog.PACKTYPEComboBox, PACKTYPEbindingSource, "PACKTYPE", "TYPE", "TYPE");
            setupComboBox(currentDialog.SPECFLAVCComboBox, SPECFLAVCbindingSource, "SPECFLAVC", "CODE", "CODE");
            setupComboBox(currentDialog.COMPANYCODEComboBox, COMPANYCODEbindingSource, "COMPANYCODE", "PMCODE", "PMCODE");

            setupNumericUpDown(currentDialog.MARKBCNumericUpDown, "MARKBC", new decimal( new int[] { 99999999,0,0,0}));
            setupNumericUpDown(currentDialog.ITEMSPACKNumericUpDown, "ITEMSPACK", new decimal(new int[] { 9999, 0, 0, 0 }));

            setupCheckBox(currentDialog.MENTHINDCheckBox, "MENTHIND");
            setupCheckBox(currentDialog.FILTINDCheckBox, "FILTIND");


            setupTextBox(currentDialog.BRANDDIFTextBox, "BRANDDIF", 50, CharacterCasing.Upper);
            setupTextBox(currentDialog.MBLONGDESCRTextBox, "MBLONGDESCR", 100, CharacterCasing.Upper);
            setupTextBox(currentDialog.BLENDTCTextBox, "BLENDTC", 50, CharacterCasing.Upper);

        }

        // Реализация полного сброса последних изменений и обновления
        protected override void vrefresh()
        {
            this.bRANDSTableAdapter.Fill(this.phmkDataSet.BRANDS);
        }

        // Реализациа подтверждения изменений
        public override void vvupdate()
        {
            this.bRANDSTableAdapter.Update(this.phmkDataSet.BRANDS);
        }

    }
}

