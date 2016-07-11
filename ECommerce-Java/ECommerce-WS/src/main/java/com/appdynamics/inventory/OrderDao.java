package com.appdynamics.inventory;

import com.appdynamicspilot.exception.InventoryServerException;

public interface OrderDao {
	public Long createOrder(OrderRequest orderRequest) throws InventoryServerException;
}