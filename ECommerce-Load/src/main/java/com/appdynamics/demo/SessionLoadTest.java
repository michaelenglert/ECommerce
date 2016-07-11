package com.appdynamics.demo;

import java.util.List;

/**
 * Created by aleftik on 6/18/14.
 */
public abstract class SessionLoadTest extends StaticRequestLoadTest {


    public SessionLoadTest(String host, String angularHost, int port, int angularPort, int callDelay, List<User> userList) {
        super(host, angularHost, port, angularPort, callDelay,userList);
    }

    abstract void login();

    abstract void logout();

    abstract void performLoad();

    @Override
    protected void executeTest() {
        try {
            login();
            performLoad();
            logout();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            destroy();
        }
    }

    public String[] getUrls() {
        return null;
    }
}
