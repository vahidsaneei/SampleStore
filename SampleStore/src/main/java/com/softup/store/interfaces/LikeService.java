package com.softup.store.interfaces;

import com.softup.store.entity.Likes;

public interface LikeService {
	
	String setLike(Likes likes);
	
	String unLike(Likes like);

}
