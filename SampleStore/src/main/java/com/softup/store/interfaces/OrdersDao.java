package com.softup.store.interfaces;

import java.util.Date;
import java.util.List;

import com.softup.store.entity.Orders;
import com.softup.store.entity.Period;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;

public interface OrdersDao {

	List<Orders> getAllOrders();

	String addOrders(Orders orders);

	String updateOrders(Orders orders);

	String removeOrders(Long id);

	Orders findById(Long id);

	List<Orders> findByDate(Date date);

	List<Orders> findUserOrders(User user);
	
	List<Orders> findUsersUncompleteOrders(User user);
	
	void removeUsersOrders(User user);

	List<Orders> canceledOrders();

	List<Orders> completeOrders();

	String cancelOrderByUser(Orders orders, String cause);

	String cancelOrderByAdmin(Orders orders, String cause);

	List<Orders> findOrdersByProduct(Product product);

	List<Product> saleInPeriod(Period period, Product product);
}
