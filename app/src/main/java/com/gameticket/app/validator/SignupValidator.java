package com.gameticket.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gameticket.app.pojo.User;

@Component
public class SignupValidator implements Validator {

	
	private final static String NAME_PATTERN = "^[a-zA-Z\\s]*$";
    private final static String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private final static int PASSWORD_MIN_LENGTH = 6;
    
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "empty-name", "name can not be blank");
		ValidationUtils.rejectIfEmpty(errors, "email", "empty-email", "email can not be blank");
		ValidationUtils.rejectIfEmpty(errors, "password", "empty-password", "password can not be blank");
		
		if (!user.getName().matches(NAME_PATTERN)) {
            errors.rejectValue("name", "invalid-name", "Name should contain only letters and spaces");
        }

        if (!user.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "invalid-email", "Email format is invalid");
        }

        if (user.getPassword().length() < PASSWORD_MIN_LENGTH) {
            errors.rejectValue("password", "invalid-password", "Password should be at least 6 characters long");
        }
	}

}
