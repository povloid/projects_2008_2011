/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.session;

import ips.planer.dao.IPS_ODaoImpl;
import java.sql.ResultSet;

/**
 * Класс департамента
 * @author kopychenko
 */
public class Dep {

    private int id;
    private String keyName;
    private String description;

    public Dep() {
    }


    public void init(int id) throws Exception{
        if(this.id==0){
            IPS_ODaoImpl.Result result = IPS_ODaoImpl.getDep(id);
            ResultSet rs = result.getRs();
            rs.next();

            this.id = rs.getInt("ID");
            this.keyName = rs.getString("KEY_NAME");
            this.description = rs.getString("DESCRIPTION");

            result.closeAll();
        }
    }

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

}
