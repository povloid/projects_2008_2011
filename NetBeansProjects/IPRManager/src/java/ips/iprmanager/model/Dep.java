/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.iprmanager.model;

/**
 *
 * @author kopychenko
 */
public class Dep {

    private int id;
    private String keyName;
    private String description;

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

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

}
