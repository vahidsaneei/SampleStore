package com.softup.store.interfaces;

import java.util.List;

import com.softup.store.models.User;

public interface UserService {

	String addUser(User user);

	User findByUsername(String username);

	List<User> getAllUsers();

	User findById(long id);

	List<User> findByAddress(String address);

	List<User> findByPhoneNumber(String number);

	List<User> getDisabledUser();

	List<User> getExpiredUser();

	List<User> getLockedUser();

	List<User> getExpireCredentialUser();

	void resetPassword(User user, String newPass);

}
