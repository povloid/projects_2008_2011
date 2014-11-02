/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kpg.db.sql.entrys.model;

/**
 *
 * @author dev_sport
 */
public class EntryIdValue {

    private Integer id;
    private String value;

    public EntryIdValue(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
