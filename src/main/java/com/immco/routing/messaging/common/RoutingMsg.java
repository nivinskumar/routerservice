package com.immco.routing.messaging.common;

import java.util.HashMap;

public class RoutingMsg extends AbstractMsg {

	public RoutingMsg(){
		super();
	}
	
	public RoutingMsg(String routingKey, String context, HashMap<String, String> kvPair) {
		super(routingKey, context, kvPair);
	}

	public RoutingMsg(String routingKey, String context) {
		super(routingKey, context);
	}

	public RoutingMsg(String routingKey, boolean pingMsg) {
		super(routingKey, pingMsg);
	}
	
}
