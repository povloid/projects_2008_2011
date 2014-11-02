/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.postgresql.ds.PGPoolingDataSource;
import pk.lib.db.orm.APDBObjectMachine.CondPar;
import pk.lib.db.orm.APDBObjectMachine.ObjectsSet;
import pk.lib.db.orm.model.Rb_0_1;
import pk.lib.db.orm.model.Rb_0_2;
import pk.lib.db.orm.model.Rb_0_3;
import pk.lib.db.orm.model.Rb_0_4;

/**
 *
 * @author traveler
 */
public class PDBPostgreObjectMachineTest {

    private final static String dbName = "test";
    private final static String dbUserName = "test";
    private final static String dbPassword = "test";
    private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbServerName = "localhost";
    private final static int dbPort = 5432;
    private final static PGPoolingDataSource source = new PGPoolingDataSource();
    private Connection conn;
    private IOM om = new PDBPostgreObjectMachine();


    public PDBPostgreObjectMachineTest() {
        System.out.println("PDBObjectMachineTest");
        try {
            conn = source.getConnection();

            System.out.println("Database product name - " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Database product version - " + conn.getMetaData().getDatabaseProductVersion());

        } catch (SQLException ex) {
            Logger.getLogger(PDBPostgreObjectMachineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("setUpClass");

        source.setDataSourceName("test source");
        source.setServerName(dbServerName);
        source.setDatabaseName(dbName);
        source.setUser(dbUserName);
        source.setPassword(dbPassword);
        source.setMaxConnections(10);
        source.setPortNumber(dbPort);


        try {

            // Установка драйвера базы данных
            Class.forName(dbDriver).newInstance();



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PDBPostgreObjectMachineTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PDBPostgreObjectMachineTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PDBPostgreObjectMachineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("tearDownClass");
    }

    @Before
    public void setUp() {
        System.out.println("setUp");
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

    /**
     * Test of load method, of class PDBObjectMachine.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
        int x = 0;

        for (int i = 0; i < 10; i++) {
            Rb_0_1 r = new Rb_0_1();
            r.setKey("a" + i);
            r.setDescription("a" + i + " is a key");

            om.insert(conn, r);

            System.out.println("id = "+ r.getId());
            x = r.getId();
        }

        Rb_0_1 r1 = new Rb_0_1(x);
        om.load(conn, r1);

        System.out.println(r1.getId() + " | " + r1.getKey() + " | " + r1.getDescription());

        conn.rollback();


    }

    /**
     * Test of insert method, of class PDBObjectMachine.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert ---------------------------------------------");

        for (int i = 0; i < 10; i++) {
            Rb_0_1 r = new Rb_0_1();
            r.setKey("a" + i);
            r.setDescription("a" + i + " is a key");

            om.insert(conn, r);

            System.out.println("id = "+ r.getId());
        }

        //conn.commit();
        conn.rollback();
        
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PDBObjectMachine.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update ---------------------------------------------");

        Rb_0_1 r = new Rb_0_1();
        r.setKey("a");
        r.setDescription("a is a key");

        om.insert(conn, r);

        r.setKey("b");
        r.setDescription("b is a key");
        om.update(conn, r);
        System.out.println("id = "+ r.getId());

        conn.rollback();

        //fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PDBObjectMachine.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete ---------------------------------------------");
        Rb_0_1 r = new Rb_0_1();
        r.setKey("a");
        r.setDescription("a is a key");

        om.insert(conn, r);

        r.setKey("b");
        r.setDescription("b is a key");
        om.update(conn, r);
        System.out.println("id = "+ r.getId());

        om.delete(conn, r);
        conn.rollback();

        //fail("The test case is a prototype.");
    }









    /**
     * Test of select method, of class PDBObjectMachine.
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");


        int x = 0;
        for (int i = 0; i < 10; i++) {
            Rb_0_1 r = new Rb_0_1();
            r.setKey("a" + i);
            r.setDescription("a" + i + " is a key");

            om.insert(conn, r);

            System.out.println("id = "+ r.getId());
            x = r.getId();
        }

        //conn.commit();
        


        List<Rb_0_1> list = om.select(conn, Rb_0_1.class, "1", new CondPar[]{});

        System.out.println(list.size());
        
        int count = om.selectCount(conn, Rb_0_1.class, "1", new CondPar[]{});
        
        System.out.println("count :" + count);
        

        list = om.select(conn, Rb_0_1.class, "2", new CondPar[]{new CondPar(1, x)});

        System.out.println(list.size());

        Rb_0_1 r2 = list.get(0);

        System.out.println(r2.getId() + " | " + r2.getKey() + " | " + r2.getDescription());


        list = om.select(conn, Rb_0_1.class, "3", new CondPar[]{new CondPar(1, x - 5),new CondPar(2, x)});

        System.out.println(list.size());

        ObjectsSet os = om.selectObjectsSet(conn, Rb_0_1.class, "3", new CondPar[]{new CondPar(1, x - 5),new CondPar(2, x)});

        while(os.next()){
            Rb_0_1 rt = os.load();
            System.out.println(rt.getId() + " | " + rt.getKey() + " | " + rt.getDescription());
        }

        os.close(false);

        conn.rollback();
    }

    /**
     * Test of selectObjectsSet method, of class PDBObjectMachine.
     */
    @Test
    public void testSelectObjectsSet() throws Exception {
        System.out.println("selectObjectsSet");

        System.out.println("select");


        int x = 0;
        for (int i = 0; i < 10; i++) {
            Rb_0_1 r = new Rb_0_1();
            r.setKey("a" + i);
            r.setDescription("a" + i + " is a key");

            om.insert(conn, r);

            System.out.println("id = "+ r.getId());
            x = r.getId();
        }

        List<Rb_0_1> list = om.select(conn, Rb_0_1.class, "3", new CondPar[]{new CondPar(1, x - 5),new CondPar(2, x)});

        System.out.println(list.size());

        ObjectsSet os = om.selectObjectsSet(conn, Rb_0_1.class, "3", new CondPar[]{new CondPar(1, x - 5),new CondPar(2, x)});

        while(os.next()){
            Rb_0_1 rt = os.load();
            System.out.println(rt.getId() + " | " + rt.getKey() + " | " + rt.getDescription());
        }

        os.close(false);
        
        int count = om.selectCount(conn, Rb_0_1.class, "3", new CondPar[]{new CondPar(1, x - 5),new CondPar(2, x)});
        
        System.out.println("count :" + count);


        conn.rollback();

    }




    ////////////////////////////////////////////////////////////////////////////
    /**
     * Комплексные тесты
     *
     */

    @Test
    public void testComplexRb_0_2() throws Exception {
        System.out.println("ComplexRb_0_2 --------------------------------------");

        Rb_0_2 r = new Rb_0_2();
        r.setId(65535);
        r.setKey("a");
        r.setDescription("a is a key");

        om.insert(conn, r);

        r.setKey("b");
        r.setDescription("b is a key");
        om.update(conn, r);
        System.out.println("id = "+ r.getId());

        om.delete(conn, r);

        conn.commit();
        
    }

    @Test
    public void testComplexRb_0_3() throws Exception {
        System.out.println("ComplexRb_0_3 --------------------------------------");

        Rb_0_3 r = new Rb_0_3(1,1,1);
        r.setDescription("1,1,1 is a key");


        om.insert(conn, r);

       
        r.setDescription("1,1,1 is a key.............................");
        om.update(conn, r);


        om.delete(conn, r);


        conn.commit();
    }


    @Test
    public void testComplexRb_0_4() throws Exception {
        System.out.println("ComplexRb_0_4 --------------------------------------");

        Rb_0_4 r = new Rb_0_4("i1","i1","i1...............");


        om.insert(conn, r);
        om.load(conn, r);
        
        System.out.println("cdate - " + r.getCdate());


        r.setDescription(".......i1......................");
        om.update(conn, r);


        om.delete(conn, r);


        conn.commit();
    }



}
