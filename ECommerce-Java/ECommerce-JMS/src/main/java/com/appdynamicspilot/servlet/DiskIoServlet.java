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

package com.appdynamicspilot.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;

import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

/**
 * User: jayesh
 * Date: Dec 28, 2009
 */
public class DiskIoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside disk io servlet ");
        int dataAmount = request.getParameter("data") == null ? 1 : Integer.valueOf(request.getParameter("data"));
        System.out.println("Data written will be " + (dataAmount * 1024));
        response.getWriter().println("Data written will be " + (dataAmount * 1024));
        writeFile(dataAmount);
        response.getWriter().println("File successfully written");
    }

    private void writeFile(int dataAmount) {
        FileOutputStream fOut;
        FileChannel fChan;
        ByteBuffer mBuf;
        System.out.println("Write file Called");
        try {
            fOut = new FileOutputStream("test.txt");
            fChan = fOut.getChannel();
            int databytes = 1024 * dataAmount;
            mBuf = ByteBuffer.allocateDirect(databytes);
            for (int i = 0; i < databytes; i++)
                mBuf.put((byte) ('A' + i));
            mBuf.rewind();
            fChan.write(mBuf);
            fChan.close();
            fOut.close();
        } catch (IOException exc) {
            System.out.println(exc);

        }
    }
}
