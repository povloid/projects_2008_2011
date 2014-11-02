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
@DBTable(sqlName = "rb_0_2")
public class Rb_0_2 {

    @DBFieldPK(getPKType=DBFieldPK.PKType.APP_SET)
    @DBField(sqlName = "id", sqlType = Types.INTEGER)
    private int id;
    @DBField(sqlName = "key_name", sqlType = Types.VARCHAR)
    private String key;
    @DBField(sqlName = "description", sqlType = Types.VARCHAR)
    private String description;


    private int ggg;

    public Rb_0_2() {
    }

    public Rb_0_2(int id) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getGgg() {
        return ggg;
    }

    public void setGgg(int ggg) {
        this.ggg = ggg;
    }


    
}
