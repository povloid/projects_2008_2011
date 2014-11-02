/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IIntAttribute;


/**
 *
 * @author kopychenko
 */
public final class IntAttribute extends BasicAttribute implements IIntAttribute{

    public IntAttribute() {
    }


    int i;
    
    public void setInt(int i) {
        this.i = i;
    }

    public int getInt() {
        return this.i;
    }


}
