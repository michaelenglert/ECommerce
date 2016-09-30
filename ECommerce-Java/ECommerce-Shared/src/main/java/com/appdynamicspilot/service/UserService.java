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

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolationException;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Named
@ApplicationScoped
@Transactional
public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);
	@Inject
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

	public void updateProfile (User user) {
		getUserPersistence();
	}

	public boolean doesUsernameExist(String username) {
		User user = getMemberByLoginName(username);
		return (user != null);
	}

	public Exception register (User user) {
		return getUserPersistence().save(user);
	}


	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}
}
