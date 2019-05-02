package my.spring.sec.controllers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecController {

	@Autowired
	private DataSource bdDataSource;
	
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
	
	@GetMapping("/bdreq1")
	public String getBDReq1() {
		return("home");
	}
}
