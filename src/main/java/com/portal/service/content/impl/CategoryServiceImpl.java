package com.portal.service.content.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.models.content.Category;
import com.portal.repository.content.CategoryRepository;
import com.portal.service.content.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		       
		 Category tempCategory = null;
		try {
		        	tempCategory = this.categoryRepository.save(category);
		        	return tempCategory;
		} catch (Exception e) {
		     e.printStackTrace();
		     return tempCategory;
		}
	}

	@Override
	public Category updateCategory(Category category) {
		Category tempCategory = null;
		try {
		           tempCategory = this.categoryRepository.save(category);	
		           return category;
		}catch (Exception e) {
		e.printStackTrace();
		return category;
		}
	}

	@Override
	public List<Category> getCategories() {
	
		 List<Category> list = null;
		 try {
			      list = this.categoryRepository.findAll();
			      return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public Category getCategory(long categoryId) {
		
                     Category category = null;		
		try {
			 category = this.categoryRepository.findByCid(categoryId);
			 return category;
		}catch (Exception e) {
	         e.printStackTrace();
	         return category;
		}
	}

	@Override
	public void deleteCategory(long categoryId) {
		try {
		  this.categoryRepository.deleteById(categoryId);
		}catch (Exception e) {
		e.printStackTrace();
		}
		
	}

}
