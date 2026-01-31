package com.rekor.pedidos.service;

import com.rekor.pedidos.model.Cliente;
import com.rekor.pedidos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarCliente(Integer id) {
        return repository.findById(id);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> atualizarCliente(Integer id, Cliente dados) {
        return repository.findById(id).map( cliente -> {
            cliente.setNome(dados.getNome());
            cliente.setTelefone(dados.getTelefone());
            cliente.setEndereco(dados.getEndereco());

            return repository.save(cliente);
        });
    }

    public boolean deletarCliente(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
