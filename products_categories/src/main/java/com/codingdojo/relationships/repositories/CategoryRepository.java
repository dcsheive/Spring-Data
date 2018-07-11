package com.codingdojo.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.relationships.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findAll();

}
