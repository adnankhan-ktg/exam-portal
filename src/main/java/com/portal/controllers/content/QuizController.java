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

import com.portal.models.content.Quiz;
import com.portal.service.content.QuizService;
@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {
	

	private static final Logger log = LoggerFactory.getLogger(QuizController.class);
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/add_quiz")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
	{
		log.info("Request came on the add Quiz controller");
		
		Quiz tempQuiz = this.quizService.addQuiz(quiz);
		if(tempQuiz != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuiz);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/get_quizzez")
	public ResponseEntity<?> getQuizzez()
	{
		log.info("Request came on the the get Quiz controller");
		List<Quiz> list = null;
		
		list = this.quizService.getQuizzez();
		if(list.size() != 0)
		{
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
	}
	
	@PostMapping("/get_quiz/{id}")
	public ResponseEntity<?> getQuiz(@PathVariable("id") long id)
	{
		Quiz quiz = null;
		quiz = this.quizService.getQuiz(id);
		if(quiz != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(quiz);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/update_quiz")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz)
	{
		Quiz tempQuiz = null;
		tempQuiz = this.quizService.updateQuiz(quiz);
		if(tempQuiz != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuiz);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/delete_quiz/{id}")
	public ResponseEntity<?> deleteQuiz(@PathVariable("id") long id )
	{
		this.quizService.deleteQuiz(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}