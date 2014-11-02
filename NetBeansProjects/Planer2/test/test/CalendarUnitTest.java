/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kopychenko
 */
public class CalendarUnitTest {

    public CalendarUnitTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void calendar() {

        GregorianCalendar cal2 = new GregorianCalendar();
        int cal2DayOfWeek = cal2.get(Calendar.DAY_OF_WEEK);
        int dDays = cal2DayOfWeek - 3;

        Date bDate = new Date((new Date()).getTime() - dDays * (24 * 60 * 60 * 1000));
        Date eDate = new Date((new Date()).getTime() + 1 * (24 * 60 * 60 * 1000));


        System.out.println(bDate + " - " + eDate);

//        if( cal2DayOfWeek == 6){
//
//            GregorianCalendar cal1 = new GregorianCalendar();
//
//            System.out.println(cal2);
//
//        } else if(true) {
//
//        }






        




    }

}