/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import java.util.Date;
import kpg.web.struts.base.edit.IIdObject;

/**
 * Спортивный показатель
 * @author dev_sport
 */
public class Dsptc implements IIdObject{

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dsptc() {
    }

    public Dsptc(int id) {
        this.id = id;
    }

    private int discipleId;
    private int categoryId;
    private int sdepId;
    private float val;
    private Date cdate = new Date();

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public int getDiscipleId() {
        return discipleId;
    }

    public void setDiscipleId(int discipleId) {
        this.discipleId = discipleId;
    }

    public int getSdepId() {
        return sdepId;
    }

    public void setSdepId(int sdepId) {
        this.sdepId = sdepId;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

}
