package br.com.pizzaria.DtoTests;

import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.dto.SaborDTO;
import br.com.pizzaria.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
 class PedidoDtoTests {

    private List<Pizza> pizzaList = new ArrayList<>();
    private List<Produto> produtoList = new ArrayList<>();

    private Cliente cliente;
    private Funcionario funcionario;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
    
    String dataString = "2023-09-20T02:03:38.724796100";
    LocalDateTime dataManual = LocalDateTime.parse(dataString, formatter);
    PedidoDTO pedido = new PedidoDTO("Nenhuma",cliente,20, Status.ANDAMENTO,pizzaList,produtoList,false,false,false,false, dataManual,funcionario);
    PedidoDTO pedido2 = new PedidoDTO();

    @Test
    void getset1Preco(){
        pedido.setPreco(20);
        Assertions.assertEquals(20,pedido.getPreco());
    }
    @Test
    void getset2Cadastro(){
        String dataString2 = "2024-09-20T02:03:38.724796100";
        LocalDateTime dataManual2 = LocalDateTime.parse(dataString2, formatter);
        pedido.setCadastro(dataManual2);
        Assertions.assertEquals(dataManual2,pedido.getCadastro());
    }
    @Test
    void getset3Cancelado(){
        pedido.setCancelado(true);
        Assertions.assertTrue(pedido.isCancelado());
    }
    @Test
    void getset4Entrega(){
        pedido.setEntrega(true);
        Assertions.assertTrue(pedido.isEntrega());
    }

    @Test
    void getset5Delivery(){
        pedido.setDelivery(true);
        Assertions.assertTrue(pedido.isDelivery());
    }
    @Test
    void getsetNome6Obervacoes(){
        pedido.setObservacoes("Oi");
        Assertions.assertEquals("Oi",pedido.getObservacoes());
    }
    @Test
    void getsetNome7Cartao(){
        pedido.setPagamentoCartao(true);
        Assertions.assertTrue(pedido.isPagamentoCartao());
    }
    @Test
    void getsetNome8Status(){
        pedido.setStatus(Status.ANDAMENTO);
        Assertions.assertEquals(Status.ANDAMENTO,pedido.getStatus());
    }
    @Test
    void testconstrutorvazio(){
        PedidoDTO pedido3 = new PedidoDTO();
        Assertions.assertEquals(pedido2,pedido3);
    }

    @Test
    void testeid(){
        pedido.setId(2L);
        Assertions.assertEquals(2,pedido.getId());
    }

    @Test
    void testeComparacao(){
        PedidoDTO pedidoDTO2 = new PedidoDTO("Nenhuma",cliente,20, Status.ANDAMENTO,pizzaList,produtoList,false,false,false,false, dataManual,funcionario);
        Assertions.assertEquals(pedido,pedidoDTO2);
    }

    @Test
    void testhashCode(){
        PedidoDTO pedidoDTO1 = new PedidoDTO("Nenhuma",cliente,20, Status.ANDAMENTO,pizzaList,produtoList,false,false,false,false, dataManual,funcionario);
        PedidoDTO pedidoDTO2 = new PedidoDTO("Nenhuma",cliente,20, Status.ANDAMENTO,pizzaList,produtoList,false,false,false,false, dataManual,funcionario);

        Assertions.assertEquals(pedidoDTO1.hashCode(),pedidoDTO2.hashCode());
    }

    @Test
    void testToString(){
        PedidoDTO pedidoDTO2 = new PedidoDTO("Nenhuma",cliente,20, Status.ANDAMENTO,pizzaList,produtoList,false,false,false,false, dataManual,funcionario);
        Assertions.assertEquals(pedidoDTO2.toString(),pedido.toString());

    }





}
