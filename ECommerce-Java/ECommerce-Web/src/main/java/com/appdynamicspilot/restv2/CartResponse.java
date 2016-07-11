package com.appdynamicspilot.restv2;

import com.appdynamicspilot.model.Cart;

/**
 * Created by swetha.ravichandran on 6/1/15.
 */
public class CartResponse {

    private String cartSize;
    private Double cartTotal;

    /**
     * No Argument Constructor - Required for creating json object
     */
    public CartResponse() {
    }

    /**
     * Argument constructor
     */
    public CartResponse(String cartSize, Double cartTotal) {
        this.cartSize = cartSize;
        this.cartTotal = cartTotal;
    }

    /**
     * Getter and Setter of cartSize
     */
    public String getCartSize() {
        return cartSize;
    }

    public void setCartSize(String cartSize) {
        this.cartSize = cartSize;
    }

    /**
     * Getter and Setter of cartTotal
     */
    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }
}
