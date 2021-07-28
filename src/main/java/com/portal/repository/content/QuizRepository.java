package com.portal.repository.content;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portal.models.content.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, Long> {

	 public Quiz findByQid(long id);
	 public List<Quiz> findByCid(long cid);
}
