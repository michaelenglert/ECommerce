package com.appdynamics.inventory;

/**
 * Created by rbolton on 8/11/16 for thread contention
 */
public class AvailableInventory {

    public Integer count;
    public String supplier;

    public void updateInventory(Integer count) {
        this.count = count;
    }

    public void updateSupplier(String supplier) {
        this.supplier = supplier;
    }
}
