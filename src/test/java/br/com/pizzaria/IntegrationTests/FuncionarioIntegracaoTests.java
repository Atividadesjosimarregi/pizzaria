package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.FuncionarioDTO;
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
class FuncionarioIntegracaoTests {



    @MockBean
    FuncionarioRepository funcionarioRepository;
    @Autowired
    FuncionarioController funcionarioController;





    private List<Funcionario> funcionarioList;



    @BeforeEach
    void injectData() {


        Funcionario funcionario = new Funcionario(1L, "funcionario");
        Funcionario funcionario2 = new Funcionario(2L, "funcionario2");
        funcionarioList = new ArrayList<>();
        funcionarioList.add(funcionario);
        funcionarioList.add(funcionario2);


        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        Mockito.when(funcionarioRepository.save(funcionario2)).thenReturn(funcionario2);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        Mockito.when(funcionarioRepository.findById(2L)).thenReturn(Optional.of(funcionario2));
        Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarioList);



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
}
