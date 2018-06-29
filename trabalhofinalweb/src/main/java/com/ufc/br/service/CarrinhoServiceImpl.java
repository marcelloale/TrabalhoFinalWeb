package com.ufc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ufc.br.model.Produto;
import com.ufc.br.repository.ProdutoRepository;
import com.ufc.br.service.CarrinhoService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CarrinhoServiceImpl implements CarrinhoService {
	
	private final ProdutoRepository produtoRepository;

    private Map<Produto, Integer> produtos = new HashMap<>();
    
    @Autowired
    public CarrinhoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduto(Produto produto) {
        if (produtos.containsKey(produto)) {
            produtos.replace(produto, produtos.get(produto) + 1);
        } else {
            produtos.put(produto, 1);
        }
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduto(Produto produto) {
        if (produtos.containsKey(produto)) {
            if (produtos.get(produto) > 1)
                produtos.replace(produto, produtos.get(produto) - 1);
            else if (produtos.get(produto) == 1) {
                produtos.remove(produto);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Produto, Integer> getProdutosnoCarrinho() {
        return Collections.unmodifiableMap(produtos);
    }

    
    @Override
    public BigDecimal getTotal() {
        return produtos.entrySet().stream()
                .map(entry -> entry.getKey().getPreco().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
