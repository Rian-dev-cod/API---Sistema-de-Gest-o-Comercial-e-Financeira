package com.upsolucoes.gestaocomercial.dto.orcamento;

import java.math.BigDecimal;

public class OrcamentoResumoDTO {

    private Long id;
    private String clienteNome;
    private String status;
    private String statusPagamento;
    private BigDecimal valorTotal;

    public OrcamentoResumoDTO() {
    }

    public OrcamentoResumoDTO(Long id, String clienteNome, String status, String statusPagamento, BigDecimal valorTotal) {
        this.id = id;
        this.clienteNome = clienteNome;
        this.status = status;
        this.statusPagamento = statusPagamento;
        this.valorTotal = valorTotal;
    }

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
}
