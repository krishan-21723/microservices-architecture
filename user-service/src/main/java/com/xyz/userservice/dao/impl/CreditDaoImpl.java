package com.xyz.userservice.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xyz.userservice.dao.CreditDao;
import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.CreditTransactionDetail;

@Repository
public class CreditDaoImpl implements CreditDao {

	@Override
	public Long saveCreditTransactionDetail(Session session, CreditTransactionDetail creditTransactionDetail) throws GenericException {
		return (long) session.save(creditTransactionDetail);
	}
}
