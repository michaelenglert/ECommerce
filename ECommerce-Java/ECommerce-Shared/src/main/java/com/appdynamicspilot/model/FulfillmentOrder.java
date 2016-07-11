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

package com.appdynamicspilot.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by aleftik on 11/14/14.
 */
@XmlRootElement(name = "fulfillment-order")
@XmlAccessorType(XmlAccessType.FIELD)
public class FulfillmentOrder implements Serializable {

    @XmlElement
    private Long id = null;
    @XmlElement
    private Double price = null;
    @XmlElement
    private String username = null;
    @XmlElement
    private Long userId = null;
    @XmlElement
    private User.CUSTOMER_TYPE type = null;


    FulfillmentOrder() {

    }


   public FulfillmentOrder(Item item,User user) {
        this.id = item.getId();
        this.price = item.getPrice();
        this.userId = user.getId();
        this.username = user.getEmail();
        this.type = user.getCustomerType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User.CUSTOMER_TYPE getType() {
        return type;
    }

    public void setType(User.CUSTOMER_TYPE type) {
        this.type = type;
    }
}
