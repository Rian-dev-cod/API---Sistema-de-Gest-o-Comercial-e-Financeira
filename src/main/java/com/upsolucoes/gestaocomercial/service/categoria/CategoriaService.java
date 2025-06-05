package com.upsolucoes.gestaocomercial.service.categoria;

import com.upsolucoes.gestaocomercial.model.categoria.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Categoria salvar(Categoria categoria);
    List<Categoria> listar();
    Optional<Categoria> buscarPorId(Long id);
    void excluir(Long id);
    Categoria atualizar(Long id, Categoria categoria);
}
