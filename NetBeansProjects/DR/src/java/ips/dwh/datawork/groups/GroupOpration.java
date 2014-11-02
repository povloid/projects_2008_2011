package ips.dwh.datawork.groups;

import ips.dwh.config.Configs;
import ips.dwh.datawork.DataWork;
import ips.dwh.datawork.DataWork.ConnCont;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GroupOpration
 */
public class GroupOpration extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public GroupOpration() {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String idS = request.getParameter("ID");
            String parentIdS = request.getParameter("PARENT_ID");
            String operation = request.getParameter("OPERATION");
            String keyName = request.getParameter("KEY_NAME");
            String description = request.getParameter("DESCRIPTION");

            System.out.println(keyName);

            if (operation.equals("add_group")) {
                // Добавить группу

                DataWork.ConnCont cc = new ConnCont();
                int newId = DataWork.addGroup(cc, parentIdS, keyName,
                        description);

                String parentPath = DataWork.getSectionPath(parentIdS + "");
                parentPath = parentPath == null ? "" : parentPath + "/";

                String dirPath = Configs.getDocRoot() + "/" + parentPath + newId;

                File newDir = new File(dirPath);
                if (newDir.mkdirs()) {
                    cc.commitAndClose();
                    System.out.println("Создана директория:" + dirPath);
                } else {
                    cc.rollbackAndClose();
                    throw new Exception("Секция " + newDir + " не создана");
                }

            } else if (operation.equals("edit_group")) {
                // Редактировать группу
                DataWork.editGroup(idS, parentIdS, keyName, description);
            } else if (operation.equals("del_group")) {
                // Удалить группу
                String dirPath = Configs.getDocRoot() + "/" + DataWork.getSectionPath(idS + "");
                File dir = new File(dirPath);
                DataWork.ConnCont cc = new ConnCont();
                DataWork.delGroup(cc, idS);
                if (dir.delete()) {
                    System.out.println("Удалена директория:" + dirPath);
                    cc.commitAndClose();
                } else {
                    cc.rollbackAndClose();
                    throw new Exception("Секция " + dir + " не удалена");
                }
            }

            response.sendRedirect(response.encodeRedirectURL("../DataWork2.jsp?op=update&parent=" + parentIdS));

        } catch (Exception e) {
            response.sendError(1000, "Во время выполнения возникла ошибка:\n" + e.getMessage());
        }

    }
}
