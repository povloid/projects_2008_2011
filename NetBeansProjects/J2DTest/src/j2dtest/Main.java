/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2dtest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pacman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        List<String> list = getPanList();

        for(String s:list)
            System.out.println(s);


    }

     static List<String> getPanList(){

        List<String> list = new ArrayList<String>();

        for(int i=0;i<2000;i++){
            long l = (long)( Math.random() * 9000000000000000d) + 1000000000000000l;
            list.add(l+"");
        }

        return list;

    }

}
