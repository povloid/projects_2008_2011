/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.dao.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "deps", catalog = "ips_o", schema = "ips")
@NamedQueries({@NamedQuery(name = "Deps.findAll", query = "SELECT d FROM Deps d"), @NamedQuery(name = "Deps.findById", query = "SELECT d FROM Deps d WHERE d.id = :id"), @NamedQuery(name = "Deps.findByKeyName", query = "SELECT d FROM Deps d WHERE d.keyName = :keyName"), @NamedQuery(name = "Deps.findByDescription", query = "SELECT d FROM Deps d WHERE d.description = :description")})
public class Deps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "key_name")
    private String keyName;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "depId")
    private Collection<WebUsers> webUsersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depId")
    private Collection<Questions> questionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromDepId")
    private Collection<Questions> questionsCollection1;

    public Deps() {
    }

    public Deps(Integer id) {
        this.id = id;
    }

    public Deps(Integer id, String keyName) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<WebUsers> getWebUsersCollection() {
        return webUsersCollection;
    }

    public void setWebUsersCollection(Collection<WebUsers> webUsersCollection) {
        this.webUsersCollection = webUsersCollection;
    }

    public Collection<Questions> getQuestionsCollection() {
        return questionsCollection;
    }

    public void setQuestionsCollection(Collection<Questions> questionsCollection) {
        this.questionsCollection = questionsCollection;
    }

    public Collection<Questions> getQuestionsCollection1() {
        return questionsCollection1;
    }

    public void setQuestionsCollection1(Collection<Questions> questionsCollection1) {
        this.questionsCollection1 = questionsCollection1;
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
        if (!(object instanceof Deps)) {
            return false;
        }
        Deps other = (Deps) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ips.planer.dao.jpa.Deps[id=" + id + "]";
    }

}
