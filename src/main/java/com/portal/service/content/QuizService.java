package com.portal.service.content;

import java.util.List;
import java.util.Set;

import com.portal.models.content.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public List<Quiz> getQuizzez();
	public Quiz getQuiz(Long quizId);
	public void deleteQuiz(Long quizId);
	public List<Quiz> findAllByCid(long cid);
	
	


}
