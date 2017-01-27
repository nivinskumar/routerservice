package com.immco.db.remote;

public class ConstructionServiceConfig
{
    private int dbServicePort = 8080;
    private String dbServiceCtx = "/dbservice";
    private String dbServiceProtocol = "http://";
    private String dbServiceIp = "127.0.0.1";
    private String baseUrl="";
    
    private String securityUserName;
    private String securityPassword;

    public String getSecurityUserName()
    {
        return securityUserName;
    }

    public String getSecurityPassword()
    {
        return securityPassword;
    }

    public void setSecurityUserName(String securityUserName)
    {
        this.securityUserName = securityUserName;
    }

    public void setSecurityPassword(String securityPassword)
    {
        this.securityPassword = securityPassword;
    }

    public int getDbServicePort()
    {
	return dbServicePort;
    }

    public String getDbServiceCtx()
    {
	return dbServiceCtx;
    }

    public String getDbServiceProtocol()
    {
	return dbServiceProtocol;
    }

    public String getDbServiceIp()
    {
	return dbServiceIp;
    }

    public void setDbServicePort(int dbServicePort)
    {
	this.dbServicePort = dbServicePort;
    }

    public void setDbServiceCtx(String dbServiceCtx)
    {
	this.dbServiceCtx = dbServiceCtx;
    }

    public void setDbServiceProtocol(String dbServiceProtocol)
    {
	this.dbServiceProtocol = dbServiceProtocol;
    }

    public void setDbServiceIp(String dbServiceIp)
    {
	this.dbServiceIp = dbServiceIp;
    }

    public String getBaseUrl()
    {
	return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
	this.baseUrl = baseUrl;
    }
}
