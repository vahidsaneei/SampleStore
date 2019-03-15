package com.softup.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.entity.Orders;
import com.softup.store.entity.Period;
import com.softup.store.entity.Product;
import com.softup.store.interfaces.OrderService;
import com.softup.store.interfaces.OrdersDao;

@Service("orderService")
@Transactional
public class OrdersServiceImpl implements OrderService {

	@Autowired
	OrdersDao ordersDao;

	@Transactional(readOnly = true)
	public Orders findById(Long id) {
		return ordersDao.findById(id);
	}

	@Transactional
	public List<Orders> findByDate(Date date) {
		return ordersDao.findByDate(date);
	}

	@Transactional
	public List<Orders> canceledOrders() {
		return ordersDao.canceledOrders();
	}

	@Transactional
	public List<Orders> completeOrders() {
		return ordersDao.completeOrders();
	}

	@Transactional
	public String cancelOrder(Orders orders, String cause) {
		return ordersDao.cancelOrder(orders, cause);
	}

	@Transactional
	public List<Orders> findOrdersByProduct(Product product) {
		return ordersDao.findOrdersByProduct(product);
	}

	@Transactional
	public List<Product> saleInPeriod(Period period, Product product) {
		return ordersDao.saleInPeriod(period, product);
	}

	@Transactional
	public String addOrders(Orders orders) {
		return ordersDao.addOrders(orders);
	}

	@Transactional
	public String updateOrders(Orders orders) {
		return ordersDao.updateOrders(orders);
	}

	@Transactional
	public String removeOrders(Long id) {
		return ordersDao.removeOrders(id);
	}

}
