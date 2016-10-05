package com.appdynamicspilot.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by aleftik on 10/4/16.
 */
public class ECommerceRestApplication extends ResourceConfig {
    public ECommerceRestApplication() {
        register(new ECommerceApplicationBinder());
        packages("com.appdymamicspilot.rest;com.appdynamicspilot.restv2");
    }
}
