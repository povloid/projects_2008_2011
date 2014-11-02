/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package example2;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;

/**
 *
 * @author traveler
 */
public class ExampleForm2 extends ActionSupport{

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }


    private String str;
    private Date date = new Date();

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    


}
