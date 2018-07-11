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

import com.codingdojo.relationships.models.Dojo;
import com.codingdojo.relationships.models.Ninja;
import com.codingdojo.relationships.services.DojoNinjaService;

@Controller
public class DojoNinjaController {
	private final DojoNinjaService serv;
	public DojoNinjaController(DojoNinjaService serv) {
        this.serv = serv;
    }
	@RequestMapping("/ninjas/new")
	public String newSong(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = serv.allDojos();
        model.addAttribute("dojos", dojos);
		return "newninjas.jsp";
	}
	@RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
        if (result.hasErrors()) {
            return "newninjas.jsp";
        } else {
            serv.createNinja(ninja);
            return "redirect:/";
        }
    }
    @RequestMapping(value="/dojos/{id}", method=RequestMethod.GET)
    public String fPerson(Model model, @PathVariable("id") Long id ) {
    	Dojo dojo  = serv.getDojo(id);
    	model.addAttribute("dojo", dojo);
    	return "show.jsp";
    }
    @RequestMapping(value="/dojos/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
        if (result.hasErrors()) {
            return "newdojo.jsp";
        } else {
            serv.createDojo(dojo);
            return "redirect:/";
        }
    }
    @RequestMapping("/dojos/new")
    public String newSong(@ModelAttribute("dojo") Dojo dojo, Model model) {
    	return "newdojo.jsp";
    }
}
