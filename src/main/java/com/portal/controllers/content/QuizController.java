package com.portal.controllers.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		log.info("Request came on the Quiz controller");
		
		Quiz tempQuiz = this.quizService.addQuiz(quiz);
		if(tempQuiz != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuiz);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
