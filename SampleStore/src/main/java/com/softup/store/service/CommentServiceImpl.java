package com.softup.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softup.store.entity.Comment;
import com.softup.store.entity.Product;
import com.softup.store.entity.User;
import com.softup.store.interfaces.CommentDao;
import com.softup.store.interfaces.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Transactional
	public String addComment(Comment comment) {
		return commentDao.addComment(comment);
	}

	@Transactional
	public String removeComment(Comment comment) {
		return commentDao.removeComment(comment);
	}

	@Transactional
	public String updateComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Transactional
	public List<Comment> productComments(Product product) {
		return commentDao.productComments(product);
	}

	@Transactional
	public List<Comment> userComment(User user) {
		return commentDao.userComment(user);
	}

}
