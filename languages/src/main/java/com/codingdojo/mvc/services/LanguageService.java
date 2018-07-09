package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Language;
import com.codingdojo.mvc.respositories.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository LanguageRepository;
	public LanguageService(LanguageRepository LanguageRepo) {
		this.LanguageRepository = LanguageRepo;
	}
	public List<Language> allLanguages() {
        return LanguageRepository.findAll();
    }
    // creates a Language
    public Language createLanguage(Language b) {
        return LanguageRepository.save(b);
    }
    // retrieves a Language
    public Language findLanguage(Long id) {
        Optional<Language> optionalLanguage = LanguageRepository.findById(id);
        if(optionalLanguage.isPresent()) {
            return optionalLanguage.get();
        } else {
            return null;
        }
    }
    public Language updateLanguage(Long id, String name, String creator, String curv) {
    	Optional<Language> oLanguage = LanguageRepository.findById(id);
    	if(oLanguage.isPresent()) {
    		oLanguage.get().setName(name);
    		oLanguage.get().setCreator(creator);
    		oLanguage.get().setCurrentVersion(curv);
    		return LanguageRepository.save(oLanguage.get());
    		
    	} else {
    		return null;
    	}
    }
    public void deleteLanguage(long id) {
    	Optional<Language> oLanguage = LanguageRepository.findById(id);
    	if(oLanguage.isPresent()) {
    		LanguageRepository.delete(oLanguage.get());
    	} 
    }
}
