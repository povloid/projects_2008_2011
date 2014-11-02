/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package disciples.model;

import java.util.Date;
import kpg.web.struts.base.edit.IIdObject;

/**
 * Ученик
 * @author dev_sport
 */
public class Disciple implements IIdObject {

    public Disciple() {
    }

    public Disciple(int id) {
        this.id = id;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String fname;
    private String iname;
    private String oname;
    private Date birthday;
    private int sdepId;
    private Date bdate;
    private Date edate;
    private int sclassId;
    private String parents = "...";
    private String hobbi;
    private String performance;
    private String description;
    private float avscore;
    
    private String foto;
    private String fotoContentType;
    
    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public int getSdepId() {
        return sdepId;
    }

    public void setSdepId(int sdepId) {
        this.sdepId = sdepId;
    }

    public int getSclassId() {
        return sclassId;
    }

    public void setSclassId(int sclassId) {
        this.sclassId = sclassId;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getHobbi() {
        return hobbi;
    }

    public void setHobbi(String hobbi) {
        this.hobbi = hobbi;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAvscore() {
        return avscore;
    }

    public void setAvscore(float avscore) {
        this.avscore = avscore;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }
    
}
