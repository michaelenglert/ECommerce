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

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ravichandra
 */
@XmlRootElement(name = "cart")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Cart")
@Table(name = "cart")
public class Cart implements java.io.Serializable {

    private static Logger log = Logger.getLogger(Cart.class.getName());
    private static final long serialVersionUID = 1L;
    @Transient
    private Double fakeAmount = 0.0;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "cart-id")
    private Long id;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<Item>();

    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


    /**
     * Getter and Setter of id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter and Setter of list of items
     */
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Getter and Setter of user
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter and Setter of item
     */
    public void addItem(Item item) {
        getItems().add(item);
    }

    public void removeItem(Item item) {
        getItems().remove(item);
    }

    /**
     * Looks for item in cart items
     * @param item
     * @return true if found, else false
     */
    public boolean findItem(Item item){
        return getItems().contains(item);
    }

    /**
     * Gets the total price of the cart
     * @return total amount
     */
    public Double getCartTotal() {
        if (fakeAmount == 0.0) {
            double total = 0;
            if (items != null) {
                for (Item item : items) {
                    total += item.getPrice();
                }
            }
            return total;
        }
        return fakeAmount;
    }

    /**
     * Gets the cart size of a particular user
     * @return Cart Sze in Integer
     */
    public Integer getCartSize() {
        return items.size();
    }

    //Not used
    public Item getTopItem() {
        int count = 0;
        Item topItem = null;
        for (Item i : items) {
            if (count == 0) {
                topItem = i;
            }
            count++;

            if (i.getPrice() > topItem.getPrice()) {
                topItem = i;
            }
        }
        return topItem;
    }

    /**
     * Setter of fakeAmount
     * @param amt
     */
    public void setAmount(Double amt) {

        this.fakeAmount = amt;
    }
}
