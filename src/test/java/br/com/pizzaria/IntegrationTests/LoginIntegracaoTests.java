package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.LoginDTO;
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
class LoginIntegracaoTests {


    @MockBean
    LoginRepository loginRepository;
    @Autowired
    LoginController loginController;



    private List<Login> loginList;



    @BeforeEach
    void injectData() {


        Login login = new Login(1L,"admin","admin");
        Login login2 = new Login(2L,"nimda","nimda");
        loginList = new ArrayList<>();
        loginList.add(login);
        loginList.add(login2);





        Mockito.when(loginRepository.save(login)).thenReturn(login);
        Mockito.when(loginRepository.save(login2)).thenReturn(login2);
        Mockito.when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        Mockito.when(loginRepository.findById(2L)).thenReturn(Optional.of(login2));
        Mockito.when(loginRepository.findAll()).thenReturn(loginList);


    }

    @Test
    void testLoginCriar() {
        var login = loginController.cadastra(new LoginDTO("admin","admin"));
        Assert.assertEquals("Registro cadastrado com sucesso", login.getBody());
    }

    @Test
    void testLoginCriarErrado() {
        var login = loginController.cadastra(new LoginDTO());
        Assert.assertEquals("Error: Login não pode ser nulo", login.getBody());
    }

    @Test
    void testPutLogin(){
        LoginDTO loginDTO = new LoginDTO("admin","admin");
        loginDTO.setId(1L);


        var login = loginController.edita(1L, loginDTO);

        Assert.assertEquals("Registro Cadastrado com Sucesso", login.getBody());
    }
    @Test
    void testPutLoginErrado(){
        LoginDTO loginDTO = new LoginDTO("admin","admin");
        loginDTO.setId(1L);


        var login = loginController.edita(10L, loginDTO);

        Assert.assertEquals("Nao foi possivel indentificar o registro informado", login.getBody());
    }

    @Test
    void testLoginDelete(){
        var login = loginController.deleta(2L);
        Assert.assertEquals("excluído", login.getBody());
    }
    @Test
    void testLoginDeleteErrado(){
        var login = loginController.deleta(10L);
        Assert.assertEquals("ERRor: Não foi possivel identificar o registro informado", login.getBody());
    }

    @Test
    void testFindByIdLogin(){
        loginController.cadastra(new LoginDTO("adm","adm"));
        var login = loginController.findById(1L);
        Assert.assertEquals(login.getBody().getLoginn(), loginController.findById(1L).getBody().getLoginn());
    }


    @Test
    void testFindAllLogin(){
        ResponseEntity<List<Login>> loginFuncaoController = loginController.list();
        List<Login> loginListController = loginFuncaoController.getBody();

        Assert.assertNotNull(loginListController);
        for(int i = 0; i < loginList.size();i ++){
            Assert.assertEquals(loginList.get(i), loginListController.get(i));
        }
    }
}
