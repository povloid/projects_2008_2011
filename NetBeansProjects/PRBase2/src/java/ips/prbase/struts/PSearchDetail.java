/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.struts;

import com.opensymphony.xwork2.ActionSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author kopychenko
 */
public class PSearchDetail extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }





    private String query1;
    private String query2;
    private String query3;

    public String getQuery1() {
        return query1;
    }

    public void setQuery1(String query1) {
        this.query1 = query1;
    }

    public String getQuery2() {
        return query2;
    }

    public void setQuery2(String query2) {
        this.query2 = query2;
    }

    public String getQuery3() {
        return query3;
    }

    public void setQuery3(String query3) {
        this.query3 = query3;
    }

    public PSearchDetail() {
    }

    @Override
    public String execute() throws Exception {

        String query = "";

        if (query1.replaceAll(" ", "").length() > 0) {
            query = query + __replace(query1, "&");
        }

        if (query2.replaceAll(" ", "").length() > 0) {
            query = query + "&(" + __replace(query2, "|") + ")";
        }

        if (query3.replaceAll(" ", "").length() > 0) {
            query = query + "&!" + __replace(query3, "&!");
        }

        request.setAttribute("query_detail", query);

        System.out.println("QUERY: " + query );

        return SUCCESS;
    }

    /**
     * Замена пробелов
     * @param s
     * @param r
     * @return
     */
    private String __replace(String s, String b) {

        String pattern = "\\s+";
        Pattern regPat = Pattern.compile(pattern);

        Matcher matcher = regPat.matcher(s);

        String c = matcher.replaceAll(b);

        return c;
    }
}
