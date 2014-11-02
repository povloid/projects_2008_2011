/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm.model;

import java.sql.Types;
import java.util.Date;
import pk.lib.db.orm.annotacions.DBField;
import pk.lib.db.orm.annotacions.DBFieldPK;
import pk.lib.db.orm.annotacions.DBTable;

/**
 * Тестовая сущность
 * @author traveler
 */
@DBTable(sqlName = "rb_0_4")
public class Rb_0_4 {

    @DBFieldPK(getPKType=DBFieldPK.PKType.APP_SET)
    @DBField(sqlName = "ids1", sqlType = Types.VARCHAR)
    private String ids1;

    @DBFieldPK(getPKType=DBFieldPK.PKType.APP_SET)
    @DBField(sqlName = "ids2", sqlType = Types.VARCHAR)
    private String ids2;


    @DBField(sqlName = "description", sqlType = Types.VARCHAR)
    private String description;
    
    @DBField(sqlName = "cdate", sqlType = Types.TIMESTAMP, inserted=false)
    private Date cdate;

    public Rb_0_4() {
    }

    public Rb_0_4(String ids1, String ids2, String description) {
        this.ids1 = ids1;
        this.ids2 = ids2;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIds1() {
        return ids1;
    }

    public void setIds1(String ids1) {
        this.ids1 = ids1;
    }

    public String getIds2() {
        return ids2;
    }

    public void setIds2(String ids2) {
        this.ids2 = ids2;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    
    
}
