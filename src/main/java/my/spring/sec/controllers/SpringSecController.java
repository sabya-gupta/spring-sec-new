package my.spring.sec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecController {

	
	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}
}
