package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.PizzaDTO;
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
class PizzaIntegracaoTests {




    @MockBean
    SaborRepository saborRepository;


    @MockBean
    PizzaRepository pizzaRepository;
    @Autowired
    PizzaController pizzaController;

    private List<Sabor> saborList;

    private List<Pizza> pizzaList;



    @BeforeEach
    void injectData() {


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
}
