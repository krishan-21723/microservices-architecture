package com.xyz.userservice.response;

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
