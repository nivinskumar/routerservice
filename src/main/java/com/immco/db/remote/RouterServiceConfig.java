package com.immco.db.remote;

public class RouterServiceConfig {
	private int routerServicePort = 9091;
	private String routerServiceCtx="/routerservice";
	private String routerServiceProtocol = "http://";
	private String routerServiceIp="127.0.0.1";
	private String baseUrl = "";

	private String securityUserName;
	private String securityPassword;

	public String getSecurityUserName() {
		return securityUserName;
	}

	public String getSecurityPassword() {
		return securityPassword;
	}

	public void setSecurityUserName(String securityUserName) {
		this.securityUserName = securityUserName;
	}

	public void setSecurityPassword(String securityPassword) {
		this.securityPassword = securityPassword;
	}

	public int getRouterServicePort() {
		return routerServicePort;
	}

	public String getRouterServiceCtx() {
		return routerServiceCtx;
	}

	public String getRouterServiceProtocol() {
		return routerServiceProtocol;
	}

	public String getRouterServiceIp() {
		return routerServiceIp;
	}

	public void setRouterServicePort(int routerServicePort) {
		this.routerServicePort = routerServicePort;
	}

	public void setRouterServiceCtx(String routerServiceCtx) {
		this.routerServiceCtx = routerServiceCtx;
	}

	public void setRouterServiceProtocol(String routerServiceProtocol) {
		this.routerServiceProtocol = routerServiceProtocol;
	}

	public void setRouterServiceIp(String routerServiceIp) {
		this.routerServiceIp = routerServiceIp;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
