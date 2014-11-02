/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kopychenko
 */
public class UserRoles {

//    public static class Role {
//
//        private int id;
//        private String keyName;
//        private String description;
//
//        public Role() {
//        }
//
//        public Role(int id, String keyName, String description) {
//            this.id = id;
//            this.keyName = keyName;
//            this.description = description;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getKeyName() {
//            return keyName;
//        }
//
//        public void setKeyName(String keyName) {
//            this.keyName = keyName;
//        }
//
//    }

    private List haveRoles = new ArrayList<Role>();
    private List otherRoles = new ArrayList<Role>();

    private List haveRoles2 = new ArrayList();
    private List otherRoles2 = new ArrayList();

   

    public UserRoles() {
    }

    public List getHaveRoles() {
        return haveRoles;
    }

    public void setHaveRoles(List haveRoles) {
        this.haveRoles = haveRoles;
    }

  
    public List getOtherRoles() {
        return otherRoles;
    }

    public void setOtherRoles(List otherRoles) {
        this.otherRoles = otherRoles;
    }

    public List getHaveRoles2() {
        return haveRoles2;
    }

    public void setHaveRoles2(List haveRoles2) {
        this.haveRoles2 = haveRoles2;
    }

    public List getOtherRoles2() {
        return otherRoles2;
    }

    public void setOtherRoles2(List otherRoles2) {
        this.otherRoles2 = otherRoles2;
    }


}
