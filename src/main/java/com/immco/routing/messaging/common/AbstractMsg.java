package com.immco.routing.messaging.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AbstractMsg {

	private String routingKey;
	private Long sendTime;
	private Long receivedTime;
	private boolean pingMsg;
	private String context;
	private HashMap<String, String> kvPair;

	public AbstractMsg() {

	}

	public AbstractMsg(String routingKey, String context) {
		this.routingKey = routingKey;
		this.context = context;
	}

	public AbstractMsg(String routingKey, String context, HashMap<String, String> kvPair) {
		this.routingKey = routingKey;
		this.context = context;
		this.kvPair = kvPair;
	}

	public AbstractMsg(String routingKey, boolean pingMsg) {
		this.routingKey = routingKey;
		this.pingMsg = pingMsg;
	}

	public String getRoutingKey() {
		return this.routingKey;
	}
	
	public void setRoutingKey(String routingKey){
		this.routingKey = routingKey;
	}

	public long getSendTime() {
		return getSTime();
	}

	public Date getSendTimeAsDate() {
		return new Date(getSTime());
	}

	public String getSendTimeInMillis() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date(getSTime()));
	}

	public long getReceivedTime() {
		return getRTime();
	}

	public Date getReceivedTimeAsDate() {
		return new Date(getRTime());
	}

	public String getReceivedTimeInMillis() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date(getRTime()));
	}

	private Long getSTime() {
		if (this.sendTime == null)
			this.sendTime = new Date().getTime();
		return this.sendTime;
	}

	private Long getRTime() {
		if (this.receivedTime == null)
			this.receivedTime = new Date().getTime();
		return this.receivedTime;
	}

	public String getRxLogMsg() {
		return "Rx Routing Key=" + this.routingKey + " at " + getReceivedTimeInMillis();
	}

	public String getTxLogMsg() {
		return "Tx Routing Key=" + this.routingKey + " at " + getSendTimeInMillis();
	}

	public boolean isPingMsg() {
		return pingMsg;
	}

	public void setPingMsg(boolean pingMsg) {
		this.pingMsg = pingMsg;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public HashMap<String, String> getKvPair() {
		return kvPair;
	}

	public void setKvPair(HashMap<String, String> kvPair) {
		this.kvPair = kvPair;
	}

}
