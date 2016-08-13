/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamics.inventory;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {

    private final static Logger LOGGER = Logger.getLogger(QueryExecutor.class.getName());
    private String oracleQueryString;
    private String threadQuery;
    private DataSource dataSource = null;

    public String getOracleQueryString() {
        return oracleQueryString;
    }
    public void setOracleQueryString(String oracleQueryString) {
        this.oracleQueryString = oracleQueryString;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getOracleThreadQuery() {
        return threadQuery;
    }
    public void setOracleThreadQuery(String oracleThreadQuery) {
        this.threadQuery = oracleThreadQuery;
    }


    public void executeOracleQuery() {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = getDataSource().getConnection();
            stmt = connection.createStatement();
            stmt.execute(getOracleQueryString());
        } catch (SQLException sqle) {
            LOGGER.error("This may be ignored in case of Oracle is not setup");
            LOGGER.error(sqle.getMessage());
        }  finally {
            if (connection != null) {try{connection.close();}catch (SQLException sqle) {}}
            if (stmt != null) {try{stmt.close();}catch (SQLException sqle) {}}
        }
    }

    public void executeOrderUpdateQuery() {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = getDataSource().getConnection();
            stmt = connection.createStatement();
            stmt.execute("update world.ORDERS SET ORDERDATE=to_date('13-JAN-05') WHERE ORDERID=11078");
        } catch (SQLException sqle) {
            LOGGER.error("This may be ignored in case of Oracle is not setup");
            LOGGER.error(sqle.getMessage());
        }  finally {
            if (connection != null) {try{connection.close();}catch (SQLException sqle) {}}
            if (stmt != null) {try{stmt.close();}catch (SQLException sqle) {}}
        }
    }
}