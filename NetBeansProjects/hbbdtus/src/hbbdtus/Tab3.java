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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "tab3")
@NamedQueries({@NamedQuery(name = "Tab3.findAll", query = "SELECT t FROM Tab3 t"), @NamedQuery(name = "Tab3.findById", query = "SELECT t FROM Tab3 t WHERE t.id = :id"), @NamedQuery(name = "Tab3.findByKeyName", query = "SELECT t FROM Tab3 t WHERE t.keyName = :keyName")})
public class Tab3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "key_name")
    private String keyName;
    @JoinColumn(name = "tab2_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tab1 tab2Id;

    public Tab3() {
    }

    public Tab3(Integer id) {
        this.id = id;
    }

    public Tab3(Integer id, String keyName) {
        this.id = id;
        this.keyName = keyName;
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

    public Tab1 getTab2Id() {
        return tab2Id;
    }

    public void setTab2Id(Tab1 tab2Id) {
        this.tab2Id = tab2Id;
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
        if (!(object instanceof Tab3)) {
            return false;
        }
        Tab3 other = (Tab3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hbbdtus.Tab3[id=" + id + "]";
    }

}
