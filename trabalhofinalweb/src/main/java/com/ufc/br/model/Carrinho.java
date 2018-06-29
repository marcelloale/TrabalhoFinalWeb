package com.ufc.br.model;

import java.math.BigDecimal;

public class Carrinho {
	
	private Produto Produto;
	
	private BigDecimal Total;

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public BigDecimal getTotal() {
		return Total;
	}

	public void setTotal(BigDecimal total) {
		Total = total;
	}
	
	
	
	

}
