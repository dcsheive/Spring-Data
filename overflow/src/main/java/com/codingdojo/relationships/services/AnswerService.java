package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Answer;
import com.codingdojo.relationships.repositories.AnswerRepository;

@Service
public class AnswerService {
	private final AnswerRepository aRepo;
	public AnswerService(AnswerRepository aRepo) {
		this.aRepo = aRepo;
	}
	public List<Answer> allAnswers(){
		return aRepo.findAll();
	}
	public Answer createAnswer(Answer p) {
		return aRepo.save(p);
	}
	public Answer getAnswer(Long id) {
		Optional<Answer> p = aRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
}