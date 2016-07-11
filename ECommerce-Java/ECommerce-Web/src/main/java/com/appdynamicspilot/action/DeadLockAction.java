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
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import java.util.concurrent.*;

public class DeadLockAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private final ExecutorService pool = Executors.newFixedThreadPool(2);

    void startDeadlock() {
//		Thread t1 = new Thread(new Runnable() {
//			public void run() {
//				lock12();
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
//				lock21();
//			}
//		});

        Callable c1 = new Callable() {
            public Object call() {
                lock12();
                return null;
            }
        };

        Callable c2 = new Callable() {
            public Object call() {
                lock21();
                return null;
            }
        };

        Future f1 = pool.submit(c1);
        Future f2 = pool.submit(c2);

        //pool.execute(c1);
        //pool.execute(t2);
        //t1.start();
        //t2.start();

        for (int i = 0; i < 1000; i++) {
            sleep();
        }

        //f1.cancel(true);
        //f2.cancel(true);
        //pool.shutdown();
    }

    private void lock12() {
        synchronized (lock1) {
            sleep();
            synchronized (lock2) {
                sleep();
            }
        }
    }

    private void lock21() {
        synchronized (lock2) {
            sleep();
            synchronized (lock1) {
                sleep();
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                // System.out.println("Thread " + threadName + " : " + i);
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runDeadLock(int count) {
        for (int i = 0; i < count; i++) {
            startDeadlock();
        }
    }

    public void runDeadLock() {
        int paramCount = Integer.parseInt(getServletRequest().getParameter("count"));
        runDeadLock(paramCount);
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}