package com.appdynamics.demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by swetha.ravichandran on 8/7/15.
 */
@XmlRootElement
public class Fault {

    /**
     * No Argument Constructor
     */
    public Fault() {
    }

    /**
     * Argument Constructor
     * @param id
     * @param bugname
     * @param username
     * @param timeframe
     */
    public Fault(Long id, String bugname, String username, String timeframe) {
        this.id = id;
        this.bugname = bugname;
        this.username = username;
        this.timeframe = timeframe;
    }

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "bugname")
    private String bugname;

    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "timeframe")
    private String timeframe;

    /**
     * Getter and setter of id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter and setter of bugname
     */
    public String getBugname() {
        return bugname;
    }

    public void setBugname(String bugname) {
        this.bugname = bugname;
    }

    /**
     * Getter and setter of username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter and setter of timeframe
     */
    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }
}
