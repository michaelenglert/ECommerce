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

import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.CartPersistence;
import com.appdynamicspilot.persistence.ItemPersistence;
import com.appdynamicspilot.webserviceclient.SoapUtils;


public class CartService implements CartServiceInterface {
    /**
     * Logger for CartService class
     */
    private static final Logger log = Logger.getLogger(CartService.class);

    /**
     * Ref to CartPersistence class
     */
    private CartPersistence cartPersistence;

    public void setCartPersistence(CartPersistence cartPersistence) {
        this.cartPersistence = cartPersistence;
    }

    /**
     * Ref to ItemPersistence class
     */
    private ItemPersistence itemPersistence;

    public ItemPersistence getItemPersistence() {
        return itemPersistence;
    }

    public void setItemPersistence(ItemPersistence itemPersistence) {
        this.itemPersistence = itemPersistence;
    }

    /**
     * Ref to SoapUtils class for Checkin out
     */
    private SoapUtils soapUtil;

    public void setSoapUtil(SoapUtils soapUtil) {
        this.soapUtil = soapUtil;
    }

    /**
     * Inserts Cart and cart-Item to the corresponding tables
     *
     * @param cart object
     */
    public void saveItemInCart(Cart cart) {
        cartPersistence.save(cart);
    }

    /**
     * Updates cart and cart-item to the corresponding tables
     *
     * @param cart
     */
    public void updateItemInCart(Cart cart) {
        cartPersistence.update(cart);
    }

    /**
     * Get the cart based on user
     *
     * @param userId
     * @return cart object
     */
    public Cart getCartByUser(Long userId) {
        return cartPersistence.getCartByUser(userId);
    }

    /**
     * Get all the items from cart based on user
     *
     * @param userId
     * @return List of items
     */
    public List<Item> getAllItemsByUser(Long userId) {
        return cartPersistence.getAllItemsByUser(userId);
    }

    /**
     * Deletes Items from cart
     *
     * @param username
     */
    public void deleteItemInCart(String username, Long id) {
        cartPersistence.deleteItemInCart(username, id);
    }

    /**
     * Deletes Items from cart v2
     *
     * @param username
     * @param id
     */
    public Integer deleteItemInCartV2(String username, Long id) {
        return cartPersistence.deleteItemInCartV2(username, id);
    }

    /**
     * checks out each item in the cart by raising PO
     *
     * @param itemId
     * @param quantity
     * @return
     * @throws Exception
     */
    public Long checkOut(Long itemId, Integer quantity) throws Exception {
        try {
            Long orderId = soapUtil.raisePO(itemId, quantity);
            return orderId;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Gets all the items from cart(Only from session)
     * can be removed in v2 as the session has been removed.
     *
     * @param cartId
     * @return List of items
     */
    public List<Item> getAllCartItems(Long cartId) {
        return cartPersistence.getAllCartItems(cartId);
    }

    //Not used in rest
    //This API is for generating error at inventory server . CartAction.sendItem will specify the wrong wsdl url here
    public Long checkOut(Long itemId, Integer quantity, String wsdlURL) throws Exception {
        try {
            Long orderId = soapUtil.raisePO(itemId, quantity, wsdlURL);
            return orderId;
        } catch (Exception e) {
            throw e;
        }
    }

    //Not used in rest
    public void deleteCartItems(Long userId) {
        cartPersistence.deleteAllCartItems(userId);
    }

    //Not used in rest
    public void deleteCart(Cart cart) {
        cartPersistence.deleteCart(cart);
    }

    //Can be removed as getCartSize has been moved to Cart model in v2
    public Integer getCartSize(Long userId) {
        return cartPersistence.getCartSize(userId);
    }
}
