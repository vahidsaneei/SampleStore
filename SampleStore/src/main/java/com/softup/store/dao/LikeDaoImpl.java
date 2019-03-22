package com.softup.store.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softup.store.entity.Likes;
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
			result = "success";
			session().flush();
		} catch (Exception e) {
			result = "Erro :some error occurred";
		} finally {
			session().clear();
		}
		return result;
	}
}
