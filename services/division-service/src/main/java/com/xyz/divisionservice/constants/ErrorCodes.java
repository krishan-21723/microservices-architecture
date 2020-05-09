package com.xyz.divisionservice.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>();

	public static final String DV001 = "DV001";
	public static final String DV002 = "DV002";

	static {
		ERROR_CODE_MAP.put(DV001, "Either of the operands cannot be null");
		ERROR_CODE_MAP.put(DV002, "Can't divide by 0");
	}
	
	public static String getErrorMsg(String errorCode) {
		return ERROR_CODE_MAP.get(errorCode);
	}
}
