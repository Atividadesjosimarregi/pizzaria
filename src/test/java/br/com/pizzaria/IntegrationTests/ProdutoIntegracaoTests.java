package br.com.pizzaria.IntegrationTests;

import br.com.pizzaria.controller.*;
import br.com.pizzaria.dto.ProdutoDTO;
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
class ProdutoIntegracaoTests {



    @MockBean
    EstoqueRepository estoqueRepository;


    @MockBean
    ProdutoRepository produtoRepository;
    @Autowired
    ProdutoController produtoController;



    private List<Estoque> estoqueList;



    private List<Produto> produtoList;

    @BeforeEach
    void injectData() {


        Estoque estoque = new Estoque(1L,10,"Coca-cola");
        Estoque estoque2 = new Estoque(2L,7,"Guaraná");
        estoqueList = new ArrayList<>();
        estoqueList.add(estoque);
        estoqueList.add(estoque2);


        Produto produto = new Produto(1L,1,estoque,20);
        Produto produto2 = new Produto(2L,1,estoque2,30);
        produtoList = new ArrayList<>();
        produtoList.add(produto);
        produtoList.add(produto2);




        Mockito.when(estoqueRepository.save(estoque)).thenReturn(estoque);
        Mockito.when(estoqueRepository.save(estoque2)).thenReturn(estoque2);
        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        Mockito.when(estoqueRepository.findById(2L)).thenReturn(Optional.of(estoque2));
        Mockito.when(estoqueRepository.findAll()).thenReturn(estoqueList);


        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(produtoRepository.save(produto2)).thenReturn(produto2);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
        Mockito.when(produtoRepository.findAll()).thenReturn(produtoList);

    }

    @Test
    void testProdutoCriar() {
        Estoque estoqueTest = new Estoque(2L,30,"Trio Pequeno");
        var produto = produtoController.cadastra(new ProdutoDTO(3,estoqueTest,20));
        Assert.assertEquals("Registro cadastrado com sucesso", produto.getBody());
    }

    @Test
    void testProdutoCriarErrado() {
        Estoque estoqueTest = new Estoque(2L,30,"Trio Pequeno");
        var produto = produtoController.cadastra(new ProdutoDTO());
        Assert.assertEquals("ErrorLá: A quantidade não pode ser nulo", produto.getBody());
    }


    @Test
    void testPutProduto(){
        Estoque estoque = new Estoque(4L,30,"Trio Pequeno");
        ProdutoDTO produtoDTO = new ProdutoDTO(1,estoque,20);
        produtoDTO.setId(1L);

        var produto = produtoController.edita(1L, produtoDTO);

        Assert.assertEquals("Registro Cadastrado com Sucesso", produto.getBody());
    }
    @Test
    void testPutProdutoErrado(){
        Estoque estoque = new Estoque(4L,30,"Trio Pequeno");
        ProdutoDTO produtoDTO = new ProdutoDTO(1,estoque,20);
        produtoDTO.setId(1L);

        var produto = produtoController.edita(10L, produtoDTO);

        Assert.assertEquals("Nao foi possivel indentificar o registro informado", produto.getBody());
    }

    @Test
    void testProdutoDelete(){
        var produto = produtoController.deleta(2L);
        Assert.assertEquals("excluído", produto.getBody());
    }
    @Test
    void testProdutoDeleteErrado(){
        var produto = produtoController.deleta(20L);
        Assert.assertEquals("ERRor: Não foi possivel identificar o registro informado", produto.getBody());
    }

    @Test
    void testFindByIdProduto(){
        Estoque estoque = new Estoque(4L,30,"Trio Pequeno");
        produtoController.cadastra(new ProdutoDTO(1,estoque,20));
        var produto = produtoController.findById(1L);
        Assert.assertEquals(produto.getBody().getEstoques(), produtoController.findById(1L).getBody().getEstoques());
    }

    @Test
    void testFindAllProduto(){
        ResponseEntity<List<Produto>> produtoFuncaoCotroller = produtoController.list();
        List<Produto> produtoListController = produtoFuncaoCotroller.getBody();

        Assert.assertNotNull(produtoListController);
        for(int i = 0; i < produtoList.size();i ++){
            Assert.assertEquals(produtoList.get(i), produtoListController.get(i));
        }
    }
}
