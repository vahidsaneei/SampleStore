package com.softup.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.entity.Likes;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.LikeDao;
import com.softup.store.interfaces.LikeService;

@Service("likeService")
@Transactional
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao likeDao;

	@Transactional
	public String setLike(Likes likes) {
		return likeDao.setLike(likes);
	}

	@Transactional
	public String unLike(Likes like) {
		return likeDao.unLike(like);
	}

	public boolean likedByUser(User user, Product product) {
		return likeDao.likedByUser(user,product);
	}
	
	

}
