/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpg.web.struts.base.edit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author dev_sport
 */
public abstract class ABaseEdit extends ActionSupport
        implements Preparable, ServletRequestAware, ServletResponseAware, ServletContextAware {

    /**
     * Вернуть объект содержащий идентификатор
     * @return
     */
    protected abstract IIdObject getIDObj();
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext servletContext;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }
    
    public void setServletContext(ServletContext sc){
        this.servletContext = sc;
    }
    

    public void prepare() throws Exception {
        System.out.println("------------ prepere -------------");
    }
    /**
     * Флаг удаления
     */
    protected boolean deleteF = false;

    public boolean isDeleteF() {
        return deleteF;
    }

    public void setDeleteF(boolean deleteF) {
        this.deleteF = deleteF;
    }
    protected String result;

    /**
     * Валидация
     * @return
     * @throws Exception
     */
    protected abstract boolean validateForm() throws Exception;

    @Override
    public String execute() throws Exception {

        // Проверки -----------------------------------------------------------
        if (!validateForm()) {
            return ERROR;
        }

        // --------------- EXECUTE -------------------
        if (getIDObj().getId() == 0) { // ADD
            System.out.println("ADD");
            addDAO();
        } else if (getIDObj().getId() > 0 && !deleteF) { // EDIT
            System.out.println("EDIT");
            editDAO();
        } else if (deleteF) { // DELETE
            System.out.println("DEL");
            delDAO();
        }

        if (result != null && result.length() > 0) {
            return result;
        } else {
            return SUCCESS;
        }
    }

    /**
     * Добавить новый объект в базу
     * @throws Exception
     */
    protected abstract void addDAO() throws Exception;

    /**
     * Редактировать объект в базе
     * @throws Exception
     */
    protected abstract void editDAO() throws Exception;

    /**
     * Удалить объект из базы
     * @throws Exception
     */
    protected abstract void delDAO() throws Exception;

    /**
     * Создать новый объект
     * @throws Exception
     */
    protected abstract void createObject() throws Exception;

    /**
     * Добавить пользователя
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        System.out.println("------------  add -------------");

        createObject();

        return INPUT;
    }

    /**
     * Загрузить редактируемый отдел
     * @param id
     * @throws Exception
     */
    protected abstract void loadOdject(int id) throws Exception;

    /**
     * Редактировать пользователя
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        System.out.println("------------  edit -------------");

        int id = Integer.parseInt(request.getParameter("id"));

        loadOdject(id);

        return INPUT;
    }

    /**
     * Удаление пользователя
     * @return
     * @throws Exception
     */
    public String del() throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        loadOdject(id);

        this.deleteF = true;

        return INPUT;
    }
}
