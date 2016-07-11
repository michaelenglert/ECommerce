package com.appdynamicspilot.faultinjection;

/**
 * Created by shiv.loka on 6/12/15.
 */
public class FaultInjectionFactory {

    public FaultInjection getFaultInjection(String faultType) {

        if (faultType.equalsIgnoreCase("memoryleak")) {
            return new MemoryLeakInjection();
        }
        return null;
    }
}
