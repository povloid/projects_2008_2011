/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.iprmanager.session;


import ips.iprmanager.dao.DAOImpl;
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
    private int accessLevel;

    private String password;
    private String password2;



    private boolean blocked;

    /**
     * Инициализировать класс
     * 
     * @param userName
     * @throws Exception
     */
    public void init(String userName) throws Exception {
        if (this.id == 0) {

            DAOImpl.Result result = null;

            try {
                result = DAOImpl.getUser(userName);
                ResultSet rs = result.getRs();
                rs.next();

                this.id = rs.getInt("ID");
                this.userName = rs.getString("USER_NAME");
                this.description = rs.getString("DESCRIPTION");
                this.depId = rs.getInt("DEP_ID");
                this.accessLevel = rs.getInt("ACCESS_LEVEL");

            } catch (Exception ex) {
            } finally {
                result.closeAll();
            }
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

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    } 

}
