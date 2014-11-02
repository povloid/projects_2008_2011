/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import test.exceptions.NonexistentEntityException;
import test.exceptions.PreexistingEntityException;

/**
 *
 * @author kopychenko
 */
public class Tab2JpaController {

    public Tab2JpaController() {
        emf = Persistence.createEntityManagerFactory("nbdbtus_webPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tab2 tab2) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tab2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTab2(tab2.getId()) != null) {
                throw new PreexistingEntityException("Tab2 " + tab2 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tab2 tab2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tab2 = em.merge(tab2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tab2.getId();
                if (findTab2(id) == null) {
                    throw new NonexistentEntityException("The tab2 with id " + id + " no longer exists.");
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
            Tab2 tab2;
            try {
                tab2 = em.getReference(Tab2.class, id);
                tab2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tab2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(tab2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tab2> findTab2Entities() {
        return findTab2Entities(true, -1, -1);
    }

    public List<Tab2> findTab2Entities(int maxResults, int firstResult) {
        return findTab2Entities(false, maxResults, firstResult);
    }

    private List<Tab2> findTab2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tab2 as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tab2 findTab2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tab2.class, id);
        } finally {
            em.close();
        }
    }

    public int getTab2Count() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tab2 as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
