package com.ufc.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GerenciaController {
	
	
	@RequestMapping("/gerencia")
	public String paginaInicial() {
		return "gerencia";
	}
		
	

}
