package com.upsolucoes.gestaocomercial.service.impl.categoria;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upsolucoes.gestaocomercial.model.categoria.Categoria;
import com.upsolucoes.gestaocomercial.repository.categoria.CategoriaRepository;
import com.upsolucoes.gestaocomercial.service.categoria.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Categoria atualizar(Long id, Categoria categoria) {
        return repository.findById(id)
                .map(c -> {
                    c.setNome(categoria.getNome());
                    return repository.save(c);
                }).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }
}