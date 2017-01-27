package com.immco.db.controller;

public class ControllerConstants {

	private final static String auditableUserId = "/{auditableUserId}";

	public static final class HUMANTASKCONTROLLER {
		public static final String BASE_URL = "/router/humantask";
		public static final String CREATE_OR_UPDATE_HUMANTASK = auditableUserId + "/createorupdatehumantask";
		public static final String FIND_HUMANTASK = auditableUserId + "/findhumantask";
		public static final String DELETE_HUMANTASK = auditableUserId + "/deletehumantask";
	}
}
