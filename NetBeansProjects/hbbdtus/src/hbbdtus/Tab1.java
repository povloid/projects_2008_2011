/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hbbdtus;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "tab1", catalog = "dbtus", schema = "public")
@NamedQueries({@NamedQuery(name = "Tab1.findAll", query = "SELECT t FROM Tab1 t"), @NamedQuery(name = "Tab1.findById", query = "SELECT t FROM Tab1 t WHERE t.id = :id"), @NamedQuery(name = "Tab1.findByKeyName", query = "SELECT t FROM Tab1 t WHERE t.keyName = :keyName")})
public class Tab1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "key_name")
    private String keyName;

    public Tab1() {
    }

    public Tab1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tab1)) {
            return false;
        }
        Tab1 other = (Tab1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hbbdtus.Tab1[id=" + id + "]";
    }

}
