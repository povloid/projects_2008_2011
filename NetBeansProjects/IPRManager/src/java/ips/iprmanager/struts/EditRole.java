/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.iprmanager.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import ips.iprmanager.dao.DAOImpl;
import ips.iprmanager.model.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author kopychenko
 */

public class EditRole extends ActionSupport
        implements Preparable, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }



    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public EditRole() {
    }

    @Override
    public String execute() throws Exception {

        // Проверки -----------------------------------------------------------
        
        // Имя пользователя
        if(role.getKeyName() == null || role.getKeyName().length() == 0){
            addFieldError("role.keyName", "Незадано наименование роли");
            return ERROR;
        }

        // --------------- EXECUTE -------------------
        if(role.getId() == 0){ // ADD NEW ROLE
            System.out.println("ADD NEW ROLE");

            DAOImpl.addNewRole(role);


        } else if (role.getId() > 0 && !deleteF){ // EDIT OR DELETE ROLE
            System.out.println("EDIT ROLE");

            DAOImpl.editRole(role);

        } else if (deleteF){
            
            System.out.println("DEL ROLE");

            DAOImpl.delRole(role);

        }


        return SUCCESS;
    }



    public void prepare() throws Exception {
        System.out.println("------------ prepere -------------");
    }




    /**
     * Добавить пользователя
     * @return
     * @throws Exception
     */
    public String addRole() throws Exception {

        role = new Role();


        return INPUT;
    }

    /**
     * Редактировать пользователя
     * @return
     * @throws Exception
     */
    public String editRole() throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        role = DAOImpl.loadRole(id);


        return INPUT;
    }


    /**
     * Флаг удаления
     */
    private boolean deleteF = false;

    public boolean isDeleteF() {
        return deleteF;
    }

    public void setDeleteF(boolean deleteF) {
        this.deleteF = deleteF;
    }


    /**
     * Удаление роли
     * @return
     * @throws Exception
     */
    public String delRole() throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        role = DAOImpl.loadRole(id);

        deleteF = true;

        return INPUT;
    }

}