/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.session;

import ips.dwh.datawork.DAOImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

                writeLog();


            } catch (Exception ex) {
            } finally {
                result.closeAll();

            }
        }
    }


    final static File file = new File(System.getProperty("catalina.base") + "/logs/" + "DR2_connection.log");
    /**
     * Записать лог коннекта
     */
    final void writeLog() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write("[" + (new Date()) + "] user: " + this.userName + "\n");
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
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
}
