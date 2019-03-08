package com.softup.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.interfaces.ProductService;
import com.softup.store.models.Product;
import com.softup.store.models.User;

@Controller
@EnableWebMvc
public class StoreController {

	@Autowired
	ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = { "/", "index", "home" }, method = RequestMethod.GET)
	public ModelAndView gethome() {
		ModelAndView model = new ModelAndView("welcomepage");
		List<Product> products = productService.getAllProducts();
		model.addObject("products", products);
		return model;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView getLogin(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView("loginForm");

		if (error != null) {
			model.addObject("error", "invalid username or password");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		return model;
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView registerCustomer() {
		ModelAndView model = new ModelAndView("newuser");
		User user = new User();
		model.addObject("user", user);
		return model;
	}

}
