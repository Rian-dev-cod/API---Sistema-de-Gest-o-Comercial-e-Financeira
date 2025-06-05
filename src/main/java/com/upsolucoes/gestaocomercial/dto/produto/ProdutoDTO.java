package com.upsolucoes.gestaocomercial.dto.produto;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoDTO(
    Long id,
    String nome,
    String descricao,
    Integer quantidade,
    BigDecimal precoUnitario,
    List<String> imagens,
    Boolean promocao,
    Long categoriaId
) {}
