/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IDateAttribute;
import java.util.Date;

/**
 *
 * @author kopychenko
 */
public final class DateAttribute extends BasicAttribute implements IDateAttribute{

    public DateAttribute() {
    }

    Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }



}
