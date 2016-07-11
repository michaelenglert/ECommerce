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

package com.appdynamicspilot.jms;

import com.appdynamicspilot.model.FulfillmentOrder;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Created by aleftik on 12/2/14.
 */
public class FulfillmentConsumer implements MessageListener {
    private static final Logger logger = Logger.getLogger(FulfillmentConsumer.class.getName());
    private String restUrl = null;

    public void onMessage(Message message) {
        try {
            ObjectMessage msg = (ObjectMessage)message;
            FulfillmentOrder order = (FulfillmentOrder) msg.getObject();
            postToRest(order);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe(e.toString());
        }

    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public String getRestUrl () {
        return this.restUrl;
    }


    public void postToRest(FulfillmentOrder order) {
        WebTarget target = ClientBuilder.newClient().target(getRestUrl());
        final String mediaType = MediaType.APPLICATION_XML;
        final Entity<FulfillmentOrder> entity = Entity.entity(order, mediaType);
        Response response = target.request().post(entity);
    }

}
