package com.appdynamics.inventory;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class OrderServiceHandler implements SOAPHandler<SOAPMessageContext>{

	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("handled");
		return true;
	}

	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("handling fault");
		return true;
	}

	public void close(MessageContext context) {
		
	}

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}
	
}
