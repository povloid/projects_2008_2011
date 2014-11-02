/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.document.repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author kopychenko
 */
public class PutFile extends HttpServlet {


    // Константы
    public static final String DOC_REPOSITORY = "/home/kopychenko/opt/ips_doc_rep";
    public static final String DOC_TMP = DOC_REPOSITORY + "/tmp";
    public static final String DOC_ROOT = DOC_REPOSITORY + "/root";


    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PutFile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PutFile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println("IS MULTIPART: " + isMultipart);


        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        System.out.println(getServletContext().getRealPath("/"));

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);

        // Set factory constraints
        factory.setSizeThreshold(30);
        factory.setRepository(new File(DOC_TMP));

        System.out.println("TEMPORY REPOSITORY: " + factory.getRepository());
        

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        //upload.setSizeMax(yourMaxRequestSize);

        try {
            // Parse the request
            List items = upload.parseRequest(request); /* FileItem */

            System.out.println("SIZE: " + items.size());

            Iterator iter = items.iterator();
            while (iter.hasNext()) { // Перебираем параметры
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) { // Служит для чтения простых полей формы

                    String name = item.getFieldName();
                    String value = item.getString();

                    System.out.println("NAME: " + name);
                    System.out.println("VALUE: " + value);


                } else {

                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

                    System.out.println("FIELD NAME: " + fieldName);
                    System.out.println("FILE NAME: " + fileName);
                    System.out.println("CONTENT TYPE: " + contentType);
                    System.out.println("IS IN MEMORY: " + isInMemory);
                    System.out.println("SIZE IN BYTES: " + sizeInBytes);

                    File uploadedFile = new File(DOC_ROOT + "/" + fileName);
                    item.write(uploadedFile);
                }
            }


        } catch (Exception ex) {
            Logger.getLogger(PutFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
