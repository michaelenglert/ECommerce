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

package com.appdynamicspilot.action;

import com.appdynamics.xml.CastorUtil;
import com.appdynamicspilot.exception.OrderException;
import com.appdynamicspilot.jms.CustomerMessageProducer;
import com.appdynamicspilot.jms.FulfillmentProducer;
import com.appdynamicspilot.jms.MessageProducer;
import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.FulfillmentOrder;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.ArgumentUtils;
import com.appdynamicspilot.webserviceclient.DotNetClient;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.tempuri.ArrayOfOrderDetail;
import org.tempuri.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartAction extends ActionSupport implements Preparable,
        ServletResponseAware, ServletRequestAware {
    private static final Logger log = Logger.getLogger(CartAction.class);
    /*List of items in the cart*/
    List<Cart> cartsList;
    /*Cart persistence service to persist data to the database*/
    private CartService cartService;
    /*Item persistence service*/
    private ItemService itemService;
    private String selectedItemId;
    private String xml;
    private MessageProducer messageProducer;
    private FulfillmentProducer fulfillmentProducer;
    private CustomerMessageProducer customerMessageProducer;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private boolean checkMe;

    private static String generateInvoice(ArrayOfOrderDetail orderDetail) {
        DotNetClient client = new DotNetClient();
        return client.callService(orderDetail);
    }

    public boolean isCheckMe() {
        return checkMe;
    }

    public void setCheckMe(boolean checkMe) {
        this.checkMe = checkMe;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public List<Cart> getCartsList() {
        return cartsList;
    }

    public void setCartsList(List<Cart> cartsList) {
        this.cartsList = cartsList;
    }

    /*Adding selected items to the cart*/
    public String addToCart() {

		if (Math.random() <= 0.05) {
			log.info("Number of items in inventory : 0");
            log.error("Unable to add item to cart");
        } else {
			Integer itemCount = (int) Math.ceil(Math.random() * 100);
        	log.info("Number of items in inventory : " + itemCount);
		}		

        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        if (ArgumentUtils.isNull(user))
            return "LOGOUT";
        //cartService.deleteCartItems(user.getId());
        if ("".equals(selectedItemId))
            return "FAILURE";

        /*parsing the selectedItemId string to get the items string array*/
        if (selectedItemId.charAt(0) == ',')
            selectedItemId = selectedItemId.substring(1);
        String[] selectedItemIds = selectedItemId.split(",");

        /*Path 1 for Bug - Slow BTs*/
        //this String load is passed to ArgumentUtils to make a boolean check for sleep.
        String load = getServletRequest().getParameter("load");
        log.debug("the load is: " + load);

        //this parameter is processed to generate the delay/sleep time.
        String delay = getServletRequest().getParameter("delay");
        log.debug("the amount of delay is: " + delay);

        String error = getServletRequest().getParameter("error");
        log.debug("error param is: " + error);

        getServletRequest().getSession().setAttribute("error", error);
        boolean sleep = (!ArgumentUtils.isNullOrEmpty(load));
        int sleepTime = 0;
        if (sleep) {
            try {
                sleepTime = Integer.parseInt(delay);
            } catch (NumberFormatException e) {

                // eat exception in case of delay is wrong!!!
            }
        }
        log.debug("The sleep time is: " + sleepTime + " sleep="
                + sleep);
        for (int i = 0; i < selectedItemIds.length; i++) {
            if (sleep) {
                log.debug("Sleep time is: " + i * sleepTime);
                try {
                    /**
                     * Adding thread.sleep to demo state slowBTs. for every
                     * transaction
                     */
                    Thread.sleep(i * sleepTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Item item = itemService.getItemByID(Long
                    .parseLong(selectedItemIds[i]));
            if (item != null) {
                Cart cart = (Cart) getServletRequest().getSession().getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                }
                getServletRequest().getSession().setAttribute("CART", cart);
                cart.addItem(item);
                //trigger the mdic
                cart.getCartTotal();
                cart.setUser(user);
                cartService.saveItemInCart(cart);
            }
        }
        List<Item> cartsList = cartService.getAllItemsByUser(user.getId());
        log.info("the number of items in the cart are:" + cartsList.size());
        request.setAttribute("cartsList", cartsList);
        log.info("cartsList size" + cartsList.size());
        return "SUCCESS";
    }

    /*Adding XML information for a cart item*/
    public String addToCartXML() {
        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        if (ArgumentUtils.isNull(user)) {
            return "LOGOUT";
        }

        cartService.deleteCartItems(user.getId());
        if ("".equals(xml))
            return "FAILURE";
        CastorUtil cu = new CastorUtil();
        cu.saveCartItems(xml);
        List<Item> cartsList = cartService.getAllItemsByUser(user.getId());
        request.setAttribute("cartsList", cartsList);
        return "sendItems";
    }

    /*Sending items from the cart to complete purchase called upon clicking the buy now button*/
    public String sendItems() {
        try {

			if (Math.random() >= 0.85) {
                log.error("Unable to create order");
            }

            String fakeAmount = (String) ActionContext.getContext().get("orderAmount");
            User user = (User) ActionContext.getContext().getSession()
                    .get("USER");
            if (ArgumentUtils.isNull(user)) {
                return "LOGOUT";
            }
            log.info("The current user is: " + user.getEmail());
            Long userId = user.getId();

            ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
            org.tempuri.ArrayOfOrderDetail arrayOfOrderDetail = new org.tempuri.ArrayOfOrderDetail();

            Cart cart = (Cart) ActionContext.getContext().getSession().get("CART");
            if (cart == null) {
                cart = new Cart();
            }
            cart = getCartService().getCartByUser(user.getId());
            log.info("Cart belongs to:" + cart.getUser() + " The number of items associated with the user" + cart.getUser() + "is: " + cart.getItems());

            List<Item> cartList = new ArrayList<>();
            if (fakeAmount != null) {
                cart.setAmount(Double.valueOf(fakeAmount));
            }
            if (cart != null) {
                cartList = cartService.getAllItemsByUser(user.getId());
                log.info("The items associated with the user are: " + cartList.size());
                cart.getCartTotal();
            } else {
                cartList = Collections.EMPTY_LIST;
            }
            String orderIds = "";
            String str1 = "";
            String invoiceId = "";
            String invoiceIds = "";
            int outOfStock = 0;

            for (Item item : cartList) {
                try {
                    Long id = cartService.checkOut(item.getId(),
                            cartService.getCartSize(userId));

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderId(id);
                    orderDetail.setId(item.getId());
                    orderDetail.setTitle(item.getTitle());
                    arrayOfOrderDetail.getOrderDetail().add(orderDetail);
                    orderIds = orderIds + id.toString() + ", ";
                    if (id == 0) {
                        outOfStock = 1;
                    }
                    FulfillmentOrder order = new FulfillmentOrder(item, user);
                    fulfillmentProducer.sendFulfillment(order);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    request.setAttribute("msg",
                            "Error in creating order " + e.getMessage());
                }
            }

            if (checkMe) {
                invoiceId = generateInvoice(arrayOfOrderDetail);
            }

            log.debug("ORDERS ARE " + orderIds);
            if (!ArgumentUtils.isNullOrEmpty(orderIds) && outOfStock == 0) {
                orderIds = orderIds.substring(0, orderIds.length() - 2);
                log.debug("Request time(ms) in CartAction: sendItems"
                        + System.currentTimeMillis());
                messageProducer.sendMessageWithOrderId(orderIds, user.getEmail());
                messageProducer.sendTextMessageWithOrderId();
                customerMessageProducer.sendCustomerMesssage(user);


                if (invoiceId == "") {
                    request.setAttribute("msg", "Order ID(s) for your order(s) : "
                            + orderIds);
                } else {
                    request.setAttribute("msg",
                            "Your Invoice ID for your order(s) " + orderIds + ": "
                                    + invoiceId);
                }
            } else {
                request.setAttribute("msg",
                        "Order not created as one or more items in your cart were out of stock");
            }
            //deleting the cart instance associated with the user.
            cartService.deleteCart(cart);
            //creating an empty new cart instance and associating it with the user.
            cart = new Cart();
            cart.setUser(user);
            ActionContext.getContext().getSession().put("CART", cart);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "ENDPAGE";
    }

    public void prepare() throws Exception {
    }

    public CartService getCartService() {
        return this.cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public MessageProducer getMessageProducer() {
        return this.messageProducer;
    }

    /**
     * @param messageProducer The messageProducer to set.
     */
    public void setMessageProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
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

    //Call OrderService Web Service (ECommerce-WS) to complete the order
    public void callOrderService(CartService cartService, Long itemId,
                                 String url) throws OrderException {
        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        try {
            log.debug("doing checkout action with error Param");
            cartService.checkOut(itemId, cartService.getCartSize(user.getId()), url);
            // removing error flag
            getServletRequest().getSession().removeAttribute("error");
        } catch (Exception e) {
            throw new OrderException(
                    "error in creating order on inventory server",
                    e.fillInStackTrace());
            // log.debug("Something went wrong at inventory server ... retrying again!!!!!");
        }
    }

    public CustomerMessageProducer getCustomerMessageProducer() {
        return customerMessageProducer;
    }

    public void setCustomerMessageProducer(CustomerMessageProducer customerMessageProducer) {
        this.customerMessageProducer = customerMessageProducer;
    }

    public FulfillmentProducer getFulfillmentProducer() {
        return fulfillmentProducer;
    }

    public void setFulfillmentProducer(FulfillmentProducer fulfillmentProducer) {
        this.fulfillmentProducer = fulfillmentProducer;
    }

    //Removing all the items from the cart, either if the user logs out of the session or if the user completes the transaction
    public String removeAllItems() {
        Cart cart = (Cart) request.getSession().getAttribute("CART");
        User user = (User) request.getSession().getAttribute("USER");
        List<Item> items = cart.getItems();
        {
            for (Item item : items) {
                cartService.deleteItemInCart(user.getEmail(), item.getId());
            }
        }
        return "SUCCESS";
    }

}
