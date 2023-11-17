package com.nt.niranjana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{

	@GetMapping({"/home","/"})
	public String showHome() 
	{
		return "home";
	}
	
	@GetMapping("/login")
	public String showLogin()
	{
		return "login";
	}
	
	@GetMapping("/hellouser")
	public String showHello() 
	{
		return "hellouser";
	}
	
	@GetMapping("/admin")
	public String showAdmin() 
	{
		return "admin";
	}
	
	@GetMapping("/customer")
	public String showCustomer() 
	{
		return "customer";
	}
	
	
}