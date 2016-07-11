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

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.appdynamicspilot.service.MailService;
import com.appdynamicspilot.util.QueryExecutor;

/**
 * Service for receiving jms messages.
 *
 * @author Vikash
 */
public class MessageConsumer implements MessageListener {

    private MailService mailService;

    private static Logger log = Logger.getLogger(MessageConsumer.class.getName());

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }


    public void onMessage(Message message) {
        //	log.info("**************** Request time(ms) in MessageConsumer :: " + System.currentTimeMillis());
        if (message instanceof MapMessage) {
            try {
                MapMessage mapMessage = (MapMessage) message;
                String command = mapMessage.getString("COMMAND");
                QueryExecutor qe = new QueryExecutor();
                qe.executeQuery(10l, mapMessage.getString("ORDER_ID"), mapMessage.getString("EMAIL_ID") + " user has placed order request");
                log.info("received message=" + command);

                if ("CMD_MAIL".equals(command)) {
                    mailService.sendOrderMail(mapMessage.getString("EMAIL_ID"), mapMessage.getString("ORDER_ID"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("error on message", e);
            }
        }
    }
}
