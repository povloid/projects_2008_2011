/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lisners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author traveler
 */
public class MyApplicationContextListener implements ServletContextListener{
    
     private int contextCount = 0;

    public void contextInitialized(ServletContextEvent sce) {
        synchronized (this) {
            contextCount++;
        }

        
        System.out.println("contextInitialized>>Total context: " + contextCount);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        synchronized (this) {
            contextCount--;
        }
        
        

        
        System.out.println("contextDestroyed>>Total context: " + contextCount);
    }
    
}
