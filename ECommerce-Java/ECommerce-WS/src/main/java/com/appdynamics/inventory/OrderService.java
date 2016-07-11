package com.appdynamics.inventory;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface OrderService {
	@WebMethod
	public Long createOrder(OrderRequest orderRequest);
	@WebMethod
	public Long createPO(Long itemId, Integer quantity);
}
