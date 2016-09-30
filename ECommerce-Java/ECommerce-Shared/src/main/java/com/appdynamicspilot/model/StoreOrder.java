package com.appdynamicspilot.model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by aleftik on 8/18/16.
 */


@XmlRootElement(name = "StoreOrder")
@Entity(name = "StoreOrder")
@Table(name = "Store_Order")
public class StoreOrder implements Serializable{
    private static Logger log = Logger.getLogger(Item.class.getName());

    private Long id = null;
    public enum CC_TYPE {VISA,MASTERCARD,DISCOVER,DINERS,VPAY};

    private List<Item> items = null;
    private String ccNumber = null;
    private CC_TYPE ccType = null;
    private Address address = null;

    public StoreOrder() {
        address = new Address();
    }

    @OneToMany(targetEntity = Item.class)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Column(name="cc_number",nullable = false)
    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    @Column(name="cc_type",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public CC_TYPE getCcType() {
        return ccType;
    }

    public void setCcType(CC_TYPE ccType) {
        this.ccType = ccType;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public CC_TYPE [] getCreditCardTypes() {
         return CC_TYPE.values();
    }

    public void setAddress(Address address) {
        this.address = address;
    }





}
