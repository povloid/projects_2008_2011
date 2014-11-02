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
public class WebUsersJpaController {

    public WebUsersJpaController() {
        emf = Persistence.createEntityManagerFactory("PlanerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WebUsers webUsers) throws PreexistingEntityException, Exception {
        if (webUsers.getQuestionsCollection() == null) {
            webUsers.setQuestionsCollection(new ArrayList<Questions>());
        }
        if (webUsers.getCounselCollection() == null) {
            webUsers.setCounselCollection(new ArrayList<Counsel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deps depId = webUsers.getDepId();
            if (depId != null) {
                depId = em.getReference(depId.getClass(), depId.getId());
                webUsers.setDepId(depId);
            }
            Collection<Questions> attachedQuestionsCollection = new ArrayList<Questions>();
            for (Questions questionsCollectionQuestionsToAttach : webUsers.getQuestionsCollection()) {
                questionsCollectionQuestionsToAttach = em.getReference(questionsCollectionQuestionsToAttach.getClass(), questionsCollectionQuestionsToAttach.getId());
                attachedQuestionsCollection.add(questionsCollectionQuestionsToAttach);
            }
            webUsers.setQuestionsCollection(attachedQuestionsCollection);
            Collection<Counsel> attachedCounselCollection = new ArrayList<Counsel>();
            for (Counsel counselCollectionCounselToAttach : webUsers.getCounselCollection()) {
                counselCollectionCounselToAttach = em.getReference(counselCollectionCounselToAttach.getClass(), counselCollectionCounselToAttach.getId());
                attachedCounselCollection.add(counselCollectionCounselToAttach);
            }
            webUsers.setCounselCollection(attachedCounselCollection);
            em.persist(webUsers);
            if (depId != null) {
                depId.getWebUsersCollection().add(webUsers);
                depId = em.merge(depId);
            }
            for (Questions questionsCollectionQuestions : webUsers.getQuestionsCollection()) {
                WebUsers oldWebUserIdOfQuestionsCollectionQuestions = questionsCollectionQuestions.getWebUserId();
                questionsCollectionQuestions.setWebUserId(webUsers);
                questionsCollectionQuestions = em.merge(questionsCollectionQuestions);
                if (oldWebUserIdOfQuestionsCollectionQuestions != null) {
                    oldWebUserIdOfQuestionsCollectionQuestions.getQuestionsCollection().remove(questionsCollectionQuestions);
                    oldWebUserIdOfQuestionsCollectionQuestions = em.merge(oldWebUserIdOfQuestionsCollectionQuestions);
                }
            }
            for (Counsel counselCollectionCounsel : webUsers.getCounselCollection()) {
                WebUsers oldWebUserIdOfCounselCollectionCounsel = counselCollectionCounsel.getWebUserId();
                counselCollectionCounsel.setWebUserId(webUsers);
                counselCollectionCounsel = em.merge(counselCollectionCounsel);
                if (oldWebUserIdOfCounselCollectionCounsel != null) {
                    oldWebUserIdOfCounselCollectionCounsel.getCounselCollection().remove(counselCollectionCounsel);
                    oldWebUserIdOfCounselCollectionCounsel = em.merge(oldWebUserIdOfCounselCollectionCounsel);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findWebUsers(webUsers.getId()) != null) {
                throw new PreexistingEntityException("WebUsers " + webUsers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WebUsers webUsers) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WebUsers persistentWebUsers = em.find(WebUsers.class, webUsers.getId());
            Deps depIdOld = persistentWebUsers.getDepId();
            Deps depIdNew = webUsers.getDepId();
            Collection<Questions> questionsCollectionOld = persistentWebUsers.getQuestionsCollection();
            Collection<Questions> questionsCollectionNew = webUsers.getQuestionsCollection();
            Collection<Counsel> counselCollectionOld = persistentWebUsers.getCounselCollection();
            Collection<Counsel> counselCollectionNew = webUsers.getCounselCollection();
            List<String> illegalOrphanMessages = null;
            for (Questions questionsCollectionOldQuestions : questionsCollectionOld) {
                if (!questionsCollectionNew.contains(questionsCollectionOldQuestions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Questions " + questionsCollectionOldQuestions + " since its webUserId field is not nullable.");
                }
            }
            for (Counsel counselCollectionOldCounsel : counselCollectionOld) {
                if (!counselCollectionNew.contains(counselCollectionOldCounsel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Counsel " + counselCollectionOldCounsel + " since its webUserId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (depIdNew != null) {
                depIdNew = em.getReference(depIdNew.getClass(), depIdNew.getId());
                webUsers.setDepId(depIdNew);
            }
            Collection<Questions> attachedQuestionsCollectionNew = new ArrayList<Questions>();
            for (Questions questionsCollectionNewQuestionsToAttach : questionsCollectionNew) {
                questionsCollectionNewQuestionsToAttach = em.getReference(questionsCollectionNewQuestionsToAttach.getClass(), questionsCollectionNewQuestionsToAttach.getId());
                attachedQuestionsCollectionNew.add(questionsCollectionNewQuestionsToAttach);
            }
            questionsCollectionNew = attachedQuestionsCollectionNew;
            webUsers.setQuestionsCollection(questionsCollectionNew);
            Collection<Counsel> attachedCounselCollectionNew = new ArrayList<Counsel>();
            for (Counsel counselCollectionNewCounselToAttach : counselCollectionNew) {
                counselCollectionNewCounselToAttach = em.getReference(counselCollectionNewCounselToAttach.getClass(), counselCollectionNewCounselToAttach.getId());
                attachedCounselCollectionNew.add(counselCollectionNewCounselToAttach);
            }
            counselCollectionNew = attachedCounselCollectionNew;
            webUsers.setCounselCollection(counselCollectionNew);
            webUsers = em.merge(webUsers);
            if (depIdOld != null && !depIdOld.equals(depIdNew)) {
                depIdOld.getWebUsersCollection().remove(webUsers);
                depIdOld = em.merge(depIdOld);
            }
            if (depIdNew != null && !depIdNew.equals(depIdOld)) {
                depIdNew.getWebUsersCollection().add(webUsers);
                depIdNew = em.merge(depIdNew);
            }
            for (Questions questionsCollectionNewQuestions : questionsCollectionNew) {
                if (!questionsCollectionOld.contains(questionsCollectionNewQuestions)) {
                    WebUsers oldWebUserIdOfQuestionsCollectionNewQuestions = questionsCollectionNewQuestions.getWebUserId();
                    questionsCollectionNewQuestions.setWebUserId(webUsers);
                    questionsCollectionNewQuestions = em.merge(questionsCollectionNewQuestions);
                    if (oldWebUserIdOfQuestionsCollectionNewQuestions != null && !oldWebUserIdOfQuestionsCollectionNewQuestions.equals(webUsers)) {
                        oldWebUserIdOfQuestionsCollectionNewQuestions.getQuestionsCollection().remove(questionsCollectionNewQuestions);
                        oldWebUserIdOfQuestionsCollectionNewQuestions = em.merge(oldWebUserIdOfQuestionsCollectionNewQuestions);
                    }
                }
            }
            for (Counsel counselCollectionNewCounsel : counselCollectionNew) {
                if (!counselCollectionOld.contains(counselCollectionNewCounsel)) {
                    WebUsers oldWebUserIdOfCounselCollectionNewCounsel = counselCollectionNewCounsel.getWebUserId();
                    counselCollectionNewCounsel.setWebUserId(webUsers);
                    counselCollectionNewCounsel = em.merge(counselCollectionNewCounsel);
                    if (oldWebUserIdOfCounselCollectionNewCounsel != null && !oldWebUserIdOfCounselCollectionNewCounsel.equals(webUsers)) {
                        oldWebUserIdOfCounselCollectionNewCounsel.getCounselCollection().remove(counselCollectionNewCounsel);
                        oldWebUserIdOfCounselCollectionNewCounsel = em.merge(oldWebUserIdOfCounselCollectionNewCounsel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = webUsers.getId();
                if (findWebUsers(id) == null) {
                    throw new NonexistentEntityException("The webUsers with id " + id + " no longer exists.");
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
            WebUsers webUsers;
            try {
                webUsers = em.getReference(WebUsers.class, id);
                webUsers.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The webUsers with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Questions> questionsCollectionOrphanCheck = webUsers.getQuestionsCollection();
            for (Questions questionsCollectionOrphanCheckQuestions : questionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WebUsers (" + webUsers + ") cannot be destroyed since the Questions " + questionsCollectionOrphanCheckQuestions + " in its questionsCollection field has a non-nullable webUserId field.");
            }
            Collection<Counsel> counselCollectionOrphanCheck = webUsers.getCounselCollection();
            for (Counsel counselCollectionOrphanCheckCounsel : counselCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WebUsers (" + webUsers + ") cannot be destroyed since the Counsel " + counselCollectionOrphanCheckCounsel + " in its counselCollection field has a non-nullable webUserId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Deps depId = webUsers.getDepId();
            if (depId != null) {
                depId.getWebUsersCollection().remove(webUsers);
                depId = em.merge(depId);
            }
            em.remove(webUsers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WebUsers> findWebUsersEntities() {
        return findWebUsersEntities(true, -1, -1);
    }

    public List<WebUsers> findWebUsersEntities(int maxResults, int firstResult) {
        return findWebUsersEntities(false, maxResults, firstResult);
    }

    private List<WebUsers> findWebUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from WebUsers as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public WebUsers findWebUsersEntityForName(String userName){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from WebUsers as o where o.userName=:userName");
            q.setParameter("userName", userName);

            return (WebUsers) q.getResultList().get(0);
        } finally {
            em.close();
        }
    }

    public WebUsers findWebUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WebUsers.class, id);
        } finally {
            em.close();
        }
    }

    public int getWebUsersCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from WebUsers as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
