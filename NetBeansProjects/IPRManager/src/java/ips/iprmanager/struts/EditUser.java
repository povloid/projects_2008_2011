/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.iprmanager.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import ips.iprmanager.dao.DAOImpl;
import ips.iprmanager.session.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author kopychenko
 */
public class EditUser extends ActionSupport
        implements Preparable, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EditUser() {
    }

    @Override
    public String execute() throws Exception {

        // Проверки -----------------------------------------------------------
        // Работа с паролем
        if (user.getPassword() != null && user.getPassword2() != null) {
            if (user.getPassword().replace(" ", "").length() == 0) {
                System.out.println("ПАРОЛЬ НЕ ЗАДАН");


            } else if (!user.getPassword().equals(user.getPassword2())) {
                System.out.println("CHECK PASSWORD ERROR!!!");

                addFieldError("user.password", "Подтверждение пароля некорректное");
                addFieldError("user.password2", "");
                return ERROR;
            } else {
                System.out.println("UPDATE PASSWORD");



            }
        }
        
        // Имя пользователя
        if(user.getUserName() == null || user.getUserName().length() == 0){
            addFieldError("user.userName", "Укажите наименование пользователя");
            return ERROR;
        }

        // --------------- EXECUTE -------------------
        if(user.getId() == 0){ // ADD NEW USER
            System.out.println("ADD NEW USER");
            DAOImpl.addNewUser(user);
        } else if (user.getId() > 0 && !deleteF){ // EDIT OR DELETE USER
            System.out.println("EDIT USER");
            DAOImpl.editUser(user);
        } else if (deleteF){ // DELETE USER

            System.out.println("DEL USER");

            DAOImpl.delUser(user);

        }


        return SUCCESS;

    }

    public void prepare() throws Exception {
        System.out.println("------------ prepere -------------");
    }
    final static String DEFAULT_PASSWORD = "123456";

    /**
     * Добавить пользователя
     * @return
     * @throws Exception
     */
    public String addUser() throws Exception {
        System.out.println("------------  addUser -------------");

        user = new User();

        DAOImpl.initUserRoles(user);

        user.setPassword(DEFAULT_PASSWORD);
        user.setPassword2(DEFAULT_PASSWORD);

        return INPUT;
    }

    /**
     * Редактировать пользователя
     * @return
     * @throws Exception
     */
    public String editUser() throws Exception {
        System.out.println("------------  editUser -------------");

        int id = Integer.parseInt(request.getParameter("id"));

        user = new User();

        DAOImpl.loadUser(id, user);

        user.setPassword("     ");
        user.setPassword2("     ");


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
     * Удаление пользователя
     * @return
     * @throws Exception
     */
    public String delUser() throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        user = new User();

        DAOImpl.loadUser(id, user);

        user.setPassword("     ");
        user.setPassword2("     ");


        this.deleteF = true;

        return INPUT;
    }

}
