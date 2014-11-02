/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.session;

import ips.planer.dao.IPS_ODaoImpl;
import java.sql.ResultSet;

/**
 *
 * @author kopychenko
 */
public class User {

    private int id;
    private String userName;
    private String description;
    private int depId;

    /**
     * Инициализировать класс
     * 
     * @param userName
     * @throws Exception
     */
    public void init(String userName) throws Exception{
        if(this.id==0){
            IPS_ODaoImpl.Result result = IPS_ODaoImpl.getUser(userName);
            ResultSet rs = result.getRs();
            rs.next();

            this.id = rs.getInt("ID");
            this.userName = rs.getString("USER_NAME");
            this.description = rs.getString("DESCRIPTION");
            this.depId = rs.getInt("DEP_ID");

            result.closeAll();
        }
    }


    public User() {
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



}
