package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Category;
import com.codingdojo.relationships.models.Product;
import com.codingdojo.relationships.repositories.CategoryRepository;
import com.codingdojo.relationships.repositories.ProductRepository;

@Service
public class CategoryProductService {
	private final ProductRepository pRepo;
	private final CategoryRepository cRepo;
	public CategoryProductService(CategoryRepository cRepo, ProductRepository pRepo) {
		this.pRepo = pRepo;
		this.cRepo = cRepo;
	}
	public List<Product> allProducts(){
		return pRepo.findAll();
	}
	public List<Category> allCategories(){
		return cRepo.findAll();
	}
	public Product createProduct(Product p) {
		return pRepo.save(p);
	}
	public Category getCategory(Long id) {
		Optional<Category> p = cRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
	public Product getProduct(Long id) {
		Optional<Product> p = pRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
	public Category createCategory(Category l) {
		return cRepo.save(l);
	}
	public void updateProduct(Long id, Long cid) {
		Optional<Product> p = pRepo.findById(id);
		if(p.isPresent()) {
			Optional<Category> c = cRepo.findById(cid);
			p.get().addCategories(c.get());
			pRepo.save(p.get());
		}
	}
	public void updateCategory(Long id, Long pid) {
		Optional<Category> c = cRepo.findById(id);
		if(c.isPresent()) {
			Optional<Product> p = pRepo.findById(pid);
			c.get().addProducts(p.get());
			cRepo.save(c.get());
		}
	}
}
