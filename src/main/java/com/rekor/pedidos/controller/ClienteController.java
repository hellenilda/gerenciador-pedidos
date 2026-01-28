package com.rekor.pedidos.controller;

import com.rekor.pedidos.model.Cliente;
import com.rekor.pedidos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @PostMapping
    private ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente salvo = repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente dados) {
        return repository.findById(id).map( cliente -> {
            cliente.setNome(dados.getNome());
            cliente.setCpf(dados.getCpf());
            cliente.setEndereco(dados.getEndereco());

            Cliente atualizado = repository.save(cliente);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable Integer id) {
        return repository.findById(id).map( cliente -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
