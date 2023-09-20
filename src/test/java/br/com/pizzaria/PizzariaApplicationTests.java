package br.com.pizzaria;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.*;
import br.com.pizzaria.entity.*;
import br.com.pizzaria.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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



	@MockBean
	EnderecoRepository enderecoRepository;
	@Autowired
	EnderecoController enderecoController;

	@MockBean
	EstoqueRepository estoqueRepository;
	@Autowired
	EstoqueController estoqueController;

	@MockBean
	LoginRepository loginRepository;
	@Autowired
	LoginController loginController;

	@MockBean
	SaborRepository saborRepository;
	@Autowired
	SaborController saborController;

	@MockBean
	PizzaRepository pizzaRepository;
	@Autowired
	PizzaController pizzaController;

	@MockBean
	ProdutoRepository produtoRepository;
	@Autowired
	ProdutoController produtoController;

	@MockBean
	PedidoRepository pedidoRepository;
	@Autowired
	PedidoController pedidoController;

	private List<Cliente> clienteList;

	private List<Funcionario> funcionarioList;

	private List<Endereco> enderecoList;

	private List<Estoque> estoqueList;

	private List<Login> loginList;

	private List<Sabor> saborList;

	private List<Pizza> pizzaList;

	private List<Produto> produtoList;

	private List<Pedido> pedidoList;

	@BeforeEach
	void injectData() {
		Cliente cliente = new Cliente(1L, "cliente");
		Cliente cliente2 = new Cliente(2L, "cliente2");
		clienteList = new ArrayList<>();
		clienteList.add(cliente);
		clienteList.add(cliente2);

		Funcionario funcionario = new Funcionario(1L, "funcionario");
		Funcionario funcionario2 = new Funcionario(2L, "funcionario2");
		funcionarioList = new ArrayList<>();
		funcionarioList.add(funcionario);
		funcionarioList.add(funcionario2);

		Endereco endereco = new Endereco(1L,"Rua alou","Vila C",303,"Casa","92452-2342",cliente);
		Endereco endereco2 = new Endereco(2L,"Rua Salve","Vila B",231,"Casa","73254-6243",cliente2);
		enderecoList = new ArrayList<>();
		enderecoList.add(endereco);
		enderecoList.add(endereco2);

		Estoque estoque = new Estoque(1L,10,"Coca-cola");
		Estoque estoque2 = new Estoque(2L,7,"Guaraná");
		estoqueList = new ArrayList<>();
		estoqueList.add(estoque);
		estoqueList.add(estoque2);

		Login login = new Login(1L,"admin","admin");
		Login login2 = new Login(2L,"nimda","nimda");
		loginList = new ArrayList<>();
		loginList.add(login);
		loginList.add(login2);

		Sabor sabor = new Sabor(1L,"Queijo");
		Sabor sabor2 = new Sabor(2L,"Frango");
		saborList = new ArrayList<>();
		saborList.add(sabor);
		saborList.add(sabor2);

		Pizza pizza = new Pizza(1L,saborList, 30, 1, Tamanho.P);
		Pizza pizza2 = new Pizza(2L,saborList,60,2,Tamanho.M);
		pizzaList = new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList.add(pizza2);

		Produto produto = new Produto(1L,1,estoque,20);
		Produto produto2 = new Produto(2L,1,estoque2,30);
		produtoList = new ArrayList<>();
		produtoList.add(produto);
		produtoList.add(produto2);

		Pedido pedido = new Pedido(1L,"Observation",cliente,20,
				Status.ANDAMENTO,pizzaList,produtoList,false,true,false,false, LocalDateTime.now(),funcionario);
		Pedido pedido2 = new Pedido(2L,"Observation2",cliente2,20,
				Status.ANDAMENTO,pizzaList,produtoList,false,true,false,false, LocalDateTime.now(),funcionario2);
		pedidoList = new ArrayList<>();
		pedidoList.add(pedido);
		pedidoList.add(pedido2);

		Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
		Mockito.when(clienteRepository.save(cliente2)).thenReturn(cliente2);
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		Mockito.when(clienteRepository.findById(2L)).thenReturn(Optional.of(cliente2));
		Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);

		Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		Mockito.when(funcionarioRepository.save(funcionario2)).thenReturn(funcionario2);
		Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
		Mockito.when(funcionarioRepository.findById(2L)).thenReturn(Optional.of(funcionario2));
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarioList);

		Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
		Mockito.when(enderecoRepository.save(endereco2)).thenReturn(endereco2);
		Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
		Mockito.when(enderecoRepository.findById(2L)).thenReturn(Optional.of(endereco2));
		Mockito.when(enderecoRepository.findAll()).thenReturn(enderecoList);

		Mockito.when(estoqueRepository.save(estoque)).thenReturn(estoque);
		Mockito.when(estoqueRepository.save(estoque2)).thenReturn(estoque2);
		Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
		Mockito.when(estoqueRepository.findById(2L)).thenReturn(Optional.of(estoque2));
		Mockito.when(estoqueRepository.findAll()).thenReturn(estoqueList);

		Mockito.when(loginRepository.save(login)).thenReturn(login);
		Mockito.when(loginRepository.save(login2)).thenReturn(login2);
		Mockito.when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
		Mockito.when(loginRepository.findById(2L)).thenReturn(Optional.of(login2));
		Mockito.when(loginRepository.findAll()).thenReturn(loginList);

		Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
		Mockito.when(saborRepository.save(sabor2)).thenReturn(sabor2);
		Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
		Mockito.when(saborRepository.findById(2L)).thenReturn(Optional.of(sabor2));
		Mockito.when(saborRepository.findAll()).thenReturn(saborList);

		Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza2);
		Mockito.when(pizzaRepository.save(pizza2)).thenReturn(pizza2);
		Mockito.when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
		Mockito.when(pizzaRepository.findById(2L)).thenReturn(Optional.of(pizza2));
		Mockito.when(pizzaRepository.findAll()).thenReturn(pizzaList);

		Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
		Mockito.when(produtoRepository.save(produto2)).thenReturn(produto2);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
		Mockito.when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
		Mockito.when(produtoRepository.findAll()).thenReturn(produtoList);

		Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
		Mockito.when(pedidoRepository.save(pedido2)).thenReturn(pedido2);
		Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
		Mockito.when(pedidoRepository.findById(2L)).thenReturn(Optional.of(pedido2));
		Mockito.when(pedidoRepository.findAll()).thenReturn(pedidoList);

		Mockito.when(pedidoRepository.findByStatus(Status.ANDAMENTO)).thenReturn(pedidoList);

		Mockito.when(pedidoRepository.findByDelivery(true)).thenReturn(pedidoList);





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
	void testFindAllCliente(){
		ResponseEntity<List<Cliente>> clienteFuncaoController = clienteController.list();
		List<Cliente> clienteListController = clienteFuncaoController.getBody();

		Assert.assertNotNull(clienteListController);
		for(int i = 0; i < clienteList.size();i ++){
			Assert.assertEquals(clienteList.get(i), clienteListController.get(i));
		}
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

	@Test
	void testFindAllFuncinario(){
		ResponseEntity<List<Funcionario>> funcionarioFuncaoController = funcionarioController.list();
		List<Funcionario> funcionarioListController = funcionarioFuncaoController.getBody();

		Assert.assertNotNull(funcionarioListController);
		for(int i = 0; i < funcionarioList.size();i ++){
			Assert.assertEquals(funcionarioList.get(i), funcionarioListController.get(i));
		}

	}

	@Test
	public void testEnderecoCriar(){
		Cliente clienteTest = new Cliente(3L,"clienteTeste");
		var endereco = enderecoController.cadastra(new EnderecoDTO("Rua ola","Vila D",123,"Casa","51232-2342",clienteTest));
		Assert.assertEquals("Registro cadastrado com sucesso", endereco.getBody());

	}

	@Test
	public void testEnderecoPut(){

		Cliente clienteTest = new Cliente(4L,"clienteTeste2");
		EnderecoDTO enderecoDTO = new EnderecoDTO("Rua Hello","Vila P",513,"Casa","62342-2341",clienteTest);
		enderecoDTO.setId(1L);

		var endereco = enderecoController.edita(1L, enderecoDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", endereco.getBody());

	}

	@Test
	void testEnderecoDelete(){
		var endereco = enderecoController.deleta(2L);
		Assert.assertEquals("excluído", endereco.getBody());
	}

	@Test
	void testFindByIdEndereco(){
		Cliente clienteTest = new Cliente(5L,"clienteTeste3");
		enderecoController.cadastra(new EnderecoDTO("Rua ola","Vila D",123,"Casa","51232-2342",clienteTest));
		var endereco = enderecoController.findById(1L);
		Assert.assertEquals(endereco.getBody().getRua(), enderecoController.findById(1L).getBody().getRua());
	}

	@Test
	void testFindAllEndereco(){
		ResponseEntity<List<Endereco>> enderecoFuncaoController = enderecoController.list();
		List<Endereco> enderecoListController = enderecoFuncaoController.getBody();

		Assert.assertNotNull(enderecoListController);
		for(int i = 0; i < enderecoList.size();i ++){
			Assert.assertEquals(enderecoList.get(i), enderecoListController.get(i));
		}

	}

	@Test
	void testCriarEstoque() {
		var estoque = estoqueController.cadastra(new EstoqueDTO(23,"Monster"));
		Assert.assertEquals("Registro cadastrado com sucesso", estoque.getBody());
	}

	@Test
	void testPutEstoque(){
		EstoqueDTO estoqueDTO = new EstoqueDTO(20,"Paçoca");
		estoqueDTO.setId(1L);


		var estoque = estoqueController.edita(1L, estoqueDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", estoque.getBody());
	}

	@Test
	void testDeleteEstoque(){
		var estoque = estoqueController.deleta(2L);
		Assert.assertEquals("excluído", estoque.getBody());
	}

	@Test
	void testFindByIdEstoque(){
		estoqueController.cadastra(new EstoqueDTO(14,"Kuat"));
		var estoque = estoqueController.findById(1L);
		Assert.assertEquals(estoque.getBody().getNome(), estoqueController.findById(1L).getBody().getNome());
	}

	@Test
	void testFindAllEstoque(){
		ResponseEntity<List<Estoque>> estoqueFuncaoController = estoqueController.list();
		List<Estoque> estoqueListController = estoqueFuncaoController.getBody();

		Assert.assertNotNull(estoqueListController);
		for(int i = 0; i < estoqueList.size();i ++){
			Assert.assertEquals(estoqueList.get(i), estoqueListController.get(i));
		}
	}

	@Test
	void testLoginCriar() {
		var login = loginController.cadastra(new LoginDTO("admin","admin"));
		Assert.assertEquals("Registro cadastrado com sucesso", login.getBody());
	}

	@Test
	void testPutLogin(){
		LoginDTO loginDTO = new LoginDTO("admin","admin");
		loginDTO.setId(1L);


		var login = loginController.edita(1L, loginDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", login.getBody());
	}

	@Test
	void testLoginDelete(){
		var login = loginController.deleta(2L);
		Assert.assertEquals("excluído", login.getBody());
	}

	@Test
	void testFindByIdLogin(){
		loginController.cadastra(new LoginDTO("adm","adm"));
		var login = loginController.findById(1L);
		Assert.assertEquals(login.getBody().getLoginn(), loginController.findById(1L).getBody().getLoginn());
	}


	@Test
	void testFindAllLogin(){
		ResponseEntity<List<Login>> loginFuncaoController = loginController.list();
		List<Login> loginListController = loginFuncaoController.getBody();

		Assert.assertNotNull(loginListController);
		for(int i = 0; i < loginList.size();i ++){
			Assert.assertEquals(loginList.get(i), loginListController.get(i));
		}
	}

	@Test
	void testSaborCriar() {
		var sabor = saborController.cadastra(new SaborDTO("Bacon"));
		Assert.assertEquals("Registro cadastrado com sucesso", sabor.getBody());
	}


	@Test
	void testPutSabor(){
		SaborDTO saborDTO = new SaborDTO("Bacon");
		saborDTO.setId(1L);


		var sabor = saborController.edita(1L, saborDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", sabor.getBody());
	}

	@Test
	void testSaborDelete(){
		var sabor = saborController.deleta(2L);
		Assert.assertEquals("excluído", sabor.getBody());
	}

	@Test
	void testFindByIdSabor(){
		saborController.cadastra(new SaborDTO("4 queijos"));
		var sabor = saborController.findById(1L);
		Assert.assertEquals(sabor.getBody().getSaborr(), saborController.findById(1L).getBody().getSaborr());
	}

	@Test
	void testFindAllSabor(){
		ResponseEntity<List<Sabor>> saborFuncaoController = saborController.list();
		List<Sabor> saborListController = saborFuncaoController.getBody();

		Assert.assertNotNull(saborListController);
		for(int i = 0; i < saborList.size();i ++){
			Assert.assertEquals(saborList.get(i), saborListController.get(i));
		}
	}


	@Test
	void testPizzaCriar() {

		var pizza = pizzaController.cadastra(new PizzaDTO(saborList,30,2,Tamanho.M));
		Assert.assertEquals("Registro cadastrado com sucesso", pizza.getBody());
	}

	@Test
	void testPutPizza(){
		PizzaDTO pizzaDTO = new PizzaDTO(saborList,20,1,Tamanho.GG);
		pizzaDTO.setId(1L);


		var pizza = pizzaController.edita(1L, pizzaDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", pizza.getBody());
	}

	@Test
	void testPizzaDelete(){
		var pizza = pizzaController.deleta(2L);
		Assert.assertEquals("excluído", pizza.getBody());
	}

	@Test
	void testFindByIdPizza(){
		pizzaController.cadastra(new PizzaDTO(saborList,20,1,Tamanho.P));
		var pizza = pizzaController.findById(1L);
		Assert.assertEquals(pizza.getBody().getSabores(), pizzaController.findById(1L).getBody().getSabores());
	}

	@Test
	void testFindAllPizza(){
		ResponseEntity<List<Pizza>> pizzaFuncaoController = pizzaController.list();
		List<Pizza> pizzaListController = pizzaFuncaoController.getBody();

		Assert.assertNotNull(pizzaListController);
		for(int i = 0; i < pizzaList.size();i ++){
			Assert.assertEquals(pizzaList.get(i), pizzaListController.get(i));
		}
	}


	@Test
	void testProdutoCriar() {
		Estoque estoqueTest = new Estoque(2L,30,"Trio Pequeno");
		var produto = produtoController.cadastra(new ProdutoDTO(3,estoqueTest,20));
		Assert.assertEquals("Registro cadastrado com sucesso", produto.getBody());
	}

	@Test
	void testPutProduto(){
		Estoque estoque = new Estoque(4L,30,"Trio Pequeno");
		ProdutoDTO produtoDTO = new ProdutoDTO(1,estoque,20);
		produtoDTO.setId(1L);

		var produto = produtoController.edita(1L, produtoDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", produto.getBody());
	}

	@Test
	void testProdutoDelete(){
		var produto = produtoController.deleta(2L);
		Assert.assertEquals("excluído", produto.getBody());
	}

	@Test
	void testFindByIdProduto(){
		Estoque estoque = new Estoque(4L,30,"Trio Pequeno");
		produtoController.cadastra(new ProdutoDTO(1,estoque,20));
		var produto = produtoController.findById(1L);
		Assert.assertEquals(produto.getBody().getEstoques(), produtoController.findById(1L).getBody().getEstoques());
	}

	@Test
	void testFindAllProduto(){
		ResponseEntity<List<Produto>> produtoFuncaoCotroller = produtoController.list();
		List<Produto> produtoListController = produtoFuncaoCotroller.getBody();

		Assert.assertNotNull(produtoListController);
		for(int i = 0; i < produtoList.size();i ++){
			Assert.assertEquals(produtoList.get(i), produtoListController.get(i));
		}
	}


	@Test
	void testPedidoCriar() {
		Cliente clienteTest = new Cliente(1L,"pedro");
		Funcionario funcionarioT = new Funcionario(1L,"salve");

		var pedido = pedidoController.cadastra(new PedidoDTO("nada",clienteTest,20,Status.ANDAMENTO,pizzaList,produtoList,true,false,false,false,LocalDateTime.now(),funcionarioT));
		Assert.assertEquals("Registro cadastrado com sucesso", pedido.getBody());
	}

	@Test
	void testPutPedido(){

		Cliente clienteTest = new Cliente(1L,"pedro");
		Funcionario funcionarioT = new Funcionario(1L,"salve");
		PedidoDTO pedidoDTO = new PedidoDTO("nada",clienteTest,20,Status.ANDAMENTO,pizzaList,produtoList,true,
				false,false,false,LocalDateTime.now(),funcionarioT);
		pedidoDTO.setId(1L);

		var pedido = pedidoController.edita(1L, pedidoDTO);

		Assert.assertEquals("Registro Cadastrado com Sucesso", pedido.getBody());
	}

	@Test
	void testPedidoDelete(){
		var pedido = pedidoController.deleta(2L);
		Assert.assertEquals("excluído", pedido.getBody());
	}

	@Test
	void testFindByIdPedido(){
		Cliente clienteTest = new Cliente(1L,"pedro");
		Funcionario funcionarioT = new Funcionario(1L,"salve");
		pedidoController.cadastra(new PedidoDTO("nada",clienteTest,20,Status.ANDAMENTO,pizzaList,produtoList,true,
				false,false,false,LocalDateTime.now(),funcionarioT));
		var pedido = pedidoController.findById(1L);
		Assert.assertEquals(pedido.getBody().getCliente(), pedidoController.findById(1L).getBody().getCliente());
	}

	@Test
	void testFindAllPedidos(){
		ResponseEntity<List<Pedido>> pedidoFuncaoController = pedidoController.list();
		List<Pedido> pedidoListController = pedidoFuncaoController.getBody();
		System.out.println(pedidoFuncaoController);
		Assert.assertNotNull(pedidoListController);
		for(int i = 0; i < pedidoList.size();i ++){
			Assert.assertEquals(pedidoList.get(i), pedidoListController.get(i));
		}
	}

	@Test
	void testFindAndamentoPedidos(){
		ResponseEntity<List<Pedido>> pedidoFuncaoController = pedidoController.solicitados();
		List<Pedido> pedidoListController = pedidoFuncaoController.getBody();
		System.out.println(pedidoFuncaoController);
		Assert.assertNotNull(pedidoListController);
		for(int i = 0; i < pedidoList.size();i ++){
			Assert.assertEquals(pedidoList.get(i), pedidoListController.get(i));
		}
	}

	@Test
	void testFindDeliveryPedidos() {
		ResponseEntity<?> responseEntity = pedidoController.delivery(true);
		Assert.assertNotNull(responseEntity);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertTrue(responseEntity.getBody() instanceof Map);


	}






}
