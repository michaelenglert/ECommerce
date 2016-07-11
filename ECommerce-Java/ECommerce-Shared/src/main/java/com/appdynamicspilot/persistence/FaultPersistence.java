package com.appdynamicspilot.persistence;

import com.appdynamicspilot.model.Fault;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
public class FaultPersistence extends BasePersistenceImpl {

    /**
     * Logger for this class
     */
    private static final Logger log = Logger.getLogger(FaultPersistence.class);

    /**
     * Get all the bugs for Fault Injection based on user
     *
     * @param userName
     * @return List of Bugs
     */
    public List<Fault> getAllFaultsByUser(String userName) {
        Query q = getEntityManager().createQuery("SELECT f FROM Fault f where f.username = :username");
        q.setParameter("username", userName);
        List<Fault> lsFaults = q.getResultList();
        if ((lsFaults == null) || (lsFaults.size() == 0)) {
            return Collections.EMPTY_LIST;
        }
        return lsFaults;
    }

    /**
     * Get all the bugs for Fault Injection
     *
     * @return List of Bugs
     */
    public List<Fault> getAllFaults() {
        Query q = getEntityManager().createQuery("SELECT f FROM Fault f");
        List<Fault> lsFaults = q.getResultList();
        if ((lsFaults == null) || (lsFaults.size() == 0)) {
            return Collections.EMPTY_LIST;
        }
        return lsFaults;
    }

    /**
     * Deletes faults based on user name and fault name
     *
     * @param userName
     * @param faultName
     * @return affected rows
     */
    public int deleteFaults(String userName, String faultName) {
        EntityManager em = getEntityManager();
        EntityTransaction txn = em.getTransaction();
        txn.begin();
        Query q = em.createQuery("DELETE FROM Fault f where f.username = :username and f.bugname = :bugname");
        int deletedCount = q.setParameter("username", userName).setParameter("bugname", faultName).executeUpdate();
        txn.commit();
        return deletedCount;
    }
}
