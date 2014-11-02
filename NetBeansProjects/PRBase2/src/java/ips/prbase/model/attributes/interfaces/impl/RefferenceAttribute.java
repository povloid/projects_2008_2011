/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IRefferenceAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kopychenko
 */
public final class RefferenceAttribute  extends BasicAttribute implements IRefferenceAttribute{

    /**
     * Справочное вхождение
     */
    public final class Entry{

        private int key;
        private String description;

        public Entry(int key, String description) {
            this.key = key;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }

    private List<Entry> list = new ArrayList<Entry>();
    
    private int key;
    private int rootId;
    private String rbName;

    private int parentRootId;
    private String parentRootName;
    private int parentId;

    /**
     * Добавить справочное значение
     * @param id
     * @param description
     */
    public void addEntry(int key, String description){
        list.add(new Entry(key, description));
    }

    public RefferenceAttribute(String rbName) {
        this.rbName = rbName;
    }

    /**
     * Получить список справочников
     * @return
     */
    public List getList() {
        return this.list;
    }

    /**
     * Выбрать значение
     * @param key
     */
    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentRootId() {
        return parentRootId;
    }

    public void setParentRootId(int parentRootId) {
        this.parentRootId = parentRootId;
    }

    public String getParentRootName() {
        return parentRootName;
    }

    public void setParentRootName(String parentRootName) {
        this.parentRootName = parentRootName;
    }

    public String getRbName() {
        return rbName;
    }

    public void setRbName(String rbName) {
        this.rbName = rbName;
    }

    public String getKeyDescription() {
        String keyDescription = "";

        for(Entry e: list){
            if(e.getKey() == this.key){
                keyDescription = e.getDescription();
            }
        }

        return keyDescription;
    }

    public void setKeyDescription(String keyDescription) {

    }



}
