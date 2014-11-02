/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import kpg.web.struts.base.edit.IIdObject;

/**
 * Мера измерения
 * @author dev_sport
 */
public class Measure implements IIdObject{

    private int id;
    private String keyName;
    private String description;

    public Measure() {
    }

    public Measure(int id) {
        this.id = id;
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
