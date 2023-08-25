package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class DemoApplicationTests {

	 @Autowired
	    private ClienteService clienteService;

	    @Test
	    @Transactional
	    public void testAdicionarCliente() {
	        Cliente novoCliente = new Cliente();
	        novoCliente.setNome("Cliente Teste");
	        novoCliente.setTipo("Pessoa Física");
	        novoCliente.setCpf("12345678900");
	        novoCliente.setRg("1234567");
	        novoCliente.setDataCadastro(new Date());
	        novoCliente.setAtivo(true);
	        novoCliente.setTelefones(new ArrayList<>());

	        Cliente clienteSalvo = clienteService.adicionarCliente(novoCliente);

	        assertThat(clienteSalvo.getId()).isNotNull();
	    }

	    @Test
	    @Transactional
	    public void testBuscarClientePorId() {
	        Cliente cliente = new Cliente();
	        cliente.setNome("Cliente Teste");
	        cliente.setTipo("Pessoa Física");
	        cliente.setCpf("12345678900");
	        cliente.setRg("1234567");
	        cliente.setDataCadastro(new Date());
	        cliente.setAtivo(true);
	        cliente.setTelefones(new ArrayList<>());

	        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

	        Cliente clienteEncontrado = clienteService.buscarClientePorId(clienteSalvo.getId()).orElse(null);
	        assertThat(clienteEncontrado).isNotNull();
	    }

	    @Test
	    @Transactional
	    public void testAtualizarCliente() {
	        Cliente cliente = new Cliente();
	        cliente.setNome("Cliente Teste");
	        cliente.setTipo("Pessoa Física");
	        cliente.setCpf("12345678900");
	        cliente.setRg("1234567");
	        cliente.setDataCadastro(new Date());
	        cliente.setAtivo(true);
	        cliente.setTelefones(new ArrayList<>());

	        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

	        clienteSalvo.setNome("Cliente Atualizado");
	        Cliente clienteAtualizado = clienteService.atualizarCliente(clienteSalvo.getId(), clienteSalvo);

	        assertThat(clienteAtualizado.getNome()).isEqualTo("Cliente Atualizado");
	    }

	    @Test
	    @Transactional
	    public void testExcluirCliente() {
	        Cliente cliente = new Cliente();
	        cliente.setNome("Cliente Teste");
	        cliente.setTipo("Pessoa Física");
	        cliente.setCpf("12345678900");
	        cliente.setRg("1234567");
	        cliente.setDataCadastro(new Date());
	        cliente.setAtivo(true);
	        cliente.setTelefones(new ArrayList<>());

	        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);
	        clienteService.excluirCliente(clienteSalvo.getId());

	        Cliente clienteExcluido = clienteService.buscarClientePorId(clienteSalvo.getId()).orElse(null);
	        assertThat(clienteExcluido).isNull();
	    }
}
