/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.planer.servlets.qwork;

import ips.planer.dao.jpa.Deps;
import ips.planer.dao.jpa.DepsJpaController;
import ips.planer.dao.jpa.Questions;
import ips.planer.dao.jpa.QuestionsJpaController;
import ips.planer.dao.jpa.WebUsers;
import ips.planer.dao.jpa.WebUsersJpaController;
import java.io.IOException;
import java.util.Date;
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
public class AEDQuestion extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        // Получаем код операции

        String op = request.getParameter("OP");

        try {

            // Перебираем все отделы
            DepsJpaController depsCont = new DepsJpaController();
            QuestionsJpaController qCont = new QuestionsJpaController();
            WebUsersJpaController usersCont = new WebUsersJpaController();

            WebUsers user = usersCont.findWebUsersEntityForName(request.getRemoteUser());


            if (op.equals("add")) { // Добавить вопрос
                Deps depId = depsCont.findDeps(Integer.parseInt(request.getParameter("DEP")));

                qCont.create(new Questions(
                        request.getParameter("QUESTION"),
                        request.getParameter("DESCRIPTION"),
                        new Date(),
                        new Date(),
                        depId,
                        user.getDepId(),
                        user));

            } else if (op.equals("edit")) {

                Questions q = qCont.findQuestions(Integer.parseInt(request.getParameter("ID")));

                q.setQuestion(request.getParameter("QUESTION"));
                q.setDescription(request.getParameter("DESCRIPTION"));

                qCont.edit(q);

            } else if (op.equals("del")) {
                System.out.println("111111111111111111111111111");
                qCont.destroy(Integer.parseInt(request.getParameter("ID")));
                System.out.println("111111111111111111111111111");
            }


        } catch (Exception ex) {
            Logger.getLogger(AEDQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
        }

        response.sendRedirect(response.encodeRedirectURL("plan.jsp"));
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
