package com.softup.store.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softup.store.entity.Comment;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.CommentDao;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public String addComment(Comment comment) {

		String result = "";

		try {
			session().save(comment);
			session().flush();
			result = "success";
		} catch (HibernateException e) {
			result = "error " + e.getMessage();
		} finally {
			session().clear();
		}

		return result;
	}

	public String removeComment(Comment comment) {

		String result = "";

		try {
			session().remove(comment);
			session().flush();
			result = "success";
		} catch (HibernateException e) {
			result = "error " + e.getMessage();
		} finally {
			session().clear();
		}

		return result;
	}

	public String updateComment(Comment comment) {

		String result = "";

		try {
			session().update(comment);
			session().flush();
			result = "success";
		} catch (HibernateException e) {
			result = "error " + e.getMessage();
		} finally {
			session().clear();
		}

		return result;
	}

	public List<Comment> productComments(Product product) {
		List<Comment> comments = session().createQuery("From Comment where product =: product", Comment.class)
				.setParameter("product", product).list();
		return comments;
	}

	public List<Comment> userComment(User user) {
		List<Comment> comments = session().createQuery("From Comment where user =: user", Comment.class)
				.setParameter("user", user).list();
		return comments;
	}

}
