package com.softup.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.interfaces.ProductService;
import com.softup.store.models.Product;

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

}
