/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hbbdtus;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "tab2")
@NamedQueries({@NamedQuery(name = "Tab2.findAll", query = "SELECT t FROM Tab2 t"), @NamedQuery(name = "Tab2.findById", query = "SELECT t FROM Tab2 t WHERE t.id = :id"), @NamedQuery(name = "Tab2.findByKeyName", query = "SELECT t FROM Tab2 t WHERE t.keyName = :keyName")})
public class Tab2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB2_ID_SEQ")
    @SequenceGenerator(name="TAB2_ID_SEQ", sequenceName="tab2_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "key_name")
    private String keyName;

    public Tab2() {
    }

    public Tab2(String keyName) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tab2)) {
            return false;
        }
        Tab2 other = (Tab2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hbbdtus.Tab2[id=" + id + "]";
    }

}
