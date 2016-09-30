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

/**
 * @author Ravichandra
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "item")
public class Item implements java.io.Serializable,Cloneable {

    public enum ItemType {
        BOOK, MUSIC;

        @Override
        public String toString() {
           switch (this) {
               case BOOK: return "Book";
               case MUSIC: return "Music";
               default:  throw new IllegalArgumentException("not in eum");
           }
        }

    }

    private static Logger log = Logger.getLogger(Item.class.getName());
    private static final long serialVersionUID = 1L;
    @XmlElement
    private Long id;
    @XmlElement
    private String title;
    @XmlElement
    private String imagePath;
    @XmlElement
    private double price;
    @XmlElement
    private String category;
    @XmlElement
    private ItemType type;
    @XmlElement
    private String description;

    public Item() {}

    public Item(Item clone) {
        this.category = clone.getCategory();
        this.description = clone.getDescription();
        this.id = clone.getId();
        this.title = clone.getTitle();
        this.imagePath = clone.getImagePath();
        this.price = clone.getPrice();
        this.type = clone.getType();
    }



    @Column(name="itemType",nullable = false)
    @Enumerated(value = EnumType.STRING)
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Column(name="description",nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**

     * Getter and Setter of Category
     */
    @Column(name = "category", nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**

     * Getter and Setter of id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter and Setter of title
     */
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter and Setter of imagepath
     */
    @Column(name = "imagePath", nullable = true)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Getter and Setter of price
     */
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

