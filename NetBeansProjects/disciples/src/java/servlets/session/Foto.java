package servlets.session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import disciples.dao.DAOImpl;
import disciples.model.Disciple;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dev_sport
 */
public class Foto extends HttpServlet {

    private static final long serialVersionUID = 1556L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Foto() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String p = getServletContext().getInitParameter("FOTO_BASE_DIR");
        
        // get the filename from the "file" parameter
        String id = (String) request.getParameter("id");
        if (id == null || id.equals("")) {
            throw new ServletException(
                    "Invalid or non-existent file parameter in Image servlet.");
        }
        
        Disciple disciple = null;
        try {
            disciple = DAOImpl.loadDisciple(Integer.parseInt(id));
        } catch (Exception ex) {
            Logger.getLogger(Foto.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
        
        System.out.println( Integer.parseInt(id) + " -> "+  disciple.getFoto() + " - " + disciple.getFotoContentType());
        


        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {

            stream = response.getOutputStream();
            File pdf = new File(p + "/" + disciple.getFoto());

            // set response headers

            // Get the absolute path of the image

            // Get the MIME type of the image
            String mimeType = disciple.getFotoContentType();
            if (mimeType == null) {
                getServletContext().log("Could not get MIME type of " + disciple.getFoto());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            // Set content type
            response.setContentType(mimeType);

            // response.setContentType("application/pdf");

            // Это раскоментировать если сделать как атачмент
            //response.addHeader("Content-Disposition", "attachment; filename="
            //		+ fileName);

            response.setContentLength((int) pdf.length());

            FileInputStream input = new FileInputStream(pdf);
            buf = new BufferedInputStream(input);
            int readBytes = 0;

            // read from the file; write to the ServletOutputStream
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }

        } catch (IOException ioe) {

            throw new ServletException(ioe.getMessage());

        } finally {

            // close the input/output streams
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }

    }
}
