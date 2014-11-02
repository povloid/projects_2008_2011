/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.ITextAttribute;

/**
 *
 * @author kopychenko
 */
public final class TextAttribute extends BasicAttribute implements ITextAttribute{

    public TextAttribute() {
      
    }


    String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
