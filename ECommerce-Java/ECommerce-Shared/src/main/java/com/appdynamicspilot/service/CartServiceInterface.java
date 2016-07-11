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

import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.CartPersistence;
import com.appdynamicspilot.webserviceclient.SoapUtils;

public interface CartServiceInterface {

    void setSoapUtil(SoapUtils soapUtil);

    void setCartPersistence(CartPersistence cartPersistence);

    void saveItemInCart(Cart cart);

    void updateItemInCart(Cart cart);

    Cart getCartByUser(Long userId);

    List<Item> getAllItemsByUser(Long userId);

    //Rest older version
    void deleteItemInCart(String username, Long id);

    //Rest v2
    Integer deleteItemInCartV2(String username, Long id);

    Long checkOut(Long itemId, Integer quantity) throws Exception;

    //Not used in rest
    void deleteCart(Cart cart);

    //Not used in rest
    void deleteCartItems(Long userId);

    //Can be removed in v2 as the session has been removed.
    List<Item> getAllCartItems(Long cartId);

    //Can be removed as getCartSize has been moved to Cart model in v2
    Integer getCartSize(Long userId);

}
