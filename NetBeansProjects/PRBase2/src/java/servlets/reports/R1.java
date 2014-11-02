package servlets.reports;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ips.prbase.dao.DAOImpl;
import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import ips.prbase.model.attributes.interfaces.IDateAttribute;
import ips.prbase.model.attributes.interfaces.IImageAttribute;
import ips.prbase.model.attributes.interfaces.IIntAttribute;
import ips.prbase.model.attributes.interfaces.IRefferenceAttribute;
import ips.prbase.model.attributes.interfaces.ITextAttribute;
import ips.prbase.model.objects.interfaces.impl.Person;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;

/**
 *
 * @author kopychenko
 */
public class R1 extends HttpServlet {

    /**
     * Создание структуры данных
     * @param id
     * @return
     * @throws Exception
     */
    private JRDataSource createReportDataSource(HttpServletRequest request , int id) throws Exception {
        JRMapArrayDataSource dataSource;
        Map[] reportRows = initializeMapArray(request,id);
        dataSource = new JRMapArrayDataSource(reportRows);
        return dataSource;
    }

    /**
     * Инициализация данных
     * @param id
     * @return
     * @throws Exception
     */
    private Map[] initializeMapArray(HttpServletRequest request, int id) throws Exception {


        Locale locale = request.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("ips.prbase.i18n.LabelsBundle");

        // Поднимаем объект
        Person person = new Person(id);
        DAOImpl.loadObjectDescriptor(person);

        HashMap[] rows = new HashMap[person.getOpenBlock().length + person.getHiddenBlock().length];

        int i = -1;
        // Обрабатываем открытый блок
        for (IBasicAttribute iba : person.getOpenBlock()) {
            rows[++i] = createRow(bundle, 1, "Открытый блок", iba);
        }

        // Обрабатываем закрытый блок
        for (IBasicAttribute iba : person.getHiddenBlock()) {
            rows[++i] = createRow(bundle, 2, "Закрытый блок", iba);
        }

        return rows;
    }

    /**
     * Создать строку
     * @param block
     * @param iba
     * @return
     */
    private HashMap createRow(ResourceBundle bundle, int block, String blockName, IBasicAttribute iba) throws Exception {




        HashMap<String, Object> hm = new HashMap<String, Object>();

        hm.put("block", block);
        hm.put("blockName", blockName);

        hm.put("caption", bundle.getString(iba.getKeyName()));

        if (iba instanceof ITextAttribute) {
            hm.put("vtext", ((ITextAttribute) iba).getText());
        } else if (iba instanceof IIntAttribute) {
            hm.put("vtext", ((IIntAttribute) iba).getInt());
        } else if (iba instanceof IDateAttribute) {
            hm.put("vtext", ((IDateAttribute) iba).getDate());
        } else if (iba instanceof IRefferenceAttribute) {
            hm.put("vtext", ((IRefferenceAttribute) iba).getKeyName());
        } else if (iba instanceof IImageAttribute) {
            IImageAttribute iia = (IImageAttribute) iba;
            hm.put("vimage", DAOImpl.getImage(iia.getImageId()));
        }



        return hm;
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletOutputStream servletOutputStream = response.getOutputStream();
        InputStream reportStream = null;

        try {

            //
            int id = Integer.parseInt(request.getParameter("id"));

            reportStream =
                    getServletConfig().getServletContext().getResourceAsStream("/resources/reptemplites/report1.jasper");

            JRDataSource dataSource = createReportDataSource(request,id);

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition",
                    "attachment; filename=personality-N"+id+".pdf");


            HashMap pars = new HashMap();
            pars.put("CAPTION", "Досье №" + id);
            pars.put("PN", "Досье №" + id);

            JasperRunManager.runReportToPdfStream(reportStream,
                    servletOutputStream, pars, dataSource);

//            JExcelApiExporter xlsExporter = new JExcelApiExporter();

        } catch (Exception ex) {
            Logger.getLogger(R1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            reportStream.close();
            servletOutputStream.flush();
            servletOutputStream.close();
        }

    }
}
