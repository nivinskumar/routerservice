package com.immco.routing.messaging.common;

import java.math.BigDecimal;

//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class PingMsg extends AbstractMsg {

	private Integer cwsId;
	private BigDecimal constructionType;

	public PingMsg(String routingKey) {
		super(routingKey,true);
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

}
