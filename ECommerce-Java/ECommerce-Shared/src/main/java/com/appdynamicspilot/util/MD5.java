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

import java.security.MessageDigest;

import org.springframework.context.ApplicationContextException;


/**
 * Provides MD5 Hashing
 *
 * <p>
 * <b>Example:</b> 
 * <br />
 * <code>
 * &nbsp;&nbsp;String hashedString = MD5.hash("hash me");
 * </code>
 * <br /><br />
 * You can run it from the command line as follows:
 * <br />
 * <code>
 * <b>java</b> com.aurigalogic.crypto.util.MD5 &lt;hash_me&gt;
 * </code>
 * </p>
 * @author <a href="mailto:ngupte@aurigalogic.com">Nikhil Gupte</a>
 * @version $Revision: 11 $ $Date: 2005-10-02 00:57:16 +0530 (Sun, 02 Oct 2005) $ 
 */
public class MD5 {

	/**
	 * Returns the hashed value of <code>clear</code>.
	 */
	public static String hash(String clear) throws Exception {
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clear.getBytes());

		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i]&255; // unsigned conversion
			if (u<16) {
				h.append("0"+Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
		} catch (Exception e) {
			throw new Exception(e);	
		}
	}

	/**
	 * Command line tool to get MD5 hash of strings.
	 */
	public static void main(String args[]) {
		try {
		} catch (Exception e) {
			usage();	
		}
	}

	/*
	 * Outputs usage info. To be used w/ the main method.
	 */
	private static void usage() {
	}
}
