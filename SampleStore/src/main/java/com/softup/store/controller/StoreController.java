package com.softup.store.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.interfaces.OrderService;
import com.softup.store.interfaces.ProductService;
import com.softup.store.interfaces.UserService;
import com.softup.store.models.Orders;
import com.softup.store.models.Product;
import com.softup.store.models.User;
import com.softup.store.utils.StoreUtils;

@Controller
@EnableWebMvc
public class StoreController {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;

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

	@RequestMapping(value = "/addtocartlist/{id}", method = RequestMethod.GET)
	public ModelAndView addToCart(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("addtocartlist");
		List<Product> products = new ArrayList<Product>();
		Product p = productService.findById(id);
		products.add(p);
		model.addObject("products", products);

		return model;
	}

	@RequestMapping(value = "/store/completesale", method = RequestMethod.GET)
	public ModelAndView loadOrder(@PathParam(value = "p") String p) {

		ModelAndView model = new ModelAndView("orderbyuser");
		Map<Product, Integer> products = retriveProduct(p);
		model.addObject("products", products);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		model.addObject("userinfo", user);
		return model;
	}

	@RequestMapping(value = "/store/addorder", method = RequestMethod.GET)
	public ModelAndView addOrder(@PathParam(value = "p") String p) {

		ModelAndView model = new ModelAndView("successorder");
		Map<Product, Integer> setproducts = retriveProduct(p);
		List<Product> products = new ArrayList<Product>();

		for (Product pr : setproducts.keySet()) {
			products.add(pr);
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);

		Orders order = new Orders(products, user);
		Date d = new Date();
		Date delivery = StoreUtils.deliveryDate(d, 3);
		order.setOrderDate(d);
		order.setDeliveryDate(delivery);
		String addOrders = orderService.addOrders(order);

		if (!addOrders.toLowerCase().contains("error")) {
			model.addObject("order", order);
			model.addObject("message", addOrders);
		} else {
			model.addObject("error", addOrders);
		}
		return model;
	}

	public Map<Product, Integer> retriveProduct(String productIds) {

		Map<Product, Integer> products = new HashMap<Product, Integer>();

		String[] prods = productIds.split(",");

		for (String string : prods) {
			String[] vars = string.split("-");
			Long l = Long.parseLong(vars[0]);
			Product p = productService.findById(l);
			products.put(p, Integer.parseInt(vars[1]));
		}
		return products;
	}
}
