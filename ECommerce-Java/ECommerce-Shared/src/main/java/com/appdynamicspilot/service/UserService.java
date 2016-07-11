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

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.persistence.UserPersistence;
import com.appdynamicspilot.util.MD5;

import java.util.List;


public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);
	private UserPersistence userPersistence;

	public boolean validateMember(String email,String password){
		User existingMember = getMemberByLoginName(email);
		if(existingMember==null ||!existingMember.getPassword().equals(password)){
			return false;
		}
		return true;
	}

	public User getMemberByLoginName(String email) {
		return userPersistence.getMemberByEmail(email);
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public List<User> getAllUser() {
		return userPersistence.getAllUser();
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}
}
