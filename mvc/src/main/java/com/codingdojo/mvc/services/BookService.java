package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.respositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	public BookService(BookRepository bookRepo) {
		this.bookRepository = bookRepo;
	}
	public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
    	Optional<Book> oBook = bookRepository.findById(id);
    	if(oBook.isPresent()) {
    		oBook.get().setTitle(title);
    		oBook.get().setDescription(desc);
    		oBook.get().setNumberOfPages(numOfPages);
    		oBook.get().setLanguage(lang);
    		return bookRepository.save(oBook.get());
    		
    	} else {
    		return null;
    	}
    }
    public void deleteBook(long id) {
    	Optional<Book> oBook = bookRepository.findById(id);
    	if(oBook.isPresent()) {
    		bookRepository.delete(oBook.get());
    	} 
    }
}
