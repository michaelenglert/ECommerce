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

package com.appdynamicspilot.restv2;

import com.appdynamicspilot.persistence.BasePersistenceImpl;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.SpringContext;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json/items")
public class Items {
    private static final Logger LOGGER = Logger.getLogger(BasePersistenceImpl.class);

    public ItemService getItemService() {
        return (ItemService) SpringContext.getBean("itemService");
    }

    @Autowired
    private ApplicationContext appContext;

    /**
     * Gets item by id
     * @param id
     * @return Item in json format(parsed using Gson)
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@PathParam("id") Long id) {
        Gson gsonItemById = new Gson();
        return gsonItemById.toJson(getItemService().getItemByID(id));
    }

    /**
     * Gets all the items
     * @return List<Item> in json format(parsed using Gson)
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItems() {
        Gson gsonAllItems = new Gson();
        return gsonAllItems.toJson(getItemService().getAllItems());
    }
}
