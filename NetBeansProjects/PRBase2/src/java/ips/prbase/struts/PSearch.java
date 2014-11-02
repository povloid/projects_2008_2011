/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.struts;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author kopychenko
 */
public class PSearch extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public PSearch() {
    }

    @Override
    public String execute() throws Exception {

        request.setAttribute("query", query);
        
        return SUCCESS;
    }
}
