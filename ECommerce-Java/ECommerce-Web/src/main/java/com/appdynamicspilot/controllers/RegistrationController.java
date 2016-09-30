package com.appdynamicspilot.controllers;

/**
 * Created by aleftik on 8/28/16.
 */

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.UserService;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Map;

import org.springframework.context.annotation.Scope;

/**
 * @author Adam Leftik
 */
@Named("registrationController")
@SessionScoped
@Scope("session")
public class RegistrationController implements Serializable {

    @Inject
    private UserService userService;

    @Produces
    @Named("USER")
    private User user;
    private boolean loggedIn = false;
    private boolean inCheckout = false;

    /**
     * Creates a new instance of RegistrationController
     */
    public RegistrationController() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsLoggedIn() {
        return loggedIn;
    }

    private void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public String register() {
        Exception result = null;
        if (getUser().getCustomerType() == null) {
            getUser().setCustomerType(User.CUSTOMER_TYPE.BRONZE);
        }

        result = userService.register(getUser());
        if (result == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + getUser().getCustomerName() + "!"));
            setLoggedIn(true);
        } else if (result instanceof ConstraintViolation) {
            ConstraintViolationException cve = (ConstraintViolationException) result;
            for (ConstraintViolation error : cve.getConstraintViolations()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + error.getMessage() + "!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error Registering " + result.getMessage() + "!"));
        }
        if(isInCheckout()) {
            return getShoppingCartController().checkout();
        }
        return "index";
    }

    public String login() {
        Map<String, String> parms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String username = getUser().getEmail();
        String password = getUser().getPassword();
        if ((username != null) && (!"".equals(username)) && (password != null) && (!"".equals(password))) {
            user = userService.getMemberByLoginName(username);
            boolean authed = userService.validateMember(username, password);

            if (authed) {
                setLoggedIn(true);
                if (isInCheckout()) {
                    return getShoppingCartController().checkout();
                }
                return "index";
            } else {
                user = new User();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect username and/or password."));
            }
        } else {
            user = new User();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You need to enter a username and password to login"));
        }

        return "login";
    }

    public String logout() {
        setLoggedIn(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        getShoppingCartController().sessionDestroyed();
        return "index";
    }

    public void updateProfile() {
        Exception result = null;
        try {
            userService.updateProfile(user);
        } catch (Exception ex) {
            result = ex;
        }
        if (result == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profile Updated for:  " + getUser().getCustomerName() + "!"));
            setLoggedIn(true);
        } else {
//            for (ConstraintViolation error : result.getConstraintViolations()) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + error.getMessage() + "!"));
//            }
        }
    }


    public void usernameUpdate() {
        String username = getUser().getEmail();
        if ((username != null) && (!"".equals(username))) {
            boolean inUse = userService.doesUsernameExist(username);
            if (inUse) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry the username " + getUser().getEmail() + " is already taken!"));
            }
        }
    }

    private ShoppingCartController getShoppingCartController() {
        ShoppingCartController controller = FacesContext.getCurrentInstance().getApplication()
                .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{shoppingCartController}", ShoppingCartController.class);
        return controller;
    }

    @PreDestroy
    public void sessioDestroy() {
        setLoggedIn(false);
        setUser(new User());

    }

    public boolean isInCheckout() {
        return inCheckout;
    }

    public void setInCheckout(boolean inCheckout) {
        this.inCheckout = inCheckout;
    }
}
