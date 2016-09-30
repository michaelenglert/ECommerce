package com.appdynamicspilot.controllers;

import com.appdynamicspilot.jms.CustomerMessageProducer;
import com.appdynamicspilot.jms.FulfillmentProducer;
import com.appdynamicspilot.jms.MessageProducer;
import com.appdynamicspilot.model.*;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.ArgumentUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.log4j.Logger;
import org.tempuri.OrderDetail;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.Scope;

@Named
@SessionScoped
@Scope("session")
public class ShoppingCartController implements Serializable {
    private static final Logger log = Logger.getLogger(ShoppingCartController.class);

    @Inject
    private CartService cartService = null;
    @Inject
    private ItemService itemService = null;
    @Inject
    private FulfillmentProducer fulfillmentProducer = null;
    @Inject
    private MessageProducer messageProducer = null;
    @Inject
    private CustomerMessageProducer customerMessageProducer = null;

    private boolean confirmedShippingAddress = false;

    private Cart cart = null;
    private StoreOrder order = null;


    public ShoppingCartController() {

    }


    public void addToCart() {
        Map<String, String> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String itemId = requestMap.get("itemId");
        Item item = null;
        if ((itemId != null) && (!"".equals(itemId))) {
            item = itemService.getItemByID(Long.parseLong(itemId));
            if (cart == null) {
                cart = new Cart();
            }
            cart.addItem(item);
            cartService.saveItemInCart(cart);
        }

    }

    public void removeItem(Item item) {
        User user = getUser();
        if (getCart().getId() == null) {
            cartService.deleteItemInCart(getUser().getEmail(), item.getId());
        } else {
            cartService.deleteItemInCart(cart.getId(), item.getId());
        }
        cart.removeItem(item);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public StoreOrder getOrder() {
        return order;
    }

    public void setOrder(StoreOrder order) {
        this.order = order;
    }

    public String getCartStatus() {
        Cart c = getCart();
        if ((c == null) || (c.getCartSize() == 0)) {
            return "No Items in Cart";

        }
        return c.getCartSize() + " items in cart";

    }

    public String viewCart() {
        return "cart";
    }


    public User getUser() {
        RegistrationController controller = getRegistrationController();
        return controller.getUser();
    }

    private RegistrationController getRegistrationController() {
        return FacesContext.getCurrentInstance().getApplication()
                .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{registrationController}", RegistrationController.class);
    }

    public String checkout() {
        if (!isLoggedIn()) {
            getRegistrationController().setInCheckout(true);
            return "login";
        }

        if (getOrder() == null) {
            setOrder(new StoreOrder());
            if (getUser() != null) {
                prepopulateDefaultShipppingAddress();
            }
            return "address";
        }

        if (hasAddressVerificationIssues() || !hasConfirmedShippingAddress()) {
            return "address";
        }

        if (isValidCreditCard()) {
            return "credit_card";
        }


        return "confirm";

    }

    public boolean isLoggedIn() {
        return getRegistrationController().getIsLoggedIn();
    }

    private boolean hasAddressVerificationIssues() {
        Set<ConstraintViolation<Address>> issues = Validation.buildDefaultValidatorFactory().getValidator().validate(getOrder().getAddress());
        if (issues.size() > 0) return true;
        return false;
    }

    protected  boolean  hasConfirmedShippingAddress() {
        return this.confirmedShippingAddress;
    }

    protected  void  setHasConfirmedShippingAddress(boolean confirmed) {
        this.confirmedShippingAddress = confirmed;
    }


    public String verifyAddress() {
        Set<ConstraintViolation<Address>> issues = Validation.buildDefaultValidatorFactory().getValidator().validate(getOrder().getAddress());
        if (issues.size() > 0) {
            return "address";
        }
        confirmedShippingAddress = true;
        return "credit_card";
    }

    public String verifyCreditCard() {
        if (!isValidCreditCard()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid credit card number"));
            return "credit_card";
        }
        return "confirm";
    }


    private void prepopulateDefaultShipppingAddress() {
        getOrder().getAddress().setZip(getUser().getAddress().getZip());
        getOrder().getAddress().setStreet1(getUser().getAddress().getStreet1());
        getOrder().getAddress().setStreet2(getUser().getAddress().getStreet2());
        getOrder().getAddress().setCountry(getUser().getAddress().getCountry());
        getOrder().getAddress().setCity(getUser().getAddress().getCity());
        getOrder().getAddress().setState(getUser().getAddress().getState());
    }

    public boolean isValidCreditCard() {
        if ((getOrder().getCcNumber() == null) || (getOrder().getCcType() == null)) {
            return true;
        }

        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.AMEX + CreditCardValidator.VISA + CreditCardValidator.DISCOVER + CreditCardValidator.MASTERCARD + CreditCardValidator.DINERS);
        return ccv.isValid(getOrder().getCcNumber());
    }

    public String verifyAndProcessOrder() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order Confirmed."));
        sendOrder();
        return "order_confirmation";

    }

    @PreDestroy
    public void sessionDestroyed() {
        if (getCart() != null) {
            if (getCart().getId() != null) {
                cartService.deleteCart(cart);
            }
            setCart(new Cart());
            setOrder(new StoreOrder());
        }
    }

    public void sendOrder() {
        setHasConfirmedShippingAddress(false    );
        List<Item> cartList = getCart().getItems();
        String orderIds = "";
        String str1 = "";
        String invoiceId = "";
        String invoiceIds = "";
        int outOfStock = 0;

        ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        org.tempuri.ArrayOfOrderDetail arrayOfOrderDetail = new org.tempuri.ArrayOfOrderDetail();

        for (Item item : cartList) {
            try {
                Long id = cartService.checkOut(item.getId(),
                        cartService.getCartSize(getUser().getId()));

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(id);
                orderDetail.setId(item.getId());
                orderDetail.setTitle(item.getTitle());
                arrayOfOrderDetail.getOrderDetail().add(orderDetail);
                orderIds = orderIds + id.toString() + ", ";
                if (id == 0) {
                    outOfStock = 1;
                }
                FulfillmentOrder order = new FulfillmentOrder(item, getUser());
                fulfillmentProducer.sendFulfillment(order);
            } catch (Exception e) {
                log.error(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error Creating Order:" + e.getMessage()));
            }
        }




        log.debug("ORDERS ARE " + orderIds);
        if (!ArgumentUtils.isNullOrEmpty(orderIds) && outOfStock == 0) {
            orderIds = orderIds.substring(0, orderIds.length() - 2);
            log.debug("Request time(ms) in CartAction: sendItems"
                    + System.currentTimeMillis());
            messageProducer.sendMessageWithOrderId(orderIds, getUser().getEmail());
            messageProducer.sendTextMessageWithOrderId();
            customerMessageProducer.sendCustomerMesssage(getUser());


            if (invoiceId == "") {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order ID(s) for your order(s) : "
                        + orderIds));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your Invoice ID for your order(s) " + orderIds + ": "
                        + invoiceId));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order not created as one or more items in your cart were out of stock"));

        }
        //deleting the cart instance associated with the user.
        cartService.deleteCart(cart);
        //creating an empty new cart instance and associating it with the user.
        cart = new Cart();
        cart.setUser(getUser());
    }

}
