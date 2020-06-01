package co.grandcircus.capstone_6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	@RequestMapping("/")
	public String home() {
		
		//If user is logged in, redirect to "review-tasks" page
		
		
		//If they are not, redirect to "login" page
		return ("redirect:/review-tasks");
	}
	
	
	
}
