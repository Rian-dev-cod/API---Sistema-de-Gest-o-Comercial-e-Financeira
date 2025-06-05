package com.upsolucoes.gestaocomercial.model.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upsolucoes.gestaocomercial.model.orcamento.Orcamento;

import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo; // CARTAO_CREDITO, CARTAO_DEBITO, PIX, BOLETO, DINHEIRO

    private BigDecimal valor;

    private LocalDate dataPagamento;

    @OneToOne
    @JoinColumn(name = "orcamento_id")
    @JsonIgnoreProperties({"itens", "pagamento"})
    private Orcamento orcamento;

    public Pagamento() {
    }

    public Pagamento(TipoPagamento tipo, BigDecimal valor, LocalDate dataPagamento, Orcamento orcamento) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.orcamento = orcamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagamento tipo) {
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

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
}
