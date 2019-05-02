package my.spring.rest.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.spring.entity.CustomerDAO;
import my.spring.entity.CustomerDAO2;

@RestController
@RequestMapping("/rest")
public class MyRestController {

	@Autowired
	CustomerDAO custDAO;
	
	@Autowired
	CustomerDAO2 custDAO2;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, The time is now: "+new Date();
	}

	@GetMapping("/hello2")
	public String sayHello2() {
		custDAO.getCustomers();
		custDAO2.getCustomers2();
		return "Hello, The time is now: "+new Date();
	}
	
	
}
