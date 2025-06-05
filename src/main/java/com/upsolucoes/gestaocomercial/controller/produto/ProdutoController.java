package com.upsolucoes.gestaocomercial.controller.produto;

import com.upsolucoes.gestaocomercial.dto.produto.ProdutoDTO;
import com.upsolucoes.gestaocomercial.model.produto.Produto;
import com.upsolucoes.gestaocomercial.service.produto.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Produto>> listarTodosSemPaginacao() {
        return ResponseEntity.ok(service.listarTodosSemPaginacao());
    }


    @GetMapping
    public ResponseEntity<Page<Produto>> listarComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        return ResponseEntity.ok(service.listarComFiltro(nome, categoriaId, precoMin, precoMax, page, size, sort));
    }
}
