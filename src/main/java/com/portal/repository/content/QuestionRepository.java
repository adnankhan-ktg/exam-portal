package com.portal.repository.content;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portal.models.content.Question;

public interface QuestionRepository extends MongoRepository<Question, Long> {

	  public Question findByQuesid(long id);
	  public List<Question> findByQid(long qid);
}
