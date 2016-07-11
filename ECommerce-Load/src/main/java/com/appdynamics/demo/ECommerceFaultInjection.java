package com.appdynamics.demo;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Created by swetha.ravichandran on 8/7/15.
 */
public class ECommerceFaultInjection {

    private Client client = null;

    static final Logger logger = Logger.getLogger(ECommerceFaultInjection.class.getName());

    public void checkAndInjectSavedFaults(String host, int port, List<User> userList) {

        try {

            // Check if faults are available for the specific user and the time frame.
            User user = getUserInfo(userList);
            client = ClientBuilder.newClient();
            WebTarget webTarget =
                    client.target(UriBuilder.fromUri("http://" + host + ":" + port + "/appdynamicspilot/rest/json/fault/getfaults").build());
            String response = webTarget.request().header("username", user.getEmail()).accept(MediaType.APPLICATION_JSON).get(String.class);
            try {
                Thread.currentThread().sleep(6000);
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
            Gson gson = new Gson();
            TypeToken<List<Fault>> token = new TypeToken<List<Fault>>() {
            };
            List<Fault> faultList = gson.fromJson(response, token.getType());

            if (faultList != null && faultList.size() > 0) {

                //Make Asynchronous calls to inject faults ( to avoid 503 error)
                final URI baseUri = URI.create("http://" + host + ":" + port + "/appdynamicspilot/rest/");
                Client client = ClientBuilder.newClient();
                webTarget = client.target(baseUri);
                final Future<String> entityFuture =
                        webTarget.path("json/fault/injectfaults").request(MediaType.TEXT_PLAIN).async().post(Entity.entity(new GenericEntity<List<Fault>>(faultList) {
                        }, MediaType.APPLICATION_JSON), new InvocationCallback<String>() {
                            @Override
                            public void completed(String response) {
                                logger.info(response);
                            }

                            @Override
                            public void failed(Throwable throwable) {
                                logger.warning("Invocation failed.");
                                logger.warning(throwable.getMessage());
                            }
                        });
                long startTime = System.currentTimeMillis();
                try {
                    Thread.currentThread().sleep(360000);
                } catch (Exception ex) {
                    logger.warning(ex.getMessage());
                }
                long endTime = System.currentTimeMillis();
                logger.info("Wait time for injecting faults : " + (endTime - startTime) + " milliseconds");
                logger.info(entityFuture.get());
            } else {
                logger.info("Empty Faults");
            }
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
    }

    private User getUserInfo(List<User> userList) {
        logger.info("FaultInjection - userInfo Size : " + userList.size());
        if (userList != null && userList.size() > 0) {
            Random generator = new Random();
            int index = generator.nextInt(userList.size());
            index = (index == userList.size()) ? index - 1 : index;
            User user = userList.get(index);
            logger.info("FaultInjection - User Name : " + user.getEmail());
            logger.info("FaultInjection - Password : " + user.getPassword());
            return user;
        }
        return null;
    }
}
