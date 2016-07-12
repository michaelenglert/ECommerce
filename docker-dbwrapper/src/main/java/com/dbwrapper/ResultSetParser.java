package com.dbwrapper;

import java.sql.ResultSet;

import org.json.JSONObject;
import org.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultSetParser {

	private static final Logger logger = LoggerFactory
			.getLogger(ResultSetParser.class);

	/**
	 * Convert a result set into a JSON Array
	 * 
	 * @param resultSet
	 * @return a JSONArray
	 * @throws Exception
	 */
	public JSONArray convertToJSON(ResultSet resultSet) throws Exception{
		logger.info("the number of columns retrieved is: "
				+ resultSet.getMetaData().getColumnCount());

		JSONArray jsonArray = new JSONArray();
		logger.info("the resultset contains values: " + resultSet.next());
		while (resultSet.next()) {

			int columns = resultSet.getMetaData().getColumnCount();

			JSONObject obj = new JSONObject();

			for (int i = 0; i < columns; i++) {
				obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
						.toLowerCase(), resultSet.getObject(i + 1));
				logger.info(obj.toString());

			}
			jsonArray.put(obj);
		}
		return jsonArray;
	}
}
