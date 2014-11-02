/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.morsekey;

import com.sun.jna.platform.KeyboardUtils;
import com.sun.jna.platform.unix.X11;
import com.sun.jna.platform.unix.X11.Display;
import com.sun.jna.platform.unix.X11.Window;
import com.sun.jna.platform.unix.X11.XEvent;

/**
 *
 * @author povloid
 */
public class JNATest {

    public JNATest() {
    }

    public static void main(String s[]) {
        X11 x = X11.INSTANCE;
        Window window;
        Display display = x.XOpenDisplay("0");
        XEvent ev = new XEvent();


        for (;;) {
            x.XNextEvent(display, ev);

            System.out.println(ev);

        }


    }
}
