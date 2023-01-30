package com.fran.springboot.backend.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
	
	@GetMapping({"/", "", "/index", "/home"})
	public String index(Model model)
	{
		model.addAttribute("titulo", "Springboot backend API MVC");
		return "index";
	}

}
