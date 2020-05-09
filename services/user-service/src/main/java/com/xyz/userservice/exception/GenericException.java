package com.xyz.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7827010059356269623L;

	private String code;

	private String msg;

}