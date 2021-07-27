package com.portal.service.content;

import java.util.List;

import com.portal.models.content.Category;


public interface CategoryService {
	
	public Category addCategory(Category category);
	public Category updateCategory(Category category);
    public List<Category> getCategories();
	public Category getCategory(long categoryId);
	public void deleteCategory(long categoryId);
	

}
