package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.service.CarrinhoService;
import com.ufc.br.service.ProdutoService;

@Controller
public class CarrinhoController {
	private final CarrinhoService carrinhoService;

    private final ProdutoService produtoService;
    
    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService, ProdutoService produtoService) {
        this.carrinhoService = carrinhoService;
        this.produtoService = produtoService;
    }
    
    @GetMapping("/carrinho")
    public ModelAndView carrinho() {
        ModelAndView modelAndView = new ModelAndView("/carrinho");
        modelAndView.addObject("produtos", carrinhoService.getProdutosnoCarrinho());
        modelAndView.addObject("total", carrinhoService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/carrinho/addProduto/{produtoId}")
    public ModelAndView addProdutonoCarrinho(@PathVariable("produtoId") Long produtoId) {
        //produtoService.buscarPorId(produtoId).ifPresent(carrinhoService::addProduto);
        //addProdutonoCarrinho(produtoService.buscarPorId(produtoId));
    	return carrinho();
    }


}
