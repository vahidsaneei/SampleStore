package com.softup.store.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

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
			session().save(user);
			session().flush();
			result = "success";
		} catch (Exception e) {
			if (e.getCause().getMessage().toLowerCase().contains("duplicate"))
				result = "error :user is already joined to store!";
			else
				result = "error :" + e.getCause().getMessage();
		}

		return result;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public User findByUsername(String username) {

		List<User> users = session().createCriteria(User.class, "From User").add(Restrictions.eq("username", username))
				.list();
		User user = null;

		if (users.size() > 0) {
			user = users.get(0);
		}
		session().clear();
		return user;
	}

	public List<User> getAllUsers(String username) {
		List<User> users = session().createQuery("From User where username !=?", User.class).setParameter(0, username)
				.list();
		return users;
	}

	public User findById(long id) {
		return session().get(User.class, id);
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

}
