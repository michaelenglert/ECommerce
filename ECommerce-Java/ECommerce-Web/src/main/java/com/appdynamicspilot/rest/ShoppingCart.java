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

package com.appdynamicspilot.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ShoppingCart implements java.io.Serializable {
    Logger log = Logger.getLogger(ShoppingCart.class);

    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        items = new ArrayList<ShoppingCartItem>();
    }

    public void addItem(ShoppingCartItem item) {
        items.add(item);
    }

    public void removeItem(ShoppingCartItem item) {
        items.remove(item);
    }

    public List<ShoppingCartItem> getAllItems() {
        return items;
    }

    public double getCartTotal() {
        double total = 0.0;
        for (ShoppingCartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
