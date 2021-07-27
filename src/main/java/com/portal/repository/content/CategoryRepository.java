package com.portal.repository.content;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portal.models.content.Category;

public interface CategoryRepository extends MongoRepository<Category , Long> {
	public Category findByCid(Long id);

}
