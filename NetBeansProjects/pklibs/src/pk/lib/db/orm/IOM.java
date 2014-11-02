/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pk.lib.db.orm;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import pk.lib.db.orm.APDBObjectMachine.CondPar;
import pk.lib.db.orm.APDBObjectMachine.ObjectsSet;

/**
 *
 * @author traveler
 */
public interface IOM {

    /**
     * Удалить запись
     * @param conn
     * @param o
     * @throws Exception
     */
    void delete(Connection conn, Object o) throws Exception;

    /**
     * Создать запись
     * @param conn
     * @param o
     * @throws Exception
     */
    void insert(Connection conn, Object o) throws Exception;

    /**
     * Загрузить объект, указав только ключевые поля
     * @param conn
     * @param o
     * @throws Exception
     */
    void load(Connection conn, Object o) throws Exception;

    /**
     * Выбрать список объектов
     * @param <T>
     * @param conn
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception
     */
    <T> List<T> select(Connection conn, Class a, String selectName, CondPar[] cpars) throws Exception;

    /**
     * Выбрать в поток в поток
     * @param conn
     * @param o
     * @param selectName
     * @param pars
     * @return
     * @throws Exception
     */
    ObjectsSet selectObjectsSet(Connection conn, Class a, String selectName, CondPar[] cpars) throws Exception;

    /**
     * Вернуть количество строк в запросе
     * @param conn
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception 
     */
    int selectCount(Connection conn, Class a, String selectName, CondPar[] cpars) throws Exception;
    
    
    
    /**
     * Обновить
     * @param conn
     * @param o
     * @throws Exception
     */
    void update(Connection conn, Object o) throws Exception;
    
    
    /**
     * Удалить запись
     * @param ds
     * @param o
     * @throws Exception
     */
    void delete(DataSource ds, Object o) throws Exception;

    /**
     * Создать запись
     * @param ds
     * @param o
     * @throws Exception
     */
    void insert(DataSource ds, Object o) throws Exception;

    /**
     * Загрузить объект только указав ключевые поля
     * @param ds
     * @param o
     * @throws Exception
     */
    void load(DataSource ds, Object o) throws Exception;

    /**
     * Выбрать список объектов
     * @param <T>
     * @param ds
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception
     */
    <T> List<T> select(DataSource ds, Class a, String selectName, CondPar[] cpars) throws Exception;

    /**
     * Получить потоковый список объектов
     * @param ds
     * @param a
     * @param selectName
     * @param cpars
     * @return
     * @throws Exception 
     */
    ObjectsSet selectObjectsSet(DataSource ds, Class a, String selectName, CondPar[] cpars) throws Exception;

    /**
     * Обновить
     * @param ds
     * @param o
     * @throws Exception
     */
    void update(DataSource ds, Object o) throws Exception;

}
