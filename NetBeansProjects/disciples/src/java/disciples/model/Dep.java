/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author kopychenko
 */
public class Dep implements IIdObject{

    private int id;
    private String keyName;
    private String description;

    public Dep() {
    }

    public Dep(int id, String keyName, String description) {
        this.id = id;
        this.keyName = keyName;
        this.description = description;
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
