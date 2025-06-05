package com.upsolucoes.gestaocomercial.dto.orcamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrcamentoResponseDTO {

    private Long id;
    private String clienteNome;
    private LocalDate dataCriacao;
    private String status;
    private String statusPagamento;
    private BigDecimal valorTotal;
    private PagamentoDTO pagamento;
    private List<ItemOrcamentoDTO> itens;

    // === DTO interno: Itens do orçamento ===
    public static class ItemOrcamentoDTO {
        private String servicoNome;
        private String produtoNome;
        private Integer quantidade;
        private BigDecimal precoUnitario;
        private BigDecimal subtotal;

        public String getServicoNome() {
            return servicoNome;
        }

        public void setServicoNome(String servicoNome) {
            this.servicoNome = servicoNome;
        }

        public String getProdutoNome() {
            return produtoNome;
        }

        public void setProdutoNome(String produtoNome) {
            this.produtoNome = produtoNome;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }

        public BigDecimal getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(BigDecimal precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }

    // === DTO interno: Detalhes do pagamento ===
    public static class PagamentoDTO {
        private String tipo;
        private BigDecimal valor;
        private LocalDate dataPagamento;

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public LocalDate getDataPagamento() {
            return dataPagamento;
        }

        public void setDataPagamento(LocalDate dataPagamento) {
            this.dataPagamento = dataPagamento;
        }
    }

    // === Getters e Setters principais ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }

    public List<ItemOrcamentoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemOrcamentoDTO> itens) {
        this.itens = itens;
    }
}
