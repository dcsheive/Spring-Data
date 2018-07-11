package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Tag;
import com.codingdojo.relationships.repositories.TagRepository;

@Service
public class TagService {
	private final TagRepository tRepo;
	public TagService(TagRepository tRepo) {
		this.tRepo = tRepo;
	}
	public List<Tag> allTags(){
		return tRepo.findAll();
	}
	public Tag createTag(Tag p) {
		return tRepo.save(p);
	}
	public Tag findTag(String tag) {
		return tRepo.findFirstByName(tag);
	}
	public Tag getTag(Long id) {
		Optional<Tag> p = tRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
}
