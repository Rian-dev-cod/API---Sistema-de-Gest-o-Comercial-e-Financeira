package com.upsolucoes.gestaocomercial.controller.cliente;

import com.upsolucoes.gestaocomercial.model.cliente.Cliente;
import com.upsolucoes.gestaocomercial.service.cliente.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;
    private final Map<String, Cliente> sessoes = new HashMap<>();

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.salvar(cliente));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Cliente>> salvarLote(@RequestBody List<Cliente> clientes) {
        return ResponseEntity.ok(service.salvarTodos(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return service.buscarPorId(id).map(c -> {
            clienteAtualizado.setId(id);
            return ResponseEntity.ok(service.salvar(clienteAtualizado));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Cliente>> buscarPorNomeOuCidade(
            @RequestParam(defaultValue = "") String nomeOuCidade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.buscarPorNomeOuCidade(nomeOuCidade, pageable));
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente credenciais) {
        return service.buscarPorCpf(credenciais.getCpf())
                .map(cliente -> {
                    if (cliente.getSenha().equals(credenciais.getSenha())) {
                        String token = UUID.randomUUID().toString();
                        sessoes.put(token, cliente);
                        Map<String, Object> resposta = new HashMap<>();
                        resposta.put("token", token);
                        resposta.put("cliente", cliente);
                        return ResponseEntity.ok(resposta);
                    } else {
                        return ResponseEntity.status(401).body("Senha incorreta");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Cliente não encontrado"));
    }

    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) {
        if (sessoes.remove(token) != null) {
            return ResponseEntity.ok("Logout efetuado com sucesso");
        }
        return ResponseEntity.status(401).body("Token inválido");
    }
}
