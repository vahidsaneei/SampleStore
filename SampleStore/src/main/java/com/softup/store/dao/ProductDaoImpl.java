package com.softup.store.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softup.store.interfaces.ProductDao;
import com.softup.store.models.Product;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public List<Product> getAllProducts() {
		List<Product> products = session().createQuery("From Product", Product.class).list();
		return products;
	}

	public Product findById(Long id) {
		return session().get(Product.class, id);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Product> findByName(String name) {
		String param = "%" + name + "%";
		List<Product> products = session().createCriteria(Product.class).add(Restrictions.like("fullname", param))
				.list();
		return products;
	}

}
