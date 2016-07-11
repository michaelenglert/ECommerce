package com.appdynamicspilot.sqs;
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.auth.*;


import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aleftik on 4/28/15.
 */
    public class FulfillmentClient extends AbstractSQSClient {
    Logger logger = Logger.getLogger(FulfillmentClient.class.getName());


    public FulfillmentClient()  {
        super();
    }

    private void recieveMessages() {
        CreateQueueResult  queue = super.getQueue();
        ReceiveMessageResult result = client.receiveMessage(new ReceiveMessageRequest(queue.getQueueUrl()));
        List <Message> messages  = result.getMessages();
        while (true) {
            for (Message message : messages) {
                processMessage(message);
            }
            try {Thread.sleep(1*1000);} catch(InterruptedException ie) {}
        }

    }

    private void processMessage(Message message) {
        client.deleteMessage(new DeleteMessageRequest(getQueue().getQueueUrl(), message.getReceiptHandle()));
        logger.info("Message Deleted:" + message.getReceiptHandle());
    }

    public static void main(String [] args) {
        FulfillmentClient client = new FulfillmentClient();
        client.recieveMessages();
    }
}
