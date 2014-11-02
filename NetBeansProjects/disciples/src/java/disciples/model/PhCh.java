/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package disciples.model;

import java.util.Date;
import kpg.web.struts.base.edit.IIdObject;

/**
 * Класс физической характеристики
 * @author dev_sport
 */
public class PhCh implements IIdObject {

    private int id;
    private int discipleId;
    private Date cdate;
    private int height;
    private int weight;
    private String description;

    public PhCh() {
    }

    public PhCh(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscipleId() {
        return discipleId;
    }

    public void setDiscipleId(int discipleId) {
        this.discipleId = discipleId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



}
