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

package com.appdynamics.inventory;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Integer quantity;
    @Temporal(value = TemporalType.DATE)
    protected Date createdOn;

    protected InventoryItem item;

    public Order() {

    }

    public Order(OrderRequest orderRequest, InventoryItem item) {
        this.quantity = orderRequest.getQuantity();
        this.createdOn = new Date();
        this.item = item;
    }

    /**
     * @return Returns the createdOn.
     */
    public Date getCreatedOn() {
        return createdOn;
    }


    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }


    /**
     * @return Returns the quantity.
     */

    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param createdOn The createdOn to set.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @param quantity The quantity to set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
