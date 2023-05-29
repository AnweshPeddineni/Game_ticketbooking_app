package com.gameticket.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gameticket.app.dao.UserDAO;
import com.gameticket.app.pojo.User;
import com.gameticket.app.validator.SignupValidator;


@Controller
public class SignUpController {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	SignupValidator signupValidator;
	
	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup-form";
	}
	
	@PostMapping("/signup")
	public String signUpUser(@ModelAttribute("user") User user, Model model, BindingResult bindingResult) {
		signupValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()) {
			return "signup-form";
		}else {
			System.out.println(user.getEmail());
			userDao.saveUser(user);
			return "redirect:/login";
		}
		
	}
}
