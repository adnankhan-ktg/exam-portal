package com.portal.controllers.content;

import java.util.ArrayList;
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

import com.portal.models.content.Category;
import com.portal.models.content.Question;
import com.portal.models.content.Quiz;
import com.portal.models.content.temp.Temp;
import com.portal.repository.content.CategoryRepository;
import com.portal.repository.content.QuizRepository;
import com.portal.service.content.QuestionService;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private QuizRepository quizRepository;
	
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	@PostMapping("/add_question")
	public ResponseEntity<?> addQuestion(@RequestBody Question question)
	{
		log.info("Request came one the add question controller");
		
		List<Question> list = this.questionService.getQuestions();
		if(list.size() == 0)
		{
			question.setQuesid(1);
		}
		else {
		Question questionAuto = list.get(list.size() - 1);
		
		question.setQuesid(questionAuto.getQuesid() + 1);
		System.out.print(question.getQuesid());
		}
		
		Question tempQuestion1 = this.questionService.getQuestion(question.getQuesid());
		if(tempQuestion1 != null)
		{
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
		}
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
	
	@PostMapping("/get_specific_question/{id}")
	public ResponseEntity<?> getAllQuestionsByQuizId(@PathVariable("id") long qid)
	{
//		Category tempCategory = this.categoryRepository.findByCid(qid);
		
		Quiz tempQuiz = this.quizRepository.findByQid(qid);
		int number = Integer.parseInt(tempQuiz.getNumberOfQuestions());
		
		log.info("Request came on the Get questions by id controller");
		ArrayList<Question> list = new ArrayList<>();
		list = (ArrayList<Question>) this.questionService.getAllQuestionsByQuizId(qid);
		if(list.size() != 0)
		{
			if(list.size() <= number)
			{
				log.info("list");
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}
			  ArrayList<Question> list1 = new ArrayList<>(list.subList(0, number));
			  log.info("list1");
			return ResponseEntity.status(HttpStatus.OK).body(list1);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	///////////////////////////////////////////////////////////
	
	@PostMapping("/get_specific_question_admin/{id}")
	public ResponseEntity<?> getAllQuestionsByQuizIdAdmin(@PathVariable("id") long qid)
	{
		
		log.info("Request came on the Get questions by id controller(ADMIN)");
		ArrayList<Question> list = new ArrayList<>();
		list = (ArrayList<Question>) this.questionService.getAllQuestionsByQuizId(qid);
		if(list.size() != 0)
		{
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}
