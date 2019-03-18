package com.softup.store.interfaces;

import java.util.Date;
import java.util.List;

import com.softup.store.entity.Orders;
import com.softup.store.entity.Period;
import com.softup.store.entity.Product;

public interface OrderService {

	List<Orders> getAllOrders();
	
	String addOrders(Orders orders);

	String updateOrders(Orders orders);

	String removeOrders(Long id);

	Orders findById(Long id);

	List<Orders> findByDate(Date date);

	List<Orders> canceledOrders();

	List<Orders> completeOrders();

	String cancelOrder(Orders orders, String cause);

	List<Orders> findOrdersByProduct(Product product);

	List<Product> saleInPeriod(Period period, Product product);

}
