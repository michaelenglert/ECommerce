package com.appdynamicspilot.sqs;
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.auth.*;
import java.util.Map;

/**
 * Created by aleftik on 4/28/15.
 */
public abstract class AbstractSQSClient {
    private static final String FULFILLMENT_QUEUE = "FulfillmentQueue";
    private static String AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
    private static String AWS_SECRET_KEY = "AWS_SECRET_KEY";
    protected BasicAWSCredentials creds = null;
    protected AmazonSQSClient client = null;

    public AbstractSQSClient() {
        Map<String,String> env = System.getenv();
        String awsAccessKey = env.get(AWS_ACCESS_KEY);
        String awsSecretKey = env.get(AWS_SECRET_KEY);
        if ((awsSecretKey != null) && (awsAccessKey != null))  {
            creds = new BasicAWSCredentials(awsAccessKey,awsSecretKey);
            client = new AmazonSQSClient(creds);

        }
    }

    protected CreateQueueResult getQueue() {
        return  client.createQueue(FULFILLMENT_QUEUE);
    }
}
