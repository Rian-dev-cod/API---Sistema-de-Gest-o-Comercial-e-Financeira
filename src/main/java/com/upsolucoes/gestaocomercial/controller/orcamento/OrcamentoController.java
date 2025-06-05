package com.upsolucoes.gestaocomercial.controller.orcamento;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upsolucoes.gestaocomercial.dto.orcamento.AtualizarStatusDTO;
import com.upsolucoes.gestaocomercial.dto.orcamento.OrcamentoResponseDTO;
import com.upsolucoes.gestaocomercial.dto.orcamento.OrcamentoResponseDTO.ItemOrcamentoDTO;
import com.upsolucoes.gestaocomercial.dto.orcamento.OrcamentoResponseDTO.PagamentoDTO;
import com.upsolucoes.gestaocomercial.dto.orcamento.OrcamentoResumoDTO;
import com.upsolucoes.gestaocomercial.model.orcamento.ItemOrcamento;
import com.upsolucoes.gestaocomercial.model.orcamento.Orcamento;
import com.upsolucoes.gestaocomercial.model.orcamento.StatusOrcamento;
import com.upsolucoes.gestaocomercial.model.pagamento.Pagamento;
import com.upsolucoes.gestaocomercial.service.orcamento.OrcamentoService;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrcamentoResponseDTO> salvar(@RequestBody Orcamento orcamento) {
        Orcamento salvo = service.salvar(orcamento);
        return ResponseEntity.ok(converterParaDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<OrcamentoResumoDTO>> listarResumido() {
        List<OrcamentoResumoDTO> lista = service.listarTodos().stream()
                .map(this::converterParaResumoDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrcamentoResponseDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(this::converterParaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrcamentoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestBody AtualizarStatusDTO dto) {

        Orcamento orcamento = service.buscarPorId(id).orElse(null);

        if (orcamento == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            StatusOrcamento novoStatus = StatusOrcamento.valueOf(dto.getStatus().toUpperCase());
            orcamento.setStatus(novoStatus);
            Orcamento atualizado = service.salvar(orcamento);
            return ResponseEntity.ok(converterParaDTO(atualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Conversão detalhada para DTO completo
    private OrcamentoResponseDTO converterParaDTO(Orcamento orcamento) {
        OrcamentoResponseDTO dto = new OrcamentoResponseDTO();
        dto.setId(orcamento.getId());
        dto.setClienteNome(orcamento.getCliente().getNome());
        dto.setDataCriacao(orcamento.getDataCriacao());
        dto.setStatus(orcamento.getStatus().name());
        dto.setStatusPagamento(orcamento.getStatusPagamento().name());
        dto.setValorTotal(orcamento.getValorTotal());

        List<ItemOrcamentoDTO> itensDTO = orcamento.getItens().stream()
                .map(this::converterItem)
                .toList();
        dto.setItens(itensDTO);

        Pagamento pagamento = orcamento.getPagamento();
        if (pagamento != null) {
            PagamentoDTO pagamentoDTO = new PagamentoDTO();
            pagamentoDTO.setTipo(pagamento.getTipo().name());
            pagamentoDTO.setValor(pagamento.getValor());
            pagamentoDTO.setDataPagamento(pagamento.getDataPagamento());
            dto.setPagamento(pagamentoDTO);
        }

        return dto;
    }

    // Conversão para item do orçamento
    private ItemOrcamentoDTO converterItem(ItemOrcamento item) {
        ItemOrcamentoDTO dto = new ItemOrcamentoDTO();
        dto.setServicoNome(item.getServico().getNome());
        dto.setProdutoNome(item.getProduto().getNome());
        dto.setQuantidade(item.getQuantidade());
        dto.setPrecoUnitario(item.getPrecoUnitario());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }

    // Conversão resumida para lista
    private OrcamentoResumoDTO converterParaResumoDTO(Orcamento orcamento) {
        return new OrcamentoResumoDTO(
            orcamento.getId(),
            orcamento.getCliente().getNome(),
            orcamento.getStatus().name(),
            orcamento.getStatusPagamento().name(),
            orcamento.getValorTotal()
        );
    }
}
