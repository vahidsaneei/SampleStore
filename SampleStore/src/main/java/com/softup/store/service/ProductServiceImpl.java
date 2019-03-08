package com.softup.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.interfaces.ProductDao;
import com.softup.store.interfaces.ProductService;
import com.softup.store.models.Product;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Transactional
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Transactional
	public Product findById(Long id) {
		return productDao.findById(id);
	}

	@Transactional
	public List<Product> findByName(String name) {
		return productDao.findByName(name);
	}

}
