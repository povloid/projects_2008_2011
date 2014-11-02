/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.buffer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kopychenko
 */
public class CopyToBuffer extends HttpServlet {

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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CopyToBuffer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CopyToBuffer at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
        //processRequest(request, response);

        //            ips.dwh.buffer.Buffer buffer = (Buffer) request.getSession().getAttribute("buffer");
//            for( Enumeration en = request.getParameterNames();  en.hasMoreElements(); ){
//
//                String parName = (String) en.nextElement();
//
//
//
//                //if(parName.equals("select_section")){
//
//
//
//                //}
//
//            }




        // Поднимаем бин буффера
        ips.dwh.buffer.Buffer buffer = (Buffer) request.getSession().getAttribute("buffer");



        // Работаем с секциями
        {
            List<Integer> sections = buffer.getSelectedSections();
            sections.clear();

            String[] selectedSections = request.getParameterValues("select_section");

            if (selectedSections != null) {

                for (String ss : selectedSections) {
                    sections.add(Integer.parseInt(ss));
                }
            }
        }

        // Работаем с секциями
        {
            List<Integer> boxes = buffer.getSelectedBoxes();
            boxes.clear();

            String[] selectedBoxes = request.getParameterValues("select_box");

            if (selectedBoxes != null) {


                for (String ss : selectedBoxes) {
                    boxes.add(Integer.parseInt(ss));
                }
            }
        }

        String parent = request.getParameter("parent");

        response.sendRedirect(response.encodeRedirectURL("../DataWork2.jsp?op=update&parent=" + parent));


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
