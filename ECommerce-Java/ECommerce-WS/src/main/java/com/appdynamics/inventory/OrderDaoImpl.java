package com.appdynamics.inventory;

import com.appdynamicspilot.exception.InventoryServerException;
import com.appdynamicspilot.util.SpringContext;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.text.*;

public class OrderDaoImpl implements OrderDao {

    Invocation.Builder invocationBuilder = null;
    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private String queryType = "join";
    private boolean slowQueryParam;
    private String selectQuery = null;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setSelectQuery(String selectQuery) {
        this.selectQuery = selectQuery;
    }

    public Long createOrder(OrderRequest orderRequest) throws InventoryServerException {
        try {
            EntityManager entityManager = getEntityManagerFactory().createEntityManager();

            if (entityManager != null) {
                Query q = entityManager.createNativeQuery(this.selectQuery);
                q.getResultList();
                entityManager.close();
            }

            logger.info("Querying oracle db - inventory");
        	Date date = new Date();
        	SimpleDateFormat minutes = new SimpleDateFormat("m");
        	String m = minutes.format(date);
			if (Integer.parseInt(m) < 20 && Math.random() < .25) {
            	QueryExecutor oracleItems = (QueryExecutor) SpringContext.getBean("queryExecutor");
            	oracleItems.executeOracleQuery();
        	}

            if (orderRequest != null)
                return processOrder(orderRequest);
            else
                logger.info("OrderRequest is null");

        } catch (Exception e) {
            logger.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            String errorDetail = writer.toString();
            logger.error(errorDetail);
        }
        return new Long(0);
    }

    private Long processOrder(OrderRequest orderRequest) {
        try {
            EntityManager entityManager = getEntityManagerFactory().createEntityManager();
            if (entityManager != null) {
                InventoryItem item = entityManager.find(InventoryItem.class,
                        orderRequest.getItemId());

                if (item != null) {
                    Order order = new Order(orderRequest, item);
                    if (order != null) {
                        order.setQuantity(orderRequest.getQuantity());

                        logger.info("order stored is: " + order.getId() + " " + order.getQuantity() + " " + order.getCreatedOn());

                        entityManager.getTransaction().begin();
                        entityManager.persist(order);
                        entityManager.getTransaction().commit();

                        entityManager.getTransaction().begin();
                        entityManager.remove(order);
                        entityManager.getTransaction().commit();

                        logger.info("order created is: " + order.getId() + " " + order.getQuantity() + " " + order.getCreatedOn());
                        return order.getId();
                    }
                }
                entityManager.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            String errorDetail = writer.toString();
            logger.error(errorDetail);
        }
        return new Long(0);
    }

}
