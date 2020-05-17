package com.xyz.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private Long createdAt;
	private Long updatedAt;
	private Double availableCredits;

}
