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

package com.appdynamicspilot.runbook;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.logging.Logger;

/**
 * Created by aleftik on 1/8/15.
 */
public class ResetConnectionPoolAction {
    public static final String MAX_TOTAL = "maxTotal";
    private Logger logger = Logger.getLogger(ResetConnectionPoolAction.class.toString());
    private static final int INC_MODE=1;
    private static final int  DEC_MODE=0;

    private int mode = 0;
    private int size = 0;

    public static void main(String [] args) {
        ResetConnectionPoolAction action = new ResetConnectionPoolAction();
        action.parseArgs(args);
        action.perform();
    }

    private void parseArgs(String [] args) {
        if (args.length != 1) {
            System.out.println("Usage: size");
        }
        try {
            size = Integer.parseInt(args[0]);

        } catch (NumberFormatException nfe) {
            System.out.println("Usage: size must be ints");
        }

    }

    public void perform() {
        try {
            MBeanServerConnection connection = connect();
            ObjectName mbeanName = new ObjectName("Catalina:type=DataSource,host=localhost,context=/appdynamicspilot,class=javax.sql.DataSource,name=\"jdbc/OracleECommerceDB\"");
            Integer oldVaue = (Integer)connection.getAttribute(mbeanName, MAX_TOTAL);
            connection.setAttribute(mbeanName, new Attribute(MAX_TOTAL, size));
            Integer newValue = (Integer) connection.getAttribute(mbeanName, MAX_TOTAL);
            System.out.println("Old Value was " + oldVaue + " new value is " + newValue);
        } catch (Exception ex) {
            logger.severe(ex.toString());
            ex.printStackTrace();
        }

    }

    public MBeanServerConnection connect() {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8888/jmxrmi");
            JMXConnector connector = JMXConnectorFactory.connect(url, null);
            connector.connect();
            return connector.getMBeanServerConnection();
        } catch (Exception ex) {
            logger.severe(ex.toString());
            ex.printStackTrace();
        }
        return null;
    }


}
