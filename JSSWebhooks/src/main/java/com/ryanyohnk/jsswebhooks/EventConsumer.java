package com.ryanyohnk.jsswebhooks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.jamfsoftware.eventnotifications.JAMFEventNotificationParameter;

public class EventConsumer {
	private EventQueue queue;
	private ExecutorService executor;
	private boolean running = true;
	private EventHttpPublisher publisher;
	private static Logger logger = Logger.getLogger(EventConsumer.class);

	public EventConsumer() {
		this.queue = new EventQueue();
		this.publisher = new EventHttpPublisher();
		
		final int numThreads = PropertyUtils.getNumThreads();
		this.executor = Executors.newFixedThreadPool(numThreads);
		
		for(int i = 0; i < numThreads; i++){
			executor.submit(new Worker());
		}
	}

	
	private class Worker implements Runnable {
		public void run() {
			while(running && !Thread.interrupted()){
				try {
					JAMFEventNotificationParameter param = queue.get();
					 publisher.publish(param);
				} catch (InterruptedException e) {
					logger.error(e, e);
					Thread.currentThread().interrupt();
				}
			}
		}
		
	}
}
