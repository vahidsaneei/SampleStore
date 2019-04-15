package com.softup.store.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softup.store.entity.Comment;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.LikeService;
import com.softup.store.interfaces.ProductService;
import com.softup.store.interfaces.UserService;
import com.softup.store.utils.StoreUtils;

@Controller
@EnableWebMvc
public class ProductsController {

	private static final String RESOURCES_IMAGES = "\\resources\\images\\";
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private ServletContext context;

	@RequestMapping(value = { "/products/{page}", "/products/" })
	public ModelAndView getAllProducts(@PathVariable(required = false) Optional<Integer> page) {

		List<Product> products = productService.getAllProducts();
		PagedListHolder<Product> pagedProduct = new PagedListHolder<Product>(products);
		pagedProduct.setPageSize(10);
		Integer maxpage = pagedProduct.getPageCount();
		Integer pageid = 1;

		if (page.isPresent())
			pageid = page.get();

		pagedProduct.setPage(pageid);

		ModelAndView model = new ModelAndView("productList");

		model.addObject("products", pagedProduct.getPageList());
		model.addObject("currentpage", pageid);
		model.addObject("maxpage", maxpage);

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
			model.setViewName("imagetoproducts");
			model.addObject("prodid", result);
		}
		return model;
	}

	@RequestMapping(value = "products/filesave/{id}", method = RequestMethod.POST)
	public ModelAndView saveProductFiles(@PathVariable("id") Long id,
			@RequestParam("headerfile") CommonsMultipartFile headerImage,
			@RequestParam("upFile") List<CommonsMultipartFile> files) {

		ModelAndView model = new ModelAndView("redirect:/products/newprod");
		model.addObject("product", new Product());
		String path = context.getRealPath(RESOURCES_IMAGES + "productsImages\\" + id);

		try {
			new File(path).mkdir();
			StoreUtils.fileUpload("header", path, headerImage);

			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getSize() > 0) {
					StoreUtils.fileUpload(Integer.toString(i), path, files.get(i));
				}
			}
		} catch (Exception e) {
			if (e instanceof MaxUploadSizeExceededException)
				System.err.println("file is too big");
			else
				System.err.println(e.getMessage());
		}

		return model;
	}

	@RequestMapping(value = "/showdetails/{id}", method = RequestMethod.GET)
	public ModelAndView getProduct(@PathVariable("id") Long id, HttpServletRequest request) {

		ModelAndView model = new ModelAndView("showdetails");
		Product pr = productService.findById(id);
		List<Comment> comments = pr.getComments();

		model.addObject("product", pr);
		model.addObject("commentList", comments);

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
		businessList.add("Sport");
		businessList.add("Other");

		return businessList;
	}
}
