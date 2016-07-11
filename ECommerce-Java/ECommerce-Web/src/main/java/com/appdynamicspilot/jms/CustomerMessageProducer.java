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

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import com.appdynamicspilot.model.User;

public class CustomerMessageProducer extends JmsGatewaySupport {

    public void sendCustomerMesssage(final User user) {
        getJmsTemplate().send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                StringBuilder builder = new StringBuilder();
                builder.append("user.id=");
                builder.append('\n');
                builder.append(user.getId());
                builder.append("user.email=");
                builder.append(user.getEmail());
                builder.append('\n');
                builder.append("user.password");
                builder.append(user.getPassword());
                msg.setText(builder.toString());
                return msg;
            }
        });
    }
}
