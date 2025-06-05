package com.upsolucoes.gestaocomercial.repository.orcamento;

import com.upsolucoes.gestaocomercial.model.orcamento.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
