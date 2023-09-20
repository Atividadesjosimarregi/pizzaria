package br.com.pizzaria;

import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.dto.SaborDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuncionarioDtoTests {

    FuncionarioDTO funcionario = new FuncionarioDTO("Douglas");


    @Test
    void getsetNome(){
        funcionario.setNome("Lucas");
        Assertions.assertEquals("Lucas",funcionario.getNome());
    }

    @Test
    void testeid(){
        funcionario.setId(2L);
        Assertions.assertEquals(2,funcionario.getId());
    }

    @Test
    void testeComparacao(){
        FuncionarioDTO funcionario2 = new FuncionarioDTO("Douglas");
        Assertions.assertEquals(funcionario,funcionario2);
    }

    @Test
    void testhashCode(){
        FuncionarioDTO funcionarioDTO1 = new FuncionarioDTO("Lucas");
        FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO("Lucas");

        Assertions.assertEquals(funcionarioDTO1.hashCode(),funcionarioDTO2.hashCode());
    }

    @Test
    void testToString(){
        FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO("Douglas");
        Assertions.assertEquals(funcionarioDTO2.toString(),funcionario.toString());

    }

}
