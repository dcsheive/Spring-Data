package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Question;
import com.codingdojo.relationships.repositories.QuestionRepository;

@Service
public class QuestionService {
	private final QuestionRepository qRepo;
	public QuestionService(QuestionRepository qRepo) {
		this.qRepo = qRepo;
	}
	public List<Question> allQuestions(){
		return qRepo.findAll();
	}
	public Question createQuestion(Question p) {
		return qRepo.save(p);
	}
	public Question getQuestion(Long id) {
		Optional<Question> p = qRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
}

