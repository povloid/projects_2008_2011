/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm.model;

import java.sql.Types;
import pk.lib.db.orm.annotacions.DBField;
import pk.lib.db.orm.annotacions.DBFieldPK;
import pk.lib.db.orm.annotacions.DBTable;

/**
 * Тестовая сущность
 * @author traveler
 */
@DBTable(sqlName = "rb_0_3")
public class Rb_0_3 {

    @DBFieldPK(getPKType=DBFieldPK.PKType.APP_SET)
    @DBField(sqlName = "id1", sqlType = Types.INTEGER)
    private int id1;
    
    @DBFieldPK(getPKType=DBFieldPK.PKType.APP_SET)
    @DBField(sqlName = "id2", sqlType = Types.INTEGER)
    private int id2;
    
    @DBFieldPK(getPKType=DBFieldPK.PKType.APP_SET)
    @DBField(sqlName = "id3", sqlType = Types.INTEGER)
    private int id3;


    @DBField(sqlName = "description", sqlType = Types.VARCHAR)
    private String description;


    public Rb_0_3() {
    }

    public Rb_0_3(int id1, int id2, int id3) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getId3() {
        return id3;
    }

    public void setId3(int id3) {
        this.id3 = id3;
    }





    
}
