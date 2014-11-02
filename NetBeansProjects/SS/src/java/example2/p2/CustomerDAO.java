/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example2.p2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author traveler
 */
public class CustomerDAO {

    private static List<Customer> list = new ArrayList<Customer>();
    
    static List<Customer> buildList() {
        
        
        for(int i=0; i < 100; i++){
            list.add(new Customer(i, "Customer" + i, "country", "city", 1000));
        }
        
        return list;
    }

    static Integer getCustomersCount(List<Customer> myCustomers) {
        return list.size();
    }

    static List<Customer> getCustomers(List<Customer> myCustomers, int from, int to) {
        return myCustomers.subList(from, to);
    }
    
}
