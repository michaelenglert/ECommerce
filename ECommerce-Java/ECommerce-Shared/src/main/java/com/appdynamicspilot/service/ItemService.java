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

package com.appdynamicspilot.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.ItemPersistence;


public class ItemService {
	/**
	 * Logger Class
	 */
	private static final Logger log = Logger.getLogger(ItemService.class);

	/**
	 * Ref to ItemPersistence class
	 */
	private ItemPersistence itemPersistence;
	public void setItemPersistence(ItemPersistence itemPersistence) {
		this.itemPersistence = itemPersistence;
	}

	/**
	 * Gets all the items
	 * @return List of items - Xml in v1 , json in v2
	 */
	public List<Item> getAllItems() {
		return itemPersistence.getAllItems();
	}

	/**
	 * Gets item by id
	 * @param id
	 * @return item - Xml in v1 , json in v2
	 */
	public Item getItemByID(Long id){
		return itemPersistence.getItemByID(id);
	}

	// Used in the method "saveCartItems" of CastorUtil class, which is in turn used in CartAction class
	public Item getItemByName(String name){
		return this.itemPersistence.getItemByName(name);
	}


}
