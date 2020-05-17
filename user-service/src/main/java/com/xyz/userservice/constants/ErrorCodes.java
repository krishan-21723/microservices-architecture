package com.xyz.userservice.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>();

	public static final String US001 = "US001";
	public static final String US002 = "US002";
	public static final String US003 = "US003";
	public static final String US004 = "US004";
	public static final String US005 = "US005";
	public static final String US006 = "US006";
	public static final String US007 = "US007";

	static {
		ERROR_CODE_MAP.put(US001, "Facing technical issue");
		ERROR_CODE_MAP.put(US002, "User doesn't exists");
		ERROR_CODE_MAP.put(US003, "User already exists");
		ERROR_CODE_MAP.put(US004, "User email can't be empty");
		ERROR_CODE_MAP.put(US005, "Credits can't be null");
		ERROR_CODE_MAP.put(US006, "Credits can't be negative");
		ERROR_CODE_MAP.put(US007, "UserId can't be null");
	}
	
	public static String getErrorMsg(String errorCode) {
		return ERROR_CODE_MAP.get(errorCode);
	}
}
