package com.softup.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.entity.CartItem;
import com.softup.store.entity.Orders;
import com.softup.store.entity.User;
import com.softup.store.interfaces.OrderService;

@Controller
@EnableWebMvc
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getOrders() {
		ModelAndView model = new ModelAndView("orderslist");
		List<Orders> orders = orderService.getAllOrders();
		model.addObject("orders", orders);
		return model;
	}

	@RequestMapping(value = "getorder/{id}", method = RequestMethod.GET)
	public ModelAndView getOrderById(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("orderinfo");
		Orders orders = orderService.findById(id);
		User user = orders.getUser();
		List<CartItem> items = new ArrayList<CartItem>(orders.getItems());
		model.addObject("order", orders);
		model.addObject("customer", user);
		model.addObject("items", items);
		return model;
	}
}
