package com.appdynamicspilot.action;

import com.appdynamicspilot.jms.FulfillmentProducer;
import com.appdynamicspilot.model.Address;
import com.appdynamicspilot.model.FulfillmentOrder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;

import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

/**
 * Created by rbolton on 8/3/16.
 *
 * This supports thread contention use case for Order Update (contention happens on WS JVM)
 */
public class OrderAction extends ActionSupport implements Preparable,
        ServletResponseAware, ServletRequestAware {

    private FulfillmentProducer fulfillmentProducer;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String trigger;

    public String update() throws IOException {
        long i = 1234L;

        Item item = new Item();
        item.setId(i);
        item.setImagePath("foo");
        item.setPrice(34);
        item.setTitle("Title");

        User user = new User();
        user.setId(i);
        Address address = new Address();
        user.setAddress(address);
        address.setCity("Seattle");
        user.setCustomerName("Rob");
        user.setCustomerType(User.CUSTOMER_TYPE.PLATINUM);

        FulfillmentOrder order = new FulfillmentOrder(item, user);
        fulfillmentProducer.sendFulfillment(order);

        InputStream response = new URL("http://ws:8080/cart/changeOrder?trigger=" + this.trigger).openStream();

        return "SUCCESS";
    }

    public FulfillmentProducer getFulfillmentProducer() {
        return fulfillmentProducer;
    }

    public void setFulfillmentProducer(FulfillmentProducer fulfillmentProducer) {
        this.fulfillmentProducer = fulfillmentProducer;
    }


    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getServletResponse() {
        return response;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void prepare() throws Exception {}

}
