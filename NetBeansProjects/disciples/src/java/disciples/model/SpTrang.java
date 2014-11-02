/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import kpg.web.struts.base.edit.IIdObject;

/**
 * Спортивная подготовка
 * @author dev_sport
 */
public class SpTrang implements IIdObject{

    private int id;
    private int sdepId;
    private String keyName;
    private String description;
    private boolean specialized;

    public SpTrang() {
    }

    public SpTrang(int id) {
        this.id = id;
    }

    public SpTrang(int id, int sdepId) {
        this.id = id;
        this.sdepId = sdepId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getSdepId() {
        return sdepId;
    }

    public void setSdepId(int sdepId) {
        this.sdepId = sdepId;
    }

    public boolean isSpecialized() {
        return specialized;
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }


}
