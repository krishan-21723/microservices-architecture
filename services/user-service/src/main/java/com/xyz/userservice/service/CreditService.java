package com.xyz.userservice.service;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.request.AddCreditRequest;
import com.xyz.userservice.request.RedeemCreditRequest;

public interface CreditService {

	public Integer addCredit(AddCreditRequest request) throws GenericException;

	public Integer validateUserAndAddCredit(AddCreditRequest request) throws GenericException;

	public Integer redeemCredit(RedeemCreditRequest request) throws GenericException;

	public Integer validateUserAndRedeemCredit(RedeemCreditRequest request) throws GenericException;

}
