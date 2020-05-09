package com.xyz.userservice.dao;

import java.util.List;

import org.hibernate.Session;

import com.xyz.userservice.model.User;

public interface UserDao {

	public Integer saveUser(Session session, User user);

	public Integer updateUserCredits(Session session, Double amountToUpdate, Integer userId);

	public User getUser(Session session, Integer userId);

	public List<User> getUser(Session session, String email);
}
