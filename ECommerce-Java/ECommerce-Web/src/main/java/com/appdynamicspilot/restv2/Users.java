package com.appdynamicspilot.restv2;

/**
 * Created by swetha.ravichandran on 7/15/15.
 */

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.UserService;
import com.appdynamicspilot.util.SpringContext;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by swetha.ravichandran on 7/15/15.
 */

@Path("/json/user")
public class Users {
    private final static Logger log = Logger.getLogger(Users.class.getName());

    /**
     * Gets userService bean
     *
     * @return UserService
     */
    public UserService getUserService() {
        return (UserService) SpringContext.getBean("userService");
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllItems() throws Exception {
        Gson gsonGetAllItems = new Gson();
        List<User> lsUser = new ArrayList<User>();
        try {
            lsUser = getUserService().getAllUser();

        } catch (Exception e) {
            log.error(e);
        }
        return gsonGetAllItems.toJson(lsUser);
    }
}
