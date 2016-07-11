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

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author Vikash
 */
public class MailSender extends Authenticator{


    private Session mailSession;
    protected PasswordAuthentication authentication;
    private String host = null;
    private String user = null;
    private String domainName = null;
    private String password = null;
    private String fromAddress = null;
    private String port = null;
    private String debug = "false";
    
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
   /**
    * Init & create a mail session.
    * 
    * @param host
    * @param user
    * @param password
    * @param port
    */  
    public void init(String host, String user, String password, String port) {
    	this.host = host;
        this.user = user;
        this.password = password;
        this.port = port;

    	mailSession = createExtMailSession();
    }

    /**
     * Create the the mail session and authenticate.
     * 
     * @return Session
     */
    private Session createExtMailSession() {
        Session session = null;
        Properties props = new Properties();

        authentication = new PasswordAuthentication(user, password);

        props.put("mail.user", user);
        props.put("mail.host", host);
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", port);
        props.put("mail.debug", debug);
        props.put("mail.smtp.auth", "true");
        session = Session.getInstance(props, this);
        return session;
    }

    /**
     * Return an authentication object.
     */
    public PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }
   
    /**
     * use this method to send text and html mail
     * @param to
     * @param textData
     * @param htmlData
     * @param subject
     * @param fromid
     * @return
     * @throws MessagingException
     */
    public String welcomeEmail(String to,String textData,String htmlData,String subject,String fromid)throws MessagingException{
    	String msgId = "";

        try {
            if(subject==null || "".equals(subject.trim())){
            	return "";
            }
        	String fromAddress = fromid;
            MimeMessage message = new MimeMessage(mailSession);
            message.addRecipients(Message.RecipientType.TO, to);
            //msg.addRecipients(Message.RecipientType.BCC, to);
            message.setFrom(new InternetAddress(fromAddress));
            //Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");
            //Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(textData);
            //Add the text part to the multipart
            multipart.addBodyPart(messageBodyPart);
            //Create the html part
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlData, "text/html; charset=UTF-8;");
            //Add html part to multi part
            multipart.addBodyPart(messageBodyPart);
            //Associate multi-part with message
            message.setContent(multipart);
            //set subject
            message.setSubject(subject);
            //Send message
            Transport.send(message);
           // Transport.send(msg);
            msgId = message.getMessageID();

        } catch (MessagingException e) {
        	throw e;
        } catch (Exception e) {
        	throw new MessagingException(e.getMessage());
        }
        return msgId;
    }
    
    public void sendemail(String toUser,String htmlBody,String textBody,String subject){
          if(!host.equals("localhost")){
        	  this.init(this.host,this.user,this.password,this.port);
          }
          try{
        	  this.welcomeEmail(toUser,textBody,htmlBody,subject,"vikash@appdynamics.com");
          }
          catch (Exception e) {
        	  e.printStackTrace();
          }
    }
}
