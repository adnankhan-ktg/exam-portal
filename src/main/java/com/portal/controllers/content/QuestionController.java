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

}
