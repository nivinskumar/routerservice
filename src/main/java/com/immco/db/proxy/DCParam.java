package com.immco.db.proxy;

/**
 * @author shajeelawrence
 * Data collector parameters.  Use this for communicating to the DB Service for persistence
 */
import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.immco.db.model.IPersistable;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("rawtypes")
public class DCParam implements Serializable {
    private static final long serialVersionUID = 8030530065230952338L;
    private static final String GENERIC_CTX = "GENERIC_CTX";
    private HashMap<String, Object> pMap = new HashMap<>();
    private String ctx = GENERIC_CTX;
    private HashMap<Class, Object> iPersistableMap = new HashMap<>();

    public DCParam() {

    }

    public DCParam(String attrName, Object attrVal, String ctx) {
	this.pMap.put(attrName, attrVal);
	this.ctx = ctx;
    }

    public DCParam(HashMap<String, Object> pMap, String ctx) {
	this.pMap = pMap;
	this.ctx = ctx;
    }

    public DCParam(String ctx) {
	this.ctx = ctx;
    }

    public HashMap<String, Object> getpMap() {
	return this.pMap;
    }

    public String getCtx() {
	return this.ctx;
    }

    public void setCtx(String ctx) {
	this.ctx = ctx;
    }

    public void addIPersistable(Class claszz, IPersistable iPersistable) {
	this.iPersistableMap.put(claszz, iPersistable);
    }

    public HashMap<Class, Object> getIPersistableMap() {
	return this.iPersistableMap;
    }

}
