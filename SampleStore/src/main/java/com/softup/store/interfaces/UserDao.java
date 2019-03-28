package com.softup.store.interfaces;

import java.util.List;

import com.softup.store.entity.Product;
import com.softup.store.entity.User;

public interface UserDao {

	String updateUser(User user);
	
	String addUser(User user);

	User findByUsername(String username);

	List<User> getAllUsers(String username);

	User findById(long id);
	
	List<Product> getUserFavoriteProducts(User user);

	List<User> findByAddress(String address);

	List<User> findByPhoneNumber(String number);

	List<User> getDisabledUser();

	List<User> getExpiredUser();

	List<User> getLockedUser();

	List<User> getExpireCredentialUser();

	void resetPassword(User user, String newPass);

	User getCurrentUser();
}
