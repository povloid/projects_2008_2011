/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.objects.interfaces;

import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import java.util.Map;

/**
 *
 * @author kopychenko
 */
public interface IObjectDescriptor {

    int getId();

    void setId(int id);

    String getKeyName();

    Map<String,IBasicAttribute> getAttributes();

}
