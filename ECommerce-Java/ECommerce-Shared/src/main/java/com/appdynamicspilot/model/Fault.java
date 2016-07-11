package com.appdynamicspilot.model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
@XmlRootElement(name = "Fault")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Fault")
@Table(name = "Fault")
public class Fault implements java.io.Serializable {

    private static Logger log = Logger.getLogger(Fault.class.getName());
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    @Column(name = "id")
    private Long id;

    @XmlElement
    @Column(name = "bugname")
    private String bugname;

    @XmlElement
    @Column(name = "username")
    private String username;

    @XmlElement
    @Column(name = "timeframe")
    private String timeframe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBugname() {
        return bugname;
    }

    public void setBugname(String bugname) {
        this.bugname = bugname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

}
