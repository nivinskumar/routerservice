
package com.immco.routing.utils;

/**
 * Enumeration LogCtx
 */
public enum LogCtx {

	ROUTING_PING("ROUTING_PING"), WORKFLOW_CACHE("WORKFLOW_CACHE"), ROUTING_SERVICE("ROUTING_SERVICE"), ROUTING_MSG("ROUTING_MSG"), JSON_PARSER("JSON_PARSER");
	/**
	 * Field value.
	 */
	private final java.lang.String value;

	/**
	 * Field enumConstants.
	 */
	private static final java.util.Map<java.lang.String, LogCtx> enumConstants = new java.util.HashMap<java.lang.String, LogCtx>();

	static {
		for (LogCtx c : LogCtx.values()) {
			LogCtx.enumConstants.put(c.value, c);
		}
	};

	private LogCtx(final java.lang.String value) {
		this.value = value;
	}

	/**
	 * Method toString.
	 * 
	 * @return the value of this constant
	 */
	public java.lang.String toString() {
		return this.value;
	}

	/**
	 * Method value.
	 * 
	 * @return the value of this constant
	 */
	public java.lang.String value() {
		return this.value;
	}

}
