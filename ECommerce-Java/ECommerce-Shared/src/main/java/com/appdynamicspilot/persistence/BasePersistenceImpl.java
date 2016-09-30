/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamicspilot.persistence;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;


/**
 * @author Vikash
 *
 */
public class BasePersistenceImpl {

    private final static Logger LOGGER = Logger.getLogger(BasePersistenceImpl.class.getName());

    private EntityManagerFactory emf = null;

    @PersistenceContext
    private EntityManager em = null;

    private static final int DEFAULT_INTERNAL = 15;

    private int interval = DEFAULT_INTERNAL;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BasePersistenceImpl.class);

	/**
	 * The method to update the serailizable business objects into the database.
	 * 
	 * @param object --
	 *            serializable object
	 * @throws PersistenceException
	 */
    @Transactional
    public Exception update(final Serializable object) {
        EntityManager entityManager = getEntityManager();
        Exception exception = null;
        try {
            entityManager.merge(object);
        } catch (Exception ex) {
            exception = ex;
            logger.error(ex);
        }
        return exception;
    }


    public EntityManagerFactory getEntityManagerFactory() {
        return this.emf;
    }

    public void setEntityManagerFactory(EntityManagerFactory factory) {
         this.emf = factory;
    }


    public EntityManager getEntityManager() {
        if (em != null) {
            return em;
        }
        return null;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
	public Exception save(final Serializable object) {
        Exception exception = null;
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.persist(object);
        } catch (Exception ex) {
            exception = ex;
            logger.error(ex);
        }
          return exception;
	}

    @Transactional
    public void delete(Serializable object){
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.remove(object);
        } catch (Exception ex) {
            logger.error(ex);
        }
	}

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @param interval
     *            the interval to set
     */
    public void setInterval(int interval) {
        if (interval < 0) {
            LOGGER.warn("Invalid interval: " + interval + "; setting to default: " + DEFAULT_INTERNAL);
            this.interval = DEFAULT_INTERNAL;
        } else {
            this.interval = interval;
        }
    }

    protected boolean shouldFireSlow() {
        return (Math.random() * 100) <= interval;
    }


}
