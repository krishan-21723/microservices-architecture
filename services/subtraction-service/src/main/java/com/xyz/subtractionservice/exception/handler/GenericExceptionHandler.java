package com.xyz.subtractionservice.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xyz.subtractionservice.exception.GenericException;
import com.xyz.subtractionservice.response.APIResponse;
import com.xyz.subtractionservice.response.ErrorResponse;
import com.xyz.subtractionservice.util.ResponseUtil;

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
