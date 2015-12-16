package com.ryanyohnk.jsswebhooks;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jamfsoftware.eventnotifications.JAMFEventNotificationParameter;

public class EventHttpPublisher {
	private Gson gson;
	private static Logger logger = Logger.getLogger(EventHttpPublisher.class);
	
	public EventHttpPublisher(){
		gson = new Gson();
	}
	
	public void publish(JAMFEventNotificationParameter param) {
		try {
			Request.Post(PropertyUtils.getPostURL()).bodyString(gson.toJson(param), ContentType.APPLICATION_JSON).execute();
		} catch (ClientProtocolException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		}
	}

}
