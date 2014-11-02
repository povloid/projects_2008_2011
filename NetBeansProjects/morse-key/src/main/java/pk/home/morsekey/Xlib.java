/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.morsekey;

import com.sun.jna.NativeLong;
import com.sun.jna.platform.unix.X11;

/**
 *
 * @author povloid
 */
public interface Xlib extends X11{
    
    int XGrabKey(Display display, int keycode, NativeLong modifiers, Window grab_window, boolean owner_events, int pointer_mode, int keyboard_mode);
    
}
