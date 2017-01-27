package com.immco.common;

import java.io.Serializable;

/**
 * Data Carrier for Database
 * 
 * @author shajeelawrence 08042016
 *
 */
public class DBDC implements Serializable {

	private static final long serialVersionUID = -7439881299204267503L;
	
	private String successMsg;
	private String errorMsg;
	private String infoMsg;
	private boolean success;

	// mandatory constructor
	public DBDC() {

	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
		this.success=true;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		this.success=false;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}