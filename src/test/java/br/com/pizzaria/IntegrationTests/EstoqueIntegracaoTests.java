package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.EstoqueDTO;
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
class EstoqueIntegracaoTests {


    @MockBean
    EstoqueRepository estoqueRepository;
    @Autowired
    EstoqueController estoqueController;



    private List<Estoque> estoqueList;



    @BeforeEach
    void injectData() {


        Estoque estoque = new Estoque(1L,10,"Coca-cola");
        Estoque estoque2 = new Estoque(2L,7,"Guaraná");
        estoqueList = new ArrayList<>();
        estoqueList.add(estoque);
        estoqueList.add(estoque2);



        Mockito.when(estoqueRepository.save(estoque)).thenReturn(estoque);
        Mockito.when(estoqueRepository.save(estoque2)).thenReturn(estoque2);
        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        Mockito.when(estoqueRepository.findById(2L)).thenReturn(Optional.of(estoque2));
        Mockito.when(estoqueRepository.findAll()).thenReturn(estoqueList);


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
}
