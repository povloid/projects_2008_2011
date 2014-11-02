/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.planer.struts;

import ips.planer.dao.IPS_ODaoImpl;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author kopychenko
 */
public class AEDActionForm extends org.apache.struts.action.ActionForm {

    private String op;
    private int id;
    private int depId;
    private String question;
    private String description;
    private int webUserId;
    private int fromDepId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getFromDepId() {
        return fromDepId;
    }

    public void setFromDepId(int fromDepId) {
        this.fromDepId = fromDepId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(int webUserId) {
        this.webUserId = webUserId;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    /**
     *
     */
    public AEDActionForm() {
        super();
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }


        String ops = request.getParameter("op");

        if (ops.equals("edit") || ops.equals("del")) {

            int qId = Integer.parseInt(request.getParameter("id"));

            IPS_ODaoImpl.Result result = null;
            try {

                result = IPS_ODaoImpl.getQuestion(qId);
                ResultSet rs = result.getRs();
                rs.next();

                this.question = rs.getString("QUESTION");
                this.description = rs.getString("DESCRIPTION");

            } catch (Exception ex) {
                Logger.getLogger(AEDActionForm.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                result.closeAll();
            }
        }

        super.reset(mapping, request);
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }


        ActionErrors errors = new ActionErrors();

        if (getQuestion() == null || getQuestion().length() < 1) {
            errors.add("question", new ActionMessage("errors.question"));
        }

        System.out.println(depId);

        return errors;
    }
}
