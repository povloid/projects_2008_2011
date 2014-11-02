/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.buffer;

import ips.dwh.config.Configs;
import ips.dwh.datawork.DataWork;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
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
public class PasteFromBuffer extends HttpServlet {

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

        try {

            ips.dwh.buffer.Buffer buffer = (Buffer) request.getSession().getAttribute("buffer");

            String parent = request.getParameter("parent");


            String toDirPath0 = null;
            if (parent.equals("root")) {
                toDirPath0 = "";
            } else {
                toDirPath0 = "" + DataWork.getSectionPath(parent + "");
            }

            String toDirPath = Configs.getDocRoot() + "/" +toDirPath0;


            // Перемещение документов
            for (int i : buffer.getSelectedBoxes()) {

                // Получаем данные по коробке
                DataWork.Result r = DataWork.getBox(request.getRemoteUser(), i + "");
                ResultSet rs = r.getRs();

                if (rs.next()) {
                    String repFilePath = rs.getString("REP_FILE_PATH");

                    File file = new File(Configs.getDocRoot() + "/" + repFilePath);
                    System.out.println(">>>" + file);

                    File toDir = new File(toDirPath);
                    System.out.println(">>>" + toDirPath);

                    boolean success = file.renameTo(new File(toDir, file.getName()));

                    if (success) {
                        // Теперь в базе
                        DataWork.movBoxToSection(i + "", parent);
                        DataWork.addBoxEnd(i + "", toDirPath0 + "/" + i + "f");
                        System.out.println("Перемещен документ " + i + " в секцию " + parent);
                    }
                }

                r.closeAll();

            }

            buffer.getSelectedBoxes().clear();

            System.out.println("Перемещения документов закончены");

            // Перемещение секций
            for (int i : buffer.getSelectedSections()) {

                // Начать транзакцию в базе

                // Получить текущий путь к папке
                String dirPath = Configs.getDocRoot() + "/" + DataWork.getSectionPath(i + "");


                System.out.println(">>>" + toDirPath);

                File dir = new File(dirPath);
                File toDir = new File(toDirPath);

                boolean success = dir.renameTo(new File(toDir, dir.getName()));

                // Теперь закончить транзакцию в базе

                // Теперь в базе
                if (success) {
                    DataWork.movSectionToSection(i + "", parent);
                    System.out.println("Перемещена секция " + i + " в секцию " + parent);
                }
            }

            buffer.getSelectedSections().clear();

            System.out.println("Перемещения секций закончены");


            response.sendRedirect(response.encodeRedirectURL("../DataWork2.jsp?op=update&parent=" + parent));

        } catch (Exception ex) {
            Logger.getLogger(PasteFromBuffer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
