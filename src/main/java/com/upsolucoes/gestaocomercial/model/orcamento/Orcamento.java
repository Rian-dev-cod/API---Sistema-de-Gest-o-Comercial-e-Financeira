package com.upsolucoes.gestaocomercial.model.orcamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upsolucoes.gestaocomercial.model.cliente.Cliente;
import com.upsolucoes.gestaocomercial.model.pagamento.Pagamento;
import com.upsolucoes.gestaocomercial.model.pagamento.StatusPagamento;

import jakarta.persistence.*;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

    private BigDecimal valorTotal;

    @OneToOne(mappedBy = "orcamento", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("orcamento")
    private Pagamento pagamento;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrcamento> itens;

    public Orcamento() {
        this.dataCriacao = LocalDate.now();
        this.status = StatusOrcamento.ABERTO;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public Orcamento(Long id, Cliente cliente, LocalDate dataCriacao, StatusOrcamento status, BigDecimal valorTotal, List<ItemOrcamento> itens) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.valorTotal = valorTotal;
        this.itens = itens;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusOrcamento getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamento status) {
        this.status = status;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public List<ItemOrcamento> getItens() {
        return itens;
    }

    public void setItens(List<ItemOrcamento> itens) {
        this.itens = itens;
    }
}
