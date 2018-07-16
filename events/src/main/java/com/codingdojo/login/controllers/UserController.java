package com.codingdojo.login.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.login.models.User;
import com.codingdojo.login.services.UserService;
import com.codingdojo.login.validator.UserValidator;

@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user, Model model) {
        List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
        		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
        model.addAttribute("states", states);
		return "index.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
    	userValidator.validate(user, result);
        if(result.hasErrors()) {
        	List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
            		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
            model.addAttribute("states", states);
            return "index.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("user", u.getId());
        return "redirect:/events";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	if(userService.authenticateUser(email, password)) {
    		User user = userService.findByEmail(email);
    		Long id = user.getId();
    		session.setAttribute("user", id );
    		return "redirect:/events";
    	}
    	else {
    		List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
            		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
            model.addAttribute("states", states);
    		model.addAttribute("error", "Could not log you in!");
    		model.addAttribute("user", new User());
    		return "index.jsp";
    	}
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.setAttribute("user", null);
        return "redirect:/";
    }
}