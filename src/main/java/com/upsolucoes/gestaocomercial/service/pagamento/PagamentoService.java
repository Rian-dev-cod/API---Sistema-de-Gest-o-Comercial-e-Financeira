package com.upsolucoes.gestaocomercial.service.pagamento;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.upsolucoes.gestaocomercial.model.orcamento.Orcamento;
import com.upsolucoes.gestaocomercial.model.pagamento.Pagamento;
import com.upsolucoes.gestaocomercial.model.pagamento.StatusPagamento;
import com.upsolucoes.gestaocomercial.repository.orcamento.OrcamentoRepository;
import com.upsolucoes.gestaocomercial.repository.pagamento.PagamentoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final OrcamentoRepository orcamentoRepository;

    public PagamentoService(PagamentoRepository repository, OrcamentoRepository orcamentoRepository) {
        this.repository = repository;
        this.orcamentoRepository = orcamentoRepository;
    }

    public Pagamento realizarPagamento(Long orcamentoId, Pagamento pagamento) {
        Orcamento orcamento = orcamentoRepository.findById(orcamentoId)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));

        if (orcamento.getStatusPagamento() == StatusPagamento.PAGO) {
            throw new RuntimeException("Este orçamento já foi pago.");
        }

        pagamento.setOrcamento(orcamento);
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setValor(orcamento.getValorTotal());

        orcamento.setStatusPagamento(StatusPagamento.PAGO);
        orcamentoRepository.save(orcamento);

        return repository.save(pagamento);
    }
}
