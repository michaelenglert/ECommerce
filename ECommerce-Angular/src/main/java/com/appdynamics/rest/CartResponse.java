package com.appdynamics.rest;

public class CartResponse {

	private String cartSize;
    private String cartTotal;

    /**
     * No Argument Constructor - Required for creating json object
     */
    public CartResponse() {
    }

    /**
     * Argument constructor
     */
    public CartResponse(String cartSize, String cartTotal) {
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
    public String getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(String cartTotal) {
        this.cartTotal = cartTotal;
    }
	
}
