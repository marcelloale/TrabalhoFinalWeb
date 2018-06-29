package com.ufc.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaInicialController {
	
	
	@RequestMapping("/")
	public String paginaInicial() {
		return "pagina-inicial";
	}
	
	@RequestMapping("/inicio")
	public String paginaInicio() {
		return "pagina-inicial";
	}
	
	/*
	@RequestMapping("/sobre")
	public String paginaSobre() {
		return "sobre";
	}
	
	@RequestMapping("/contato")
	public String paginaContato() {
		return "contato";
	}
	*/
	
	
	

}
