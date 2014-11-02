/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kpg.db.sql.rb.BasicDB.Field;
import kpg.db.sql.rb.BasicDBForID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public class DirNameTest {

    public DirNameTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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

    @Test
    public void hello() {





        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
        System.out.println(sdf.format(new Date()) + "/" + (new Date()).getTime());



    }

    @Test
    public void insert() throws SQLException, Exception {


        //        // Устанавливаем соединение
//        String url = "jdbc:postgresql:ips_o";
//        Properties props = new Properties();
//        props.setProperty("user", "ips_architect");
//        props.setProperty("password", "paradox");
//        Connection con = DriverManager.getConnection(url, props);


        PGPoolingDataSource source = new PGPoolingDataSource();

        source.setDataSourceName("ips_o source");
        source.setServerName("localhost");
        source.setDatabaseName("ips_o");
        source.setUser("ips_architect");
        source.setPassword("paradox");
        source.setMaxConnections(10);
        source.setPortNumber(5432);

        Connection conn = source.getConnection();

        List<Field> fieldsList = new ArrayList<Field>();
//        fieldsList.add(new Field("id", Types.INTEGER));
        fieldsList.add(new Field("key_name", Types.VARCHAR));

        BasicDBForID basic = new BasicDBForID(source, "ips.stable", fieldsList);

        Map<String, Object> parsMap = new HashMap<String, Object>();
        parsMap.put("key_name", "Привет 444");

        //basic.insert(parsMap);
        basic.update(1, parsMap);
        basic.delete(3);

        






    }
}
