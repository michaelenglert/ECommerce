package com.appdynamics.inventory;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appdynamicspilot.exception.InventoryServerException;


@WebService(endpointInterface="com.appdynamics.inventory.OrderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	public void setOrderDao(OrderDao orderDao){
		this.orderDao = orderDao;
	}
	
	public Long createOrder(OrderRequest orderRequest) {
		
		log.info("creating order with order request: " +orderRequest.getItemId());
		try {	
			return orderDao.createOrder(orderRequest);
		} catch (InventoryServerException e) {
			log.error(e.getErrorMessage());
		}
		return (long) 0;
	}
	
	public Long createPO(Long itemId, Integer quantity){
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setItemId(itemId);
		orderRequest.setQuantity(quantity);
		log.info("creating order with request: " +orderRequest.getItemId());
		
		try{
			return orderDao.createOrder(orderRequest);
		}catch(Exception e){
			e.printStackTrace();	
			log.error("Error in creating order [" +orderRequest.getItemId() + "]");
		}
		
		return (long) 0;
	}
	
}