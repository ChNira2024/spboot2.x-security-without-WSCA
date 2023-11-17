package com.nt.niranjana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.niranjana.entity.User;
import com.nt.niranjana.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController 
{

	@Autowired
	private UserService service;
	
	//1. show Register page
	@GetMapping("/register")
	public String showReg() 
	{
		return "UserRegister";
	}
	
	//2. save user data
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model)
	{
		Integer id = service.saveUser(user);
		String message = "User '"+id+"' created!";
		model.addAttribute("Message", message);
		return "UserRegister";
	}
	
	
}
