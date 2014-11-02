/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces;

import java.util.Date;

/**
 *
 * @author kopychenko
 */
public interface IDateAttribute  extends IBasicAttribute{

    void setDate(Date date);

    Date getDate();

}
