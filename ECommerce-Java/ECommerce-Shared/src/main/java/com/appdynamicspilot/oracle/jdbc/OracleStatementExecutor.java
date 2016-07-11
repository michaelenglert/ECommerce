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

package com.appdynamicspilot.oracle.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 * Created by aleftik on 3/8/15.
 */
public class OracleStatementExecutor extends OracleQueryExecutor {
    private static final Logger logger = Logger.getLogger(OracleStatementExecutor.class.getName());

    @Override
    public void executeOracleQuery() {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = getDataSource().getConnection();
            stmt = connection.createStatement();
            stmt.execute(getOracleQueryString());
        } catch (SQLException sqle) {
            logger.error("This may be ignored in case of Oracle is not setup");
            logger.error(sqle.getMessage());
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement element : sqle.getStackTrace()) {
                sb.append(element.toString());
                sb.append("\n");
            }
            logger.error(sb.toString());
        }  finally {
            if (connection != null) {try{connection.close();}catch (SQLException sqle) {}}
            if (stmt != null) {try{stmt.close();}catch (SQLException sqle) {}}
        }


    }
}
