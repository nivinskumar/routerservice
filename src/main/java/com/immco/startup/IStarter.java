package com.immco.startup;

public interface IStarter {
	
	boolean start();

	boolean stop();
	
	boolean getStatus();
	
	String getServiceName();
}
