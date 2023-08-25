package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Optional<Cliente> buscarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteNovo) {
        return clienteRepository.findById(id)
            .map(clienteExistente -> {
                clienteExistente.setNome(clienteNovo.getNome());
                clienteExistente.setTipo(clienteNovo.getTipo());
                clienteExistente.setCpf(clienteNovo.getCpf());
                clienteExistente.setCnpj(clienteNovo.getCnpj());
                clienteExistente.setRg(clienteNovo.getRg());
                clienteExistente.setIe(clienteNovo.getIe());
                clienteExistente.setDataCadastro(clienteNovo.getDataCadastro());
                clienteExistente.setAtivo(clienteNovo.isAtivo());
                clienteExistente.setTelefone(clienteNovo.getTelefone());
                return clienteRepository.save(clienteExistente);
            })
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}
