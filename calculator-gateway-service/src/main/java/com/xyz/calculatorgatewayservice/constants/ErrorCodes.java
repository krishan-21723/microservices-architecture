package com.xyz.calculatorgatewayservice.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>();

	public static final String CA001 = "CA001";
	public static final String CA002 = "CA002";
	public static final String CA003 = "CA003";
	public static final String CA004 = "CA004";
	public static final String CA005 = "CA005";

	static {
		ERROR_CODE_MAP.put(CA001, "Facing technical issue");
		ERROR_CODE_MAP.put(CA002, "User doesn't exist");
		ERROR_CODE_MAP.put(CA003, "User doesn't have enough credits");
		ERROR_CODE_MAP.put(CA004, "Mapping Excpetion");
		ERROR_CODE_MAP.put(CA005, "Service Not available");
	}
	
	public static String getErrorMsg(String errorCode) {
		return ERROR_CODE_MAP.get(errorCode);
	}
}
