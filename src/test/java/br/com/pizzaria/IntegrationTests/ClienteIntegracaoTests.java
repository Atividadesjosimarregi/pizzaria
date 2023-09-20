package br.com.pizzaria.IntegrationTests;

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

import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@SpringBootTest
class ClienteIntegracaoTests {


    @MockBean
    ClienteRepository clienteRepository;
    @Autowired
    ClienteController clienteController;


    private List<Cliente> clienteList;



    @BeforeEach
    void injectData() {
        Cliente cliente = new Cliente(1L, "cliente");
        Cliente cliente2 = new Cliente(2L, "cliente2");
        clienteList = new ArrayList<>();
        clienteList.add(cliente);
        clienteList.add(cliente2);



        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(clienteRepository.save(cliente2)).thenReturn(cliente2);
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(clienteRepository.findById(2L)).thenReturn(Optional.of(cliente2));
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);






    }

    @Test
    void testClienteCriar() {
        var cliente = clienteController.cadastra(new ClienteDTO("Josimar"));
        Assert.assertEquals("Registro cadastrado com sucesso", cliente.getBody());
    }

    @Test
    void testClienteCriarErrado() {
        var cliente = clienteController.cadastra(new ClienteDTO());
        Assert.assertEquals("Error: Nome não pode ser nulo", cliente.getBody());
    }

    @Test
    void testPutCliente(){
        ClienteDTO clienteDTO = new ClienteDTO("João");
        clienteDTO.setId(1L);


        var cliente = clienteController.edita(1L, clienteDTO);

        Assert.assertEquals("Registro cadastrado com sucesso", cliente.getBody());
    }

    @Test
    void testPutClienteErrado(){
        ClienteDTO clienteDTO = new ClienteDTO("João");


        var cliente = clienteController.edita(1L, clienteDTO);

        Assert.assertEquals("Nao foi possivel indentificar o registro informado", cliente.getBody());
    }



    @Test
    void testDeleteCliente(){
        var cliente = clienteController.deleta(2L);
        Assert.assertEquals("excluído", cliente.getBody());
    }

    @Test
    void testDeleteClienteErrado(){
        var cliente = clienteController.deleta(10L);
        Assert.assertEquals("Nao foi possivel indentificar o registro informado", cliente.getBody());
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
}
