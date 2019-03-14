package com.softup.store.service;

import java.math.BigDecimal;
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

	@Transactional
	public String addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Transactional
	public String removeProduct(Long id) {
		return productDao.removeProduct(id);
	}
	@Transactional
	public List<Product> getNewestProducts() {
		return productDao.getNewestProducts();
	}

	@Transactional
	public List<Product> getPopularProducts() {
		return productDao.getPopularProducts();
	}

	@Transactional
	public List<Product> limitPrice(BigDecimal start, BigDecimal end) {
		return productDao.limitPrice(start, end);
	}

	@Transactional
	public List<Product> expireInNextDay(Integer num) {
		return productDao.expireInNextDay(num);
	}

	@Transactional
	public List<Product> getProductFromStore(String storename) {
		return productDao.getProductFromStore(storename);
	}

	@Transactional
	public List<Product> mostCommentedProduct() {
		return productDao.mostCommentedProduct();
	}

	@Transactional
	public List<Product> mostOrdered() {
		return productDao.mostOrdered();
	}

	@Transactional
	public void rechargeProduct(Product product, Integer quantity) {
		productDao.rechargeProduct(product, quantity);
	}

	@Transactional
	public List<Product> emptyProducts() {
		return productDao.emptyProducts();
	}
	@Transactional
	public String updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

}
