package com.upsolucoes.gestaocomercial.repository.produto;

import com.upsolucoes.gestaocomercial.model.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Produto> findByCategoriaId(Long categoriaId, Pageable pageable);

    Page<Produto> findByPrecoUnitarioBetween(Double precoMin, Double precoMax, Pageable pageable);
}
