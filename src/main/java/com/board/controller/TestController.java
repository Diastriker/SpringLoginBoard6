package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TestController {
	
	@RequestMapping("/")
	public  String  home() {
		return "home";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "<h2>Hello SpringBoot!!!</h2>";
	}
	
}





