package com.xyz.userservice.validator.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xyz.userservice.constants.ErrorCodes;
import com.xyz.userservice.dao.UserDao;
import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.User;
import com.xyz.userservice.request.AddCreditRequest;
import com.xyz.userservice.request.AddUserRequest;
import com.xyz.userservice.request.RedeemCreditRequest;
import com.xyz.userservice.util.CommonUtils;
import com.xyz.userservice.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserValidatorImpl implements UserValidator {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;

	@Override
	public void validateAddUserRequest(AddUserRequest request) throws GenericException {
		validateEmail(request.getEmail());
		validateForExistingUserOnEmail(request);
	}

	@Override
	public void validateAddCreditRequest(AddCreditRequest request) throws GenericException {
		if (request.getAmountToAdd() == null) {
			throw new GenericException(ErrorCodes.US005, ErrorCodes.getErrorMsg(ErrorCodes.US005));
		}
		if (request.getAmountToAdd() < 0) {
			throw new GenericException(ErrorCodes.US006, ErrorCodes.getErrorMsg(ErrorCodes.US006));
		}
		if (request.getUserId() == null) {
			throw new GenericException(ErrorCodes.US007, ErrorCodes.getErrorMsg(ErrorCodes.US007));
		}
	}

	@Override
	public void validateRedeemCreditRequest(RedeemCreditRequest request) throws GenericException {
		if (request.getAmountToRedeem() == null) {
			throw new GenericException(ErrorCodes.US005, ErrorCodes.getErrorMsg(ErrorCodes.US005));
		}
		if (request.getAmountToRedeem() < 0) {
			throw new GenericException(ErrorCodes.US006, ErrorCodes.getErrorMsg(ErrorCodes.US006));
		}
		if (request.getUserId() == null) {
			throw new GenericException(ErrorCodes.US007, ErrorCodes.getErrorMsg(ErrorCodes.US007));
		}
	}
	
	private void validateEmail(String email) throws GenericException {
		if (StringUtils.isEmpty(email)) {
			throw new GenericException(ErrorCodes.US004, ErrorCodes.getErrorMsg(ErrorCodes.US004));
		}
	}
	
	public void validateNonExistingUserByUserId(Integer userId) throws GenericException {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			session.setHibernateFlushMode(FlushMode.AUTO);
			tx = session.beginTransaction();
			user = userDao.getUser(session, userId);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			log.info("Exception while validating user for userId {} with", userId, e);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (user == null) {
			throw new GenericException(ErrorCodes.US002, ErrorCodes.getErrorMsg(ErrorCodes.US002));
		}
	}
	
	private void validateForExistingUserOnEmail(AddUserRequest request) throws GenericException {
		Session session = null;
		Transaction tx = null;
		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			session.setHibernateFlushMode(FlushMode.AUTO);
			tx = session.beginTransaction();
			users = userDao.getUser(session, request.getEmail());
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			log.info("Exception while validating user for request {} with", CommonUtils.toJson(request), e);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (!CollectionUtils.isEmpty(users)) {
			throw new GenericException(ErrorCodes.US003, ErrorCodes.getErrorMsg(ErrorCodes.US003));
		}
	}

	@Override
	public void validateUser(User user) throws GenericException {
		if (user == null) {
			throw new GenericException(ErrorCodes.US002, ErrorCodes.getErrorMsg(ErrorCodes.US002));
		}		
	}

}
