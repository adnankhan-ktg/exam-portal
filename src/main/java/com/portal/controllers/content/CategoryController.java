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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.models.content.Category;
import com.portal.models.content.Quiz;
import com.portal.repository.content.QuizRepository;
import com.portal.service.content.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private QuizRepository quizRepository;

	 private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	 @PostMapping("/add_category")
	public ResponseEntity<?> addCategory(@RequestBody Category category )
	{
		log.info("The request came on the Add_Category controller");
		        
		List<Category> list = this.categoryService.getCategories();
		System.out.println(list.size());
		if(list.size() == 0)
		{
			category.setCid(1);
		}
		else {
		Category tempCat = list.get(list.size() - 1);
		
		category.setCid(tempCat.getCid() + 1);
		System.out.print(category.getCid());
		}
		
		         Category tempCategory = this.categoryService.getCategory(category.getCid());
		         if(tempCategory != null)
		         {
		        	 return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
		         }
		        Category cat = this.categoryService.addCategory(category);
		        if(cat != null)
		        {
		        	return ResponseEntity.status(HttpStatus.OK).body(cat);
		        }
		        else {
		        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		        }
	}
	 
	 @GetMapping("/get_categories")
	 public ResponseEntity<?> getCategories()
	 {
		 log.info("Request came on the get categories controller");
		 List<Category>  list = this.categoryService.getCategories();
		 if(list.size() != 0)
		 {
			 return ResponseEntity.status(HttpStatus.OK).body(list);
		 }
		 else {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	 }
	 
	 @PostMapping("/get_category/{cid}")
	 public ResponseEntity<?> getCategory(@PathVariable("cid") long id)
	 {
		 log.info("Request came on the get category controller");
		 Category tempCategory = this.categoryService.getCategory(id);
		 if(tempCategory != null)
		 {
			 return ResponseEntity.status(HttpStatus.OK).body(tempCategory);
		 }
		 else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 }
	 }
	 
	 @PostMapping("/delete_category/{cid}")
	 public ResponseEntity<?> deleteCategory(@PathVariable("cid") long id)
	 {
		 List<Quiz> list = null; 
		list = this.quizRepository.findByCid(id);
		 if(list.size() != 0)
		 {
			 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		 }
		 log.info("Request came on the delete category controller");
		 this.categoryService.deleteCategory(id);
		 return ResponseEntity.status(HttpStatus.OK).build();
	 }

	   @PostMapping("/update_category")
	   public ResponseEntity<?> updateCategory(@RequestBody Category category)
	   {
		   log.info("Request came on the update category controller");
		   Category tempCategory = null;
		   tempCategory = this.categoryService.updateCategory(category);
		   if(tempCategory != null)
		   {
			   return ResponseEntity.status(HttpStatus.OK).body(tempCategory);
		   }
		   else {
			   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		   }
	   }
}
