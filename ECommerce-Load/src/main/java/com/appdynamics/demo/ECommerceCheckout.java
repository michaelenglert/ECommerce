package com.appdynamics.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by aleftik on 6/18/14.
 */
public class ECommerceCheckout extends ECommerceSession {

    static private final Random randomGen = new Random();

    public ECommerceCheckout(String host, String angularHost, int port, int angularPort, int callDelay, List<User> userList) {
        super(host, angularHost, port, angularPort, callDelay,userList);
    }

    @Override
    void performLoad() {


        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {

            //jsp
            List<WebElement> checkBoxes = driver.findElements(By.id("selectedId"));

            int numBooks = randomGen.nextInt(5);
            for (int i = 0; i < numBooks + 1; i++) {
                int bookNumber = randomGen.nextInt(checkBoxes.size() - 1);
                WebElement randCBox = (WebElement) checkBoxes.toArray()[bookNumber];
                randCBox.click();
                logger.info("Selected book # " + bookNumber);
            }

            WebElement addToCart = driver.findElement(By.id("itemsForm_submitValue"));
            addToCart.click();
            logger.info("Items added to Cart");

            WebElement submit = driver.findElement(By.id("ViewCart_submitValue"));
            submit.click();
            logger.info("Checkout Cart");

        } catch (Exception ex) {
            logger.warning("Ignored Exception");
        }
    }


    @Override
    User getUserInfo() {
        List<User> users = getUserList();
        logger.info("userInfo Size : " + users.size());
        if (users != null && users.size() > 0) {
            Random generator = new Random();
            int index = generator.nextInt(users.size());
            index = (index == users.size()) ? index - 1 : index;
            User user = users.get(index);
            logger.info("User Name : " + user.getEmail());
            logger.info("Password : " + user.getPassword());
            return user;
        }
        return null;
    }

    @Override
    String getLoginUrl() {
        return "/appdynamicspilot/";
    }

    String getScheme() {
        return "http://";
    }
}
