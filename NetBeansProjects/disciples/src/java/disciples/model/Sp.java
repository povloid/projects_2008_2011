/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import java.util.Date;
import kpg.web.struts.base.edit.IIdObject;

/**
 * Спортивные достижения
 * @author dev_sport
 */
public class Sp implements IIdObject{

    private int id;
    private int discipleId;
    private Date cdate = new Date();
    private String description;

    public Sp() {
    }

    public Sp(int id) {
        this.id = id;
    }

    public Sp(int id, int discipleId) {
        this.id = id;
        this.discipleId = discipleId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
