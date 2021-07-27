package com.portal.service.content.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.models.content.Quiz;
import com.portal.repository.content.QuizRepository;
import com.portal.service.content.QuizService;

@Service 
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
	 
		 Quiz tempQuiz = null;
		 try {
			   tempQuiz = this.quizRepository.save(quiz);
			   return tempQuiz;
		 }
		 catch (Exception e) {
			
			e.printStackTrace();
			return tempQuiz;
		}
		 
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Quiz> getQuizzez() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub

	}

}
