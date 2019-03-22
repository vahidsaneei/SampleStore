package com.softup.store.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.softup.store.entity.Product;
import com.softup.store.entity.User;

public interface ProductDao {
	List<Product> getAllProducts();

	Product findById(Long id);

	String addProduct(Product product);

	String removeProduct(Long id);
	
	String updateProduct(Product product);

	List<Product> findByName(String name);
	
	void setLike(Long id,User user);

	List<Product> getNewestProducts();

	List<Product> getPopularProducts();

	List<Product> limitPrice(BigDecimal start, BigDecimal end);

	List<Product> expireInNextDay(Integer num);

	List<Product> getProductFromStore(String storename);

	List<Product> mostCommentedProduct();

	List<Product> mostOrdered();

	String rechargeProduct(Product product, Integer quantity);

	List<Product> emptyProducts();
}
