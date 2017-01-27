package com.immco.db.service.exec;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.exec.UdefCustomFormExecDao;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.proxy.DCParam;

@Service("udefCustomFormExecSerive")
@Transactional
public class UdefCustomFormExecServiceImpl implements UdefCustomFormExecService {

	@Autowired
	UdefCustomFormExecDao udefCustomFormExecDao;
	@Autowired
	DbLogger dbLogger;

	@Override
	public DBDC createOrUpdateCustomForm(UDefTaskExec taskExec) {
		DBDC dbdc = new DBDC();
		try {
			if (taskExec.getpKey() == null) {
				udefCustomFormExecDao.createOrUpdateUdefCustomFormExec(taskExec);
				dbdc.setSuccessMsg("Custom Form " + taskExec.getFormName() + " Created");
			} else {
				udefCustomFormExecDao.createOrUpdateUdefCustomFormExec(taskExec);
				dbdc.setSuccessMsg("Custom Form " + taskExec.getFormName() + " updated");
			}
			dbdc.setInfoMsg(taskExec.getpKey().toString());
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, UdefCustomFormExecServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public UDefTaskExec findUdefCustomFormExec(DCParam dcParam) {

		BigDecimal pKey = new BigDecimal(dcParam.getpMap().get(UDefTaskExec.PKEY).toString());
		UDefTaskExec udeftaskExec = udefCustomFormExecDao.findUDefCustomFormExecByPK(pKey);
		return udeftaskExec;
	}

}
