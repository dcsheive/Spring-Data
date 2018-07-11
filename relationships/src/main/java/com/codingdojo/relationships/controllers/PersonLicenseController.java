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

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.services.LicensePersonService;

@Controller
public class PersonLicenseController {
	private final LicensePersonService serv;
	public PersonLicenseController(LicensePersonService serv) {
        this.serv = serv;
    }
	@RequestMapping("")
    public String index(Model model) {
        List<License> licenses = serv.allLicenses();
        model.addAttribute("licenses", licenses);
        return "index.jsp";
    }
	@RequestMapping("/persons/new")
	public String newSong(@ModelAttribute("person") Person person) {
		return "newperson.jsp";
	}
	@RequestMapping(value="/persons/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "newperson.jsp";
        } else {
            serv.createPerson(person);
            return "redirect:/";
        }
    }
    @RequestMapping(value="/persons/{id}", method=RequestMethod.GET)
    public String fPerson(Model model, @PathVariable("id") Long id ) {
    	Person person  = serv.getPerson(id);
    	model.addAttribute("person",person);
    	return "show.jsp";
    }
    @RequestMapping(value="/licenses/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("license") License license, BindingResult result) {
        if (result.hasErrors()) {
            return "newlicense.jsp";
        } else {
            serv.createLicense(license);
            return "redirect:/";
        }
    }
    @RequestMapping("/licenses/new")
    public String newSong(@ModelAttribute("license") License license, Model model) {
    	List<Person> persons = serv.allPeople();
        model.addAttribute("persons", persons);
    	return "newlicense.jsp";
    }
}
