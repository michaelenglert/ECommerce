package com.appdynamicspilot.service;

import com.appdynamicspilot.model.Fault;

import java.util.List;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
public interface FaultServiceInterface {

    void saveFaults(Fault fault);

    List<Fault> getAllFaultsByUser(String userName);

    List<Fault> getAllFaults();

    int deleteFaults(String userName, String faultName);
}
