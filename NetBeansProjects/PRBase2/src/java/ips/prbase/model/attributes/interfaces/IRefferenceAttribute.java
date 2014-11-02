/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces;

import java.util.List;

/**
 *
 * @author kopychenko
 */
public interface IRefferenceAttribute extends IBasicAttribute{

    List getList();

    void setKey(int key);

    int getKey();

    int getRootId();

}
