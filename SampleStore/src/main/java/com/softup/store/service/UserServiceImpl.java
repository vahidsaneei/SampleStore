package com.softup.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.interfaces.UserDao;
import com.softup.store.interfaces.UserService;
import com.softup.store.models.User;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Transactional
	public String addUser(User user) {
		return userDao.addUser(user);
	}

	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Transactional
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> findByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> findByPhoneNumber(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> getDisabledUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> getExpiredUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> getLockedUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> getExpireCredentialUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void resetPassword(User user, String newPass) {
		// TODO Auto-generated method stub

	}

}
