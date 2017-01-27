package com.immco.routing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum LoggerService {
	instance;

	public static final LoggerService getInstance() {
		return instance;
	}

	public void debug(String message, Class<?> className) {
		Logger logger = LoggerFactory.getLogger(className);
		logger.debug(message);

	}

	public void info(LogCtx logCtx, String message, Class<?> className) {
		Logger logger = LoggerFactory.getLogger(className);
		StringBuffer sb = new StringBuffer("[ ").append(logCtx.value()).append(" ] ").append(message);
		logger.info(sb.toString());
	}

	public void warning(LogCtx logCtx, String message, Class<?> className) {
		Logger logger = LoggerFactory.getLogger(className);
		StringBuffer sb = new StringBuffer("[ ").append(logCtx.value()).append(" ] ").append(message);
		logger.warn(sb.toString());
	}

	public void warning(String message, Class<?> className) {
		Logger logger = LoggerFactory.getLogger(className);
		logger.warn(message);
	}

	public void error(LogCtx logCtx, String message, Class<?> className) {
		Logger logger = LoggerFactory.getLogger(className);
		StringBuffer sb = new StringBuffer("[ ").append(logCtx.value()).append(" ] ").append(message);
		logger.error(sb.toString());
	}

	public void error(LogCtx logCtx, String message, Class<?> className, Throwable e) {
		Logger logger = LoggerFactory.getLogger(className);
		StringBuffer sb = new StringBuffer("[ ").append(logCtx.value()).append(" ] ").append(message);
		logger.error(sb.toString(), e);
	}
}
