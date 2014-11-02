/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm.model;

import java.sql.Types;
import pk.lib.db.orm.annotacions.DBField;
import pk.lib.db.orm.annotacions.DBFieldPK;
import pk.lib.db.orm.annotacions.DBSQuery;
import pk.lib.db.orm.annotacions.DBTable;
import pk.lib.db.orm.annotacions.SQuery;

/**
 * Тестовая сущность
 * @author traveler
 */


@DBTable(sqlName = "rb_0_1")
@DBSQuery(querys={@SQuery(qname="1",query="SELECT * FROM $table"),
        @SQuery(qname="2",query="SELECT * FROM $table WHERE id=?",qtypes={Types.INTEGER}),
        @SQuery(qname="3",query="SELECT * FROM $table WHERE id>? AND id<?",qtypes={Types.INTEGER,Types.INTEGER})})
public class Rb_0_1 {

    @DBFieldPK(getPKType=DBFieldPK.PKType.AUTOINDENTING)
    @DBField(sqlName = "id", sqlType = Types.INTEGER)
    private int id;
    @DBField(sqlName = "key_name", sqlType = Types.VARCHAR)
    private String key;
    @DBField(sqlName = "description", sqlType = Types.VARCHAR)
    private String description;


    private int ggg;

    public Rb_0_1() {
    }

    public Rb_0_1(int id) {
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
