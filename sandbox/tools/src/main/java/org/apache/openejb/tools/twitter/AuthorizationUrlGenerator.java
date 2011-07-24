/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.openejb.tools.twitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.springframework.core.io.ClassPathResource;

public class AuthorizationUrlGenerator {
	static Properties retweetToolProperties;
	static OAuthConsumer consumer;
	static OAuthProvider provider;

	public static void main(String[] args) {
		retweetToolProperties = getTwitterAppProperties();
		intializeOAuthConsumerAndProvider();
		getAuthorizationUrl();

	}

	private static Properties getTwitterAppProperties() {

		Properties retweetAppProperties = new Properties();
		try {
			ClassPathResource retweetToolPropertiesFile = new ClassPathResource(
			"RetweetTool.properties");
			retweetAppProperties.load(retweetToolPropertiesFile
					.getInputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print("Using the following properties: ");
		System.out.print("---------------------------------");
		retweetAppProperties.list(System.out);
		return retweetAppProperties;

	}
	
	private static void intializeOAuthConsumerAndProvider() {

		consumer = new DefaultOAuthConsumer(
				retweetToolProperties.getProperty("retweetApp.consumer.key"),
				retweetToolProperties
				.getProperty("retweetApp.consumerSecret.key"));

		provider = new DefaultOAuthProvider(
				"http://twitter.com/oauth/request_token",
				"http://twitter.com/oauth/access_token",
				"http://twitter.com/oauth/authorize");

	}

	
	private static void getAuthorizationUrl() {

		try {
			getAccessAuthorizationURL();
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}

	}

	private static void getAccessAuthorizationURL()
	throws OAuthMessageSignerException, OAuthNotAuthorizedException,
	OAuthExpectationFailedException, OAuthCommunicationException {

		String authUrl;
		authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
		System.out.println("#####################");
		System.out.println("Paste the below URL in the browser and authorize");
		System.out.println(authUrl);
		System.out.println("#####################");

	}

	
	
}
