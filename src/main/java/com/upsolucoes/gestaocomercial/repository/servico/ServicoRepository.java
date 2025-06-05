package com.upsolucoes.gestaocomercial.repository.servico;

import com.upsolucoes.gestaocomercial.model.servico.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
