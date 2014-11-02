/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.op.sections;

import ips.dwh.datawork.DAOImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kopychenko
 */
public class SectionOpStrutsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    //private static final String FAILURE = "failure";

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


        // extract user data
        SectionOpActionForm formBean = (SectionOpActionForm) form;

        String op = formBean.getOp();

        if (op.equals("add")) {

            DAOImpl.addSection(formBean.getParentId(), formBean.getKeyName(), formBean.getDescription());

            System.out.print("#>add section ");
        } else if (op.equals("edit")){

            DAOImpl.editSection(formBean.getId(),
                    formBean.getKeyName(), formBean.getDescription());

            System.out.print("#>edit section ");
        } else if (op.equals("del")) {
            DAOImpl.delSection(formBean.getId());
            System.out.print("#>del section ");
        }


        System.out.println(">>> op=" + formBean.getOp() +
                "; id=" + formBean.getId() +
                "; parentId=" + formBean.getParentId());


        return mapping.findForward(SUCCESS);
    }
}
