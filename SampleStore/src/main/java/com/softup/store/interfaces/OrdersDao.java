package com.softup.store.interfaces;

import java.util.Date;
import java.util.List;

import com.softup.store.models.Orders;
import com.softup.store.models.Period;
import com.softup.store.models.Product;

public interface OrdersDao {

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
