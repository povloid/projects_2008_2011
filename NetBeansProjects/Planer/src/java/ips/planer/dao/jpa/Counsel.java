/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.dao.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "counsel", catalog = "ips_o", schema = "ips")
@NamedQueries({@NamedQuery(name = "Counsel.findAll", query = "SELECT c FROM Counsel c"), @NamedQuery(name = "Counsel.findById", query = "SELECT c FROM Counsel c WHERE c.id = :id"), @NamedQuery(name = "Counsel.findByPost", query = "SELECT c FROM Counsel c WHERE c.post = :post"), @NamedQuery(name = "Counsel.findByCtime", query = "SELECT c FROM Counsel c WHERE c.ctime = :ctime")})
public class Counsel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "post")
    private String post;
    @Basic(optional = false)
    @Column(name = "ctime")
    @Temporal(TemporalType.TIME)
    private Date ctime;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questions questionId;
    @JoinColumn(name = "web_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WebUsers webUserId;

    public Counsel() {
    }

    public Counsel(Integer id) {
        this.id = id;
    }

    public Counsel(Integer id, String post, Date ctime) {
        this.id = id;
        this.post = post;
        this.ctime = ctime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Questions getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Questions questionId) {
        this.questionId = questionId;
    }

    public WebUsers getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(WebUsers webUserId) {
        this.webUserId = webUserId;
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
        if (!(object instanceof Counsel)) {
            return false;
        }
        Counsel other = (Counsel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ips.planer.dao.jpa.Counsel[id=" + id + "]";
    }

}
