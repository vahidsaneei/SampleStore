package com.softup.store.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.ProductDao;
import com.softup.store.utils.StoreUtils;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

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

	public List<Product> getNewestProducts() {

		return null;
	}

	public List<Product> getPopularProducts() {

		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Product> limitPrice(BigDecimal start, BigDecimal end) {
		List<Product> products = session().createCriteria(Product.class).add(Restrictions.between("price", start, end))
				.list();
		return products;
	}

	public List<Product> expireInNextDay(Integer num) {
		Date date = StoreUtils.deliveryDate(new Date(), num);

		List<Product> expireProducts = session().createQuery("from product where createdDate=:?", Product.class)
				.setParameter(0, date).list();
		return expireProducts;
	}

	public List<Product> getProductFromStore(String storename) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> mostCommentedProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> mostOrdered() {
		// TODO Auto-generated method stub
		return null;
	}

	public String rechargeProduct(Product product, Integer quantity) {
		Integer i = product.getQuantity();
		i += quantity;
		product.setQuantity(i);
		String result = updateProduct(product);

		return result;
	}

	public List<Product> emptyProducts() {
		List<Product> products = session().createQuery("from product where quantity=:?", Product.class)
				.setParameter(0, 0).list();
		return products;
	}

	public String addProduct(Product product) {
		String result = "";

		try {
			session().save(product);
			result = "success";
			session().flush();
		} catch (Exception e) {
			if (e.getCause().getMessage().toLowerCase().contains("duplicate"))
				result = "error product already exist";
			else
				result = "error " + e.getCause().getMessage();
		} finally {
			session().clear();
		}

		return result;
	}

	public String removeProduct(Long id) {
		String result = "";

		try {
			Product p = findById(id);
			if (p != null) {
				session().remove(p);
				result = "success";
				session().flush();
			} else {
				result = "error product not found";
			}
		} catch (Exception e) {
			result = "error " + e.getCause().getMessage();
		} finally {
			session().clear();
		}
		return result;
	}

	public String updateProduct(Product product) {
		String result = "";

		try {
			session().update(product);
			result = "success";
			session().flush();
		} catch (Exception e) {
			result = "error " + e.getCause().getMessage();
		} finally {
			session().clear();
		}

		return result;
	}

	public void setLike(Long id,User user) {
		Product p = session().get(Product.class, id);
		Integer like = p.getLikeCount();

		if (like == null) {
			p.setLikeCount(1);
		} else {
			like += 1;
			p.setLikeCount(like);
			updateProduct(p);
		}
	}

}
