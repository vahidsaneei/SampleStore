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
import com.softup.store.entity.User;
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
		Orders orders = session().get(Orders.class, id);

		return orders;
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
			session().save(orders);
			session().flush();
			result = "success";

		} catch (HibernateException e) {
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
			session().flush();
			result = "success";

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
				session().flush();
				result = "success";
			} else {
				result = "error :orders not found";
			}

		} catch (Exception e) {
			result = "error " + e.getMessage();
		} finally {
			session().clear();
		}
		return result;
	}

	public List<Orders> getAllOrders() {
		List<Orders> orders = session().createQuery("From Orders", Orders.class).list();

		return orders;
	}

	public String cancelOrderByUser(Orders orders, String cause) {
		orders.setUserCancelCause(cause);
		orders.setCanceledByUser(true);
		return updateOrders(orders);
	}

	public String cancelOrderByAdmin(Orders orders, String cause) {
		orders.setAdminCancelCause(cause);
		orders.setCanceledByAdmin(true);
		return updateOrders(orders);
	}

	public List<Orders> findUserOrders(User user) {

		List<Orders> userOrders = session().createQuery("From Orders where user=:user", Orders.class)
				.setParameter("user", user).list();

		return userOrders;
	}

	public List<Orders> findUsersUncompleteOrders(User user) {

		List<Orders> userOrders = session()
				.createQuery("From Orders where user != :user and finished = 0 ", Orders.class)
				.setParameter("user", user).list();

		return userOrders;
	}

	public void removeUsersOrders(User user) {
		session().createQuery("remove from Orders where user= :user").setParameter("user", user).executeUpdate();
	}
}
