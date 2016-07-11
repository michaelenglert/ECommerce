package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by swetha.ravichandran on 6/18/14.
 */
public abstract class ECommerceAngularSession extends SessionLoadTest {


    public ECommerceAngularSession(String host, String angularHost, int port, int angularPort, int callDelay , List<User> userList) {
        super(host, angularHost, port, angularPort, callDelay, userList);
    }

    @Override
    void login() {
        //Angular
        try {
            User user = getUserInfo();
            WebDriver angularDriver = getDriver();
            angularDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            angularDriver.get(getScheme() + getAngularHost() + ':' + getAngularPort() + getAngularLoginUrl());
            angularDriver.findElement(By.id("username")).sendKeys(user.getEmail());
            angularDriver.findElement(By.id("password")).sendKeys(user.getPassword());
            angularDriver.findElement(By.id("btnLogin")).click();
            logger.info("Angular - Logging into " + getScheme() + "angular" + ':' + getAngularPort() + getAngularLoginUrl());
            try {
                Thread.currentThread().sleep(500);
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }

        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }

    }

    @Override
    void logout() {
        logger.info("Angular - Logged out");
    }

    @Override
    abstract void performLoad();

    abstract String getAngularLoginUrl();

    abstract String getAngularProductsUrl();

    abstract User getUserInfo();

    abstract String getScheme();
}
