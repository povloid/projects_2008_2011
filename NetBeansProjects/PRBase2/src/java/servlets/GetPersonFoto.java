/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import ips.prbase.dao.DAOImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kopychenko
 */
public class GetPersonFoto extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetPersonFoto</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetPersonFoto at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */

            String idS = request.getParameter("id");
            System.out.println("Пробуем поднять фаил объект id=" + idS);
            int id = Integer.parseInt(idS);

            //DAOImpl.getOid(response, id);
            DAOImpl.getImage(response, id);

        } catch (Exception ex) {
            Logger.getLogger(GetPersonFoto.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            //out.close();\
            response.getOutputStream().close();
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
