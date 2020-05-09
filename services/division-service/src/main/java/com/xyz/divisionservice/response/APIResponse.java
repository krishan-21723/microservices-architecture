package com.xyz.divisionservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> extends ResponseDTO {
	private T result;
	private ErrorResponse error;
}
