package com.ryanyohnk.jsswebhooks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.jamfsoftware.eventnotifications.JAMFEventNotificationParameter;

public class EventQueue {
	private BlockingQueue<JAMFEventNotificationParameter> queue;
	
	public EventQueue(){
		queue = new LinkedBlockingQueue<JAMFEventNotificationParameter>();
	}
	
	public void submit(JAMFEventNotificationParameter p){
		queue.add(p);
	}
	
	public JAMFEventNotificationParameter get() throws InterruptedException{
		return queue.take();
	}
}
