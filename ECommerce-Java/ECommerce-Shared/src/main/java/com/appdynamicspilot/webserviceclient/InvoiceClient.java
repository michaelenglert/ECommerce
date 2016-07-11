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

import java.io.IOException;

import java.net.URL;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;

import org.apache.axis.client.*;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.Options;
import org.tempuri.OrderDetail;

import javax.xml.rpc.ParameterMode;
import java.util.logging.*; 
import java.util.ArrayList;


import javax.xml.namespace.QName;


 public class InvoiceClient {
	Logger logger=Logger.getLogger("Invoice");
	
	public String receiveInvoice(ArrayList<OrderDetail> orderDetailList) throws Exception {
		try {
				FileHandler handler = new FileHandler("my.log", true);	 
					 
			   logger.setLevel(Level.ALL); 
			   logger.addHandler(handler);    
			   Thread.sleep(10);
			   
			   
			   System.out.println("==========Before Calling .net Webservice=============");
				
				URL url = new URL("http://ec2-50-17-161-205.compute-1.amazonaws.com:8000/invoice/Invoice_Service.asmx?wsdl");

				Service service = new Service();

				Call call  = (Call)service.createCall();
				call.setTargetEndpointAddress(url);
				String result = (String)call.invoke("DoWork", new Object[]{""});
				System.out.println("==========.net Webservice successfully called==============");
				
				System.out.println(result);

				return result;

       } catch (Exception e) {
			System.err.println(e.toString());
			return null;
       }
	} 	 
	
}
