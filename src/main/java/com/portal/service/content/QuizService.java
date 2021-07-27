package com.portal.service.content;

import java.util.Set;

import com.portal.models.content.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuizzez();
	public Quiz getQuiz(Long quizId);
	public void deleteQuiz(Long quizId);
	
	


}
