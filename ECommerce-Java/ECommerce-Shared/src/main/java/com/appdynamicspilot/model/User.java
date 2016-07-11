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

/**
 * @author Vikash
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {
    public enum CUSTOMER_TYPE {DIAMOND, PLATINUM, GOLD, SILVER, BRONZE}

    ;
    private static Logger log = Logger.getLogger(User.class.getName());
    private static final long serialVersionUID = 1L;
    private Long id = null;
    private String email = null;
    private String password = null;
    private String customerName = null;
    private CUSTOMER_TYPE customerType = null;
    private String cityName = null;

    /**
     * Getter and Setter of customerName
     */
    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter and Setter of Id
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
     * Getter and Setter of email
     */
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter and Setter of password
     */
    @Column(name = "password", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter and Setter of customerType
     */
    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    public CUSTOMER_TYPE getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(CUSTOMER_TYPE customerType) {
        this.customerType = customerType;
    }

    /**
     * Getter and Setter of CityName
     */
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

