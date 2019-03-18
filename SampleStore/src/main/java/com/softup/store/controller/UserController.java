package com.softup.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.entity.User;
import com.softup.store.interfaces.UserService;

@Controller
@EnableWebMvc
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "user/showprofile", method = RequestMethod.GET)
	public ModelAndView getUserProfile() {
		ModelAndView model = new ModelAndView("profilepage");
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(name);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		ModelAndView model = new ModelAndView("userlist");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> users = userService.getAllUsers(username);
		model.addObject("userList", users);
		return model;
	}

	@RequestMapping(value = "users/newuser")
	public ModelAndView addNewUser() {
		ModelAndView model = new ModelAndView("adminnewuser");
		User user = new User();
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "users/edituser/{id}", method = RequestMethod.GET)
	public ModelAndView getUpdateUser(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("adminnewuser");
		User user = userService.findById(id);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "users/saveuser")
	public ModelAndView saveNewUser(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();

		String res = "";

		if (user.getId() != null)
			res = userService.addUser(user);
		else
			res = userService.updateUser(user);

		if (!res.contains("error")) {
			model.addObject("message", res);
		} else {
			model.addObject("error", res);
			model.setViewName("adminnewuser");
		}

		return model;
	}

	@ModelAttribute("roleList")
	public List<String> roleList() {
		List<String> roles = new ArrayList<String>();

		roles.add("ROLE_ADMIN");
		roles.add("ROLE_DBA_ADMIN");
		roles.add("ROLE_SALE_MANAGER");
		roles.add("ROLE_STORE_MANAGER");
		roles.add("ROLE_USER");
		roles.add("ROLE_FINANACE_MANAGER");
		roles.add("ROLE_CASHIER");
		roles.add("ROLE_EMPLOYEE");

		return roles;
	}
}
