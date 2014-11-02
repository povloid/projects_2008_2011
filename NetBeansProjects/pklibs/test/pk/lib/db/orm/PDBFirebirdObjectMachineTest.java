/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.lib.db.orm;

import pk.lib.db.orm.model.Rb_0_2;
import pk.lib.db.orm.model.Rb_0_3;
import pk.lib.db.orm.model.Rb_0_4;
import pk.lib.db.orm.APDBObjectMachine.ObjectsSet;
import pk.lib.db.orm.APDBObjectMachine.CondPar;
import java.util.List;
import pk.lib.db.orm.model.Rb_0_1;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author traveler
 */
public class PDBFirebirdObjectMachineTest {

    private final static org.firebirdsql.pool.FBWrappingDataSource dataSource = new org.firebirdsql.pool.FBWrappingDataSource();
    private Connection conn;
    private IOM om = new PDBFirebirdObjectMachine();

    public PDBFirebirdObjectMachineTest() {
        try {
            System.out.println("GET CONNECTION");
            conn = dataSource.getConnection ("sysdba", "masterkey");
            System.out.println("Database product name - " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Database product version - " + conn.getMetaData().getDatabaseProductVersion());
        } catch (SQLException ex) {
            Logger.getLogger(PDBFirebirdObjectMachineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        dataSource.setDatabase("localhost:/mnt/db/fdbs/fdb.fdb");
        dataSource.setDescription("An example base");
        dataSource.setEncoding("UTF-8");
        //dataSource.setRoleName("SYSDBA");
        //dataSource.setPassword("masterkey");
        //dataSource.setLoginTimeout(10);


    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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
