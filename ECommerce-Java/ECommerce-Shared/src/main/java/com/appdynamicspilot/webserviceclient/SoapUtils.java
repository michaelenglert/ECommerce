/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamicspilot.webserviceclient;

import java.rmi.RemoteException;

import com.appdynamics.inventory.OrderService;
import org.apache.log4j.Logger;

//import com.appdynamics.inventory.OrderServicePortTypeProxy;
import com.appdynamics.inventory.OrderRequest;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SoapUtils {
	private String axisUrl;
	private String axis14Url;
	private static final Logger log = Logger.getLogger(SoapUtils.class);
	
	public void setAxisUrl(String axisUrl) {
		this.axisUrl = axisUrl;
	}
	
	public Long raisePO(Long itemId,Integer quanity) {

		OrderRequest orderRequest = new OrderRequest();

		orderRequest.setItemId(itemId);
		orderRequest.setQuantity(quanity);
		try {

			URL url = new URL("http://ws:8080/cart/orderService?wsdl");

			QName qname = new QName("http://inventory.appdynamics.com/", "OrderServiceImplService");

			Service service = Service.create(url, qname);

			OrderService orderService = service.getPort(OrderService.class);

			log.info(orderService.createOrder(orderRequest));
			Long orderId = orderService.createOrder(orderRequest);
			return orderId;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return Long.valueOf(0);
	}


	/**
	 * This API is for generating error at inventory server . CartAction.sendItem will specify the wrong wsdl url here
	 * 
	 */
	public Long raisePO(Long itemId,Integer quanity,String wsdlURL){

		wsdlURL = getAxisUrl();
		OrderRequest orderRequest = new OrderRequest();

		orderRequest.setItemId(itemId);
		orderRequest.setQuantity(quanity);
		try {

			URL url = new URL(wsdlURL);

			QName qname = new QName("http://inventory.appdynamics.com/", "OrderServiceImplService");

			Service service = Service.create(url, qname);

			OrderService orderService = service.getPort(OrderService.class);

			log.info(orderService.createOrder(orderRequest));

			Long orderId = orderService.createOrder(orderRequest);
			return orderId;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		return Long.valueOf(0);
	}
	public Long raiseJMSPO(){

		OrderRequest orderRequest = new OrderRequest();

		orderRequest.setItemId(5L);
		orderRequest.setQuantity(1);
		try {

			URL url = new URL("http://ws:8080/cart/orderService?wsdl");

			QName qname = new QName("http://inventory.appdynamics.com/", "OrderServiceImplService");

			Service service = Service.create(url, qname);

			OrderService orderService = service.getPort(OrderService.class);

			log.info(orderService.createOrder(orderRequest));

			Long orderId = orderService.createOrder(orderRequest);
			return orderId;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return Long.valueOf(0);
	}

	public String getAxisUrl() {
	    return axisUrl;
	}

	public String getAxis14Url() {
	    return axis14Url;
	}

	public void setAxis14Url(String axis14Url) {
	    this.axis14Url = axis14Url;
	}
			
	
}
