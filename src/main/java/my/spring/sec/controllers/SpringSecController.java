package my.spring.sec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecController {

	
	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/show-login")
	public String showLogin() {
		return "do-Login";
	}
	
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders-page";
	}

	@GetMapping("/systems")
	public String showSystems() {
		return "systems-page";
	}
	
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		System.out.println("Inside access denied");
		return "denied-page_";
	}
}
