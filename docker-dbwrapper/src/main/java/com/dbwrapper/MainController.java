package com.dbwrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainController {

    private static final Logger logger = LoggerFactory
            .getLogger(MainController.class);
    Properties props = new Properties();
    InputStream input = null;
    private String dbName, driver, dbUrl, username, password;
    Connection conn = null;
    //Getting the hostname from the system variable HNAME
    String hostname = "oracle-db";

    public void setDBProperties(String db) {
        this.dbName = db;
        Properties prop = new Properties();
        InputStream input = null;
        logger.info("Connecting to database: "+this.dbName);
        try {
            input = getClass().getClassLoader().getResourceAsStream("database.properties");
            prop.load(input);



            if (this.dbName.equalsIgnoreCase("oracle")) {
                try {
                    logger.info(prop.getProperty("oracleDriver"));
                    logger.info(prop.getProperty("oracleUrl"));
                    logger.info(prop.getProperty("oracleUsername"));
                    logger.info(prop.getProperty("oraclePassword"));
                    this.driver = prop.getProperty("oracleDriver");
                    this.dbUrl = prop.getProperty("oracleUrl");
                    this.username = prop.getProperty("oracleUsername");
                    this.password = prop.getProperty("oraclePassword");
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            logger.error("IO Exception in oracle connection" + e);
                        }
                    }
                }
            }
            else {
                try {
                    this.driver = prop.getProperty("mysqlDriver");
                    this.dbUrl = prop.getProperty("mysqlUrl");
                    this.username = prop.getProperty("mysqlUsername");
                    this.password = prop.getProperty("mysqlPassword");
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            logger.error("Sorry, IO exception has occurred in MySQL Connection", e);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            logger.error("File not found" + e);
        } catch (IOException e) {
            logger.error("error in IO" + e);
        }


    }

    /**
     * Creates and returns a connection to the database
     *
     * @param
     * @return conn
     */

    public Connection establishConnection() {

        try {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(this.dbUrl, this.username,
                    this.password);

            return conn;

        } catch (ClassNotFoundException e) {
            logger.error("Sorry, an exception has occurred", e);
        } catch (SQLException e) {
            logger.error("Sorry, an exception has occurred", e);
        }

        return conn;
    }
}
