package com.appdynamicspilot.sqs;

import com.appdynamicspilot.model.FulfillmentOrder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by aleftik on 4/15/15.
 */
public class SQSFullfilmentSender extends AbstractSQSClient {

    private static Logger logger = Logger.getLogger(SQSFullfilmentSender.class.getName());


    public SQSFullfilmentSender()  {
        super();
    }

    public void sendOrder(FulfillmentOrder order) {
        if (creds != null) {
            client.sendMessage(getQueue().getQueueUrl(), toString(order));
        }
    }

    public String toString(FulfillmentOrder order) {
        StringWriter writer = new StringWriter();

        try {
            JAXBContext context = JAXBContext.newInstance(FulfillmentOrder.class);
            Marshaller m = context.createMarshaller();
            m.marshal(order, writer);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe(ex.getMessage());
        }

        return writer.toString();

    }

}
