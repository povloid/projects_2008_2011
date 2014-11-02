/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.conf;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import javax.swing.UIManager;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.AErrorDialog;
import minersinstrument.ui.AHelpDialog;
import minersinstrument.ui.ASimpleCloseDialog;
import minersinstrument.ui.AUniversalAddDialog;
import minersinstrument.ui.AUniversalDelDialog;
import minersinstrument.ui.AUniversalEditDialog;
import minersinstrument.ui.AXHeader;
import org.jdesktop.swingx.JXLoginPane;

/**
 *
 * @author PKopychenko
 */
public class GUISettings {

    private static final String lafSettingsFile = "laf.xml";

    public static void init() {

        // Установка локали
        Locale.setDefault(new Locale("ru", "RU"));

        // Настраиваем дилог на русские названия

        //String CLASS_NAME = JXLoginPane.class.getCanonicalName();
        String CLASS_NAME = JXLoginPane.class.getSimpleName();
        //loginDialog.getPanel().setBannerText("Вход");
        UIManager.put(CLASS_NAME + ".bannerString", "Вход");
        UIManager.put(CLASS_NAME + ".nameString", "Имя:");
        UIManager.put(CLASS_NAME + ".passwordString", "Пароль:");
        UIManager.put(CLASS_NAME + ".loginString", "Вход");
        UIManager.put(CLASS_NAME + ".cancelString", "Закрыть");

        // Настройка руссификации JXTABLE   
        UIManager.put("JXTable.column.horizontalScroll", "Горизонтальная прокрутка");
        UIManager.put("JXTable.column.packAll", "Упаковать колонки");
        UIManager.put("JXTable.column.packSelected", "Упаковать выбраннную колонку");

        // Руссификация JXDatePicker                                                                                          
        UIManager.put("JXDatePicker.linkFormat", "Сегодня {0,date, dd MMMM yyyy}");
        UIManager.put("JXDatePicker.longFormat", "EEE dd.MM.yyyy");
        UIManager.put("JXDatePicker.mediumFormat", "dd.MM.yyyy");
        UIManager.put("JXDatePicker.shortFormat", "MM.dd");
        UIManager.put("JXDatePicker.numColumns", "10");

        // Установка стиля
        AppLookAndFeelSettings laf;

        try {

            FileInputStream in = new FileInputStream(lafSettingsFile);
            XMLDecoder xmlDecoder = new XMLDecoder(in);
            laf = (AppLookAndFeelSettings) xmlDecoder.readObject();
            xmlDecoder.close();

            setLookAndFeel(laf.getLaf());

        } catch (Exception ex) {
        }

        // Настраиваим свои иконки для MinersStruments
        AHelpDialog.iconPath = "/tradeterminal/icons/TT_icons/32X32/spravka.png";

        AErrorDialog.iconPath = "/tradeterminal/icons/TT_icons/32X32/error.png";
        AXHeader.iconPath = "/tradeterminal/icons/TT_icons/16X16/spravka.png";

        ADialog.okIconPath = "/tradeterminal/icons/TT_icons/32X32/primenit.png";
        ADialog.cancelIconPath = "/tradeterminal/icons/TT_icons/32X32/cancel.png";

        ASimpleCloseDialog.iconPath = "/tradeterminal/icons/TT_icons/32X32/del.png";
        AUniversalAddDialog.iconPath = "/tradeterminal/icons/TT_icons/32X32/additional/5.png";
        AUniversalDelDialog.iconPath = "/tradeterminal/icons/TT_icons/32X32/additional/4.png";
        AUniversalEditDialog.iconPath = "/tradeterminal/icons/TT_icons/32X32/additional/3.png";


    }

    public static void setLookAndFeel(String s) {
        if (s != null && s.length() > 0) {

            // Установка темы оформления ----------------
            try {
                // select Look and Feel
                //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

                //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
                //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMangoLookAndFeel");
                //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
                //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMagmaLookAndFeel");

                UIManager.setLookAndFeel(s);


                AppLookAndFeelSettings laf = new AppLookAndFeelSettings(s);

                FileOutputStream out = new FileOutputStream(lafSettingsFile);
                XMLEncoder xmlEncoder = new XMLEncoder(out);
                xmlEncoder.writeObject(laf);
                xmlEncoder.flush();
                xmlEncoder.close();



                //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel");
                //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");

                //UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");

                //UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
                //UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");

                // UIManager.setLookAndFeel("com.digitprop.tonic.TonicLookAndFeel");

                // Comercial
                // UIManager.setLookAndFeel("com.Trendy.swing.plaf.TrendyLookAndFeel");
                //com.jgoodies.looks.plastic.Plastic3DLookAndFeel
                // ++++++ /////////////////////////
                //UIManager.setLookAndFeel("com.incors.plaf.alloy.AlloyLookAndFeel");
                //com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.isLookAndFeelFrameDecoration",
                //        "true");

                //com.incors.plaf.alloy.AlloyTheme theme = null;

                //theme = new com.incors.plaf.alloy.themes.acid.AcidTheme();
                // theme = new com.incors.plaf.alloy.themes.ej.EJTheme();
                //theme = new com.incors.plaf.alloy.themes.bedouin.BedouinTheme();
                //theme = new com.incors.plaf.alloy.themes.glass.GlassTheme();

                //javax.swing.LookAndFeel alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel(theme);
                //javax.swing.UIManager.setLookAndFeel(alloyLnF);

                // GUIUtils.updateAllWidgets();

                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

                //UIManager.setLookAndFeel("com.lipstikLF.LipstikLookAndFeel");
                //com.lipstikLF.LipstikLookAndFeel.setMyCurrentTheme(new com.lipstikLF.theme.DefaultTheme());
            } catch (Exception ex) {
                System.out.print(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
