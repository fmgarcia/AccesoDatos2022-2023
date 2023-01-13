package com.fran.ejemplo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"/index","","/","/home"})
	public String index(Model model) {
		model.addAttribute("titulo","Este es el título de la página");
		model.addAttribute("contenido","Este es el contenido de la página");
		return "index";
	}

}
