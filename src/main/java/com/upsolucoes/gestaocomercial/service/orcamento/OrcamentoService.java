package com.upsolucoes.gestaocomercial.service.orcamento;

import com.upsolucoes.gestaocomercial.model.cliente.Cliente;
import com.upsolucoes.gestaocomercial.model.orcamento.ItemOrcamento;
import com.upsolucoes.gestaocomercial.model.orcamento.Orcamento;
import com.upsolucoes.gestaocomercial.model.produto.Produto;
import com.upsolucoes.gestaocomercial.model.servico.Servico;
import com.upsolucoes.gestaocomercial.repository.cliente.ClienteRepository;
import com.upsolucoes.gestaocomercial.repository.orcamento.OrcamentoRepository;
import com.upsolucoes.gestaocomercial.repository.produto.ProdutoRepository;
import com.upsolucoes.gestaocomercial.repository.servico.ServicoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ServicoRepository servicoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository,
                            ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository,
                            ServicoRepository servicoRepository) {
        this.orcamentoRepository = orcamentoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.servicoRepository = servicoRepository;
    }

    public Orcamento salvar(Orcamento orcamento) {
        Cliente cliente = clienteRepository.findById(orcamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + orcamento.getCliente().getId()));
        orcamento.setCliente(cliente);

        BigDecimal valorTotal = BigDecimal.ZERO;

        if (orcamento.getItens() != null) {
            for (ItemOrcamento item : orcamento.getItens()) {
                Produto produto = produtoRepository.findById(item.getProduto().getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + item.getProduto().getId()));
                Servico servico = servicoRepository.findById(item.getServico().getId())
                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + item.getServico().getId()));

                item.setProduto(produto);
                item.setServico(servico);
                item.setOrcamento(orcamento);

                BigDecimal precoUnitario = produto.getPrecoUnitario().add(servico.getPrecoBase());
                item.setPrecoUnitario(precoUnitario);

                BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(item.getQuantidade()));
                item.setSubtotal(subtotal);

                valorTotal = valorTotal.add(subtotal);
            }
        }

        orcamento.setValorTotal(valorTotal);
        return orcamentoRepository.save(orcamento);
    }

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.findAll();
    }

    public Optional<Orcamento> buscarPorId(Long id) {
        return orcamentoRepository.findById(id);
    }

    public void deletar(Long id) {
        orcamentoRepository.deleteById(id);
    }
}
