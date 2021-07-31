package com.portal.controllers.content;

import java.util.List;
import java.util.stream.Collectors;

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

import com.portal.models.content.Category;
import com.portal.models.content.Question;
import com.portal.models.content.Quiz;
import com.portal.repository.content.QuestionRepository;
import com.portal.service.content.QuizService;
@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {
	

	private static final Logger log = LoggerFactory.getLogger(QuizController.class);
	@Autowired
	private QuizService quizService;
	@Autowired
	private QuestionRepository questionRepository;
	@PostMapping("/add_quiz")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
	{
	    
		log.info("Request came on the add Quiz controller");
		List<Quiz> list = this.quizService.getQuizzez();
		if(list.size() == 0)
		{
			quiz.setQid(1);
		}else {
		Quiz quizAuto = list.get(list.size() - 1);
		
		quiz.setQid(quizAuto.getQid() + 1);
		System.out.print(quiz.getQid());
		}
		
		
		Quiz tempQuiz1 = this.quizService.getQuiz(quiz.getQid());
		if(tempQuiz1 != null)
		{
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
		}
		
		Quiz tempQuiz = this.quizService.addQuiz(quiz);
		if(tempQuiz != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(tempQuiz);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping("/get_quizzez")
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
		List<Question> list = null; 
		list = this.questionRepository.findByQid(id);
		 if(list.size() != 0)
		 {
			 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		 }
		this.quizService.deleteQuiz(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/get_specific_quiz/{id}")
	public ResponseEntity<?> getAllQuizzezById(@PathVariable("id") long cid)
	{
		 List<Quiz> list = null;
		 list = this.quizService.findAllByCid(cid);
		 if(list.size() != 0)
		 {
			 return ResponseEntity.status(HttpStatus.OK).body(list);
		 }else {
			 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		 }
		 
		 
	}
	
	
	@PostMapping("/get_active_specific_quiz/{id}")
	public ResponseEntity<?> getAllActiveQuizzezById(@PathVariable("id") long cid)
	{
		 List<Quiz> list = null;
		 list = this.quizService.findAllByCid(cid);
		 if(list.size() != 0)
		 {
			 List<Quiz> newList = list.stream().filter(i-> i.isActive() == true).collect(Collectors.toList());
			 return ResponseEntity.status(HttpStatus.OK).body(newList);
		 }else {
			 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		 }
		 
		 
	}
	
}
