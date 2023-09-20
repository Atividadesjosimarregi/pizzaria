package br.com.pizzaria;

import br.com.pizzaria.dto.SaborDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaborDtoTests {

    SaborDTO sabor = new SaborDTO("Bacon");


    @Test
    void getsetNome(){
        sabor.setSabor("Queijo");
        Assertions.assertEquals("Queijo",sabor.getSabor());
    }

    @Test
    void testeid(){
        sabor.setId(2L);
        Assertions.assertEquals(2,sabor.getId());
    }

    @Test
    void testeComparacao(){
        SaborDTO sabor2 = new SaborDTO("Bacon");
        Assertions.assertEquals(sabor,sabor2);
    }

    @Test
    void testhashCode(){
        SaborDTO saborDTO1 = new SaborDTO("Bacon");
        SaborDTO saborDTO2 = new SaborDTO("Bacon");

        Assertions.assertEquals(saborDTO1.hashCode(),saborDTO2.hashCode());
    }

    @Test
    void testToString(){
        SaborDTO saborDTO2 = new SaborDTO("Bacon");
        Assertions.assertEquals(saborDTO2.toString(),sabor.toString());

    }

}