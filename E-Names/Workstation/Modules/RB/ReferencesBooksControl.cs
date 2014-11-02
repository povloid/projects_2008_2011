using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using Workstation.Modules.RB;
using Workstation.Modules.A;

namespace Workstation.Modules.RB
{
    public partial class ReferencesBooksControl : UserControl
    {
        AReferenceBook currRefBook = null;
        AReferenceBooks currRefBooks = null;

        Hashtable hash;

        public ReferencesBooksControl()
        {
            InitializeComponent();
                       
            hash = new Hashtable();

            RBBrandFamily rBBrandFamily = new RBBrandFamily();
            hash.Add("brandFamily",rBBrandFamily);

            RBCompanyCode rbCompanyCode = new RBCompanyCode();
            hash.Add("companyCode",rbCompanyCode);

            RBThicknessCategory rbThicknessCategory = new RBThicknessCategory();
            hash.Add("thicknessCategory", rbThicknessCategory);

            RBLengthCategory rbLengthCategory = new RBLengthCategory();
            hash.Add("lengthCategory", rbLengthCategory);

            RBPackType rbPackType = new RBPackType();
            hash.Add("packType", rbPackType);

            RBSpecialFlavorCode rbSpecialFlavorCode = new RBSpecialFlavorCode();
            hash.Add("specialFlavorCode", rbSpecialFlavorCode);

            RBGlobalBlocking rBGlobalBlocking = new RBGlobalBlocking();
            hash.Add("globalBlocking", rBGlobalBlocking);

            RBBrands rBBrands = new RBBrands(rBBrandFamily.bRANDFAMILYBindingSource,
                rbThicknessCategory.tHICKNESSCATEGORYBindingSource,
                rbLengthCategory.lENGTHCATEGORYBindingSource,
                rbPackType.pACKTYPEBindingSource,
                rbSpecialFlavorCode.sPECIALFLAVORCODEBindingSource,
                rbCompanyCode.cOMPANYCODEBindingSource);
            hash.Add("brandVariant", rBBrands);

            RBSignatureType rBSignatureType = new RBSignatureType();
            hash.Add("signatureType", rBSignatureType);

            RBLoyalty rBLoyalty = new RBLoyalty();
            hash.Add("loyalty", rBLoyalty);

            RBLanguage rBLanguage = new RBLanguage();
            hash.Add("language", rBLanguage);

            RBPassportType rBPassportType = new RBPassportType();
            hash.Add("passportType", rBPassportType);

            RBOblast rBOblast = new RBOblast();
            hash.Add("oblast",rBOblast);

            RBRegion rBRegion = new RBRegion(rBOblast.oBLASTBindingSource);
            hash.Add("region",rBRegion);

            RBSettlementType rBSettlementType = new RBSettlementType();
            hash.Add("settlementType", rBSettlementType);

            RBPopulatedPoints rBPopulatedPoints = new RBPopulatedPoints(rBOblast.oBLASTBindingSource,
                rBRegion.rEGIONBindingSource,
                rBSettlementType.sETTLEMENTTYPEBindingSource);
            hash.Add("populatedPoints", rBPopulatedPoints);

            RBStreetType rBStreetType = new RBStreetType();
            hash.Add("streetType", rBStreetType);

            RBStreet rBStreet = new RBStreet(rBStreetType.sTREETTYPEBindingSource);
            hash.Add("street", rBStreet);

            RBQuestionTypes rBQuestionTypes = new RBQuestionTypes();
            hash.Add("questionTypes", rBQuestionTypes);

            RBsQuestions rBsQuestions = new RBsQuestions(rBQuestionTypes.qUESTIONTYPESBindingSource);
            hash.Add("questions", rBsQuestions);

            RBPrizes rBPrizes = new RBPrizes();
            hash.Add("prizes", rBPrizes);

            RBMediaCluster rBMediaCluster = new RBMediaCluster();
            hash.Add("mediaCluster", rBMediaCluster);

            RBMedia rBMedia = new RBMedia(rBMediaCluster.mEDIACLUSTERBindingSource);
            hash.Add("media",rBMedia);

            RBAddressBlocking rBAddressBlocking = new RBAddressBlocking();
            hash.Add("addressBlocking", rBAddressBlocking);

            RBEmailBlocking rBEmailBlocking = new RBEmailBlocking();
            hash.Add("emailBlocking", rBEmailBlocking);

            RBMobilePhoneBlocking rBMobilePhoneBlocking = new RBMobilePhoneBlocking();
            hash.Add("mobilePhoneBlocking", rBMobilePhoneBlocking);

            RBPhoneBlocking rBPhoneBlocking = new RBPhoneBlocking();
            hash.Add("phoneBlocking", rBPhoneBlocking);

            RBCompaign rBCompaign = new RBCompaign();
            hash.Add("compaign", rBCompaign);

            RBResponceChanels rBResponceChanels = new RBResponceChanels();
            hash.Add("responceChanels", rBResponceChanels);

            RBMicrodistrict rBMicrodistrict = new RBMicrodistrict();
            hash.Add("microdistrict", rBMicrodistrict);
            
        }

        private void RBTreeView_AfterSelect(object sender, TreeViewEventArgs e)
        {
            // splitContainer.Panel2.Controls.Clear();
            // Выполняем действия со старым
            
            string s = rBTreeView.SelectedNode.Name.ToString();

            Object obj = hash[s];

            if ( obj != null)
            {
                if (obj is AReferenceBook)
                    setupCurrRefBook(((AReferenceBook)obj));
                else if (obj is AReferenceBooks)
                    setupCurrRB(((AReferenceBooks)obj));
                else if (obj is Control)
                    setupCurrControl(((Control)obj));
            }
            else
                splitContainer.Panel2.Controls.Clear(); // иначе чистим контейнер
        }

        private void setupCurrControl(Control c)
        {
            changePanel2Controls(c);
        }

        // ---------
        private void setupCurrRB(AReferenceBooks rb)
        {
            rb.refresh();
            changePanel2Controls(rb);
            // Назначаем новый текущий виджет
            currRefBooks = rb;
        }

        private void setupCurrRefBook(AReferenceBook rb)
        {
            rb.refresh();
            changePanel2Controls(rb);
            // Назначаем новый текущий виджет
            currRefBook = rb;  
        }
        //------------

        private void changePanel2Controls( Control c)
        {
            splitContainer.Panel2.Controls.Add(c);
            if (splitContainer.Panel2.Controls.Count >= 2) // Если элементов уже много то надо удалить лишние
            {
                List<Control> remuvedControls = new List<Control>();

                // Сначало формироуем список того что надо удалить
                for (int i = 0; i < splitContainer.Panel2.Controls.Count; i++)
                    if (splitContainer.Panel2.Controls[i] != c)
                        remuvedControls.Add(splitContainer.Panel2.Controls[i]);
                // Теперь удаляепм только то что надо
                for (int i = 0; i < remuvedControls.Count; i++)
                    splitContainer.Panel2.Controls.Remove(remuvedControls[i]);
            }
        }
    }
}
