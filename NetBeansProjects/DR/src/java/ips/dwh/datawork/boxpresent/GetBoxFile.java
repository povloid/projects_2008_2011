/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.datawork.boxpresent;

import ips.dwh.config.Configs;
import ips.dwh.datawork.DataWork;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pk
 */
public class GetBoxFile extends HttpServlet {

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
            /* 
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetBoxFile</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetBoxFile at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
        }
    }

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
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;

        String idS = request.getParameter("id");
        String userName = request.getRemoteUser();
        String fileName = null;
        String repFilePath = null;

        try {
            {
                String s0 = DataWork.getBoxRepFilePath(idS, userName);
                String[] ss = s0.split(";");
                repFilePath = ss[0];
                fileName = ss[1];
            }
            stream = response.getOutputStream();
            File file = new File(Configs.getDocRoot() + "/" + repFilePath);

            response.setContentLength((int) file.length());

            response.setContentType("charset=UTF-8");
            response.addHeader("Content-Disposition",
                    "attachment; filename=" + fileName);


            FileInputStream input = new FileInputStream(file);
            buf = new BufferedInputStream(input);

            int readBytes = 0;

            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }

        } catch (Exception ex) {
            //throw new ServletException(ex.getMessage());
            Logger.getLogger(GetBoxFile.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(2000, ex.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }
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
