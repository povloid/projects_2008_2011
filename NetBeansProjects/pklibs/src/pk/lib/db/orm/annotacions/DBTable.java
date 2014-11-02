/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pk.lib.db.orm.annotacions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author tt
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    String sqlName();
}
