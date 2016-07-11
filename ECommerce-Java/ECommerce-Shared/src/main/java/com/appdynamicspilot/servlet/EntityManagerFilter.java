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

import com.appdynamicspilot.persistence.EntityManagerHelper;
import com.appdynamicspilot.persistence.EntityManagerHolder;
import com.appdynamicspilot.util.SpringContext;
import org.springframework.context.ApplicationContext;

import org.springframework.aop.framework.Advised;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.Filter;
import javax.persistence.Persistence;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by aleftik on 10/8/14.
 */
public class EntityManagerFilter implements Filter {

    EntityManagerFactory emf;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        EntityManagerHolder holder = (EntityManagerHolder) SpringContext.getBean("entityManagerHolder");
        emf = holder.getEntityManagerFactory();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        EntityManagerHelper.getInstance().setEntityManager(emf.createEntityManager());
        filterChain.doFilter(servletRequest,servletResponse);

        EntityManagerHelper.getInstance().getEntityManager().close();
        EntityManagerHelper.getInstance().setEntityManager(null);
    }

    public void destroy() {

    }
}
