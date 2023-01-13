package com.fran.ejemplo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/param")
public class ParamController {
	
	@GetMapping({"/index","","/","/home"})
	public String index(Model model) {
		model.addAttribute("titulo","Index Param");
		model.addAttribute("contenido","Este es el contenido de la página");
		return "param/index";
	}
	
	@GetMapping("/string")
	public String ver(@RequestParam(name="texto",required=false,defaultValue="Texto por defecto") String texto,
			Model model) {
		model.addAttribute("titulo","Request Param");
		model.addAttribute("contenido","Datos de param: " + texto);
		return "param/ver";
	}

}
