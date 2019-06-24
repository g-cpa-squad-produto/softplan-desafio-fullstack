package com.challenge.api.services;

import java.util.List;

import com.challenge.api.documents.Comments;

public interface CommentsService {
	List<Comments> listAll();
	
	Comments listByID(String id);
	
	Comments create(Comments comments);
	
	Comments update(Comments comments);
	
	void remove(String id);

}
