package com.codingdojo.login.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.login.models.User;
import com.codingdojo.login.repositories.UserRepository;

@Component
public class UserValidator implements Validator {
	private final UserRepository userRepo;
	public UserValidator(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	@Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }
        if (userRepo.findByEmail(user.getEmail()) !=null ) {
            errors.rejectValue("email", "Taken");
        }
    }
}
