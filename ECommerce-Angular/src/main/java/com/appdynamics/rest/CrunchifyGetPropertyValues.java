package com.appdynamics.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CrunchifyGetPropertyValues {
	 
	public String getrestv2Values() throws IOException {
 
		String result = "";
		Properties prop = new Properties();
		String propFileName = "config.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
 
		// get the property value and print it out
		String restv2 = prop.getProperty("restv2");
 
		result = restv2;
		return result;
	}
	
	public String getrestv1Values() throws IOException {
		 
		String result = "";
		Properties prop = new Properties();
		String propFileName = "config.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
 
		// get the property value and print it out
		String restv1 = prop.getProperty("restv1");
 
		result = restv1;
		return result;
	}
}