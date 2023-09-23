package br.com.pizzaria.DtoTests;

import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.dto.SaborDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class LoginDtoTests {

    LoginDTO login = new LoginDTO("admin","admin");
    LoginDTO login2 = new LoginDTO();


    @Test
    void getset1login(){
        login.setLoginn("login");
        Assertions.assertEquals("login",login.getLoginn());
    }
    @Test
    void testNull(){
        login.setSenha("senha");
        Assertions.assertNotNull(login.getSenha());
    }
    @Test
    void getset2senha(){
        login.setSenha("senha");
        Assertions.assertEquals("senha",login.getSenha());
    }

    @Test
    void testconstrutorvazio(){
        LoginDTO login3 = new LoginDTO();
        Assertions.assertEquals(login2,login3);
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
