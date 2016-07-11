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

package com.appdynamicspilot.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.appdynamicspilot.model.Cart;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.ArgumentUtils;

public class ItemAction extends ActionSupport implements Preparable, ServletRequestAware {
    private static final Logger log = Logger.getLogger(ItemAction.class);
    private Item item;
    private ItemService itemService;
    private CartService cartService;
    private List<Item> itemsList;
    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;

    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    /*Getting all the items to be viewed on the UI, for selection*/
    public String getAllItems() {
        log.info("Calling cartService");
        log.info("Inside the item action class");
        User user = (User) ActionContext.getContext().getSession().get("USER");

        if (ArgumentUtils.isNull(user))
            return "LOGOUT";

        //Deleting all the items in the user cart with UserID passed into the method.
        cartService.deleteCartItems(user.getId());

        //Getting all the items from the inventory.
        itemsList = itemService.getAllItems();

        request.setAttribute("itemsList", itemsList);
        return "SUCCESS";
    }

    public void prepare() throws Exception {
        log.info("Inside the Prepare method");
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
