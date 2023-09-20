package br.com.pizzaria.DtoTests;

import br.com.pizzaria.dto.SaborDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class SaborDtoTests {

    SaborDTO sabor = new SaborDTO("Bacon");
    SaborDTO sabor2 = new SaborDTO();


    @Test
    void getset1Sabor(){
        sabor.setSaborr("Queijo");
        Assertions.assertEquals("Queijo",sabor.getSaborr());
    }
    @Test
    void testconstrutorvazio(){
        SaborDTO sabor3 = new SaborDTO();
        Assertions.assertEquals(sabor2,sabor3);
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
