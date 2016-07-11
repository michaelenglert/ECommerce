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


public class MailService {
	
	private MailSender mailerService;
	private static final Logger log = Logger.getLogger(MailService.class);

	public MailSender getMailerService() {
		return mailerService;
	}

	public void setMailerService(MailSender mailerService) {
		this.mailerService = mailerService;
	}


	public void sendOrderMail(String email, String orderId) {
		log.info("SENDING MAIL TO :"+email);
		String htmlBody = "<html><head><title></title></head><body> <b>Thank you for placing your order(s) with appdynamics. The following are the orders that have been placed by you:  </b> <br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;"+orderId+"<br/> <br/><br/><br/>Thanks,<br/> AppDynamics";
		String subject = "[Appdynamics] Attention! Order Confirmation";
		mailerService.sendemail(email, htmlBody, "Your transaction id "+orderId, subject);
	}
}
