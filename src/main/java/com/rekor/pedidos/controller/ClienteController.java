package com.rekor.pedidos.controller;

import com.rekor.pedidos.model.Cliente;
import com.rekor.pedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public Optional<Cliente> buscarCliente(@PathVariable Integer id) {
        return service.buscarCliente(id);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastraCliente(@RequestBody Cliente cliente) {
        Cliente salvo = service.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public Optional<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente dados) {
        return service.atualizarCliente(id, dados);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCliente(@PathVariable Integer id) {
        return service.deletarCliente(id);
    }
}
