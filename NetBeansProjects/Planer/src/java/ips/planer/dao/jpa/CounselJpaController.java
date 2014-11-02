/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.dao.jpa;

import ips.planer.dao.jpa.exceptions.NonexistentEntityException;
import ips.planer.dao.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author kopychenko
 */
public class CounselJpaController {

    public CounselJpaController() {
        emf = Persistence.createEntityManagerFactory("PlanerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Counsel counsel) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questions questionId = counsel.getQuestionId();
            if (questionId != null) {
                questionId = em.getReference(questionId.getClass(), questionId.getId());
                counsel.setQuestionId(questionId);
            }
            WebUsers webUserId = counsel.getWebUserId();
            if (webUserId != null) {
                webUserId = em.getReference(webUserId.getClass(), webUserId.getId());
                counsel.setWebUserId(webUserId);
            }
            em.persist(counsel);
            if (questionId != null) {
                questionId.getCounselCollection().add(counsel);
                questionId = em.merge(questionId);
            }
            if (webUserId != null) {
                webUserId.getCounselCollection().add(counsel);
                webUserId = em.merge(webUserId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCounsel(counsel.getId()) != null) {
                throw new PreexistingEntityException("Counsel " + counsel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Counsel counsel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Counsel persistentCounsel = em.find(Counsel.class, counsel.getId());
            Questions questionIdOld = persistentCounsel.getQuestionId();
            Questions questionIdNew = counsel.getQuestionId();
            WebUsers webUserIdOld = persistentCounsel.getWebUserId();
            WebUsers webUserIdNew = counsel.getWebUserId();
            if (questionIdNew != null) {
                questionIdNew = em.getReference(questionIdNew.getClass(), questionIdNew.getId());
                counsel.setQuestionId(questionIdNew);
            }
            if (webUserIdNew != null) {
                webUserIdNew = em.getReference(webUserIdNew.getClass(), webUserIdNew.getId());
                counsel.setWebUserId(webUserIdNew);
            }
            counsel = em.merge(counsel);
            if (questionIdOld != null && !questionIdOld.equals(questionIdNew)) {
                questionIdOld.getCounselCollection().remove(counsel);
                questionIdOld = em.merge(questionIdOld);
            }
            if (questionIdNew != null && !questionIdNew.equals(questionIdOld)) {
                questionIdNew.getCounselCollection().add(counsel);
                questionIdNew = em.merge(questionIdNew);
            }
            if (webUserIdOld != null && !webUserIdOld.equals(webUserIdNew)) {
                webUserIdOld.getCounselCollection().remove(counsel);
                webUserIdOld = em.merge(webUserIdOld);
            }
            if (webUserIdNew != null && !webUserIdNew.equals(webUserIdOld)) {
                webUserIdNew.getCounselCollection().add(counsel);
                webUserIdNew = em.merge(webUserIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = counsel.getId();
                if (findCounsel(id) == null) {
                    throw new NonexistentEntityException("The counsel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Counsel counsel;
            try {
                counsel = em.getReference(Counsel.class, id);
                counsel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The counsel with id " + id + " no longer exists.", enfe);
            }
            Questions questionId = counsel.getQuestionId();
            if (questionId != null) {
                questionId.getCounselCollection().remove(counsel);
                questionId = em.merge(questionId);
            }
            WebUsers webUserId = counsel.getWebUserId();
            if (webUserId != null) {
                webUserId.getCounselCollection().remove(counsel);
                webUserId = em.merge(webUserId);
            }
            em.remove(counsel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Counsel> findCounselEntities() {
        return findCounselEntities(true, -1, -1);
    }

    public List<Counsel> findCounselEntities(int maxResults, int firstResult) {
        return findCounselEntities(false, maxResults, firstResult);
    }

    private List<Counsel> findCounselEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Counsel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Counsel findCounsel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Counsel.class, id);
        } finally {
            em.close();
        }
    }

    public int getCounselCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Counsel as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
