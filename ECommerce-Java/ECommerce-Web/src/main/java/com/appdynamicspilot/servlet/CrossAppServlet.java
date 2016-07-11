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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.net.URL;
import javax.servlet.ServletConfig;

/**
 * Created by aleftik on 10/24/14.
 */
public class CrossAppServlet extends HttpServlet {
    private String host = null;
    public void init(ServletConfig config) {

        String envSuffix = config.getInitParameter("VAR");
        if (envSuffix == null) {
            envSuffix = "REST_URL";
        }
        Map<String, String> envs = System.getenv();

        for (String env:envs.keySet()) {
            if (env.equals(envSuffix)) {
                host = envs.get(env);
            }
        }

    }
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (host != null) {
            if (!"".equals(host)) {
                URL url = new URL("http://" + host + request.getContextPath() + "/rest/items/all");
                url.openConnection().getContent();
            }
        }
        response.setStatus(204);
    }


}
