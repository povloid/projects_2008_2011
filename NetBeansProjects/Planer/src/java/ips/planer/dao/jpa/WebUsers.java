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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "web_users", catalog = "ips_o", schema = "ips")
@NamedQueries({@NamedQuery(name = "WebUsers.findAll", query = "SELECT w FROM WebUsers w"), @NamedQuery(name = "WebUsers.findById", query = "SELECT w FROM WebUsers w WHERE w.id = :id"), @NamedQuery(name = "WebUsers.findByUserName", query = "SELECT w FROM WebUsers w WHERE w.userName = :userName"), @NamedQuery(name = "WebUsers.findByUserPassword", query = "SELECT w FROM WebUsers w WHERE w.userPassword = :userPassword"), @NamedQuery(name = "WebUsers.findByDescription", query = "SELECT w FROM WebUsers w WHERE w.description = :description")})
public class WebUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "dep_id", referencedColumnName = "id")
    @ManyToOne
    private Deps depId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "webUserId")
    private Collection<Questions> questionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "webUserId")
    private Collection<Counsel> counselCollection;

    public WebUsers() {
    }

    public WebUsers(Integer id) {
        this.id = id;
    }

    public WebUsers(Integer id, String userName, String description) {
        this.id = id;
        this.userName = userName;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Deps getDepId() {
        return depId;
    }

    public void setDepId(Deps depId) {
        this.depId = depId;
    }

    public Collection<Questions> getQuestionsCollection() {
        return questionsCollection;
    }

    public void setQuestionsCollection(Collection<Questions> questionsCollection) {
        this.questionsCollection = questionsCollection;
    }

    public Collection<Counsel> getCounselCollection() {
        return counselCollection;
    }

    public void setCounselCollection(Collection<Counsel> counselCollection) {
        this.counselCollection = counselCollection;
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
        if (!(object instanceof WebUsers)) {
            return false;
        }
        WebUsers other = (WebUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ips.planer.dao.jpa.WebUsers[id=" + id + "]";
    }

}
