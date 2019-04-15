package com.softup.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.softup.store.entity.Likes;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	PasswordEncoder encoder;

	protected Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException he) {
			return sessionFactory.openSession();
		}
	}

	public String addUser(User user) {

		String result = "";
		String pass = encoder.encode(user.getPassword());
		user.setPassword(pass);

		try {
			Long id = (Long) session().save(user);
			session().flush();
			result = "" + id;
		} catch (Exception e) {
			if (e.getCause().getMessage().toLowerCase().contains("duplicate"))
				result = "error :user is already joined to store!";
			else
				result = "error :" + e.getCause().getMessage();
		} finally {
			session().clear();
		}

		return result;
	}

	public User findByUsername(String username) {

		List<User> users = session().createQuery("From User where username= :user_name", User.class)
				.setParameter("user_name", username).list();
		User user = null;

		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public List<User> getAllUsers(String username) {
		List<User> users = session().createQuery("From User where username !=?", User.class).setParameter(0, username)
				.list();

		return users;
	}

	public User findById(long id) {
		User user = session().get(User.class, id);

		return user;
	}

	public List<User> findByAddress(String address) {
		List<User> users = session().createQuery("From User where address =?", User.class).setParameter(0, address)
				.list();

		return users;
	}

	public List<User> findByPhoneNumber(String number) {
		List<User> users = session().createQuery("From User where phoneNumber =?", User.class).setParameter(0, number)
				.list();

		return users;
	}

	public List<User> getDisabledUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getExpiredUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getLockedUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getExpireCredentialUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public void resetPassword(User user, String newPass) {
		// TODO Auto-generated method stub

	}

	public String updateUser(User user) {

		String result = "";

		try {
			session().update(user);
			session().flush();
			result = "success";
		} catch (Exception e) {
			result = "error :" + e.getCause().getMessage();
		}

		return result;
	}

	public List<Product> getUserFavoriteProducts(User user) {
		List<Likes> likes = session().createQuery("From Likes where user =:user", Likes.class)
				.setParameter("user", user).list();

		List<Product> products = new ArrayList<Product>();

		for (Likes like : likes) {
			products.add(like.getProduct());
		}

		return products;
	}

	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = findByUsername(username);
		return user;
	}

}
