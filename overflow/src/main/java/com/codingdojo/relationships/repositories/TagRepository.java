package com.codingdojo.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.relationships.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	List<Tag> findAll();
	Tag findFirstByName(String tag);

}
