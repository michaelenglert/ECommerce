package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class ECommerceSession extends SessionLoadTest {


    public ECommerceSession(String host, String angularHost, int port, int angularPort, int callDelay, List<User> userList) {
        super(host, angularHost, port, angularPort, callDelay,userList);
    }

    @Override
    void login() {
        //jsp
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' + getPort() + getLoginUrl());
        logger.info("Logging into " + getScheme() + getHost() + ':' + getPort() + getLoginUrl());
        User user = getUserInfo();
        driver.findElement(By.id("textBox")).sendKeys(user.getEmail());
        driver.findElement(By.id("password")).sendKeys(user.getPassword());
        WebElement facebookHack = driver.findElement(By.id("fb"));
        facebookHack.click();
        try {
            Thread.currentThread().sleep(500);
        } catch (Exception ex) {
        }
        driver.findElement(By.id("UserLogin_Login")).click();
    }

    @Override
    void logout() {
        WebDriver driver = getDriver();
        driver.get(getScheme() + getHost() + ':' + getPort() + "/appdynamicspilot/UserLogOut.action");
        logger.info("Logged out");
    }

    @Override
    abstract void performLoad();

    abstract String getLoginUrl();


    abstract User getUserInfo();

    abstract String getScheme();
}
