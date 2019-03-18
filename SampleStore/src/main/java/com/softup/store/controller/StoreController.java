package com.softup.store.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.entity.CartItem;
import com.softup.store.entity.Orders;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.entity.UserRole;
import com.softup.store.interfaces.OrderService;
import com.softup.store.interfaces.ProductService;
import com.softup.store.interfaces.UserService;
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

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public ModelAndView saveNewUser(@ModelAttribute User user) {

		ModelAndView model = new ModelAndView();
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		UserRole role = new UserRole(user, "ROLE_USER");
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(role);
		user.setUserRoles(roles);

		String result = userService.addUser(user);

		if (!result.toLowerCase().startsWith("error")) {
			model.setViewName("loginForm");
		} else {
			model.setViewName("newuser");
			model.addObject("error", result);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "removefromcart/{id}", method = RequestMethod.GET)
	public String removeFromCart(@PathVariable("id") Long id, HttpSession session) {

		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		Integer i = checkItemExist(id, cart);
		CartItem ci = cart.get(i);
		cart.remove(ci);

		if (cart.size() == 0)
			session.setAttribute("cart", null);
		else
			session.setAttribute("cart", cart);

		return "addtocartlist";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "addtocartlist/{id}", "addtocartlist" }, method = RequestMethod.GET)
	public String addToCart(@PathVariable(value = "id", required = false) String id, HttpSession session) {

		if (id != null) {
			Long l = Long.parseLong(id);
			Product p = productService.findById(l);
			if (session.getAttribute("cart") == null) {
				List<CartItem> cart = new ArrayList<CartItem>();
				cart.add(new CartItem(p, 1));
				session.setAttribute("cart", cart);
			} else {
				List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
				Integer i = checkItemExist(l, cart);
				if (i == -1)
					cart.add(new CartItem(p, 1));
				else {
					Integer q = cart.get(i).getQuantity() + 1;
					cart.get(i).setQuantity(q);
				}
				session.setAttribute("cart", cart);
			}
		} else if (id == null && session.getAttribute("cart") == null) {
			session.setAttribute("cart", null);
		}
		session.setAttribute("error", null);
		return "addtocartlist";
	}

	protected Integer checkItemExist(Long id, List<CartItem> cart) {

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/store/completesale", method = RequestMethod.POST)
	public ModelAndView loadOrder(HttpSession session, HttpServletRequest request) {

		String[] quantities = request.getParameterValues("quantity");

		ModelAndView model = new ModelAndView("orderbyuser");
		List<CartItem> products = (List<CartItem>) session.getAttribute("cart");

		for (int i = 0; i < products.size(); i++) {
			products.get(i).setQuantity(Integer.parseInt(quantities[i]));
		}

		model.addObject("items", products);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		model.addObject("userinfo", user);
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/store/addorder", method = RequestMethod.GET)
	public ModelAndView addOrder(HttpSession session) {

		ModelAndView model = new ModelAndView();

		// create list of products that we have to add in orders
		System.err.println("item get to retrieving---------------");
		List<CartItem> items = (List<CartItem>) session.getAttribute("cart");
		for (CartItem cartItem : items) {
			System.err.println(cartItem.getProduct());
		}

		// find user by username to set orders userinfo
		System.err.println("user information get to retriving---------------");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		System.err.println(user);

		// new order generated and setter methods invoke
		System.err.println("new order generated-------------------");
		Orders order = new Orders(items, user);
		Date d = new Date();
		Date delivery = StoreUtils.deliveryDate(d, 3);
		order.setOrderDate(d);
		order.setDeliveryDate(delivery);
		order.setUser(user);
		order.setItems(items);
		String addOrders = orderService.addOrders(order);
		System.err.println("new order add status " + addOrders + "-------------");
		System.err.println(order);
		// change product quantity after adding new order
		for (int i = 0; i < items.size(); i++) {
			Integer qSale = items.get(i).getQuantity() * (-1);
			productService.rechargeProduct(items.get(i).getProduct(), qSale);
		}

		if (!addOrders.toLowerCase().contains("error")) {
			model.setViewName("successorder");
			model.addObject("order", order);
			model.addObject("message", addOrders);
			session.setAttribute("cart", null);
		} else {
			session.setAttribute("error", addOrders);
			model.setViewName("redirect:/addtocartlist");
		}
		return model;
	}

	public List<Product> retriveProduct(String productIds) {

		List<Product> products = new ArrayList<Product>();

		String[] prods = productIds.split(",");

		for (String string : prods) {
			String[] vars = string.split("-");
			Long l = Long.parseLong(vars[0]);
			Product p = productService.findById(l);
			p.setQuantity(Integer.parseInt(vars[1]));
			products.add(p);
		}
		return products;
	}
}
