package com.codingdojo.mvc.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.codingdojo.mvc.models.Language;
import com.codingdojo.mvc.services.LanguageService;
@RestController
public class LanguageApi {
    private final LanguageService languageService;
    public LanguageApi(LanguageService languageService){
        this.languageService = languageService;
    }
    @RequestMapping("/api/languages")
    public List<Language> index() {
        return languageService.allLanguages();
    }
    
    @RequestMapping(value="/api/languages", method=RequestMethod.POST)
    public Language create(@RequestParam(value="name") String name, @RequestParam(value="creator") String creator, @RequestParam(value="currentVersion") String curv) {
        Language language = new Language(name,creator,curv);
        return languageService.createLanguage(language);
    }
    
    @RequestMapping(value="/api/languages/{id}", method=RequestMethod.GET)
    public Language show(@PathVariable("id") Long id) {
        Language language = languageService.findLanguage(id);
        return language;
    }
    @RequestMapping(value="/api/languages/{id}", method=RequestMethod.PUT)
    public Language update(@RequestParam(value="name") String name, @RequestParam(value="creator") String creator, @RequestParam(value="currentVersion") String curv, @PathVariable("id") Long id) {
    	Language language = languageService.updateLanguage(id, name,creator,curv);
        return language;
    }
    
    @RequestMapping(value="/api/languages/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        languageService.deleteLanguage(id);
    }
}
