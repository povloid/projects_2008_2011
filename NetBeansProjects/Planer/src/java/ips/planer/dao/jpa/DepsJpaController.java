/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.dao.jpa;

import ips.planer.dao.jpa.exceptions.IllegalOrphanException;
import ips.planer.dao.jpa.exceptions.NonexistentEntityException;
import ips.planer.dao.jpa.exceptions.PreexistingEntityException;
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
public class DepsJpaController {

    public DepsJpaController() {
        emf = Persistence.createEntityManagerFactory("PlanerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Deps deps) throws PreexistingEntityException, Exception {
        if (deps.getWebUsersCollection() == null) {
            deps.setWebUsersCollection(new ArrayList<WebUsers>());
        }
        if (deps.getQuestionsCollection() == null) {
            deps.setQuestionsCollection(new ArrayList<Questions>());
        }
        if (deps.getQuestionsCollection1() == null) {
            deps.setQuestionsCollection1(new ArrayList<Questions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<WebUsers> attachedWebUsersCollection = new ArrayList<WebUsers>();
            for (WebUsers webUsersCollectionWebUsersToAttach : deps.getWebUsersCollection()) {
                webUsersCollectionWebUsersToAttach = em.getReference(webUsersCollectionWebUsersToAttach.getClass(), webUsersCollectionWebUsersToAttach.getId());
                attachedWebUsersCollection.add(webUsersCollectionWebUsersToAttach);
            }
            deps.setWebUsersCollection(attachedWebUsersCollection);
            Collection<Questions> attachedQuestionsCollection = new ArrayList<Questions>();
            for (Questions questionsCollectionQuestionsToAttach : deps.getQuestionsCollection()) {
                questionsCollectionQuestionsToAttach = em.getReference(questionsCollectionQuestionsToAttach.getClass(), questionsCollectionQuestionsToAttach.getId());
                attachedQuestionsCollection.add(questionsCollectionQuestionsToAttach);
            }
            deps.setQuestionsCollection(attachedQuestionsCollection);
            Collection<Questions> attachedQuestionsCollection1 = new ArrayList<Questions>();
            for (Questions questionsCollection1QuestionsToAttach : deps.getQuestionsCollection1()) {
                questionsCollection1QuestionsToAttach = em.getReference(questionsCollection1QuestionsToAttach.getClass(), questionsCollection1QuestionsToAttach.getId());
                attachedQuestionsCollection1.add(questionsCollection1QuestionsToAttach);
            }
            deps.setQuestionsCollection1(attachedQuestionsCollection1);
            em.persist(deps);
            for (WebUsers webUsersCollectionWebUsers : deps.getWebUsersCollection()) {
                Deps oldDepIdOfWebUsersCollectionWebUsers = webUsersCollectionWebUsers.getDepId();
                webUsersCollectionWebUsers.setDepId(deps);
                webUsersCollectionWebUsers = em.merge(webUsersCollectionWebUsers);
                if (oldDepIdOfWebUsersCollectionWebUsers != null) {
                    oldDepIdOfWebUsersCollectionWebUsers.getWebUsersCollection().remove(webUsersCollectionWebUsers);
                    oldDepIdOfWebUsersCollectionWebUsers = em.merge(oldDepIdOfWebUsersCollectionWebUsers);
                }
            }
            for (Questions questionsCollectionQuestions : deps.getQuestionsCollection()) {
                Deps oldDepIdOfQuestionsCollectionQuestions = questionsCollectionQuestions.getDepId();
                questionsCollectionQuestions.setDepId(deps);
                questionsCollectionQuestions = em.merge(questionsCollectionQuestions);
                if (oldDepIdOfQuestionsCollectionQuestions != null) {
                    oldDepIdOfQuestionsCollectionQuestions.getQuestionsCollection().remove(questionsCollectionQuestions);
                    oldDepIdOfQuestionsCollectionQuestions = em.merge(oldDepIdOfQuestionsCollectionQuestions);
                }
            }
            for (Questions questionsCollection1Questions : deps.getQuestionsCollection1()) {
                Deps oldFromDepIdOfQuestionsCollection1Questions = questionsCollection1Questions.getFromDepId();
                questionsCollection1Questions.setFromDepId(deps);
                questionsCollection1Questions = em.merge(questionsCollection1Questions);
                if (oldFromDepIdOfQuestionsCollection1Questions != null) {
                    oldFromDepIdOfQuestionsCollection1Questions.getQuestionsCollection1().remove(questionsCollection1Questions);
                    oldFromDepIdOfQuestionsCollection1Questions = em.merge(oldFromDepIdOfQuestionsCollection1Questions);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDeps(deps.getId()) != null) {
                throw new PreexistingEntityException("Deps " + deps + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Deps deps) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deps persistentDeps = em.find(Deps.class, deps.getId());
            Collection<WebUsers> webUsersCollectionOld = persistentDeps.getWebUsersCollection();
            Collection<WebUsers> webUsersCollectionNew = deps.getWebUsersCollection();
            Collection<Questions> questionsCollectionOld = persistentDeps.getQuestionsCollection();
            Collection<Questions> questionsCollectionNew = deps.getQuestionsCollection();
            Collection<Questions> questionsCollection1Old = persistentDeps.getQuestionsCollection1();
            Collection<Questions> questionsCollection1New = deps.getQuestionsCollection1();
            List<String> illegalOrphanMessages = null;
            for (Questions questionsCollectionOldQuestions : questionsCollectionOld) {
                if (!questionsCollectionNew.contains(questionsCollectionOldQuestions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Questions " + questionsCollectionOldQuestions + " since its depId field is not nullable.");
                }
            }
            for (Questions questionsCollection1OldQuestions : questionsCollection1Old) {
                if (!questionsCollection1New.contains(questionsCollection1OldQuestions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Questions " + questionsCollection1OldQuestions + " since its fromDepId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<WebUsers> attachedWebUsersCollectionNew = new ArrayList<WebUsers>();
            for (WebUsers webUsersCollectionNewWebUsersToAttach : webUsersCollectionNew) {
                webUsersCollectionNewWebUsersToAttach = em.getReference(webUsersCollectionNewWebUsersToAttach.getClass(), webUsersCollectionNewWebUsersToAttach.getId());
                attachedWebUsersCollectionNew.add(webUsersCollectionNewWebUsersToAttach);
            }
            webUsersCollectionNew = attachedWebUsersCollectionNew;
            deps.setWebUsersCollection(webUsersCollectionNew);
            Collection<Questions> attachedQuestionsCollectionNew = new ArrayList<Questions>();
            for (Questions questionsCollectionNewQuestionsToAttach : questionsCollectionNew) {
                questionsCollectionNewQuestionsToAttach = em.getReference(questionsCollectionNewQuestionsToAttach.getClass(), questionsCollectionNewQuestionsToAttach.getId());
                attachedQuestionsCollectionNew.add(questionsCollectionNewQuestionsToAttach);
            }
            questionsCollectionNew = attachedQuestionsCollectionNew;
            deps.setQuestionsCollection(questionsCollectionNew);
            Collection<Questions> attachedQuestionsCollection1New = new ArrayList<Questions>();
            for (Questions questionsCollection1NewQuestionsToAttach : questionsCollection1New) {
                questionsCollection1NewQuestionsToAttach = em.getReference(questionsCollection1NewQuestionsToAttach.getClass(), questionsCollection1NewQuestionsToAttach.getId());
                attachedQuestionsCollection1New.add(questionsCollection1NewQuestionsToAttach);
            }
            questionsCollection1New = attachedQuestionsCollection1New;
            deps.setQuestionsCollection1(questionsCollection1New);
            deps = em.merge(deps);
            for (WebUsers webUsersCollectionOldWebUsers : webUsersCollectionOld) {
                if (!webUsersCollectionNew.contains(webUsersCollectionOldWebUsers)) {
                    webUsersCollectionOldWebUsers.setDepId(null);
                    webUsersCollectionOldWebUsers = em.merge(webUsersCollectionOldWebUsers);
                }
            }
            for (WebUsers webUsersCollectionNewWebUsers : webUsersCollectionNew) {
                if (!webUsersCollectionOld.contains(webUsersCollectionNewWebUsers)) {
                    Deps oldDepIdOfWebUsersCollectionNewWebUsers = webUsersCollectionNewWebUsers.getDepId();
                    webUsersCollectionNewWebUsers.setDepId(deps);
                    webUsersCollectionNewWebUsers = em.merge(webUsersCollectionNewWebUsers);
                    if (oldDepIdOfWebUsersCollectionNewWebUsers != null && !oldDepIdOfWebUsersCollectionNewWebUsers.equals(deps)) {
                        oldDepIdOfWebUsersCollectionNewWebUsers.getWebUsersCollection().remove(webUsersCollectionNewWebUsers);
                        oldDepIdOfWebUsersCollectionNewWebUsers = em.merge(oldDepIdOfWebUsersCollectionNewWebUsers);
                    }
                }
            }
            for (Questions questionsCollectionNewQuestions : questionsCollectionNew) {
                if (!questionsCollectionOld.contains(questionsCollectionNewQuestions)) {
                    Deps oldDepIdOfQuestionsCollectionNewQuestions = questionsCollectionNewQuestions.getDepId();
                    questionsCollectionNewQuestions.setDepId(deps);
                    questionsCollectionNewQuestions = em.merge(questionsCollectionNewQuestions);
                    if (oldDepIdOfQuestionsCollectionNewQuestions != null && !oldDepIdOfQuestionsCollectionNewQuestions.equals(deps)) {
                        oldDepIdOfQuestionsCollectionNewQuestions.getQuestionsCollection().remove(questionsCollectionNewQuestions);
                        oldDepIdOfQuestionsCollectionNewQuestions = em.merge(oldDepIdOfQuestionsCollectionNewQuestions);
                    }
                }
            }
            for (Questions questionsCollection1NewQuestions : questionsCollection1New) {
                if (!questionsCollection1Old.contains(questionsCollection1NewQuestions)) {
                    Deps oldFromDepIdOfQuestionsCollection1NewQuestions = questionsCollection1NewQuestions.getFromDepId();
                    questionsCollection1NewQuestions.setFromDepId(deps);
                    questionsCollection1NewQuestions = em.merge(questionsCollection1NewQuestions);
                    if (oldFromDepIdOfQuestionsCollection1NewQuestions != null && !oldFromDepIdOfQuestionsCollection1NewQuestions.equals(deps)) {
                        oldFromDepIdOfQuestionsCollection1NewQuestions.getQuestionsCollection1().remove(questionsCollection1NewQuestions);
                        oldFromDepIdOfQuestionsCollection1NewQuestions = em.merge(oldFromDepIdOfQuestionsCollection1NewQuestions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = deps.getId();
                if (findDeps(id) == null) {
                    throw new NonexistentEntityException("The deps with id " + id + " no longer exists.");
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
            Deps deps;
            try {
                deps = em.getReference(Deps.class, id);
                deps.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deps with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Questions> questionsCollectionOrphanCheck = deps.getQuestionsCollection();
            for (Questions questionsCollectionOrphanCheckQuestions : questionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Deps (" + deps + ") cannot be destroyed since the Questions " + questionsCollectionOrphanCheckQuestions + " in its questionsCollection field has a non-nullable depId field.");
            }
            Collection<Questions> questionsCollection1OrphanCheck = deps.getQuestionsCollection1();
            for (Questions questionsCollection1OrphanCheckQuestions : questionsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Deps (" + deps + ") cannot be destroyed since the Questions " + questionsCollection1OrphanCheckQuestions + " in its questionsCollection1 field has a non-nullable fromDepId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<WebUsers> webUsersCollection = deps.getWebUsersCollection();
            for (WebUsers webUsersCollectionWebUsers : webUsersCollection) {
                webUsersCollectionWebUsers.setDepId(null);
                webUsersCollectionWebUsers = em.merge(webUsersCollectionWebUsers);
            }
            em.remove(deps);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Deps> findDepsEntities() {
        return findDepsEntities(true, -1, -1);
    }

    public List<Deps> findDepsEntities(int maxResults, int firstResult) {
        return findDepsEntities(false, maxResults, firstResult);
    }

    private List<Deps> findDepsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Deps as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Deps> findDepsEntitiesForNotForDep(Deps deps) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Deps as o where o!=:deps");
            q.setParameter("deps", deps);

            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public Deps findDeps(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Deps.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Deps as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
