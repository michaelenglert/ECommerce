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

package com.appdynamicspilot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;


public enum MemoryLeakService {
	instance;

	public static final int DEFAULT_CLEAR_PERCENT = 90;

	private static final Logger log = Logger.getLogger(MemoryLeakService.class);
	private AtomicInteger clearPercent = new AtomicInteger(
			DEFAULT_CLEAR_PERCENT);
	private List<byte[]> list = new ArrayList<byte[]>();

	/**
	 * Increase the memory by objectCount with each object size of
	 * objectSizeInByte.
	 * 
	 * This service clears the memory if clearPercentThreshold is met after
	 * memory is increased.
	 * 
	 * @param objectCount
	 * @param objectSizeInByte
	 */
	public void increase(int objectCount, int objectSizeInByte) {
		if (objectCount <= 0) {
			throw new IllegalArgumentException("Invalid objectCount");
		}
		if (objectSizeInByte <= 0) {
			throw new IllegalArgumentException("Invalid objectSizeInByte");
		}

		boolean isDebug = log.isDebugEnabled();
		Runtime runtime = Runtime.getRuntime();
		
		for (int i = 0; i < objectCount; i++) {
			byte[] copy = new byte[objectSizeInByte];
			synchronized (this.list) {
				this.list.add(copy);
			}

			long freeMemory = runtime.freeMemory();
			long totalMemory = runtime.totalMemory();
			double freePercent = ((double) freeMemory / (double) totalMemory) * 100;
			if (isDebug) {
				log.debug("Free memory: " + freeMemory + "; Total Memory: "
						+ totalMemory);
			}

			if (freePercent < (double) (100 - this.clearPercent.get())) {
				if (isDebug) {
					log.debug("Clearing memory leak");
				}
				this.clear();
			}
		}
	}

	/**
	 * clear all object from this service
	 */
	public void clear() {
		synchronized (this.list) {
			this.list.clear();
		}
	}

	/**
	 * Get the current count of object
	 * 
	 * @return
	 */
	public int getSize() {
		synchronized (this.list) {
			return this.list.size();
		}
	}

	public int getClearPercent() {
		return this.clearPercent.get();
	}

	/**
	 * Setting the threshold to clear the data structure preventing crashing the
	 * JVM
	 * 
	 * @param clearPercent
	 */
	public void setClearPercent(int clearPercent) {
		if (clearPercent < 0 || clearPercent > 100) {
			throw new IllegalArgumentException(
					"Invalid clearPercent; Expecting value between 0 to 99");
		}
		this.clearPercent.set(clearPercent);
	}

}
