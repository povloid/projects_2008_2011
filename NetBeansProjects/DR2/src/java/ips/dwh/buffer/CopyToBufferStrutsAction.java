/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.buffer;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kopychenko
 */
public class CopyToBufferStrutsAction extends org.apache.struts.action.Action {

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


        System.out.println("CopyToBufferStrutsAction");

        // Поднимаем бин буффера
        ips.dwh.buffer.Buffer buffer = (Buffer) request.getSession().getAttribute("buffer");


        if (buffer != null) {
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

            // Работаем с коробками
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
        }
        return mapping.findForward(SUCCESS);
    }
}
