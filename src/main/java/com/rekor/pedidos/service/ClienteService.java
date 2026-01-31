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
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarCliente(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> atualizarCliente(Integer id, Cliente dados) {
        return clienteRepository.findById(id).map( cliente -> {
            cliente.setNome(dados.getNome());
            cliente.setTelefone(dados.getTelefone());
            cliente.setEndereco(dados.getEndereco());

            return clienteRepository.save(cliente);
        });
    }

    public boolean deletarCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
