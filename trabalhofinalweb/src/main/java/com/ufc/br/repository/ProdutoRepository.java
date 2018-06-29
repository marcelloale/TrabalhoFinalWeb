package com.ufc.br.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Optional<Produto> findById(Long id);
}
