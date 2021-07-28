package com.portal.service.content;

import java.util.List;
import java.util.Set;

import com.portal.models.content.Question;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public List<Question> getQuestions();
	public Question getQuestion(Long questionId);
	public void deleteQuestion(Long questionId);
	public List<Question> getAllQuestionsByQuizId(long qid);

}
