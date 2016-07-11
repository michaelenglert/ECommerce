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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.UserService;
import com.appdynamicspilot.util.SpringContext;
import com.appdynamicspilot.webserviceclient.SoapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckTransactionAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CheckTransactionAction.class);

    public void init(ServletConfig config) throws ServletException {
        logger.info("init");
    }

    private SoapUtils soapUtil;
    private UserService userService;


    public void setSoapUtil(SoapUtils soapUtil) {
        this.soapUtil = soapUtil;
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userService = (UserService) SpringContext.getBean("userService");
        User user = userService.getMemberByLoginName("ravi");
        CartService cartService = (CartService) SpringContext.getBean("cartService");
        cartService.getAllItemsByUser(user.getId());

        logger.info("The password for this user is " + user.getPassword());
        logger.info("Inside JMS transactions");

        //       MessageProducer messageProducer=(MessageProducer)SpringContext.getBean("messageProducer");
        //       messageProducer.sendTextMessageWithOrderId();
        logger.info("Calling Axis Stub");
        logger.info("Making Web Services WS2 Call");


        soapUtil = (SoapUtils) SpringContext.getBean("soapUtil");
        Long orderId = soapUtil.raiseJMSPO();
        //StockQuoteServiceSoapBindingStub stub=new StockQuoteServiceSoapBindingStub(new URL(soapUtil.getAxis14Url()),null);

        PrintWriter out = response.getWriter();
        out.println("Request Type : " + request.getMethod());
        out.println("Hello " + request.getParameter("firstName") + " " + request.getParameter("lastName") + ". Roses are red.");
        out.print("Violets are blue.");
        //out.print("Web Services are fetching  " +stub.getQuote("XXX") +" for you");
        logger.info("Sent Message");

        out.print("\nWS Axis2 fetched : " + orderId + " ");
        //   out.print("\nWS Axis1.4 fetched : "+stub.getQuote("XXX"));
        Cookie mycookie = new Cookie("firstCookie", "appdynamics");
        response.addCookie(mycookie);
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
