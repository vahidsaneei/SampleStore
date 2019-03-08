package com.softup.store.interfaces;

import java.util.List;

import com.softup.store.models.Product;

public interface ProductDao {
	List<Product> getAllProducts();

	Product findById(Long id);

	List<Product> findByName(String name);

}
