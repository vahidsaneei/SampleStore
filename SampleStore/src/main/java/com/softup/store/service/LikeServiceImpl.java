package com.softup.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.entity.Likes;
import com.softup.store.interfaces.LikeDao;
import com.softup.store.interfaces.LikeService;

@Service("likeService")
@Transactional
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao likeDao;

	@Transactional(readOnly = true)
	public String setLike(Likes likes) {
		return likeDao.setLike(likes);
	}

	@Transactional
	public String unLike(Likes like) {
		return likeDao.unLike(like);
	}

}
