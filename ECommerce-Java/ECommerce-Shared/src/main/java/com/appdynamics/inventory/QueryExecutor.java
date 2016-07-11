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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.appdynamics.jdbc.MConnection;

public class QueryExecutor {
    public static long SLEEP_TIME;

    public Connection getMockDBConnection(){
        Connection connection = null;
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            connection = new MConnection();
            System.out.println(">Got Mock DB Connection!");
        } catch(Exception se){
            System.err.println("Exception1:");
            se.printStackTrace();
        }
        return connection;
    }

    public void executeSimpleS(long sleep){
        SLEEP_TIME = sleep;
        Connection con = getMockDBConnection();
        if(con != null){
            try{
                Statement stmt = con.createStatement();
                String query = "SELECT * FROM user WHERE FIRST_NAME = 'sid'";
                long startT = System.currentTimeMillis();
                ResultSet rs2 = stmt.executeQuery(query);
                System.out.println("mock query took = "+(System.currentTimeMillis() - startT));
                if(rs2.next()){
                    System.out.println("This result is from mock statement");
                }
            }catch(Exception e ){
                System.err.println("Exception:");
                System.err.println(e.getMessage());
            }
        }
    }

    public void executeSimplePS(long sleep){
        SLEEP_TIME = sleep;
        Connection con = getMockDBConnection();
        if(con!=null){
            try{
                PreparedStatement pstat;
                String query = "insert into OrderRequest ( item_id, notes ) values ( ?,  ? )";
                pstat = con.prepareStatement(query);
                pstat.setLong(1, 3);
                pstat.setString(2, "Chronicles of Narnia");
                long startT = System.currentTimeMillis();
                ResultSet rset = pstat.executeQuery();
                System.out.println("mock query took = "+(System.currentTimeMillis() - startT));
                if(rset.next()){
                    System.out.println("This result is from mock prepare statement");
                }
            }catch(Exception e ){
                System.err.println("Exception:");
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }

    }

    public void executeSimpleCS(long sleep){
        SLEEP_TIME = sleep;
        Connection con = getMockDBConnection();
        if(con != null){
            try{
                CallableStatement callStmt = con.prepareCall("call MyFirstStoreProc()");
                long startT = System.currentTimeMillis();
                ResultSet rset = callStmt.executeQuery();
                System.out.println("mock query took = "+(System.currentTimeMillis() - startT));
                if(rset.next()){
                    System.out.println("This result is from mock callable statement");
                }
            }catch(Exception e ){
                System.err.println("Exception:");
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }

    public void executeSimlpeSReccursive(int n, long sleep){
        if(n == 0){
            return;
        }else{
            executeSimpleS(sleep);
            n = n - 1;
            executeSimlpeSReccursive(n, sleep);
        }
    }

    public void executeSimplePSReccursive(int n, long sleep){
        if(n == 0){
            return;
        }else{
            executeSimplePS(sleep);
            n = n - 1;
            executeSimplePSReccursive(n, sleep);
        }
    }

    public void executeSimpleCSReccursive(int n, long sleep){
        if(n == 0){
            return;
        }else{
            executeSimpleCS(sleep);
            n = n - 1;
            executeSimpleCSReccursive(n, sleep);
        }
    }

    public void executeRandomQuery(int n, long sleep){
        int simpleS = 0;
        int simplePS = 0;
        int simpleCS = 0;
        for(int i = 0; i < n; i++){
            double d = Math.random();
            if(d <= 0.4){
                executeSimpleS(sleep);
                simpleS = simpleS + 1;
            }else if(d < 0.7){
                executeSimplePS(sleep);
                simplePS = simplePS + 1;
            }else if(d >= 0.7){
                executeSimpleCS(sleep);
                simpleCS = simpleCS + 1;
            }
        }
        System.out.println("executeSimpleS     No. of calls = "+simpleS);
        System.out.println("executeSimplePS    No. of calls = "+simplePS);
        System.out.println("executeSimpleCS    No. of calls = "+simpleCS);
    }

    public void startExecution(){
        boolean loop = true;
        while(loop){
            int input = options();
            long sleep = 0;
            if(input != 0) sleep = sleepTime();
            switch(input){
                case 0: loop = false; break;
                case 1: executeSimpleS(sleep); break;
                case 2: executeSimplePS(sleep); break;
                case 3: executeSimpleCS(sleep); break;
                case 4: executeSimlpeSReccursive(inputForRecurrsion(), sleep); break;
                case 5: executeSimplePSReccursive(inputForRecurrsion(), sleep); break;
                case 6: executeSimpleCSReccursive(inputForRecurrsion(), sleep); break;
                case 7: executeRandomQuery(inputForRecurrsion(), sleep); break;
            }
        }
    }

    public int options(){
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try{
               String input = consoleIn.readLine().trim();
               int inputInt = Integer.parseInt(input);
               if(inputInt>= 0 && inputInt <= 7){
                    return inputInt;
               }else{
                    System.err.print("Please enter an integer from the list:");
               }
            }catch(IOException ie){
                ie.printStackTrace();
            }catch(NumberFormatException ne){
                System.err.print("Please enter an integer:");
            }
        }

    }

    public long sleepTime(){
        System.out.print("Please enter query execution time(millisec):");
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try{
               String input = (consoleIn.readLine()).trim();
               long inputInt = Long.parseLong(input);
               if(inputInt > 0){
                    return inputInt;
               }else{
                    System.err.print("Please enter an integer greater than zero:");
               }
            }catch(IOException ie){
                ie.printStackTrace();
            }catch(NumberFormatException ne){
                System.err.print("Please enter an integer:");
            }
        }
    }

    public int inputForRecurrsion(){
        System.out.print("Please enter number of queries:");
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try{
               String input = (consoleIn.readLine()).trim();
               int inputInt = Integer.parseInt(input);
               if(inputInt > 0){
                    return inputInt;
               }else{
                    System.err.print("Please enter an integer greater than zero:");
               }
            }catch(IOException ie){
                ie.printStackTrace();
            }catch(NumberFormatException ne){
                System.err.print("Please enter an integer:");
            }
        }
    }

}

