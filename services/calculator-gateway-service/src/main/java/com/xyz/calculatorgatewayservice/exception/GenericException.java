package com.xyz.calculatorgatewayservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7827010059356269623L;

	private String code;

	private String msg;

}