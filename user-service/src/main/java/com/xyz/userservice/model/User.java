package com.xyz.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.xyz.userservice.constants.QueryConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = QueryConstants.UserQueries.UPDATE_USER_AVAILABLE_CREDITS, query = QueryConstants.UserQueries.UPDATE_USER_AVAILABLE_CREDITS_QRY),
	@NamedQuery(name = QueryConstants.UserQueries.GET_USER_BY_EMAIL, query = QueryConstants.UserQueries.GET_USER_BY_EMAIL_QRY)
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "created_at")
	private Long createdAt;
	
	@Column(name = "updated_at")
	private Long updatedAt;
	
	@Column(name = "available_credits")
	private Double availableCredits;

}
