package com.portal.service.content.impl;

import java.util.List;
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
	public List<Question> getQuestions() {
		List<Question> list = null;
		try {
			list = this.questionRepository.findAll();
			return list;
		}
		catch (Exception e) {
		  e.printStackTrace();
		  return list;
		}
	}

	@Override
	public Question getQuestion(Long questionId) {
		Question question = null;
		try {
			question = this.questionRepository.findByQuesid(questionId);
			return question;
		}catch (Exception e) {
		 e.printStackTrace();
		 return question;
		}
	}

	@Override
	public void deleteQuestion(Long questionId) {
            try {
            	this.questionRepository.deleteById(questionId);
            }catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public List<Question> getAllQuestionsByQuizId(long qid) {
		List<Question> list = null;
		try {
			list = this.questionRepository.findByQid(qid);
			return list;
		}catch (Exception e) {
		e.printStackTrace();
		return list;
		}
	}

}
