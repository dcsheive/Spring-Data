package com.codingdojo.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.relationships.models.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{
	List<Product> findAll();
}
