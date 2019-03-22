package com.softup.store.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softup.store.entity.Orders;
import com.softup.store.entity.Period;
import com.softup.store.entity.Product;
import com.softup.store.interfaces.OrdersDao;

@Repository("ordersDao")
public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	SessionFactory sessionFactory;

	protected Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public Orders findById(Long id) {
		return session().get(Orders.class, id);
	}

	public List<Orders> findByDate(Date date) {
		List<Orders> orders = session().createQuery("from orders where delivery=:date", Orders.class)
				.setParameter(0, date, TemporalType.TIMESTAMP).list();
		return orders;
	}

	@SuppressWarnings({ "deprecation" })
	public List<Orders> canceledOrders() {
		String sql = "SELECT * FROM ORDERS WHERE canceled=:canceled";
		List<Orders> orders = session().createQuery(sql, Orders.class).setBoolean("canceled", Boolean.TRUE).list();

		return orders;
	}

	@SuppressWarnings({ "deprecation" })
	public List<Orders> completeOrders() {
		String sql = "SELECT * FROM ORDERS WHERE success=:success";
		List<Orders> orders = session().createQuery(sql, Orders.class).setBoolean("success", Boolean.TRUE).list();
		return orders;
	}

	public String cancelOrder(Orders orders, String cause) {
		orders.setCancelCause(cause);
		orders.setCanceled(true);
		return updateOrders(orders);

	}

	public List<Orders> findOrdersByProduct(Product product) {
		String sql = "SELECT From CartItem where prd_id=:prdId";
		List<Orders> orders = session().createQuery(sql, Orders.class).setParameter(0, product.getId()).list();
		return orders;
	}

	public List<Product> saleInPeriod(Period period, Product product) {
		return null;
	}

	public String addOrders(Orders orders) {
		String result = "";

		try {
			Long id = (Long) session().save(orders);
			result = Long.toString(id);

		} catch (Exception e) {
			if (e.getMessage().toLowerCase().contains("duplicate"))
				result = "error this  order already was exist";
			else
				result = "error " + e.getMessage();
			System.err.println(result);

		} finally {
			session().clear();
		}
		return result;
	}

	public String updateOrders(Orders orders) {
		String result = "";

		try {
			session().update(orders);
			result = "success";
			session().flush();

		} catch (Exception e) {
			result = "error " + e.getCause().getMessage();
		} finally {
			session().clear();
		}
		return result;
	}

	public String removeOrders(Long id) {
		String result = "";

		try {
			Orders orders = findById(id);

			if (orders != null) {
				session().remove(orders);
				result = "success";
				session().flush();
			} else {
				result = "error :orders not found";
			}

		} catch (Exception e) {
			result = "error " + e.getCause().getMessage();
		} finally {
			session().clear();
		}
		return result;
	}

	public List<Orders> getAllOrders() {
		List<Orders> orders = session().createQuery("From Orders", Orders.class).list();
		return orders;
	}

}
