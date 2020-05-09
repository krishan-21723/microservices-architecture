package com.xyz.divisionservice.service;

import java.math.BigDecimal;

import com.xyz.divisionservice.exception.GenericException;

public interface DivisionService {

	public BigDecimal divide(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
