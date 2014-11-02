/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.model.objects.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import ips.prbase.model.objects.interfaces.IObjectDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

/**
 *
 * @author kopychenko
 */
public class ObjectDescriptor implements IObjectDescriptor {

    protected int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    protected Map<String, IBasicAttribute> attributes = new HashMap<String, IBasicAttribute>();

    public Map<String, IBasicAttribute> getAttributes() {
        return this.attributes;
    }

    public String getKeyName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void init() {
        try {
            Field fields[] = this.getClass().getDeclaredFields();
            System.out.println("Обработка всех полей - инициализация");

            for (Field f : fields) {
                if (f.get(this) instanceof IBasicAttribute) {
                    System.out.println("<< Field Name: " + f.getName());
                    //f.setAccessible(true);
                    //System.out.println(f.get(this) + "\n");

                    IBasicAttribute iba = (IBasicAttribute) f.get(this);
                    iba.setObjDescriptor(this);
                    iba.setKeyName(f.getName());

                    attributes.put(f.getName(), iba);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка");
            System.out.println(e.getMessage());
        }
    }
}
