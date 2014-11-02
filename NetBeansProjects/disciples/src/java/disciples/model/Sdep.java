/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import kpg.web.struts.base.edit.IIdObject;

/**
 * Отделение
 * @author dev_sport
 */
public class Sdep implements IIdObject{

    private int id;
    private String keyName;
    private String description;

    public Sdep() {
    }

    public Sdep(int id, String keyName, String description) {
        this.id = id;
        this.keyName = keyName;
        this.description = description;
    }

    public Sdep(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
