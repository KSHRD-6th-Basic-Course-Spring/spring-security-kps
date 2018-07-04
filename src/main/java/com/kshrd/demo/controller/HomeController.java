package com.kshrd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping("/")
	public String guest() {
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/index";
	}
	
	@GetMapping("/editor")
	public String editor() {
		return "editor/index";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user/index";
	}
	
	@GetMapping("/user/profile")
	public String userProfile() {
		return "user/profile";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
//	@GetMapping("/encrypt")
//	@ResponseBody
//	public String encrypt(String pw) {
//		return bcrypt.encode(pw);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
