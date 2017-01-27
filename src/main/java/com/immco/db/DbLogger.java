package com.immco.db;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.immco.common.DBDC;
import com.immco.common.JsonWrapper;
import com.immco.db.model.IPersistable;
import com.immco.routing.utils.LogCtx;
import com.immco.routing.utils.LoggerService;

@Service("dbLogger")
public class DbLogger {

	@Autowired
	private JsonWrapper jsonWrapper;

	public DbLogger() {

	}

	public void logCreate(Class<?> srcClass, IPersistable iPersistable) {
		iPersistable.toString();
	}

	public void logUpdate(Class<?> srcClass, IPersistable iPersistable) {

	}

	public void logDelete(Class<?> srcClass, IPersistable iPersistable) {

	}

	public void logInfo() {

	}

	public void logError(Exception error, Class<?> className, DBDC dbDc) {
		dbDc.setErrorMsg(error.getMessage());
		LoggerService.getInstance().error(LogCtx.ROUTING_SERVICE, error.getMessage(), className, error);
	}

	public String logErrorToJson(Exception e, Class<?> clazz) {
		LoggerService.getInstance().error(LogCtx.ROUTING_SERVICE, e.getMessage(), clazz, e);
		DBDC dbDC = new DBDC();
		Throwable rootCause = ExceptionUtils.getRootCause(e);
		dbDC.setErrorMsg(rootCause.getMessage());
		try {
			String jsonStr = jsonWrapper.toJSON(dbDC);
			return jsonStr;
		} catch (JsonProcessingException e1) {
			LoggerService.getInstance().error(LogCtx.JSON_PARSER, e.getMessage(), clazz, e);
		}

		return "<ERROR>";
	}
}
