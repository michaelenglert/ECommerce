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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.appdynamicspilot.util.PropertyLoader;
//import NET.webserviceX.www.GlobalWeatherSoapProxy;
//import NET.webserviceX.www.LengthUnitSoapProxy;
//import NET.webserviceX.www.Lengths;
//import NET.webserviceX.www.PeriodictableSoapProxy;

public class BackendUtil {

	String driverClass = PropertyLoader.getProperty("DB_Driver");
	String url = PropertyLoader.getProperty("DB_URL");
	String uName = PropertyLoader.getProperty("DB_Username");
	String pWord = PropertyLoader.getProperty("DB_Password");
	String table = PropertyLoader.getProperty("table");
	String httpCallsCount = PropertyLoader.getProperty("httpCallsCount");
	
	private static String generateData()
	{
		String data = String.valueOf(Math.random());
		for (int i = 0; i < 450; i++)
		{
			data = data + "a";
		}
		return data;
	}
	
	public void callCEP(int i, int delay){
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String callDatabase(){
		String msg = null;
		Connection connection = null;
		try
		{
			Thread.sleep(500);
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url,uName,pWord);
			System.out.println("connected");
				String sql = "SELECT * FROM " + table;
				Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("Query :: " + sql);
			int count =0;
			System.out.println("Case 1: Without Setting Fetch Size ");

            long t1 = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis());
			while(rs.next()){
				count++;
		//		callDB();
			}
			  System.out.println("Time to iterate ResultSet -> " + (System.currentTimeMillis() - t1));

              int fetchSize = 100;

              System.out.println("Case 2: With Setting Fetch Size value ->" + fetchSize);

              rs = statement.executeQuery(sql);
              // rs.setFetchSize(100);

              t1 = System.currentTimeMillis();

              System.out.println(System.currentTimeMillis());

              rs.setFetchSize(fetchSize);

              System.out.println("Time to iterate ResultSet -> " + (System.currentTimeMillis() - t1));

			msg += "\n" + count;	
			System.out.println("Total resultsets  ::  " + rs);
			statement.close();
			connection.close();
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return msg;
	}
	
	public void callHttpClient()
	  {
	    try
	    {
	    	int lmt = Integer.parseInt(httpCallsCount);
	    	String addr = null;
	      for (int i = 1; i <= lmt; ++i) {
	    	addr =  PropertyLoader.getProperty("address"+i);
	        URL serverAddress = new URL(addr);
	        HttpURLConnection connection = null;
	        connection = (HttpURLConnection)serverAddress.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setDoOutput(true);
	        connection.setReadTimeout(10000);
	        connection.connect();

	        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	        System.out.println(">>>>>>>>>>>>>JAVA HTTP CLIENT REPONSE STARTS FOR " + addr + "<<<< < <<<<<<<<<<<");
	        System.out.println(">>>>>>>>>>>>>JAVA HTTP CLIENT REPONSE ENDS " + addr + "<<<< < <<<<<<<<<<<");
	        connection.disconnect();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
//	public void callWebService(){
//		try {
//
//			//Client1
//			String endpoint = "http://www.webservicex.net/length.asmx?WSDL";
//			System.out.println("EndPoint : "+endpoint);
//			Double value = (double) new Double((Math.random()*10)).intValue();
//			LengthUnitSoapProxy proxy = new LengthUnitSoapProxy(endpoint);
//			Double result = proxy.changeLengthUnit(value, Lengths.Meters, Lengths.Centimeters);
//
//			//Client2
//			endpoint = "http://www.webservicex.net/globalweather.asmx?WSDL";
//			GlobalWeatherSoapProxy proxy2 = new GlobalWeatherSoapProxy(endpoint);
//			String result2 = proxy2.getWeather("Delhi", "India");
//			String result3 = proxy2.getCitiesByCountry("India");
//
//			//Client3
//			endpoint = "http://www.webservicex.net/periodictable.asmx?WSDL";
//			PeriodictableSoapProxy proxy3 = new PeriodictableSoapProxy(endpoint);
//			String result4 = proxy3.getAtoms();
//			String result5 = proxy3.getAtomicNumber("Calcium");
//			String result6 = proxy3.getAtomicWeight("Calcium");
//			String result7 = proxy3.getElementSymbol("Calcium");
//
//
//			String msg = "Axis1 Webservice called succsessfully and the results are "
//							+ "</br>" + result +"</br>" + result2 + "</br>" + result3
//							+ "</br>" + result4 +"</br>" + result5 + "</br>" + result6
//							+ "</br>" + result7;
//			System.out.println(msg);
//
//
//		} catch (Exception e) {
//			System.err.println(e.toString());
//		}
//	}
}
