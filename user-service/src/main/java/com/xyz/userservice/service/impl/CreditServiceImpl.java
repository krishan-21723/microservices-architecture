package com.xyz.userservice.service.impl;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.userservice.constants.ErrorCodes;
import com.xyz.userservice.dao.CreditDao;
import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.CreditTransactionDetail;
import com.xyz.userservice.request.AddCreditRequest;
import com.xyz.userservice.request.RedeemCreditRequest;
import com.xyz.userservice.service.CreditService;
import com.xyz.userservice.service.UserService;
import com.xyz.userservice.service.enums.TransactionType;
import com.xyz.userservice.util.CommonUtils;
import com.xyz.userservice.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditServiceImpl implements CreditService {

	@Autowired
	private CreditDao creditDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer validateUserAndAddCredit(AddCreditRequest request) throws GenericException {
		userValidator.validateAddCreditRequest(request);
		userValidator.validateNonExistingUserByUserId(request.getUserId());
		Integer txId = addCredit(request);
		return txId;
	}
	
	@Override
	public Integer addCredit(AddCreditRequest request) throws GenericException {
		Long currentTime = System.currentTimeMillis();
		Session session = null;
		Transaction tx = null;
		Integer txId = null;
		try {
			session = sessionFactory.openSession();
			session.setHibernateFlushMode(FlushMode.AUTO);
			tx = session.beginTransaction();

			CreditTransactionDetail creditTransactionDetail = CreditTransactionDetail.builder()
					.amount(request.getAmountToAdd())
					.createdAt(currentTime)
					.purpose(request.getPurpose())
					.transactionType(TransactionType.CREDIT.toString())
					.userId(request.getUserId())
					.build();
			txId = creditDao.saveCreditTransactionDetail(session, creditTransactionDetail).intValue();
			session.flush();
			tx.commit();
			
			userService.updateUserCredits(request.getAmountToAdd(), request.getUserId());

		} catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			log.info("Exception while adding credits for request {} with", CommonUtils.toJson(request), e);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return txId;
	}
	
	public Integer validateUserAndRedeemCredit(RedeemCreditRequest request) throws GenericException {
		userValidator.validateRedeemCreditRequest(request);
		userValidator.validateNonExistingUserByUserId(request.getUserId());
		Integer txId = redeemCredit(request);
		return txId;
	}

	@Override
	public Integer redeemCredit(RedeemCreditRequest request) throws GenericException {
		Long currentTime = System.currentTimeMillis();
		
		Session session = null;
		Transaction tx = null;
		Integer txId = null;
		try {
			session = sessionFactory.openSession();
			session.setHibernateFlushMode(FlushMode.AUTO);
			tx = session.beginTransaction();

			CreditTransactionDetail creditTransactionDetail = CreditTransactionDetail.builder()
					.amount(request.getAmountToRedeem())
					.createdAt(currentTime)
					.purpose(request.getPurpose())
					.transactionType(TransactionType.DEBIT.toString())
					.userId(request.getUserId())
					.build();
			txId = creditDao.saveCreditTransactionDetail(session, creditTransactionDetail).intValue();
			
			session.flush();
			tx.commit();
			
			userService.updateUserCredits((-1) * request.getAmountToRedeem(), request.getUserId());

		} catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			log.info("Exception while redeeming credits for request {} with", CommonUtils.toJson(request), e);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return txId;
	}
}
