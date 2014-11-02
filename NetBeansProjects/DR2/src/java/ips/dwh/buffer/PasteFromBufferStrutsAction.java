/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.buffer;

import ips.dwh.datawork.DAOImpl;
import ips.dwh.session.DataWork;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kopychenko
 */
public class PasteFromBufferStrutsAction extends org.apache.struts.action.Action {

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


        DataWork dwBean = (DataWork) request.getSession().getAttribute("dateWork");
        ips.dwh.buffer.Buffer buffer = (Buffer) request.getSession().getAttribute("buffer");

        if (dwBean != null && buffer != null) {
            //String parent = request.getParameter("parentId");

            // Перемещение документов
            for (int i : buffer.getSelectedBoxes()) {
                // Получаем данные по коробке
                DAOImpl.movBoxToSection(i, dwBean.getParentId());
                System.out.println("Перемещен документ " + i + " в секцию " + dwBean.getParentId());
            }

            buffer.getSelectedBoxes().clear();


            System.out.println("Перемещения документов закончены");

            // Перемещение секций
            for (int i : buffer.getSelectedSections()) {
                // Получить текущий путь к папке
                DAOImpl.movSectionToSection(i, dwBean.getParentId());
                System.out.println("Перемещена секция " + i + " в секцию " + dwBean.getParentId());
            }

            buffer.getSelectedSections().clear();
            System.out.println("Перемещения секций закончены");

        }

        return mapping.findForward(SUCCESS);
    }
}
