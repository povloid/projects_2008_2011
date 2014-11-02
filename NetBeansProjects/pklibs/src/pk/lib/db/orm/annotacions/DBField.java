/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pk.lib.db.orm.annotacions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  Мапирование поля
 * @author tt
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DBField {

    /**
     * Имя поля в базе
     * @return
     */
    String sqlName();

    /**
     * Тип поля
     * @return
     */
    int sqlType();

    /**
     * Будет ли вставляться при INSERT
     * @return
     */
    boolean inserted() default true;

    /**
     * Будет ли обновляться при UPDATE
     * @return
     */
    boolean updated() default true;

}
