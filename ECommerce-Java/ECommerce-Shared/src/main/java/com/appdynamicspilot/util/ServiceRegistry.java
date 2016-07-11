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

package com.appdynamicspilot.util;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServiceRegistry {

	private static ApplicationContext context = null;
	public static final String CONENZA_PERSISTENCE ="dbProvider";

	static{
		//String[] file = {"../applicationContext-jdbc.xml", "../applicationContext-service.xml", "../applicationContext-resources.xml"}; 
		String[] file = {"D:/myTomcat/tomcat/webapps/appdynamicspilot/WEB-INF/applicationContext.xml","D:/myTomcat/tomcat/webapps/appdynamicspilot/WEB-INF/applicationContext-jms.xml"};
		initialize(file);
	}
	
	public static Object getBean(final String key) {
		//String[] file = {"../applicationContext-jdbc.xml", "../applicationContext-service.xml", "../applicationContext-resources.xml"}; 
		//String[] file = {"applicationContext-jdbc.xml", "applicationContext-service.xml", "applicationContext-resources.xml"};
		//initialize(file);
		return get(key);
	}
	
	public static void initialize(final String[] file) {
		if (context == null) {
			context = new FileSystemXmlApplicationContext(file);
		}
	}

//	public static void initialize() {
//		if (context == null) {
//			context = SpringContextSingleton.getInstance().getContext();
//		}
//	}

	public static Object get(final String key) {
		return context.getBean(key);
	}

	public static String getMessage(final String key, final Locale locale) {
		return context.getMessage(key, new Object[0], locale);
	}
	public static String getMessage(final String key,final Object[] args, final Locale locale) {
		return context.getMessage(key, args, locale);
	}
}
