package com.appdynamicspilot.survey;

import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageListener;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Override;
import java.lang.String;
import java.lang.Thread;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class JMSMessageListener implements MessageListener {
    @Override
    public void onMessage(javax.jms.Message msg) {
        try {
            if (!(msg instanceof TextMessage))
                throw new RuntimeException("no text message");
            TextMessage tm = (TextMessage) msg;
            System.out.println(tm.getText());                  // print message
            //Check the time to induce memory leak
            try {
                // Code has been repeated in order to induce memory leak in customer survey container.
                SimpleDateFormat parser = new SimpleDateFormat("HH:mm", Locale.US);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                String currentTime = sdf.format(new Date());
                Date parsedCurrentTime = parser.parse(currentTime);
                Date parsedStartTime = parser.parse("09:00");
                Date parsedEndTime = parser.parse("10:00");
                if (parsedCurrentTime.after(parsedStartTime) && parsedCurrentTime.before(parsedEndTime)) {
                    List<byte[]> list = new ArrayList<byte[]>();
                    long usedMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
                    long totalMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
                    double usedPercentage = ((double) usedMemory / (double) totalMemory) * 100.0;
                    int i = 0;
                    while (usedPercentage <= 83) {
                        byte[] copy = new byte[1024];
                        synchronized (list) {
                            list.add(copy);
                        }
                        i++;
                        if (i % 1000 == 0) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e){
                                System.err.println(e.getMessage());
                            }
                            usedMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
                            totalMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
                            usedPercentage = ((double) usedMemory / (double) totalMemory) * 100.0;
                        }
                    }
                }
            }
            catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        } catch (JMSException e) {
            System.err.println("Error Reading Msg: " + e.toString());
        }
    }
}

public class customerSurvey {

    /**
     * Main method.
     *
     */

    public static void main(String[] args) throws RuntimeException, IOException {
        Properties prop = new Properties();
        String propFileName = "jms.properties";

        InputStream inputStream = customerSurvey.class.getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        String url = prop.getProperty("jms.url");
        String duration = prop.getProperty("duration");
        boolean runForever = false;
        int runDuration = 1;

        if (duration.trim().equalsIgnoreCase("forever")) {
            runForever = true;
        } else {
            if (duration.trim().matches("^[1-9]\\d*$")) {
                try {
                    runDuration = Integer.parseInt(duration.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Duration is invalid: " + duration.trim() + " " + e.toString());
                    runDuration = 1;
                }
            }
        }
        System.out.println("Properties duration: " + duration.trim() + " runDuration = " + runDuration + " Forever = " + runForever);

        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = null;

        try {
            connection = factory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("CustomerQueue");
            MessageConsumer consumer = session.createConsumer(queue);
            JMSMessageListener listener = new JMSMessageListener();
            consumer.setMessageListener(listener);
            connection.start();
            do {
                Thread.sleep(runDuration * 60 * 1000);
            } while (runForever);
        } catch (JMSException | InterruptedException e) {
            if (e instanceof JMSException)
                System.err.println("Error Connecting: " + e.toString());
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                System.err.println("Error Disconnecting: " + e.toString());
            }
        }
    }
}
