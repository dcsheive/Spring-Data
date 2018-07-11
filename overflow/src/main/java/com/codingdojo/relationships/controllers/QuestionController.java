package com.codingdojo.relationships.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.relationships.models.Answer;
import com.codingdojo.relationships.models.Question;
import com.codingdojo.relationships.models.Tag;
import com.codingdojo.relationships.services.AnswerService;
import com.codingdojo.relationships.services.QuestionService;
import com.codingdojo.relationships.services.TagService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private final QuestionService qServ;
	private final TagService tServ;
	private final AnswerService aServ;
	public QuestionController(QuestionService qServ, AnswerService aServ, TagService tServ) {
		this.qServ = qServ;
		this.tServ = tServ;
		this.aServ = aServ;
	}
	@RequestMapping("")
	public String home(Model model) {
		List<Question> questions = qServ.allQuestions();
		model.addAttribute("questions", questions);
		return "questions.jsp";
	}
	@RequestMapping(value="/new", method=RequestMethod.POST)
    public String createQuestion(@RequestParam("question") String question, @RequestParam("tags") String tags, RedirectAttributes rAttr) {
        	tags = tags.replaceAll(" ", "");
        	String pattern = "([a-z]+, ?)+[a-z]+";
        	String pattern2 = "[a-z]+";
        	if(!tags.matches(pattern) && !tags.matches(pattern2)) {
        		rAttr.addFlashAttribute("error", "Tags must be lowercase and separated by commas!");
        		return "redirect:/questions/new";
        	}
        	String[] stags = tags.split(",");
        	if(stags.length>3) {
        		rAttr.addFlashAttribute("error", "Only 3 tags allowed!");
        		return "redirect:/questions/new";

        	}
        	List<Tag> tagadd = new ArrayList<Tag>();
        	for(int i = 0; i<stags.length; i++) {
        		if(tServ.findTag(stags[i])!=null) {
        			tagadd.add(tServ.findTag(stags[i]));
        		}
        		else {
        			Tag x = new Tag(stags[i]);
        			tServ.createTag(x);
        			tagadd.add(x);
        		}
        	}
        	Question nQuestion = new Question(question, tagadd);
            qServ.createQuestion(nQuestion);
            return "redirect:/questions";
    }
    @RequestMapping("/new")
    public String newQuestion(@ModelAttribute("question") Question question, Model model) {
    	return "new.jsp";
    }
    @RequestMapping("/{id}")
    public String aQuestions(@PathVariable("id") Long id, Model model, @ModelAttribute("answer") Answer answer) {
    	Question q = qServ.getQuestion(id);
    	model.addAttribute("question",q);
    	return "question.jsp";
    }
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public String aQuestions(@Valid @ModelAttribute("answer") Answer answer, BindingResult result, @PathVariable("id") Long id, Model model) {
    	if(result.hasErrors()) {
    		Question q = qServ.getQuestion(id);
        	model.addAttribute("question",q);
        	return "question.jsp";
    	}
    	else {
    		aServ.createAnswer(answer);
    		return "redirect:/questions/"+id;
    	}
    }
}
