package com.softup.store.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	public User findByUsername(String username) {

		List<User> users = session().createQuery("from User where username=?", User.class).setParameter(0, username)
				.list();
		User user = null;

		if (users.size() > 0) {
			user = users.get(0);
		}

		return user;
	}

	public List<User> getAllUsers() {
		List<User> users = session().createQuery("From User", User.class).list();
		return users;
	}

	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByPhoneNumber(String number) {
		// TODO Auto-generated method stub
		return null;
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

}
