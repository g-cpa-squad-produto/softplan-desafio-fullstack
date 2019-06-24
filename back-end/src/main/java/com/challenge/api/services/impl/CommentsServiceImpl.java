package com.challenge.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.api.documents.Comments;
import com.challenge.api.repositories.CommentsRepository;
import com.challenge.api.services.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;

	@Override
	public List<Comments> listAll() {
		return this.commentsRepository.findAll();
	}

	@Override
	public Comments listByID(String id) {
		return this.commentsRepository.findById(id).orElse(null);
	}

	@Override
	public Comments create(Comments comments) {
		return this.commentsRepository.save(comments);
	}

	@Override
	public Comments update(Comments comments) {
		return this.commentsRepository.save(comments);
	}

	@Override
	public void remove(String id) {
		this.commentsRepository.deleteById(id);
	}

}
