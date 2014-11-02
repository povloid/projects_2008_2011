/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.dao.jpa;

import ips.planer.dao.jpa.exceptions.IllegalOrphanException;
import ips.planer.dao.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author kopychenko
 */
public class QuestionsJpaController {

    public QuestionsJpaController() {
        emf = Persistence.createEntityManagerFactory("PlanerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Questions questions) {
        if (questions.getCounselCollection() == null) {
            questions.setCounselCollection(new ArrayList<Counsel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deps depId = questions.getDepId();
            if (depId != null) {
                depId = em.getReference(depId.getClass(), depId.getId());
                questions.setDepId(depId);
            }
            Deps fromDepId = questions.getFromDepId();
            if (fromDepId != null) {
                fromDepId = em.getReference(fromDepId.getClass(), fromDepId.getId());
                questions.setFromDepId(fromDepId);
            }
            WebUsers webUserId = questions.getWebUserId();
            if (webUserId != null) {
                webUserId = em.getReference(webUserId.getClass(), webUserId.getId());
                questions.setWebUserId(webUserId);
            }
            Collection<Counsel> attachedCounselCollection = new ArrayList<Counsel>();
            for (Counsel counselCollectionCounselToAttach : questions.getCounselCollection()) {
                counselCollectionCounselToAttach = em.getReference(counselCollectionCounselToAttach.getClass(), counselCollectionCounselToAttach.getId());
                attachedCounselCollection.add(counselCollectionCounselToAttach);
            }
            questions.setCounselCollection(attachedCounselCollection);
            em.persist(questions);
            if (depId != null) {
                depId.getQuestionsCollection().add(questions);
                depId = em.merge(depId);
            }
            if (fromDepId != null) {
                fromDepId.getQuestionsCollection().add(questions);
                fromDepId = em.merge(fromDepId);
            }
            if (webUserId != null) {
                webUserId.getQuestionsCollection().add(questions);
                webUserId = em.merge(webUserId);
            }
            for (Counsel counselCollectionCounsel : questions.getCounselCollection()) {
                Questions oldQuestionIdOfCounselCollectionCounsel = counselCollectionCounsel.getQuestionId();
                counselCollectionCounsel.setQuestionId(questions);
                counselCollectionCounsel = em.merge(counselCollectionCounsel);
                if (oldQuestionIdOfCounselCollectionCounsel != null) {
                    oldQuestionIdOfCounselCollectionCounsel.getCounselCollection().remove(counselCollectionCounsel);
                    oldQuestionIdOfCounselCollectionCounsel = em.merge(oldQuestionIdOfCounselCollectionCounsel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Questions questions) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questions persistentQuestions = em.find(Questions.class, questions.getId());
            Deps depIdOld = persistentQuestions.getDepId();
            Deps depIdNew = questions.getDepId();
            Deps fromDepIdOld = persistentQuestions.getFromDepId();
            Deps fromDepIdNew = questions.getFromDepId();
            WebUsers webUserIdOld = persistentQuestions.getWebUserId();
            WebUsers webUserIdNew = questions.getWebUserId();
            Collection<Counsel> counselCollectionOld = persistentQuestions.getCounselCollection();
            Collection<Counsel> counselCollectionNew = questions.getCounselCollection();
            List<String> illegalOrphanMessages = null;
            for (Counsel counselCollectionOldCounsel : counselCollectionOld) {
                if (!counselCollectionNew.contains(counselCollectionOldCounsel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Counsel " + counselCollectionOldCounsel + " since its questionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (depIdNew != null) {
                depIdNew = em.getReference(depIdNew.getClass(), depIdNew.getId());
                questions.setDepId(depIdNew);
            }
            if (fromDepIdNew != null) {
                fromDepIdNew = em.getReference(fromDepIdNew.getClass(), fromDepIdNew.getId());
                questions.setFromDepId(fromDepIdNew);
            }
            if (webUserIdNew != null) {
                webUserIdNew = em.getReference(webUserIdNew.getClass(), webUserIdNew.getId());
                questions.setWebUserId(webUserIdNew);
            }
            Collection<Counsel> attachedCounselCollectionNew = new ArrayList<Counsel>();
            for (Counsel counselCollectionNewCounselToAttach : counselCollectionNew) {
                counselCollectionNewCounselToAttach = em.getReference(counselCollectionNewCounselToAttach.getClass(), counselCollectionNewCounselToAttach.getId());
                attachedCounselCollectionNew.add(counselCollectionNewCounselToAttach);
            }
            counselCollectionNew = attachedCounselCollectionNew;
            questions.setCounselCollection(counselCollectionNew);
            questions = em.merge(questions);
            if (depIdOld != null && !depIdOld.equals(depIdNew)) {
                depIdOld.getQuestionsCollection().remove(questions);
                depIdOld = em.merge(depIdOld);
            }
            if (depIdNew != null && !depIdNew.equals(depIdOld)) {
                depIdNew.getQuestionsCollection().add(questions);
                depIdNew = em.merge(depIdNew);
            }
            if (fromDepIdOld != null && !fromDepIdOld.equals(fromDepIdNew)) {
                fromDepIdOld.getQuestionsCollection().remove(questions);
                fromDepIdOld = em.merge(fromDepIdOld);
            }
            if (fromDepIdNew != null && !fromDepIdNew.equals(fromDepIdOld)) {
                fromDepIdNew.getQuestionsCollection().add(questions);
                fromDepIdNew = em.merge(fromDepIdNew);
            }
            if (webUserIdOld != null && !webUserIdOld.equals(webUserIdNew)) {
                webUserIdOld.getQuestionsCollection().remove(questions);
                webUserIdOld = em.merge(webUserIdOld);
            }
            if (webUserIdNew != null && !webUserIdNew.equals(webUserIdOld)) {
                webUserIdNew.getQuestionsCollection().add(questions);
                webUserIdNew = em.merge(webUserIdNew);
            }
            for (Counsel counselCollectionNewCounsel : counselCollectionNew) {
                if (!counselCollectionOld.contains(counselCollectionNewCounsel)) {
                    Questions oldQuestionIdOfCounselCollectionNewCounsel = counselCollectionNewCounsel.getQuestionId();
                    counselCollectionNewCounsel.setQuestionId(questions);
                    counselCollectionNewCounsel = em.merge(counselCollectionNewCounsel);
                    if (oldQuestionIdOfCounselCollectionNewCounsel != null && !oldQuestionIdOfCounselCollectionNewCounsel.equals(questions)) {
                        oldQuestionIdOfCounselCollectionNewCounsel.getCounselCollection().remove(counselCollectionNewCounsel);
                        oldQuestionIdOfCounselCollectionNewCounsel = em.merge(oldQuestionIdOfCounselCollectionNewCounsel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = questions.getId();
                if (findQuestions(id) == null) {
                    throw new NonexistentEntityException("The questions with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questions questions;
            try {
                questions = em.getReference(Questions.class, id);
                questions.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The questions with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Counsel> counselCollectionOrphanCheck = questions.getCounselCollection();
            for (Counsel counselCollectionOrphanCheckCounsel : counselCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questions (" + questions + ") cannot be destroyed since the Counsel " + counselCollectionOrphanCheckCounsel + " in its counselCollection field has a non-nullable questionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Deps depId = questions.getDepId();
            if (depId != null) {
                depId.getQuestionsCollection().remove(questions);
                depId = em.merge(depId);
            }
            Deps fromDepId = questions.getFromDepId();
            if (fromDepId != null) {
                fromDepId.getQuestionsCollection().remove(questions);
                fromDepId = em.merge(fromDepId);
            }
            WebUsers webUserId = questions.getWebUserId();
            if (webUserId != null) {
                webUserId.getQuestionsCollection().remove(questions);
                webUserId = em.merge(webUserId);
            }
            em.remove(questions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Questions> findQuestionsEntities() {
        return findQuestionsEntities(true, -1, -1);
    }

    public List<Questions> findQuestionsEntities(int maxResults, int firstResult) {
        return findQuestionsEntities(false, maxResults, firstResult);
    }

    private List<Questions> findQuestionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Questions as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Questions> findQuestionsEntitiesForDeps(Deps dep, Deps fromDep) {
        EntityManager em = getEntityManager();
        try {
            Query q =
                    em.createQuery("select object(o) from Questions as o " +
                    "WHERE o.depId = :dep AND o.fromDepId=:fromDep");
            q.setParameter("dep", dep);
            q.setParameter("fromDep", fromDep);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Questions findQuestions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Questions.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuestionsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Questions as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
