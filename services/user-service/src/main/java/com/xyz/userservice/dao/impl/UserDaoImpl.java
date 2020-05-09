package com.xyz.userservice.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xyz.userservice.constants.QueryConstants;
import com.xyz.userservice.dao.UserDao;
import com.xyz.userservice.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public Integer saveUser(Session session, User user) {
		return (int) session.save(user);
	}

	@Override
	public User getUser(Session session, Integer userId) {
		return session.get(User.class, userId);
	}

	@Override
	public List<User> getUser(Session session, String email) {
		List<User> users = null;
		try {
			users = (List<User>) session.getNamedQuery(QueryConstants.UserQueries.GET_USER_BY_EMAIL)
					.setParameter(QueryConstants.UserFields.EMAIL, email)
					.list();
		} catch (Exception e) {
			log.error("Exception while getting user by email with email {} and ", email, e);
		}
		return users;
	}
	
	@Override
	public Integer updateUserCredits(Session session, Double amountToUpdate, Integer userId) {
		session.getNamedQuery(QueryConstants.UserQueries.UPDATE_USER_AVAILABLE_CREDITS)
				.setParameter(QueryConstants.UserFields.AMOUNT_TO_UPDATE, amountToUpdate)
				.setParameter(QueryConstants.UserFields.USER_ID, userId)
				.executeUpdate();
		return null;
	}

}
