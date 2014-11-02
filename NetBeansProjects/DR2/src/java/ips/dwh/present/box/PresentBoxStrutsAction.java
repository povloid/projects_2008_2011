/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.present.box;

import ips.dwh.config.Config;
import ips.dwh.datawork.DAOImpl;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kopychenko
 */
public class PresentBoxStrutsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        ServletOutputStream stream = null;
        BufferedInputStream buf = null;

        String userName = request.getRemoteUser();
        //String fileName = null;
        String repFilePath = null;

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            DAOImpl.BoxFile boxFile = DAOImpl.getBoxFile(id);

            System.out.println("Передается фаил по запросу: " + boxFile.getFileName());

            stream = response.getOutputStream();
            File file = new File(Config.getDocRoot() + "/" + boxFile.getRepFilePath());

            response.setContentLength((int) file.length());

            response.setContentType("charset=UTF-8");
            response.addHeader("Content-Disposition",
                    "attachment; filename=" + boxFile.getFileName());


            FileInputStream input = new FileInputStream(file);
            buf = new BufferedInputStream(input);

            int readBytes = 0;

            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }

        } catch (Exception ex) {
            //throw new ServletException(ex.getMessage());
            Logger.getLogger(PresentBoxStrutsAction.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(2000, ex.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }


        return mapping.findForward(SUCCESS);
    }
}
