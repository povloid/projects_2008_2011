/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hbbdtus;

import hbbdtus.exceptions.NonexistentEntityException;
import hbbdtus.exceptions.PreexistingEntityException;
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
public class Tab3JpaController {

    public Tab3JpaController() {
        emf = Persistence.createEntityManagerFactory("hbbdtusPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tab3 tab3) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tab3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTab3(tab3.getId()) != null) {
                throw new PreexistingEntityException("Tab3 " + tab3 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tab3 tab3) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tab3 = em.merge(tab3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tab3.getId();
                if (findTab3(id) == null) {
                    throw new NonexistentEntityException("The tab3 with id " + id + " no longer exists.");
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
            Tab3 tab3;
            try {
                tab3 = em.getReference(Tab3.class, id);
                tab3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tab3 with id " + id + " no longer exists.", enfe);
            }
            em.remove(tab3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tab3> findTab3Entities() {
        return findTab3Entities(true, -1, -1);
    }

    public List<Tab3> findTab3Entities(int maxResults, int firstResult) {
        return findTab3Entities(false, maxResults, firstResult);
    }

    private List<Tab3> findTab3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tab3 as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tab3 findTab3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tab3.class, id);
        } finally {
            em.close();
        }
    }

    public int getTab3Count() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tab3 as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
