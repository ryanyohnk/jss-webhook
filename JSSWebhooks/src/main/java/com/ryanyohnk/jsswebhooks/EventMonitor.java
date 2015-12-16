package com.ryanyohnk.jsswebhooks;

import com.jamfsoftware.eventnotifications.JAMFEventNotificationMonitor;
import com.jamfsoftware.eventnotifications.JAMFEventNotificationMonitorResponse;
import com.jamfsoftware.eventnotifications.JAMFEventNotificationParameter;
import com.jamfsoftware.eventnotifications.events.EventType.EventTypeIdentifier;

public class EventMonitor implements JAMFEventNotificationMonitor {
	private EventQueue queue;
	
	public EventMonitor(){
		queue = new EventQueue();
	}
	
	public JAMFEventNotificationMonitorResponse eventOccurred(JAMFEventNotificationParameter arg0) {
		queue.submit(arg0);
		return new JAMFEventNotificationMonitorResponse(this);
	}

	public boolean isRegisteredForEvent(EventTypeIdentifier arg0) {
		return true;
	}

}
