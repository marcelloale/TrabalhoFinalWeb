package com.ufc.br.service;

import java.math.BigDecimal;
import java.util.Map;

import com.ufc.br.model.Produto;

public interface CarrinhoService {
	void addProduto(Produto produto);

    void removeProduto(Produto produto);

    Map<Produto, Integer> getProdutosnoCarrinho();

    //void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
