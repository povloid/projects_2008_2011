/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.op.addfile;

import ips.dwh.config.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author kopychenko
 */
public class AddFileActionForm extends org.apache.struts.action.ActionForm {

    protected FormFile _file;

    public FormFile getMyFile() {
        return _file;
    }

    public void setMyFile(FormFile f) {
        _file = f;
    }

    /**
     *
     */
    public AddFileActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();
        InputStream fis = null;
        FileOutputStream fos = null;
        FormFile fFile = null;
        try {

            fFile = getMyFile();
            System.out.println("Запись файла --------- ");
            System.out.println(fFile.getFileSize());
            System.out.println(fFile.getFileName());
            fis = fFile.getInputStream();
            // Now open the file
            File file = new File(Config.getDocRoot() + "/" + 1111);

            fos = new FileOutputStream(file);
            // Copy the data from the file to the large object
            byte[] buf = new byte[2048];
            int s;
            while ((s = fis.read(buf, 0, 2048)) > 0) {
                fos.write(buf, 0, s);
            }

            System.out.println(".");

            return errors;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddFileActionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddFileActionForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fFile != null) {
                    fFile.destroy();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(AddFileActionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            return errors;
        }
    }


}
