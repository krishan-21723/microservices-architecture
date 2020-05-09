package com.xyz.userservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserRequest {

	private String name;
	private String email;
}
