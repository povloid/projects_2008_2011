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
 * Смена пароля
 * @author kopychenko
 */
public class OpPasswordAction extends ActionSupport
        implements Preparable, ServletRequestAware, ServletResponseAware {

    private String password1 = "123456";
    private String password2 = "123456";

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }

    public OpPasswordAction() {
    }

    @Override
    public String execute() throws Exception {

        // Проверка правильности ввода пароля
        if (!password1.equals(password2)) {
            System.out.println("CHECK PASSWORD ERROR!!!");

            addFieldError("password1", "Подтверждение пароля некорректное");
            addFieldError("password2", "");
            return ERROR;
        }

        // Получаем id пользователя
        User user = ((User)request.getSession().getAttribute("user"));

        // Производим операцию смены пароля
        DAOImpl.changePassword(user, password1);

        return SUCCESS;
    }

    public void prepare() throws Exception {
    }

    /**
     * Смена пароля
     * @return
     */
    public String changePassword() {


        return INPUT;

    }
}
