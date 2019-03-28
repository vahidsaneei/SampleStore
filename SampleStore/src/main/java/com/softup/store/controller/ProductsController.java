package com.softup.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.LikeService;
import com.softup.store.interfaces.ProductService;
import com.softup.store.interfaces.UserService;

@Controller
@EnableWebMvc
public class ProductsController {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;

	@RequestMapping(value = "/products")
	public ModelAndView getAllProducts() {

		List<Product> products = productService.getAllProducts();
		ModelAndView model = new ModelAndView("productList");
		model.addObject("products", products);

		return model;
	}

	@RequestMapping(value = "/products/newprod", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView newProduct() {
		ModelAndView model = new ModelAndView("newproduct");
		model.addObject("product", new Product());

		return model;
	}

	@RequestMapping(value = "/products/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("newproduct");
		Product product = productService.findById(id);
		model.addObject("product", product);

		return model;
	}

	@RequestMapping(value = "/products/remove/{id}", method = RequestMethod.GET)
	public ModelAndView removeProduct(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("redirect:/products");
		Long pid = Long.parseLong(id);
		productService.removeProduct(pid);
		model.addObject("message", "product with id :" + id + " have been removed");
		return model;
	}

	@RequestMapping(value = "/products/saveprod", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute Product product) {

		ModelAndView model = new ModelAndView();
		String result = "";

		if (product.getId() == null) {
			result = productService.addProduct(product);
		} else {
			result = productService.updateProduct(product);
		}

		if (result.toLowerCase().startsWith("error")) {
			model.setViewName("newproducts");
			model.addObject("product", product);
			model.addObject("error", result);
		} else {
			model.getModelMap().clear();
			model.setViewName("redirect:/products");
		}
		return model;
	}

	@RequestMapping(value = "/showdetails/{id}", method = RequestMethod.GET)
	public ModelAndView getProduct(@PathVariable("id") String id, HttpServletRequest request) {

		ModelAndView model = new ModelAndView("showdetails");
		Product pr = productService.findById(Long.parseLong(id));

		model.addObject("product", pr);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		if (username != null) {
			User user = findUser(username);
			boolean liked = likeService.likedByUser(user, pr);
			if (liked)
				model.addObject("liked", true);
			else
				model.addObject("liked", null);
		}

		return model;

	}

	public User findUser(String username) {
		User user = userService.findByUsername(username);
		return user;
	}

	@ModelAttribute("businessList")
	public List<String> getBusinessList() {

		List<String> businessList = new ArrayList<String>();

		businessList.add("Clothes");
		businessList.add("Accessories");
		businessList.add("Food");
		businessList.add("Dairy");
		businessList.add("Shoe and Sneakers");
		businessList.add("Electronics");
		businessList.add("Home Appliances");

		return businessList;
	}
}
