/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeterminal.operations.info;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Types;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import minersinstrument.db.ADBProc;
import minersinstrument.db.ADBProcParametr;
import minersinstrument.db.PADBUtils;
import minersinstrument.db.PADBUtils.PADBResult;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import tradeterminal.Setup;

/**
 *
 * @author pacman
 */
public class OrderInfoM2 {

    public static void showOpInfo(int orderId) {
        try {
            ADBProc proc = new ADBProc("rpt_orders_history_m2_for_id");
            proc.addInParametr(new ADBProcParametr(Types.INTEGER, orderId));
            PADBUtils.executeVoidProcedure(Setup.getSource(), proc);
            PADBResult result = PADBUtils.getResultSet(Setup.getSource(), proc);

            //JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResource("/tradeterminal/reports/orders_history/oinfo.jrxml").openStream());
            ClassLoader cl = OrderInfoM2.class.getClassLoader();
            JasperReport jasperReport = JasperCompileManager.compileReport(cl.getResourceAsStream("tradeterminal/reports/orders_history/oinfo.jrxml"));


            HashMap map = new HashMap();
            map.put("CAPTION_TEXT_REPORT_PARAMS", "по ордеру:");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRResultSetDataSource(result.getRs()));

            result.close();


            JRViewer jv = new JRViewer(jasperPrint);

            JDialog d = new JDialog();
            d.setLayout(new BorderLayout(2, 2));
            d.add(jv, BorderLayout.CENTER);
            d.setMinimumSize(new Dimension(800, 600));
            d.setModal(true);
            d.setVisible(true);
            d.dispose();

        } catch (JRException ex) {
            Logger.getLogger(OrderInfoM2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
