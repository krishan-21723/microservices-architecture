package com.xyz.userservice.dao;

import org.hibernate.Session;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.CreditTransactionDetail;

public interface CreditDao {

	public Long saveCreditTransactionDetail(Session session, CreditTransactionDetail creditTransactionDetail) throws GenericException;

}
