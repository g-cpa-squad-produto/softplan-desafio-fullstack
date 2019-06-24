package com.challenge.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.api.documents.Comments;

public interface CommentsRepository extends MongoRepository<Comments, String> {

}
