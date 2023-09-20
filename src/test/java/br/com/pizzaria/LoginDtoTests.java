package br.com.pizzaria;

import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.dto.SaborDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginDtoTests {

    LoginDTO login = new LoginDTO("admin","admin");


    @Test
    void getsetNome(){
        login.setLogin("login");
        Assertions.assertEquals("login",login.getLogin());
    }
    @Test
    void getsetNome2(){
        login.setSenha("senha");
        Assertions.assertEquals("senha",login.getSenha());
    }

    @Test
    void testeid(){
        login.setId(2L);
        Assertions.assertEquals(2,login.getId());
    }

    @Test
    void testeComparacao(){
        LoginDTO login2 = new LoginDTO("admin","admin");
        Assertions.assertEquals(login,login2);
    }

    @Test
    void testhashCode(){
        LoginDTO loginDTO1 = new LoginDTO("admin","admin");
        LoginDTO loginDTO2 = new LoginDTO("admin","admin");

        Assertions.assertEquals(loginDTO1.hashCode(),loginDTO2.hashCode());
    }

    @Test
    void testToString(){
        LoginDTO loginDTO2 = new LoginDTO("admin","admin");
        Assertions.assertEquals(loginDTO2.toString(),login.toString());

    }

}
