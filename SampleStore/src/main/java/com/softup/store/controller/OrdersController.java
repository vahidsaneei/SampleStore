package com.softup.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.entity.Orders;
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
}
