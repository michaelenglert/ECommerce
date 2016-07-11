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

package com.appdynamicspilot.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.SpringContext;

public class BooksListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sleepTime = req.getParameter("delay");
        if (sleepTime == null) sleepTime = "1000";
        long delay = Long.parseLong(sleepTime);
        List<Item> books = getBooksList(delay);
        //for (Item temp : books) {
        //System.out.println(temp.getTitle());
        //}
        System.out.println(books);
        req.setAttribute("itemsList", books);
        String forwardAddr = "WEB-INF/presentation/bookslist.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(forwardAddr);
        rd.forward(req, resp);

    }

    public List<Item> getBooksList(long delay) {
        try {
            ItemService itemService = (ItemService) SpringContext.getBean("itemService");
            Thread.sleep(delay);
            return itemService.getAllItems();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}