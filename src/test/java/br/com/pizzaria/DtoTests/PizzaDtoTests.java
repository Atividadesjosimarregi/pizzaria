package br.com.pizzaria.DtoTests;

import br.com.pizzaria.dto.PizzaDTO;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.entity.Tamanho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
 class PizzaDtoTests {

    private List<Sabor> saborList = new ArrayList<>();
    PizzaDTO pizza = new PizzaDTO(saborList,20,2, Tamanho.P);
    PizzaDTO pizza2 = new PizzaDTO();

    @Test
    void getset1Quantidade(){
        pizza.setQuantidade(3);
        Assertions.assertEquals(3,pizza.getQuantidade());
    }


    @Test
    void getset2Preco(){
        pizza.setPreco(20);
        Assertions.assertEquals(20,pizza.getPreco());
    }
    @Test
    void getset3Tamanho(){
        pizza.setTamanho(Tamanho.M);
        Assertions.assertEquals(Tamanho.M,pizza.getTamanho());
    }
    @Test
    void testconstrutorvazio(){
        PizzaDTO pizza3 = new PizzaDTO();
        Assertions.assertEquals(pizza2,pizza3);
    }

    @Test
    void testeid(){
        pizza.setId(2L);
        Assertions.assertEquals(2,pizza.getId());
    }

    @Test
    void testeComparacao(){
        PizzaDTO pizza2 = new PizzaDTO(saborList,20,2,Tamanho.P);
        Assertions.assertEquals(pizza,pizza2);
    }

    @Test
    void testhashCode(){
        PizzaDTO pizzaDTO1 = new PizzaDTO(saborList,21,2,Tamanho.P);
        PizzaDTO pizzaDTO2 = new PizzaDTO(saborList,21,2,Tamanho.P);

        Assertions.assertEquals(pizzaDTO1.hashCode(),pizzaDTO2.hashCode());
    }

    @Test
    void testToString(){
        PizzaDTO pizzaDTO2 = new PizzaDTO(saborList,20,2,Tamanho.P);
        Assertions.assertEquals(pizzaDTO2.toString(),pizza.toString());

    }

}
