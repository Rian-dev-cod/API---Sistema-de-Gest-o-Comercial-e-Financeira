package com.upsolucoes.gestaocomercial.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upsolucoes.gestaocomercial.model.pagamento.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
