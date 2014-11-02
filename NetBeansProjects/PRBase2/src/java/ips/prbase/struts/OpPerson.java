/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import ips.prbase.dao.DAOImpl;
import ips.prbase.model.objects.interfaces.impl.Person;
import ips.prbase.session.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author kopychenko
 */
public class OpPerson
        extends ActionSupport
        implements Preparable, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }

    
    Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OpPerson() {
        person = new Person();
    }

    @Override
    public String execute() throws Exception {
        System.out.println("------------- execute ---------------");

        User user = (User) request.getSession().getAttribute("user");

        DAOImpl.loadObjectDescriptor(person,false);
        DAOImpl.saveObjectDescriptor(person, user.getId());
        System.out.println("id=" + person.getId());

        return SUCCESS;
    }

    /**
     * Добавить новую персону
     * @return
     * @throws Exception
     */
    public String addPerson() throws Exception {
        System.out.println("------------  addPerson -------------");
        System.out.println("Пробуем создать объект");
        return INPUT;
    }

    /**
     * Редактировать персону
     * @return
     * @throws Exception
     */
    public String editPerson() throws Exception {
        System.out.println("------------  editPerson -------------");

        if (request.getParameterMap().containsKey("id")) {

            String idS = request.getParameter("id");
            System.out.println("Пробуем поднять объект id=" + idS);

            person.setId(Integer.parseInt(idS));

            DAOImpl.loadObjectDescriptor(person);
        }

        return INPUT;
    }

    public void prepare() throws Exception {
        System.out.println("------------ prepere -------------");

//        if(request.getParameterMap().containsKey("id")){
//
//            String idS = request.getParameter("id");
//            System.out.println("Пробуем поднять объект id="+idS);
//            person.setId(Integer.parseInt(idS));
//
//            //TO DO: сделать поднятие объекта
//
//        } else {
//
//            System.out.println("Пробуем создать объект");
//            DAOImpl.createNewObjectDescriptor(person);
//
//        }

        System.out.println("id=" + person.getId());

    }
}
