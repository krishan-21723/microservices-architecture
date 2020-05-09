package com.xyz.multiplicationservice.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xyz.multiplicationservice.exception.GenericException;
import com.xyz.multiplicationservice.response.APIResponse;
import com.xyz.multiplicationservice.response.ErrorResponse;
import com.xyz.multiplicationservice.util.ResponseUtil;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<APIResponse<Object>> genericException(GenericException exception) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(exception.getCode());
		error.setMsg(exception.getMsg());
		return ResponseUtil.getApiResponse(error);

	}
}
