package com.appdynamicspilot.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

/**
 * Created by aleftik on 9/2/16.
 */
@Embeddable
public class Address {
    private String Street1;
    private String Street2;
    private String cityName = null;
    private String state = null;
    private String country = null;
    private String zip = null;



    @Column(name="STREET1",nullable = true)
    public String getStreet1() {
        return Street1;
    }

    public void setStreet1(String street1) {
        Street1 = street1;
    }

    @Column(name="STREET2",nullable = true)
    public String getStreet2() {
        return Street2;
    }

    public void setStreet2(String street2) {
        Street2 = street2;
    }

    @Column(name="STATE",nullable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="COUNTRY",nullable = true)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * Getter and Setter of CityName
     */
    @Column(name = "CITY_NAME")
    public String getCity() {
        return cityName;
    }

    public void setCity(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "ZIP")
    @NotNull(message="{zip.required}")
    @Pattern(regexp="\\d{5}",message="{zip.length}")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
