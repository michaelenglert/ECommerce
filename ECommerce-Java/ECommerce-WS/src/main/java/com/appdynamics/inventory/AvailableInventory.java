package com.appdynamics.inventory;

/**
 * Created by rbolton on 8/11/16 for thread contention
 * Singleton class to use for syncronization
 */
public class AvailableInventory {

    public Integer count;
    public String supplier;

    private static final AvailableInventory instance = new AvailableInventory();

    private AvailableInventory(){}

    public static AvailableInventory getInstance(){
        return instance;
    }

    public void updateInventory(Integer count) {
        this.count = count;
    }

    public void updateSupplier(String supplier) {
        this.supplier = supplier;
    }
}
