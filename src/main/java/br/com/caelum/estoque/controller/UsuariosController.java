package br.com.caelum.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuariosController {
	
	@RequestMapping(value="/login-form", method=RequestMethod.GET)
	public String index() {
		return "usuarios/login-form";
	}
}
