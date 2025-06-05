package com.upsolucoes.gestaocomercial.controller.pagamento;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upsolucoes.gestaocomercial.model.pagamento.Pagamento;
import com.upsolucoes.gestaocomercial.service.pagamento.PagamentoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping("/{orcamentoId}")
    public ResponseEntity<Pagamento> pagar(
            @PathVariable Long orcamentoId,
            @RequestBody Pagamento pagamento) {
        Pagamento realizado = service.realizarPagamento(orcamentoId, pagamento);
        return ResponseEntity.ok(realizado);
    }
}
