/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces;

import ips.prbase.model.objects.interfaces.IObjectDescriptor;
import java.util.Date;

/**
 * Интерфейс базового атрибута
 * @author kopychenko
 */
public interface IBasicAttribute {

    IObjectDescriptor getObjDescriptor();

    void setObjDescriptor(IObjectDescriptor oDescr);

    int getUserId();

    void setUserId(int userId);

    Date getCdate();

    void setCdate(Date cdate);


    /**
     * Получит id
     * @return
     */
    int getId();

    /**
     * Установить id
     * @param id
     */
    void setId(int id);
    

    String getKeyName();


    void setKeyName(String keyName);
    

}
