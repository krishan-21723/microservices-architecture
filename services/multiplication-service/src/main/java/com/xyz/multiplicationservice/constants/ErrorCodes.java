package com.xyz.multiplicationservice.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>();

	public static final String MU001 = "MU001";

	static {
		ERROR_CODE_MAP.put(MU001, "Either of the operands cannot be null");
	}
	
	public static String getErrorMsg(String errorCode) {
		return ERROR_CODE_MAP.get(errorCode);
	}
}
