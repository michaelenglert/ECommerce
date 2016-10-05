package com.appdynamicspilot.rest;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.persistence.CartPersistence;
import com.appdynamicspilot.persistence.ItemPersistence;
import com.appdynamicspilot.persistence.UserPersistence;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.UserService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by aleftik on 10/4/16.
 */
public class ECommerceApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(UserService.class).to(UserService.class);
        bind(CartService.class).to(CartService.class);
        bind(UserService.class).to(UserService.class);
        bind(UserPersistence.class).to(UserPersistence.class);
        bind(CartPersistence.class).to(CartPersistence.class);
        bind(ItemPersistence.class).to(ItemPersistence.class);
    }

}
