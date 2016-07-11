package com.appdynamics.demo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by swetha.ravichandran on 7/15/15.
 */
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

@XmlRootElement
public class User {

    public User() {
    }

    public User(Long id, String email, String password, String customerName, String customerType, String cityName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.customerName = customerName;
        this.customerType = customerType;
        this.cityName = cityName;
    }

    @XmlElement(name = "id")
    private Long id = null;
    @XmlElement(name = "email")
    private String email = null;
    @XmlElement(name = "password")
    private String password = null;
    @XmlElement(name = "customerName")
    private String customerName = null;
    @XmlElement(name = "customerType")
    private String customerType = null;
    @XmlElement(name = "cityName")
    private String cityName = null;


    /**
     * Getter and Setter of customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter and Setter of Id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter and Setter of email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter and Setter of password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter and Setter of customerType
     */
    public String getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * Getter and Setter of CityName
     */
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

