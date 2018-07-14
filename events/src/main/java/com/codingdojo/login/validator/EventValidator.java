package com.codingdojo.login.validator;


import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.login.models.Event;

@Component
public class EventValidator implements Validator {

	@Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        Date now = new Date();
        
        if (event.getDate().before(now)) {
        	errors.rejectValue("date", "Before");
        }
    }
}
