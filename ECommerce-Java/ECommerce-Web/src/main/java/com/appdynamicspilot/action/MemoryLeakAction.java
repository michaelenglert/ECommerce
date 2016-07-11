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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.appdynamicspilot.service.MemoryLeakService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * This action creates memory leak situation
 *
 * @author kevinip
 */
public class MemoryLeakAction extends ActionSupport implements
        ServletRequestAware {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private HttpServletRequest request;

    /**
     * controller to handle increasing memory by the count and size specified
     * <p/>
     * optionally setting the clear percentage threshold to flush the data structure for customization
     *
     * @return
     */
    public String increase() {
        int objectCount = NumberUtils.toInt(this.request.getParameter("count"), 0);
        int objectSize = NumberUtils.toInt(this.request.getParameter("size"), 0);

        MemoryLeakService memoryLeakService = MemoryLeakService.instance;
        if (objectCount > 0 && objectSize > 0) {
            memoryLeakService.increase(objectCount, objectSize);
        }

        String clearPercentParam = this.request.getParameter("clearPercent");
        if (clearPercentParam != null && (clearPercentParam = clearPercentParam.trim()).length() > 0) {
            int clearPercent = NumberUtils.toInt(clearPercentParam, MemoryLeakService.DEFAULT_CLEAR_PERCENT);
            memoryLeakService.setClearPercent(clearPercent);
        }

        this.populateResult();

        return "SUCCESS";
    }

    private void populateResult() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        double freePercent = ((double) freeMemory / (double) totalMemory) * 100;
        this.request.setAttribute("freeMem", freeMemory);
        this.request.setAttribute("totalMem", totalMemory);
        this.request.setAttribute("freePercent", freePercent);
        this.request.setAttribute("inUsedPercent", (100d - freePercent));

        MemoryLeakService memoryLeakService = MemoryLeakService.instance;
        this.request.setAttribute("objectCount", memoryLeakService.getSize());
    }

    public String clear() {
        MemoryLeakService.instance.clear();

        this.populateResult();
        return "SUCCESS";
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}