package com.portal.service.content.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.models.content.Question;
import com.portal.repository.content.QuestionRepository;
import com.portal.service.content.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	 private QuestionRepository questionRepository;
	@Override
	public Question addQuestion(Question question) {
		Question tempQuestion = null;
		try {
		      tempQuestion = this.questionRepository.save(question);
		      return tempQuestion;
		}catch (Exception e) {
		   e.printStackTrace();
		   return tempQuestion;
		}
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getQuestion(Long questionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteQuestion(Long questionId) {
		// TODO Auto-generated method stub

	}

}
