package com.softup.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.interfaces.ProductService;
import com.softup.store.interfaces.UserService;
import com.softup.store.models.Product;

@Controller
@EnableWebMvc
public class ProductsController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

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

	@RequestMapping(value = "/products/editproduct/", method = RequestMethod.GET)
	public ModelAndView editProduct() {
		ModelAndView model = new ModelAndView("newproduct");
		Product product = new Product();
		model.addObject("product", product);

		return model;
	}

	@RequestMapping(value = "/products/remove/{id}", method = RequestMethod.GET)
	public ModelAndView removeProduct(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("productList");
		Long pid = Long.parseLong(id);
		productService.removeProduct(pid);
		model.addObject("message", "product with id :" + id + " have been removed");
		return model;
	}

	@RequestMapping(value = "/products/saveprod", method = RequestMethod.POST)
	public ModelAndView saveProductt(@ModelAttribute Product product) {

		ModelAndView model = new ModelAndView();

		String result = productService.addProduct(product);

		if (result.toLowerCase().startsWith("error")) {
			model.setViewName("newproducts");
			model.addObject("error", result);
		} else {
			Long id = Long.parseLong(result);
			product.setId(id);
			model.setViewName("productList");
		}
		return model;
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
