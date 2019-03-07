package com.softup.store.interfaces;

import java.util.List;

import com.softup.store.models.User;


public interface UserService {

	String addUser(User user);

	User findByUsername(String username);

	List<User> getAllUsers();

}
