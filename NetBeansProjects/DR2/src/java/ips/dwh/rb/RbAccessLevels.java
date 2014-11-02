/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.dwh.rb;

/**
 * Уровень доступа
 * @author kopychenko
 */
public class RbAccessLevels {

    private int id;
    private int accessLevel;
    private String keyName;
    private String description;

    public RbAccessLevels() {
    }

    public RbAccessLevels(int id, int accessLevel, String keyName, String description) {
        this.id = id;
        this.accessLevel = accessLevel;
        this.keyName = keyName;
        this.description = description;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
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

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

}
