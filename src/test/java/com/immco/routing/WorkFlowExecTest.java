package com.immco.routing;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.immco.common.RouterConstants;
import com.immco.db.service.exec.WorkFlowExecutorService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class WorkFlowExecTest {

	@Autowired
	WorkFlowExecutorService wfExecService;

	@Test
	public void testWorkFlowExec() {
		try {
			wfExecService.createWorkFlowExec(new BigDecimal(1), 62523, RouterConstants.Phase.SERVICEABILITY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
