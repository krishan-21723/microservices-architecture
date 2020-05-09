package com.xyz.subtractionservice.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>();

	public static final String SU001 = "SU001";

	static {
		ERROR_CODE_MAP.put(SU001, "Either of the operands cannot be null");
	}
	
	public static String getErrorMsg(String errorCode) {
		return ERROR_CODE_MAP.get(errorCode);
	}
}
