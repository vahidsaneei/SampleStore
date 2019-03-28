package com.softup.store.interfaces;

import com.softup.store.entity.Likes;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;

public interface LikeService {

	String setLike(Likes likes);

	String unLike(Likes like);

	boolean likedByUser(User user, Product product);

}
