package com.xyz.userservice.service.impl;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.userservice.constants.AppConstants;
import com.xyz.userservice.constants.ErrorCodes;
import com.xyz.userservice.dao.UserDao;
import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.User;
import com.xyz.userservice.request.AddCreditRequest;
import com.xyz.userservice.request.AddUserRequest;
import com.xyz.userservice.service.CreditService;
import com.xyz.userservice.service.UserService;
import com.xyz.userservice.util.CommonUtils;
import com.xyz.userservice.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserValidator validator;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CreditService creditService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer saveUser(AddUserRequest request) throws GenericException {
		Integer userId = null;
		validator.validateAddUserRequest(request);
		Session session = null;
		Transaction tx = null;	
		try {
			session = sessionFactory.openSession();
			session.setHibernateFlushMode(FlushMode.AUTO);
			tx = session.beginTransaction();
			User mappedUser = mapUserFromAddUserRequest(request);
			userId = userDao.saveUser(session, mappedUser);
			log.info("Saved user with id ", userId);
			session.flush();
            tx.commit();
			if (userId != null) {
				creditService.addCredit(getAddCreditRequest(userId));
			}
		}
		catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			log.info("Exception while saving user in db for request {} with", CommonUtils.toJson(request), e);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return userId;
	}

	@Override
	public User getUser(Integer userId) throws GenericException {
		User user = null;
		Session session = null;
		Transaction tx = null;	
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
			log.info("Exception while getting user details for userId", userId);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
		userValidator.validateUser(user);
		return user;
	}
	
	@Override
	public void updateUserCredits(Double amountToUpdate, Integer userId) throws GenericException {
		Session session = null;
		Transaction tx = null;	
		try {
			session = sessionFactory.openSession();
			session.setHibernateFlushMode(FlushMode.AUTO);
			tx = session.beginTransaction();
			userDao.updateUserCredits(session, amountToUpdate, userId);
			session.flush();
            tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			log.info("Exception while getting user details for userId {} with ", userId, e);
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	private User mapUserFromAddUserRequest(AddUserRequest request) {
		Long currentTime = System.currentTimeMillis();
		return User.builder()
				.availableCredits(0d)
				.createdAt(currentTime)
				.email(request.getEmail())
				.name(request.getName())
				.updatedAt(currentTime)
				.build();
	}
	
	private AddCreditRequest getAddCreditRequest(Integer userId) {
		return AddCreditRequest.builder().amountToAdd(AppConstants.NEW_USER_CREDITS).userId(userId).purpose(AppConstants.NEW_USER_PURPOSE).build();
	}

}
