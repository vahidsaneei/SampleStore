package com.softup.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
}
