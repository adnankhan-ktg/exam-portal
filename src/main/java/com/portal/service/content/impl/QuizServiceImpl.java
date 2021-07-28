package com.portal.service.content.impl;

import java.util.List;
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

        Quiz tempQuiz = null;
        try {
               tempQuiz = this.quizRepository.save(quiz);
               return tempQuiz;
        }catch (Exception e) {
		   e.printStackTrace();
		   return tempQuiz;
		}
	}

	@Override
	public List<Quiz> getQuizzez() {
	  List<Quiz> list = null;
	  try {
		  list = this.quizRepository.findAll();
		  return list;
	  }
	  catch (Exception e) {
	   e.printStackTrace();
	   return list;
	}
	  
	}

	@Override
	public Quiz getQuiz(Long quizId) {
	
		   Quiz quiz = null;
		   try {
			       quiz = this.quizRepository.findByQid(quizId);
			       return quiz;
		   }catch (Exception e) {
			e.printStackTrace();
			return quiz;
		}
	}

	@Override
	public void deleteQuiz(Long quizId) {
		try {
			this.quizRepository.deleteById(quizId);
		}catch (Exception e) {
		    e.printStackTrace();
		}

	}

	@Override
	public List<Quiz> findAllByCid(long cid) {
		  List<Quiz> list = null;
		  try {
			  list = this.quizRepository.findByCid(cid);
			  return list;
		  }catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	

}
