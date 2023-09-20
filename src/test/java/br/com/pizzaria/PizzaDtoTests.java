package br.com.pizzaria;

import br.com.pizzaria.dto.PizzaDTO;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.entity.Tamanho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PizzaDtoTests {

    private List<Sabor> saborList = new ArrayList<>();
    PizzaDTO pizza = new PizzaDTO(saborList,20,2, Tamanho.P);

    @Test
    void getsetNome(){
        pizza.setQuantidade(3);
        Assertions.assertEquals(3,pizza.getQuantidade());
    }

    @Test
    void getsetNomes2(){
        pizza.setPreco(20);
        Assertions.assertEquals(20,pizza.getPreco());
    }
    @Test
    void getsetNomes3(){
        pizza.setTamanho(Tamanho.M);
        Assertions.assertEquals(Tamanho.P,pizza.getTamanho());
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
