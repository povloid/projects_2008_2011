/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import ips.prbase.model.objects.interfaces.IObjectDescriptor;
import java.util.Date;

/**
 *
 * @author kopychenko
 */
public abstract class BasicAttribute implements IBasicAttribute{

    public BasicAttribute() {
    }

    IObjectDescriptor objectDescriptor;

    public IObjectDescriptor getObjDescriptor() {
        return this.objectDescriptor;
    }

    public void setObjDescriptor(IObjectDescriptor objectDescriptor) {
        this.objectDescriptor = objectDescriptor;
    }

    int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    Date cdate;

    public Date getCdate() {
        return this.cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String keyName;

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

}
