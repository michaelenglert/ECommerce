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


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class OracleCallableStatementExecutor extends OracleQueryExecutor {
    private final static Logger LOGGER = Logger.getLogger(OracleCallableStatementExecutor.class.getName());

        public void executeOracleQuery() {
			Connection connection = null;
			CallableStatement cs = null;
			try {
				connection = getDataSource().getConnection();
				cs = connection.prepareCall("{ call getItem(?) }");
                cs.setInt(1,200);
				cs.execute();
			} catch (Exception ex) {
				LOGGER.error("This may be ignored in case of Oracle is not setup");
				LOGGER.error(ex.getMessage());
			} finally {
				if (cs != null) try {cs.close();} catch (Exception ex) {}
				if (connection != null) try {connection.close();} catch (Exception ex) {}
			}
		LOGGER.info("Done");
    }
}