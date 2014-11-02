/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example2.p3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author traveler
 */
public class CustomerDAO3 {

    private static List<Customer3> list = new ArrayList<Customer3>();
    
    static List<Customer3> buildList() {
        
        
        for(int i=6; i < 60; i++){
            list.add(new Customer3(i, "Customer" + i, "country", "city", 1000));
        }
        
        return list;
    }

    static Integer getCustomersCount(List<Customer3> myCustomers) {
        return list.size();
    }

    static List<Customer3> getCustomers(List<Customer3> myCustomers, int from, int to) {
        return myCustomers.subList(from, to);
    }
    
}
