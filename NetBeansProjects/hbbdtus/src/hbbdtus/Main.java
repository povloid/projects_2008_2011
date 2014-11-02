/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hbbdtus;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kopychenko
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {


            System.out.println("Начало");
            Tab2JpaController cntr = new Tab2JpaController();


//            for (int i = 0; i < 1000000; i++) {
//
//                Tab2 tab2 = new Tab2("Привет " + i);
//
//                cntr.create(tab2);
//
//                System.out.println(tab2.getId());
//
//            }


            Tab2 tab2 = cntr.findTab2(500000);
            tab2.setKeyName("Изменен");
            cntr.edit(tab2);


            Tab2 tab22 = cntr.findTab2(500000);
            System.out.println(tab22.getKeyName());


//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
