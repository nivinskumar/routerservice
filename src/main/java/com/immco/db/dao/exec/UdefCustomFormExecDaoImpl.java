package com.immco.db.dao.exec;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;

@Repository("udefCustomFormExecDao")
public class UdefCustomFormExecDaoImpl extends AbstractDao<BigDecimal, UDefTaskExec> implements UdefCustomFormExecDao {

	@Override
	public void createOrUpdateUdefCustomFormExec(UDefTaskExec taskExec) throws Exception {
		getSession().saveOrUpdate(taskExec);
	}

	@Override
	public UDefTaskExec findUDefCustomFormExecByPK(BigDecimal pKey) {
		
		return getByKey(pKey);
	}

}
