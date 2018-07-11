package com.codingdojo.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.relationships.models.Category;
import com.codingdojo.relationships.models.Product;
import com.codingdojo.relationships.services.CategoryProductService;

@Controller
public class CategoryProductController {
	private final CategoryProductService serv;
	public CategoryProductController(CategoryProductService serv) {
        this.serv = serv;
    }
	@RequestMapping("/products/new")
	public String newSong(@ModelAttribute("product") Product product, Model model) {
		return "newproduct.jsp";
	}
	@RequestMapping(value="/products/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "newproduct.jsp";
        } else {
            serv.createProduct(product);
            return "redirect:/";
        }
    }
    @RequestMapping(value="/products/{id}", method=RequestMethod.GET)
    public String fPerson(Model model, @PathVariable("id") Long id ) {
    	Product product  = serv.getProduct(id);
    	model.addAttribute("product", product);
    	List<Category> categories = serv.allCategories();
    	model.addAttribute("categories", categories);
    	return "product.jsp";
    }
    @RequestMapping(value="/categories/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "newcategory.jsp";
        } else {
            serv.createCategory(category);
            return "redirect:/";
        }
    }
    @RequestMapping("/categories/new")
    public String newSong(@ModelAttribute("category") Category category, Model model) {
    	return "newcategory.jsp";
    }
    @RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
    public String fCate(Model model, @PathVariable("id") Long id ) {
    	Category category  = serv.getCategory(id);
    	model.addAttribute("category", category);
    	List<Product> products = serv.allProducts();
    	model.addAttribute("products", products);
    	return "category.jsp";
    }
    @RequestMapping(value="/categories/{id}/add", method=RequestMethod.POST)
    public String fCate(@RequestParam("product") Long p, @PathVariable("id") Long id ) {
    	serv.updateCategory(id, p);
    	return "redirect:/categories/"+id;
    }
    @RequestMapping(value="/products/{id}/add", method=RequestMethod.POST)
    public String fPre(@RequestParam("category") Long p, @PathVariable("id") Long id ) {
    	serv.updateProduct(id, p);
    	return "redirect:/products/"+id;
    }
}
