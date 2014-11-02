/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import ips.planer.dao.IPS_ODaoImpl;
import ips.planer.struts.AEDActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kopychenko
 */
public class AEDAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";





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

//        request.setCharacterEncoding("UTF-8");
//
//        response.setContentType("text/html;charset=UTF-8");

        // extract user data
        AEDActionForm formBean = (AEDActionForm) form;

        String op = formBean.getOp();

        if (op.equals("add")) {

            IPS_ODaoImpl.addQuestion(formBean.getDepId(),
                    formBean.getQuestion(), formBean.getDescription(),
                    formBean.getWebUserId(), formBean.getFromDepId());

        } else if (op.equals("edit")) {

            IPS_ODaoImpl.editQuestion(formBean.getId(),
                    formBean.getQuestion(), formBean.getDescription());

        } else if (op.equals("del")) {

            IPS_ODaoImpl.delQuestion(formBean.getId());

        }


        System.out.println(formBean.getQuestion());


        return mapping.findForward(SUCCESS);
    }
}
