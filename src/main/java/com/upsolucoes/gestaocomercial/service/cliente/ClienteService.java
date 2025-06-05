package com.upsolucoes.gestaocomercial.service.cliente;

import com.upsolucoes.gestaocomercial.model.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(Long id);
    void deletar(Long id);
    Page<Cliente> buscarPorNomeOuCidade(String nomeOuCidade, Pageable pageable);
    List<Cliente> salvarTodos(List<Cliente> clientes);
    Optional<Cliente> buscarPorCpf(String cpf);
}
