package br.com.pizzaria;

import br.com.pizzaria.controller.ClienteController;
import br.com.pizzaria.controller.FuncionarioController;
import br.com.pizzaria.dto.ClienteDTO;
import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Funcionario;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;

import java.util.Optional;

@SpringBootTest
class PizzariaApplicationTests {

	@MockBean
	ClienteRepository clienteRepository;
	@Autowired
	ClienteController clienteController;
	@MockBean
	FuncionarioRepository funcionarioRepository;
	@Autowired
	FuncionarioController funcionarioController;

	@BeforeEach
	void injectData() {
		Cliente cliente = new Cliente(1L, "cliente");
		Cliente cliente2 = new Cliente(2L, "cliente2");
		Funcionario funcionario = new Funcionario(1L, "funcionario");
		Funcionario funcionario2 = new Funcionario(2L, "funcionario2");

		Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
		Mockito.when(clienteRepository.save(cliente2)).thenReturn(cliente2);
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		Mockito.when(clienteRepository.findById(2L)).thenReturn(Optional.of(cliente2));

		Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		Mockito.when(funcionarioRepository.save(funcionario2)).thenReturn(funcionario2);
		Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
		Mockito.when(funcionarioRepository.findById(2L)).thenReturn(Optional.of(funcionario2));
	}

	@Test
	void testClienteCriar() {
		var cliente = clienteController.cadastra(new ClienteDTO("Josimar"));
		Assert.assertEquals("Registro cadastrado com sucesso", cliente.getBody());
	}

	@Test
	void testPutCliente(){
		ClienteDTO clienteDTO = new ClienteDTO("João");
		clienteDTO.setId(1L);


		var cliente = clienteController.edita(1L, clienteDTO);

		Assert.assertEquals("Registro cadastrado com sucesso", cliente.getBody());
	}



	@Test
	void testDeleteCliente(){
		var cliente = clienteController.deleta(2L);
		Assert.assertEquals("excluído", cliente.getBody());
	}


	@Test
	void testFindByIdCliente(){
		clienteController.cadastra(new ClienteDTO("Josimar"));
		var cliente = clienteController.findById(1L);
		Assert.assertEquals(cliente.getBody().getNome(), clienteController.findById(1L).getBody().getNome());
	}

	@Test
	void testFuncionarioCriar() {
		var funcionario = funcionarioController.cadastra(new FuncionarioDTO("Funcionario41"));
		Assert.assertEquals("Registro cadastrado com sucesso", funcionario.getBody());
	}

	@Test
	void testPutFuncionario(){

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO("Funcionario43");
		funcionarioDTO.setId(1L);

		var funcionario = funcionarioController.edita(1L, funcionarioDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", funcionario.getBody());
	}

	@Test
	void testDeleteFuncionario(){
		var funcionario = funcionarioController.deleta(2L);
		Assert.assertEquals("excluído", funcionario.getBody());
	}

	@Test
	void testFindByIdFuncionario(){
		funcionarioController.cadastra(new FuncionarioDTO("Robson"));
		var funcionario = funcionarioController.findById(1L);
		Assert.assertEquals(funcionario.getBody().getNome(), funcionarioController.findById(1L).getBody().getNome());
	}


}
