package com.softup.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.UserDao;
import com.softup.store.interfaces.UserService;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Transactional
	public String addUser(User user) {
		return userDao.addUser(user);
	}

	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Transactional
	public List<User> getAllUsers(String username) {
		return userDao.getAllUsers(username);
	}

	@Transactional
	public User findById(long id) {
		return userDao.findById(id);
	}

	@Transactional
	public List<User> findByAddress(String address) {
		return userDao.findByAddress(address);
	}

	@Transactional
	public List<User> findByPhoneNumber(String number) {
		return userDao.findByPhoneNumber(number);
	}

	@Transactional
	public List<User> getDisabledUser() {
		return userDao.getDisabledUser();
	}

	@Transactional
	public List<User> getExpiredUser() {
		return userDao.getExpiredUser();
	}

	@Transactional
	public List<User> getLockedUser() {
		return userDao.getLockedUser();
	}

	@Transactional
	public List<User> getExpireCredentialUser() {
		return userDao.getExpireCredentialUser();
	}

	@Transactional
	public void resetPassword(User user, String newPass) {
		userDao.resetPassword(user, newPass);
	}

	@Transactional
	public String updateUser(User user) {
		return userDao.updateUser(user);
	}

	public List<Product> getUserFavoriteProducts(User user) {
		return userDao.getUserFavoriteProducts(user);
	}

	public User getCurrentUser() {
		return userDao.getCurrentUser();
	}
	@Transactional
	public String removeUser(Long id) {
		return userDao.removeUser(id);
	}
	@Transactional
	public boolean toggleUserEnable(Long id) {

		return userDao.toggleUserEnable(id);
	}
	@Transactional
	public boolean toggleUserExpireCredential(Long id) {

		return userDao.toggleUserExpireCredential(id);
	}
	@Transactional
	public boolean toggleUserLocked(Long id) {

		return userDao.toggleUserLocked(id);
	}

}
