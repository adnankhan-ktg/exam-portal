package com.portal.repository.content;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portal.models.content.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, Long> {

}
