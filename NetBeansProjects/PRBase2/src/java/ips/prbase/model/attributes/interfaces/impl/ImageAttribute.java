/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IImageAttribute;

/**
 *
 * @author kopychenko
 */
public class ImageAttribute  extends OidAttribute  implements IImageAttribute{

    int imageId;

    public int getImageId() {
        return this.imageId;
    }

    public void setImageId(int id) {
        this.imageId = id;
    }

}
