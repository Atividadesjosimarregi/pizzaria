package br.com.pizzaria;

import br.com.pizzaria.dto.ClienteDTO;
import br.com.pizzaria.dto.EstoqueDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstoqueDtoTests {

    EstoqueDTO estoque = new EstoqueDTO(10,"coca");

    @Test
    void getsetPreco(){
        estoque.setPreco(20);
        Assertions.assertEquals(20, estoque.getPreco());
    }
    @Test
    void getsetNome(){
        estoque.setNome("Kuat");
        Assertions.assertEquals("Kuat", estoque.getNome());
    }

    @Test
    void testeid(){

        estoque.setId(2L);
        Assertions.assertEquals(2, estoque.getId());
    }

    @Test
    void testeComparacao(){
        EstoqueDTO estoque2 = new EstoqueDTO(10,"coca");
        Assertions.assertEquals(estoque,estoque2);
    }

    @Test
    void testHashCode() {
        EstoqueDTO estoque1 = new EstoqueDTO(10,"coca");
        EstoqueDTO estoque2 = new EstoqueDTO(10,"coca");


        Assertions.assertEquals(estoque1.hashCode(), estoque2.hashCode());;

    }

    @Test
    void testToString() {
        EstoqueDTO estoque2 = new EstoqueDTO(10,"coca");
        Assertions.assertEquals(estoque2.toString(), estoque.toString());


    }



}
