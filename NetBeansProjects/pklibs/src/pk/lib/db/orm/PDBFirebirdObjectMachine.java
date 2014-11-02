/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm;

import java.sql.Connection;

/**
 * SQL Машина
 * @author traveler
 */
public final class PDBFirebirdObjectMachine extends APDBObjectMachine {

    /**
     * Запрос вставки новой записи
     */
    private final static String INSERT_TEMPLITE = "INSERT INTO " + $TABLE + " (" + $FIELDS + ") VALUES (" + $VALUES + ") RETURNING " + $KEYS + ";";

    @Override
    protected String getInsetTemplite() {
        return INSERT_TEMPLITE;
    }

    
    

}
