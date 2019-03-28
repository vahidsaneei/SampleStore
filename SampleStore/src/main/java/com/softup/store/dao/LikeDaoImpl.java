package com.softup.store.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softup.store.entity.Likes;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.LikeDao;

@Repository("likeDao")
public class LikeDaoImpl implements LikeDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session session() {

		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException he) {
			return sessionFactory.openSession();
		}
	}

	public String setLike(Likes likes) {
		String result = "";

		try {
			session().save(likes);
			session().flush();
			result = "success";
		} catch (Exception e) {
			result = "Erro :some error occurred\n" + e.getMessage();
		} finally {
			session().clear();
		}
		return result;
	}

	public String unLike(Likes like) {
		String result = "";

		try {
			session().remove(like);
			session().flush();
			result = "success";
		} catch (HibernateException e) {
			result = "Erro :some error occurred" + e.getMessage();

		} finally {
			session().clear();
		}
		return result;
	}

	public boolean likedByUser(User user, Product product) {
		Likes like = null;
		String query = "From Likes where user =:user AND product =:product";
		List<Likes> likes = session().createQuery(query, Likes.class).setParameter("user", user)
				.setParameter("product", product).list();

		if (likes.size() > 0) {
			like = likes.get(0);
		}

		if (like != null)
			return true;

		return false;
	}

}
