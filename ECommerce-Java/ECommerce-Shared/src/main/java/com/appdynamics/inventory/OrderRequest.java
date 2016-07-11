
package com.appdynamics.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for orderRequest complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="orderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderRequest", propOrder = {
        "itemId",
        "quantity"
})
public class OrderRequest {

    protected Long itemId;
    protected Integer quantity;

    /**
     * Gets the value of the itemId property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setItemId(Long value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the quantity property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

}
