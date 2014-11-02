/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.dao.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kopychenko
 */
@Entity
@Table(name = "questions", catalog = "ips_o", schema = "ips")
@NamedQueries({@NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q"), @NamedQuery(name = "Questions.findById", query = "SELECT q FROM Questions q WHERE q.id = :id"), @NamedQuery(name = "Questions.findByQuestion", query = "SELECT q FROM Questions q WHERE q.question = :question"), @NamedQuery(name = "Questions.findByDescription", query = "SELECT q FROM Questions q WHERE q.description = :description"), @NamedQuery(name = "Questions.findByCtime", query = "SELECT q FROM Questions q WHERE q.ctime = :ctime"), @NamedQuery(name = "Questions.findByUtime", query = "SELECT q FROM Questions q WHERE q.utime = :utime")})
public class Questions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="QUESTIONS_ID_SEQ", sequenceName="ips.questions_id_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUESTIONS_ID_SEQ")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "question")
    private String question;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "ctime")
    @Temporal(TemporalType.TIME)
    private Date ctime;
    @Basic(optional = false)
    @Column(name = "utime")
    @Temporal(TemporalType.TIME)
    private Date utime;
    @JoinColumn(name = "dep_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Deps depId;
    @JoinColumn(name = "from_dep_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Deps fromDepId;
    @JoinColumn(name = "web_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WebUsers webUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<Counsel> counselCollection;

    public Questions() {
    }

    public Questions(Integer id) {
        this.id = id;
    }

    public Questions(Integer id, String question, Date ctime, Date utime) {
        this.id = id;
        this.question = question;
        this.ctime = ctime;
        this.utime = utime;
    }

    public Questions(String question, String description, Date ctime, Date utime, Deps depId, Deps fromDepId, WebUsers webUserId) {
        this.question = question;
        this.description = description;
        this.ctime = ctime;
        this.utime = utime;
        this.depId = depId;
        this.fromDepId = fromDepId;
        this.webUserId = webUserId;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Deps getDepId() {
        return depId;
    }

    public void setDepId(Deps depId) {
        this.depId = depId;
    }

    public Deps getFromDepId() {
        return fromDepId;
    }

    public void setFromDepId(Deps fromDepId) {
        this.fromDepId = fromDepId;
    }

    public WebUsers getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(WebUsers webUserId) {
        this.webUserId = webUserId;
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
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ips.planer.dao.jpa.Questions[id=" + id + "]";
    }

}
