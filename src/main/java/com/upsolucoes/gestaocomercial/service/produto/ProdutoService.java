package com.upsolucoes.gestaocomercial.service.produto;

import com.upsolucoes.gestaocomercial.dto.produto.ProdutoDTO;
import com.upsolucoes.gestaocomercial.model.categoria.Categoria;
import com.upsolucoes.gestaocomercial.model.produto.Produto;
import com.upsolucoes.gestaocomercial.repository.categoria.CategoriaRepository;
import com.upsolucoes.gestaocomercial.repository.produto.ProdutoRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto salvar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoUnitario(dto.precoUnitario());
        produto.setImagens(dto.imagens());
        produto.setPromocao(dto.promocao());

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produto = buscarPorId(id);
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoUnitario(dto.precoUnitario());
        produto.setImagens(dto.imagens());
        produto.setPromocao(dto.promocao());

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public Page<Produto> listarComFiltro(String nome, Long categoriaId, Double precoMin, Double precoMax, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(getSortOrder(sort)));

        if (nome != null && !nome.isBlank()) {
            return produtoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else if (categoriaId != null) {
            return produtoRepository.findByCategoriaId(categoriaId, pageable);
        } else if (precoMin != null && precoMax != null) {
            return produtoRepository.findByPrecoUnitarioBetween(precoMin, precoMax, pageable);
        }

        return produtoRepository.findAll(pageable);
    }

    private Sort.Order getSortOrder(String sortParam) {
        if (sortParam != null && sortParam.contains(",")) {
            String[] parts = sortParam.split(",");
            return new Sort.Order(Sort.Direction.fromString(parts[1]), parts[0]);
        }
        return new Sort.Order(Sort.Direction.ASC, "id");
    }

    public List<Produto> listarTodosSemPaginacao() {
        return produtoRepository.findAll();
    }
}
