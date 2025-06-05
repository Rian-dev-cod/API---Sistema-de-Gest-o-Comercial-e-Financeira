package com.upsolucoes.gestaocomercial.repository.cliente;

import com.upsolucoes.gestaocomercial.model.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByNomeContainingIgnoreCaseOrEnderecoContainingIgnoreCase(String nome, String endereco, Pageable pageable);
    Optional<Cliente> findByCpf(String cpf);
}
