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
public class Tab1JpaController {

    public Tab1JpaController() {
        emf = Persistence.createEntityManagerFactory("hbbdtusPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tab1 tab1) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tab1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTab1(tab1.getId()) != null) {
                throw new PreexistingEntityException("Tab1 " + tab1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tab1 tab1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tab1 = em.merge(tab1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tab1.getId();
                if (findTab1(id) == null) {
                    throw new NonexistentEntityException("The tab1 with id " + id + " no longer exists.");
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
            Tab1 tab1;
            try {
                tab1 = em.getReference(Tab1.class, id);
                tab1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tab1 with id " + id + " no longer exists.", enfe);
            }
            em.remove(tab1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tab1> findTab1Entities() {
        return findTab1Entities(true, -1, -1);
    }

    public List<Tab1> findTab1Entities(int maxResults, int firstResult) {
        return findTab1Entities(false, maxResults, firstResult);
    }

    private List<Tab1> findTab1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tab1 as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tab1 findTab1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tab1.class, id);
        } finally {
            em.close();
        }
    }

    public int getTab1Count() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tab1 as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
