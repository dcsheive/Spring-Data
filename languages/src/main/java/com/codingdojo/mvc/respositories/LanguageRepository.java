package com.codingdojo.mvc.respositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.mvc.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language,Long>{
	List<Language> findAll();
    // this method find a Language by their description

}
