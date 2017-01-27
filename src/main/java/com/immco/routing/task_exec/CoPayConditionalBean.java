package com.immco.routing.task_exec;

import java.util.HashMap;
import java.util.Map;

public class CoPayConditionalBean implements ConditionalTaskExecIFace {
	private Integer customerPay=0;
	@Override
	public Map<String, Object> getParamMap() {
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("CUSTOMER_PAY", customerPay);
		return paramMap;
	}

}
