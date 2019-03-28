package com.softup.store.interfaces;

import java.util.List;

import com.softup.store.entity.Comment;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;

public interface CommentService {
	
	String addComment(Comment comment);
	
	String removeComment(Comment comment);
	
	String updateComment(Comment comment);
	
	List<Comment> productComments(Product product);
	
	List<Comment> userComment(User user);

}
