package com.upsolucoes.gestaocomercial.service.impl.cliente;

import com.upsolucoes.gestaocomercial.model.cliente.Cliente;
import com.upsolucoes.gestaocomercial.repository.cliente.ClienteRepository;
import com.upsolucoes.gestaocomercial.service.cliente.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        if (cliente.getDataCadastro() == null) {
            cliente.setDataCadastro(java.time.LocalDate.now());
        }
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Cliente> buscarPorNomeOuCidade(String nomeOuCidade, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCaseOrEnderecoContainingIgnoreCase(nomeOuCidade, nomeOuCidade, pageable);
    }

    @Override
    public List<Cliente> salvarTodos(List<Cliente> clientes) {
        clientes.forEach(c -> {
            if (c.getDataCadastro() == null) {
                c.setDataCadastro(java.time.LocalDate.now());
            }
        });
        return repository.saveAll(clientes);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
