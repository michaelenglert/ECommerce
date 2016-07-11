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

import java.lang.Math;
import java.util.List;

import org.apache.log4j.Logger;


import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.oracle.jdbc.OracleQueryExecutor;
import com.appdynamicspilot.util.ArgumentUtils;
import com.appdynamicspilot.util.SpringContext;

import javax.persistence.*;

public class ItemPersistence extends BasePersistenceImpl {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(ItemPersistence.class);

    /**
     * Call to mysql db to get all the items
     * Connects to Oracle in case of slow fire
     * @return List<Item>
     */
    public List<Item> getAllItems() {
        List<Item> itemList = getEntityManager().createQuery("SELECT i FROM Item i ORDER BY i.id").getResultList();

		//DEMO-367 Calling Oracle db in certain percentage
        //Supressed the slow query to make it generic
		if (shouldFireSlow()) {
			LOGGER.info("Querying oracle db");
			if (Math.random() >= 0.7) {
				LOGGER.error("Critical transaction Error, rolling back changes. Order execution aborted.");
			}

			OracleQueryExecutor oracleItems = (OracleQueryExecutor) SpringContext
					.getBean("oracleQueryExecutor");
			oracleItems.executeOracleQuery();
		}

		return itemList;
	}

    /**
     * Gets Item by Id, looks through Item class
     * @param id
     * @return Item
     */
    public Item getItemByID(Long id) {
        return getEntityManager().find(Item.class, id);
    }

    /**
     * Call to mysql db to get item by name
     * @param Item name
     * @return Item
     */
    public Item getItemByName(String name) {
        Query q = getEntityManager().createQuery("SELECT i FROM Item i WHERE item i.title=:title");
        q.setParameter("title", name);
        List<Item> itemList = (List<Item>) q.getResultList();
        return (ArgumentUtils.isNullOrEmpty(itemList) ? null : itemList.get(0));
    }

}
