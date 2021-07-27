package com.portal.service.content;

import java.util.Set;

import com.portal.models.content.Question;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public Set<Question> getQuestions();
	public Question getQuestion(Long questionId);
	public void deleteQuestion(Long questionId);

}
