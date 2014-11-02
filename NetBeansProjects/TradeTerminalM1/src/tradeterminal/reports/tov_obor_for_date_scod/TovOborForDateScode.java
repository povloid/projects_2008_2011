/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.reports.tov_obor_for_date_scod;

import java.sql.Types;
import java.text.DateFormat;
import java.util.HashMap;
import minersinstrument.db.ADBProc;
import minersinstrument.db.ADBProcParametr;
import minersinstrument.db.PADBUtils;
import minersinstrument.db.PADBUtils.PADBResult;
import minersinstrument.ui.ADialog;
import minersinstrument.ui.AUniversalDialog;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import tradeterminal.Setup;
import tradeterminal.reports.IReport;

/**
 *
 * @author kopychenko
 */
public class TovOborForDateScode implements IReport {

    private DateFormat df = DateFormat.getDateInstance();

    public JRViewer makeReport() throws Exception {

        ProductDPanel p = new ProductDPanel();

        AUniversalDialog d = new AUniversalDialog(p, null, true);
        d.setTitleIcon(new javax.swing.ImageIcon(getClass().getResource("/tradeterminal/icons/TT_icons/32X32/reports/4.png")));
        d.setVisible(true);
        d.dispose();

        if (d.getReturnStatus() == ADialog.RET_OK) {

            ADBProc proc = new ADBProc("rpt_tov_obor_for_date_scod");
            proc.addInParametr(
                    new ADBProcParametr(Types.DATE,
                    p.getBeginDate()));

            proc.addInParametr(
                    new ADBProcParametr(Types.DATE,
                    p.getEndDate()));

            proc.addInParametr(
                    new ADBProcParametr(Types.VARCHAR,
                    p.getScode()));

            //PADBUtils.executeVoidProcedure(Setup.getSource(), proc);

            PADBResult result = PADBUtils.getResultSet(Setup.getSource(), proc);


            JasperReport jasperReport =
                    JasperCompileManager.compileReport(
                    getClass().getResource("/tradeterminal/reports/tov_obor_for_date_scod/tov_obor_for_scod.jrxml").openStream());

            HashMap map = new HashMap();

            map.put("SCOD_LABEL_TEXT",
                    "Код товара: " + p.getScode());
            map.put("PAR_LABEL_TEXT",
                    "За период с " + df.format(p.getBeginDate()) + " по " + df.format(p.getEndDate()) + ".");
            {
                ADBProc proc2 = new ADBProc("get_product_path_n_scode");
                proc2.addInParametr(
                    new ADBProcParametr(Types.VARCHAR,
                    p.getScode()));

                String path = PADBUtils.getStringScalar(Setup.getSource(), proc2);

                map.put("TOV_PATH_LABEL_TEXT",
                    "Путь: " + path);
            }


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map,
                    new JRResultSetDataSource(result.getRs()));

            result.close();

            return new JRViewer(jasperPrint);

        } else {
            return null;
        }

    }
}
