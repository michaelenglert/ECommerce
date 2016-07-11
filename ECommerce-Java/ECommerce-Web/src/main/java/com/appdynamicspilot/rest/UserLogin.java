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

package com.appdynamicspilot.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.UserService;
import com.appdynamicspilot.util.SpringContext;

@Path("/user")
public class UserLogin {
    private static final Logger logger = Logger.getLogger(UserLogin.class.getName());

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void post(@Context HttpServletRequest req, @FormParam("username") String name, @FormParam("password") String password) {
        HttpSession session = req.getSession(true);
        UserService userSvc = getUserService();
        boolean valid = userSvc.validateMember(name, password);

        if (userSvc.validateMember(name, password.trim())) {
            User user = userSvc.getMemberByLoginName(name);
            session.setAttribute("USER", user);
        }
    }

    @Path("/login")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String validateUser(@Context HttpServletRequest req, @FormParam("username") String name, @FormParam("password") String password) {
        HttpSession session = req.getSession(true);
        UserService userSvc = getUserService();
        boolean valid = userSvc.validateMember(name, password.trim());
        if (valid) {
            User user = userSvc.getMemberByLoginName(name);
            session.setAttribute("USER", user);
        }

        return valid ? "success" : "fail";
    }

    public UserService getUserService() {
        return (UserService) SpringContext.getBean("userService");
    }
}
