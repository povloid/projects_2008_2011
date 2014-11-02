/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.op.addfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kopychenko
 */
public class AddFileAction extends org.apache.struts.action.Action {

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

        AddFileActionForm formBean = (AddFileActionForm) form;

//        FormFile fFile = formBean.getMyFile();
//
//        System.out.println(fFile.getFileSize());
//        System.out.println(fFile.getFileName());
        

//        InputStream fis = fFile.getInputStream();
//
//        // Now open the file
//        File file = new File(Config.getDocRoot() + "/" + 1111);
//        FileOutputStream fos = new FileOutputStream(file);
//
//        // Copy the data from the file to the large object
//        byte buf[] = new byte[2048];
//        int s, tl = 0;
//        while ((s = fis.read(buf, 0, 2048)) > 0) {
//            fos.write(buf, 0, s);
//            tl += s;
//        }
//
//
//        fFile.destroy();
//
//        fis.close();
//        fos.close();

        System.out.println("2");

        return mapping.findForward(SUCCESS);
    }
}
