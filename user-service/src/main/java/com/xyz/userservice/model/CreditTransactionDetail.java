package com.xyz.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "credit_transaction_detail")
public class CreditTransactionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "transactionType")
	private String transactionType;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "purpose")
	private String purpose;
	
	@Column(name = "created_at")
	private Long createdAt;
}
