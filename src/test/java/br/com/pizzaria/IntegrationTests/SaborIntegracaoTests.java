package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.SaborDTO;
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
class SaborIntegracaoTests {

    @MockBean
    SaborRepository saborRepository;
    @Autowired
    SaborController saborController;


    private List<Sabor> saborList;



    @BeforeEach
    void injectData() {


        Sabor sabor = new Sabor(1L,"Queijo");
        Sabor sabor2 = new Sabor(2L,"Frango");
        saborList = new ArrayList<>();
        saborList.add(sabor);
        saborList.add(sabor2);


        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saborRepository.save(sabor2)).thenReturn(sabor2);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
        Mockito.when(saborRepository.findById(2L)).thenReturn(Optional.of(sabor2));
        Mockito.when(saborRepository.findAll()).thenReturn(saborList);



    }

    @Test
    void testSaborCriar() {
        var sabor = saborController.cadastra(new SaborDTO("Bacon"));
        Assert.assertEquals("Registro cadastrado com sucesso", sabor.getBody());
    }


    @Test
    void testPutSabor(){
        SaborDTO saborDTO = new SaborDTO("Bacon");
        saborDTO.setId(1L);


        var sabor = saborController.edita(1L, saborDTO);

        Assert.assertEquals("Registro Cadastrado com Sucesso", sabor.getBody());
    }

    @Test
    void testSaborDelete(){
        var sabor = saborController.deleta(2L);
        Assert.assertEquals("excluído", sabor.getBody());
    }

    @Test
    void testFindByIdSabor(){
        saborController.cadastra(new SaborDTO("4 queijos"));
        var sabor = saborController.findById(1L);
        Assert.assertEquals(sabor.getBody().getSaborr(), saborController.findById(1L).getBody().getSaborr());
    }

    @Test
    void testFindAllSabor(){
        ResponseEntity<List<Sabor>> saborFuncaoController = saborController.list();
        List<Sabor> saborListController = saborFuncaoController.getBody();

        Assert.assertNotNull(saborListController);
        for(int i = 0; i < saborList.size();i ++){
            Assert.assertEquals(saborList.get(i), saborListController.get(i));
        }
    }
}
