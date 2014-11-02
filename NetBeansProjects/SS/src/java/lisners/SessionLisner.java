/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lisners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author traveler
 */
public class SessionLisner implements HttpSessionListener {

    private int sessionCount = 0;

    public void sessionCreated(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount++;
        }

        HttpSession session = event.getSession();
        String username = (String) session.getAttribute("user");

        System.out.println("Session Created: " + event.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
        System.out.println("User: " + username);



    }

    public void sessionDestroyed(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount--;
        }
        System.out.println("Session Destroyed: " + event.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
    }
}
