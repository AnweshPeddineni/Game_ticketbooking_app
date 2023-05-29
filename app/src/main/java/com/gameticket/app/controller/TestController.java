package com.gameticket.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/test.htm")
	public String handleGet() {
		System.out.println("Inside TestController");
		return "user-view";
	}
}
