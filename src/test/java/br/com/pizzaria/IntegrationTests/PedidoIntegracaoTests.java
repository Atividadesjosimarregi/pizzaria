package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.*;
import br.com.pizzaria.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@SpringBootTest
class PedidoIntegracaoTests {




    @MockBean
    ClienteRepository clienteRepository;

    @MockBean
    FuncionarioRepository funcionarioRepository;




    @MockBean
    EnderecoRepository enderecoRepository;


    @MockBean
    EstoqueRepository estoqueRepository;


    @MockBean
    LoginRepository loginRepository;


    @MockBean
    SaborRepository saborRepository;


    @MockBean
    PizzaRepository pizzaRepository;


    @MockBean
    ProdutoRepository produtoRepository;


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
    void testPedidoCriar() {
        Cliente clienteTest = new Cliente(1L,"pedro");
        Funcionario funcionarioT = new Funcionario(1L,"salve");

        var pedido = pedidoController.cadastra(new PedidoDTO("nada",clienteTest,20,Status.ANDAMENTO,pizzaList,produtoList,true,false,false,false,LocalDateTime.now(),funcionarioT));
        Assert.assertEquals("Registro cadastrado com sucesso", pedido.getBody());
    }

    @Test
    void testPedidoCriarErrado() {
        Cliente clienteTest = new Cliente(1L,"pedro");
        Funcionario funcionarioT = new Funcionario(1L,"salve");

        var pedido = pedidoController.cadastra(new PedidoDTO());
        Assert.assertEquals("Error: Cliente não pode ser nulo", pedido.getBody());
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
    void testPutPedidoErrado(){

        Cliente clienteTest = new Cliente(1L,"pedro");
        Funcionario funcionarioT = new Funcionario(1L,"salve");
        PedidoDTO pedidoDTO = new PedidoDTO("nada",clienteTest,20,Status.ANDAMENTO,pizzaList,produtoList,true,
                false,false,false,LocalDateTime.now(),funcionarioT);
        pedidoDTO.setId(1L);

        var pedido = pedidoController.edita(10L, pedidoDTO);

        Assert.assertEquals("Nao foi possivel indentificar o registro informado", pedido.getBody());
    }

    @Test
    void testPedidoDelete(){
        var pedido = pedidoController.deleta(2L);
        Assert.assertEquals("excluído", pedido.getBody());
    }

    @Test
    void testPedidoDeleteErrado(){
        var pedido = pedidoController.deleta(20L);
        Assert.assertEquals("ERRor: Não foi possivel identificar o registro informado", pedido.getBody());
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