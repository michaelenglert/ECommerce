package com.appdynamics.inventory.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class CartClient {

	public static void main(String[] args) {
		OrderRequest or = new OrderRequest();
		Long itemId = (long) 123123123;
		int quantity = 2;
		
		or.setItemId(itemId);
		or.setQuantity(quantity);
		try {
			
			URL url = new URL("http://localhost:8080/cart/orderService?wsdl");
			
			QName qname = new QName("http://inventory.appdynamics.com/", "OrderServiceImplService");
			
			Service service = Service.create(url, qname);
			
			OrderService cart = service.getPort(OrderService.class);
			
			System.out.println(cart.createOrder(or));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
