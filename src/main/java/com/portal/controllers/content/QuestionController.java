package com.portal.controllers.content;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.models.content.Question;
import com.portal.service.content.QuestionService;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	@PostMapping("/add_question")
	public ResponseEntity<?> addQuestion(@RequestBody Question question)
	{
		log.info("Request came one the add question controller");
		Question tempQuestion = this.questionService.addQuestion(question);
		if(tempQuestion != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuestion);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	@GetMapping("/get_questions")
	public ResponseEntity<?> getQuestions()
	{
		log.info("Request came on the Get all questions controller");
		List<Question> list = null;
		list = this.questionService.getQuestions();
		
		if(list.size() != 0)
		{
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PostMapping("/get_question/{q_id}")
	public ResponseEntity<?> getQuestion(@PathVariable("q_id") long qId)
	{
		log.info("Request came on the Get question by id controller");
		Question tempQuestion = null;
		tempQuestion = this.questionService.getQuestion(qId);
		if(tempQuestion != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuestion);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/update_question")
	public ResponseEntity<?> updateQuestion(Question question)
	{
		log.info("Request came on the Update Question controller");
		Question tempQuestion = null;
		tempQuestion = this.questionService.updateQuestion(question);
		if(tempQuestion != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuestion);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/delete_question/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("id") long id)
	{
		log.info("Request came on the Delete question controller");
		this.questionService.deleteQuestion(id);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

}
