package com.immco.routing.messaging.common;

import java.math.BigDecimal;

public class NewSurveyMsg extends AbstractMsg {

	private Integer cwsId;
	private BigDecimal constructionType;

	
	
	public NewSurveyMsg() {
		super();
	}

	public NewSurveyMsg(Integer cwsId, BigDecimal constructionType, String routingKey) {
		this.setCwsId(cwsId);
		this.setConstructionType(constructionType);
		super.setRoutingKey(routingKey);
	}

	public Integer getCwsId() {
		return cwsId;
	}

	public void setCwsId(Integer cwsId) {
		this.cwsId = cwsId;
	}

	public BigDecimal getConstructionType() {
		return constructionType;
	}

	public void setConstructionType(BigDecimal constructionType) {
		this.constructionType = constructionType;
	}

	@Override
	public String toString() {
		return "NewSurveyMsg [cwsId=" + cwsId + ", constructionType=" + constructionType + ", getRoutingKey()=" + getRoutingKey() + ", getSendTime()=" + getSendTime() + ", getSendTimeAsDate()="
				+ getSendTimeAsDate() + ", getSendTimeInMillis()=" + getSendTimeInMillis() + ", getReceivedTime()=" + getReceivedTime() + ", getReceivedTimeAsDate()=" + getReceivedTimeAsDate()
				+ ", getReceivedTimeInMillis()=" + getReceivedTimeInMillis() + ", getRxLogMsg()=" + getRxLogMsg() + ", getTxLogMsg()=" + getTxLogMsg() + ", isPingMsg()=" + isPingMsg()
				+ ", getContext()=" + getContext() + ", getKvPair()=" + getKvPair() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
