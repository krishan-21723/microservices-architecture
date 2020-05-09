package com.xyz.calculatorgatewayservice.constants;

import java.util.HashMap;
import java.util.Map;

import com.xyz.calculatorgatewayservice.enums.OperationType;

public class AppConstants {

	public static final String CREDIT_TOP_UP = "Credit TopUp";
	
	private static final Map<OperationType, Double> opercationCostMap = new HashMap<>();
	private static final Map<OperationType, String> opercationPurposeMap = new HashMap<>();
	static {
		opercationCostMap.put(OperationType.ADD, 1d);
		opercationCostMap.put(OperationType.SUBTRACT, 1d);
		opercationCostMap.put(OperationType.MULTIPLY, 2d);
		opercationCostMap.put(OperationType.DIVIDE, 1.5d);
	}

	public static Double getOpercationCost(OperationType operationType) {
		return opercationCostMap.get(operationType);
	}

	static {
		opercationPurposeMap.put(OperationType.ADD, "Addition Service");
		opercationPurposeMap.put(OperationType.SUBTRACT, "Subtraction Service");
		opercationPurposeMap.put(OperationType.MULTIPLY, "Multiplication Service");
		opercationPurposeMap.put(OperationType.DIVIDE, "Division Service");
	}

	public static String getOpercationPurpose(OperationType operationType) {
		return opercationPurposeMap.get(operationType);
	}
	
	public static class OperationTypeValue {
		public static final String ADDITION = "addOp";
		public static final String SUBTRACTION = "subOp";
		public static final String MULTIPLICATION = "mulOp";
		public static final String DIVISION = "divOp";
	}

}
