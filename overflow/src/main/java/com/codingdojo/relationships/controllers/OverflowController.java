package com.codingdojo.relationships.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OverflowController {
	@RequestMapping("")
	public String redir() {
		return "redirect:/questions";
	}
}
