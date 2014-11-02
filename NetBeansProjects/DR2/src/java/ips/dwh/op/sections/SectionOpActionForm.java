/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.op.sections;

import ips.dwh.datawork.DAOImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * 
 * @author kopychenko
 */
public class SectionOpActionForm extends org.apache.struts.action.ActionForm {

    private String op;
    private int id;
    private Integer parentId;
    private String keyName;
    private String description;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        if (parentId.intValue() != 0) {
            this.parentId = parentId;
        } else {
            this.parentId = null;
        }
    }

    /**
     *
     */
    public SectionOpActionForm() {
        super();
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        String ops = request.getParameter("op");


        //description = "...";

        if (ops.equals("edit") || ops.equals("del")) {

            Integer tId = Integer.parseInt(request.getParameter("id"));


            try {

                DAOImpl.Result result = DAOImpl.getSection(tId);

                ResultSet rs = result.getRs();

                if(rs.next()){
                    this.id = rs.getInt("ID");
                    this.parentId = (Integer) rs.getObject("parent_id");
                    this.keyName = rs.getString("key_name");
                    this.description = rs.getString("description");
                }

                result.closeAll();
            } catch (Exception ex) {
                Logger.getLogger(SectionOpActionForm.class.getName()).log(Level.SEVERE, null, ex);
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
        ActionErrors errors = new ActionErrors();

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        if (getKeyName() == null || getKeyName().length() < 1) {
            errors.add("keyName", new ActionMessage("errors.keyname"));
        }

        return errors;
    }


    /**
     * Получить новый каталог
     * @return
     */
    private final String getNewRepoFolderName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH");
        Date nowDate = new Date();
        return sdf.format(nowDate);
    }

}
