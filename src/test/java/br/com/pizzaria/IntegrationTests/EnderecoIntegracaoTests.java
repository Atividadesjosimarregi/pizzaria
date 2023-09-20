package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.entity.*;
import br.com.pizzaria.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class EnderecoIntegracaoTests {





    @MockBean
    EnderecoRepository enderecoRepository;
    @Autowired
    EnderecoController enderecoController;


    private List<Cliente> clienteList;

    private List<Endereco> enderecoList;



    @BeforeEach
    void injectData() {
        Cliente cliente = new Cliente(1L, "cliente");
        Cliente cliente2 = new Cliente(2L, "cliente2");
        clienteList = new ArrayList<>();
        clienteList.add(cliente);
        clienteList.add(cliente2);



        Endereco endereco = new Endereco(1L,"Rua alou","Vila C",303,"Casa","92452-2342",cliente);
        Endereco endereco2 = new Endereco(2L,"Rua Salve","Vila B",231,"Casa","73254-6243",cliente2);
        enderecoList = new ArrayList<>();
        enderecoList.add(endereco);
        enderecoList.add(endereco2);





        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.save(endereco2)).thenReturn(endereco2);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoRepository.findById(2L)).thenReturn(Optional.of(endereco2));
        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecoList);

    }

    @Test
     void testEnderecoCriar(){
        Cliente clienteTest = new Cliente(3L,"clienteTeste");
        var endereco = enderecoController.cadastra(new EnderecoDTO("Rua ola","Vila D",123,"Casa","51232-2342",clienteTest));
        Assert.assertEquals("Registro cadastrado com sucesso", endereco.getBody());

    }

    @Test
     void testEnderecoCriarErrado(){
        Cliente clienteTest = new Cliente(3L,"clienteTeste");
        var endereco = enderecoController.cadastra(new EnderecoDTO());
        Assert.assertEquals("ERror: Endereço não pode ser nulo", endereco.getBody());

    }

    @Test
     void testEnderecoPut(){

        Cliente clienteTest = new Cliente(4L,"clienteTeste2");
        EnderecoDTO enderecoDTO = new EnderecoDTO("Rua Hello","Vila P",513,"Casa","62342-2341",clienteTest);
        enderecoDTO.setId(1L);

        var endereco = enderecoController.edita(1L, enderecoDTO);

        Assert.assertEquals("Registro Cadastrado com Sucesso", endereco.getBody());

    }
    @Test
     void testEnderecoPutErrado(){

        Cliente clienteTest = new Cliente(4L,"clienteTeste2");
        EnderecoDTO enderecoDTO = new EnderecoDTO("Rua Hello","Vila P",513,"Casa","62342-2341",clienteTest);
        enderecoDTO.setId(1L);

        var endereco = enderecoController.edita(10L, enderecoDTO);

        Assert.assertEquals("Nao foi possivel indentificar o registro informado", endereco.getBody());

    }

    @Test
    void testEnderecoDelete(){
        var endereco = enderecoController.deleta(2L);
        Assert.assertEquals("excluído", endereco.getBody());
    }
    @Test
    void testEnderecoDeleteErrado(){
        var endereco = enderecoController.deleta(10L);
        Assert.assertEquals("ERRor: Não foi possivel encontrar o registro informado", endereco.getBody());
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
}
